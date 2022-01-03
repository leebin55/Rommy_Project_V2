// import { useEffect, useState } from 'react'
// import axios from 'axios'
import './App.css';
import { Routes, Route } from 'react-router-dom';
import MainPage from './pages/MainPage';
import Search from './pages/Search';
import Feeds from './pages/Feeds';
//------------------------------------
import RoomPage from './pages/room/RoomPage';
import * as Room from './pages/room/RoomComps';

function App() {
  return (
    <div className="App">
      <Routes>
        <Route path="/" element={<MainPage />}>
          <Route index element={<Feeds />} />
          <Route path="search" element={<Search />} />
          {/* <Route path="game" element={<Game />} /> */}
          {/* <Route path="news" element={<News />} /> */}
        </Route>
        <Route path="/room/:userId" element={<RoomPage />}>
          <Route index element={<Room.Main />} />
          <Route path="board/*" element={<Room.Board />} />
          <Route path="todo" element={<Room.Todo />} />
          <Route path="gallery" element={<Room.Gallery />} />
          {/* <Route path="gallery/:board_seq" element={<GalleryDetail />} /> */}
          <Route path="guest" element={<Room.Guest />} />
          <Route path="friendlist" element={<Room.Friend />} />
          <Route path="setting" element={<Room.Setting />} />
        </Route>
      </Routes>
    </div>
  );
  // } else {
  // 	return (
  // 	<div>Loading...</div>

  // 	)
  //   }
}

export default App;
