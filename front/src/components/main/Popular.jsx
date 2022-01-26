import { useEffect, useState } from 'react';
import axiosInstance from '../../utils/AxiosInstance';
import PopularSingle from './PopularSingle';

function Popular() {
  const [rooms, setRooms] = useState([]);

  const loadPopularRoom = async () => {
    await axiosInstance.get('/rooms/top').then((res) => {
      console.log('인기 미니홈피 :', res.data);
      setRooms(res.data);
    });
  };
  useEffect(() => {
    loadPopularRoom();
  }, []);

  return (
    <div className="popular-box">
      <p className="popular-title">&#10024; 이번주 인기 미니홈피 &#10024;</p>
      {rooms.length > 0 && rooms.map((item) => <PopularSingle item={item} />)}
    </div>
  );
}

export default Popular;
