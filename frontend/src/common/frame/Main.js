import React from 'react';
import MainMap from './MainMap';

const Main = ({ searchResults, onMarkerSelect }) => {
  return (
    <div>
      <MainMap searchResults={searchResults} onMarkerSelect={onMarkerSelect} />
    </div>
  );
};

export default Main;
