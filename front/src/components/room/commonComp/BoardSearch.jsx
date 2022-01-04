import React, { useState } from 'react';
import axiosInstance from '../../../utils/AxiosInstance';

function BoardSearch({ userId, boardType }) {
  const [select, setSelect] = useState('0'); // 검색 select box 선택한 것
  const [search, setSearch] = useState(''); // 검색 input box 에 입력한 내용

  const getSearchList = async () => {
    await axiosInstance
      .get(
        `/room/${userId}/${boardType}/search?query=${search}&select=${select}`
      )
      .then((res) => {
        console.log(res.data);
      });
  };

  return (
    <div>
      <select
        style={{
          height: '25px',
          outline: 'none',
          border: '1px solid lightgray',
        }}
        value={select}
        onChange={(e) => {
          setSelect(e.target.value);
        }}
      >
        <option value="0">제목만</option>
        <option value="1">제목+내용</option>
        <option value="2">내용만</option>
      </select>
      <input
        style={{
          width: '250px',
          height: '25px',
          margin: '15px',
        }}
        value={search}
        onChange={(e) => {
          setSearch(e.target.value);
        }}
      />
      <button
        style={{
          width: '50px',
          height: '25px',
          border: ' 1px solid lightgray',
        }}
        onClick={() => getSearchList()}
      >
        검색
      </button>
    </div>
  );
}

export default BoardSearch;
