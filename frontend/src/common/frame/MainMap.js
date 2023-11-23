import React, { useState, useEffect } from 'react';
const { kakao } = window;

const MainMap = ({ selectedLocation, searchResults, onMarkerSelect, enableMarkerCreation }) => {
  const [map, setMap] = useState(null);
  useEffect(() => {
    const mapContainer = document.getElementById('map');
    const mapOption = {
      center: new kakao.maps.LatLng(37.566826, 126.9786567),
      level: 10,
    };
    const createdMap = new kakao.maps.Map(mapContainer, mapOption);
    setMap(createdMap);
  }, []);
  console.log(selectedLocation);
  useEffect(() => {
    if (searchResults && searchResults.length > 0 && map) {
      const mapCenter = searchResults[0];
      const centerPosition = new kakao.maps.LatLng(mapCenter.y, mapCenter.x);
      map.setCenter(centerPosition);

      searchResults.forEach(place => {
        const position = new kakao.maps.LatLng(place.y, place.x);
        const marker = new kakao.maps.Marker({
          position,
          map,
        });

        kakao.maps.event.addListener(marker, 'click', () => {
          onMarkerSelect(place);
        });
      });
    }
  }, [searchResults, map]);
  useEffect(() => {
    if (enableMarkerCreation && map) {
      kakao.maps.event.addListener(map, 'click', function (mouseEvent) {
        const clickedPosition = mouseEvent.latLng;
        const marker = new kakao.maps.Marker({
          position: clickedPosition,
          map: map,
        });
        onMarkerSelect({
          position: clickedPosition,
          map: map,
        });
      });
    }
  }, [map, enableMarkerCreation]);
  useEffect(() => {
    if (selectedLocation && map) {
      const moveLatLon = new kakao.maps.LatLng(
        selectedLocation.longitude,
        selectedLocation.latitude,
      );
      map.panTo(moveLatLon);
    }
  }, [selectedLocation, map]);
  return (
    <div>
      <div
        id="map"
        style={{
          position: 'fixed',
          top: 0,
          left: 0,
          width: '100vw',
          height: '100vh',
        }}
      />
    </div>
  );
};

export default MainMap;
