import React, { useState } from 'react';
import { BrowserRouter, Routes, Route } from 'react-router-dom';
import './App.css';
import Main from './common/frame/Main';
import Header from './common/frame/Header';
import Sidebar from './common/frame/Sidebar';
import Redirect from './api/Redirect';
import PostingModal from './common/userposting/PostingModal';

function App() {
  const [searchResults, setSearchResults] = useState([]);
  const [selectedMarker, setSelectedMarker] = useState('');
  const [enableMarkerCreation, setEnableMarkerCreation] = useState(false);
  const [selectedLocation, setSelectedLocation] = useState('');

  return (
    <BrowserRouter>
      <div className="App">
        <Header />
        <Routes>
          <Route
            path="/"
            element={
              <>
                <Main
                  searchResults={searchResults}
                  onMarkerSelect={setSelectedMarker}
                  enableMarkerCreation={enableMarkerCreation}
                  selectedLocation={selectedLocation}
                />{' '}
                <Sidebar
                  onSearchResults={setSearchResults}
                  selectedMarker={selectedMarker}
                  onEnableMarkerCreation={setEnableMarkerCreation}
                  onPostClick={setSelectedLocation}
                />
              </>
            }
          />
          <Route exact path="/kakao/callback" element={<Redirect />} />
        </Routes>
      </div>
    </BrowserRouter>
  );
}

export default App;
