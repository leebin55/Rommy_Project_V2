import React from 'react';

function PopularSingle({ item }) {
  return (
    <div className="popular-mini">
      <img
        className="popular-mini-img"
        src="img/noimage.png"
        alt="popular page"
      />
      <p>나는 인기있는 미니홈피</p>
    </div>
  );
}

export default PopularSingle;
