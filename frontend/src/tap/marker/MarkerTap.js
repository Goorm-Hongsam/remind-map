import React, { useEffect, useState } from 'react';
import MarkerModal from './MarkerModal';

const MarkerTap = ({ onSearchResults, selectedMarker }) => {
  const [place, setPlace] = useState('');
  const [savedSearchResults, setSavedSearchResults] = useState([]);
  const [modalVisible, setModalVisible] = useState('');
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

  return (
    <div>
      <form onSubmit={handleSubmit}>
        <input
          type="text"
          value={place}
          onChange={handleInputChange}
          placeholder="장소를 입력하세요"
        />
        <button type="submit">검색</button>
      </form>
      <ul>
        {savedSearchResults.map((result, index) => (
          <li key={index}>
            <strong>{result.place_name}</strong>
            <p>주소: {result.road_address_name || result.address_name}</p>
            <p>전화번호: {result.phone}</p>
          </li>
        ))}
      </ul>
      {modalVisible && <MarkerModal data={selectedMarker} onClose={closeModal} />}
    </div>
  );
};

export default MarkerTap;
