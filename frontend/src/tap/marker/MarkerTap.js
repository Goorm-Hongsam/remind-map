import React, { useEffect, useState } from 'react';
import MarkerModal from './ui_components/MarkerModal';
import styles from './MarkerTap.module.css';
import Posting from './ui_components/Posting';
import GroupSelect from './ui_components/GroupSelect';

const MarkerTap = ({ onPostClick, onSearchResults, selectedMarker, onEnableMarkerCreation }) => {
  const [place, setPlace] = useState('');
  const [savedSearchResults, setSavedSearchResults] = useState([]);
  const [modalVisible, setModalVisible] = useState('');
  const [enableMarkerCreation, setEnableMarkerCreation] = useState(false);
  const [selectedGroup, setSelectedGroup] = useState([]);
  const searchPlaces = () => {
    if (!place.trim()) {
      alert('검색어를 입력해주세요');
      return;
    }

    const ps = new window.kakao.maps.services.Places();
    ps.keywordSearch(place, (data, status) => {
      if (status === window.kakao.maps.services.Status.OK) {
        onSearchResults(data);
        setSavedSearchResults(data);
      } else {
        alert('검색 결과가 없습니다.');
        setSavedSearchResults([]);
        onSearchResults([]);
      }
    });
  };
  useEffect(() => {
    if (selectedMarker) {
      setModalVisible(true);
    } else {
      setModalVisible(false);
    }
  }, [selectedMarker]);
  const closeModal = () => {
    setModalVisible(false);
  };
  const handleInputChange = e => {
    setPlace(e.target.value);
  };
  const handleSubmit = e => {
    e.preventDefault();
    searchPlaces();
  };
  const handleMarkerCreation = () => {
    setEnableMarkerCreation(true);
    onEnableMarkerCreation(true);
  };
  const handleGroupSelect = e => {
    setSelectedGroup(e);
  };
  const handlePostClick = location => {
    onPostClick(location);
  };

  return (
    <>
      <div className={styles.markerTap}>
        <GroupSelect onSelect={handleGroupSelect} />
        <form className={styles.searchForm} onSubmit={handleSubmit}>
          <input
            className={styles.searchInput}
            type="text"
            value={place}
            onChange={handleInputChange}
            placeholder="장소를 입력하세요"
          />
        </form>
        <div className={styles.createMarker}>
          {selectedGroup.length > 0
            ? selectedGroup.map((marker, index) => (
                <Posting
                  key={index}
                  latitude={marker.latitude}
                  longitude={marker.longitude}
                  onPostClick={handlePostClick}
                />
              ))
            : savedSearchResults.map((result, index) => <Posting key={index} {...result} />)}
          <button onClick={handleMarkerCreation}>마커 생성하기</button>
        </div>
      </div>
      <div>{modalVisible && <MarkerModal data={selectedMarker} onClose={closeModal} />}</div>
    </>
  );
};

export default MarkerTap;

/*        
  return (
    <>
      <div className={styles.markerTap}>
        <GroupSelect onSelect={handleGroupSelect} />
        <form className={styles.searchForm} onSubmit={handleSubmit}>
          <input
            className={styles.searchInput}
            type="text"
            value={place}
            onChange={handleInputChange}
            placeholder="장소를 입력하세요"
          />
        </form>
        <div className={styles.createMarker}>
          {savedSearchResults.map((result, index) => (
            <Posting
              key={index}
              place_name={result.place_name}
              road_address_name={result.road_address_name}
              address_name={result.address_name}
              phone={result.phone}
            />
          ))}
          <button onClick={handleMarkerCreation}>마커 생성하기</button>
        </div>
      </div>
      <div>{modalVisible && <MarkerModal data={selectedMarker} onClose={closeModal} />}</div>
    </>
  );
};

export default MarkerTap;

*/
