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

function App() {
  return (
    <div className="App">
      <Routes>
        <Route path="/" element={<MainPage />}>
          <Route index element={<Feeds />} />
          <Route path="search" element={<Search />} />

          <Route path="news" element={<News />} />
        </Route>
        <Route path="/room/:userId" element={<RoomPage />}>
          <Route index element={<Room.Main />} />
          <Route path="board/*" element={<Room.Board />} />
          <Route path="board/:board_seq" element={<BoardDetail />} />
          <Route path="gallery" element={<Room.Gallery />} />
          <Route path="gallery/:board_seq" element={<GalleryDetail />} />
          <Route path="guest" element={<Room.Guest />} />
          <Route path="friends" element={<Room.Friend />} />
          <Route path="setting" element={<Room.Setting />} />
        </Route>
      </Routes>
    </div>
  );
}

export default App;
