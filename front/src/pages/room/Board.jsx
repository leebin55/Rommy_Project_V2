import React, { useEffect, useState } from 'react';
import axiosInstance from '../../utils/AxiosInstance';
import BoardContextProvider from '../../context/BoardContextProvider';
import BoardList from '../../components/room/board/BoardList';
import BoardWrite from '../../components/room/board/BoardWrite';
import { useParams } from 'react-router-dom';
import '../../css/room/Board.css';

function Board() {
  // write , update , list 조회 인지  board 의 상태 (글 상세보기(detail) 은 url 로 직접 들어올 수 있도록 여기서는 위 세개만)
  const [boardState, setBoardState] = useState({
    list: true,
    write: false,
    update: false,
  });

  return (
    <>
      {boardState.list ? (
        <>
          <BoardList setBoardState={setBoardState} />
        </>
      ) : (
        <>
          {' '}
          <BoardWrite />{' '}
        </>
      )}
    </>
  );
}

export default Board;
