import { useEffect } from 'react';
import axiosInstance from '../../utils/AxiosInstance';

function Popular() {
  const loadPopularRoom = async () => {
    await axiosInstance.get('/rooms/top').then((res) => {
      console.log(res.data);
    });
  };
  useEffect(() => {}, []);

  return (
    <div className="popular-box">
      <p className="popular-title">&#10024; 이번주 인기 미니홈피 &#10024;</p>
      <div className="popular-mini">
        <img className="popular-mini-img" src="img/noimage.png" />
        <p>나는 인기있는 미니홈피</p>
      </div>
      <div className="popular-mini">
        <img className="popular-mini-img" src="img/noimage.png" />
        <p>미니홈피명</p>
      </div>
      <div className="popular-mini">
        <img className="popular-mini-img" src="img/noimage.png" />
        <p>미니홈피명</p>
      </div>
      <div className="popular-mini">
        <img className="popular-mini-img" src="img/noimage.png" />
        <p>미니홈피명</p>
      </div>
    </div>
  );
}

export default Popular;
