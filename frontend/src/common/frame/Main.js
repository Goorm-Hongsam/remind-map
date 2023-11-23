import React from 'react';
import MainMap from './MainMap';

const Main = ({ selectedLocation, searchResults, onMarkerSelect, enableMarkerCreation }) => {
  return (
    <div>
      <MainMap
        enableMarkerCreation={enableMarkerCreation}
        searchResults={searchResults}
        onMarkerSelect={onMarkerSelect}
        selectedLocation={selectedLocation}
      />
    </div>
  );
};

export default Main;
