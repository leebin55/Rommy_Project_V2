import React from 'react';

function GuestPostBox({ item, index }) {
  const baseURL = 'http://localhost:3000/rooms';
  return (
    <div key={index} className="main-guest-box">
      <img src="/img/postit.png" alt="guest-post" />
      <div className="guest-post-nickname">
        <p>{item.nickname}</p>
      </div>
      <div className="guest-post-content">
        <p>{item.content}</p>
      </div>
    </div>
  );
}

export default GuestPostBox;
