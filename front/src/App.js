import './App.css';
import { Routes, Route } from 'react-router-dom';
import MainPage from './pages/MainPage';
import Search from './pages/Search';
import Feeds from './pages/Feeds';
import News from './pages/News';
//------------------------------------
import RoomPage from './pages/room/RoomPage';
import * as Room from './pages/room/RoomComps';
import GalleryDetail from './components/room/gallery/GalleryDetail';
import BoardDetail from './components/room/board/BoardDetail';
import Login from './pages/Login';

function App() {
  return (
    <div className="App">
      <Routes>
        <Route path="login" element={<Login />} />
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
