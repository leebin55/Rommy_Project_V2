import './App.css';
import { Routes, Route } from 'react-router-dom';
import MainPage from './pages/MainPage';
import Search from './components/Search';
import Feeds from './components/feed/Feeds';
//------------------------------------
import RoomPage from './pages/RoomPage';
import * as Room from './components/room/RoomComps';
import GalleryDetail from './components/room/gallery/GalleryDetail';
import BoardDetail from './components/room/board/BoardDetail';
//------------------------------------
import ReqLogin from './pages/ReqLogin';
import ErrorPage from './pages/ErrorPage';

function App() {
  return (
    <div className="App">
      <Routes>
        <Route path="/error" element={<ErrorPage />} />
        <Route path="/login" element={<ReqLogin />} />
        <Route path="/" element={<MainPage />}>
          <Route index element={<Feeds />} />
          <Route path="search" element={<Search />} />
        </Route>
        <Route path="/rooms/:roomUser/:roomId" element={<RoomPage />}>
          <Route index element={<Room.Main />} />
          <Route path="boards/*" element={<Room.Board />} />
          <Route path="boards/:boardSeq" element={<BoardDetail />} />
          <Route path="galleries" element={<Room.Gallery />} />
          <Route path="galleries/:boardSeq" element={<GalleryDetail />} />
          <Route path="guests" element={<Room.Guest />} />
          <Route path="follow" element={<Room.Friend />} />
          <Route path="setting" element={<Room.Setting />} />
        </Route>
      </Routes>
    </div>
  );
}

export default App;
