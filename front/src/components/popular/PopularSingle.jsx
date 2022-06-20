import React, { useState, useEffect } from 'react';
import { useNavigate } from 'react-router';

function PopularSingle({ item }) {
  const navigate = useNavigate();
  const [profileUrl, setProfileUrl] = useState('');

  const roomClickHandler = () => {
    console.log('room으로 이동');
    navigate(`/rooms/${item.username}/${item.roomId}`);
  };

  useEffect(() => {
    setProfileUrl(`http://localhost:8080/uploads/${item.profile}`);
  });
  return (
    <div className="popular-mini" onClick={roomClickHandler}>
      {item.profile === null ? (
        <img
          className="popular-mini-img"
          src="img/noimage.png"
          alt="popular page"
        ></img>
      ) : (
        <img
          className="popular-mini-img"
          src={profileUrl}
          alt="popular page"
        ></img>
      )}
      <p className="popular-mini-room-name">{item.roomName}</p>
      <p>{item.nickname}</p>
    </div>
  );
}

export default PopularSingle;
