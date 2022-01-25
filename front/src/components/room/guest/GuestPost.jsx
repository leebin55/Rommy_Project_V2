import React from 'react';

function GuestPost({ item }) {
  const baseURL = 'http://localhost:3000/rooms';
  return (
    <div className="main-guest-box">
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

export default GuestPost;
