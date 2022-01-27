import React, { useState } from 'react';
import { useEffect } from 'react';
import { useNavigate } from 'react-router';

function PopularSingle({ item }) {
  const navigate = useNavigate();
  const [profileUrl, setProfileUrl] = useState('');

  const roomNameClick = () => {
    console.log('room으로 이동');
    navigate(`/rooms/${item.username}/${item.roomId}`);
  };

  useEffect(() => {
    setProfileUrl(`http://localhost:8080/uploads/${item.profile}`);
  });
  return (
    <div className="popular-mini">
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
      <p className="popular-mini-room-name" onClick={roomNameClick}>
        {item.roomName}
      </p>
      <p>{item.nickname}</p>
    </div>
  );
}

export default PopularSingle;
