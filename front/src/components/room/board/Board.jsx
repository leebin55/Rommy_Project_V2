import { useState } from 'react';
import '../../../css/room/Board.css';
import BoardList from './BoardList';
import BoardWrite from './BoardWrite';

function Board() {
  const [boardState, setBoardState] = useState({
    list: true,
    update: false,
  });

  const toBoardPage = (state) => {
    if (state === 'list') {
      setBoardState({
        list: true,
        update: false,
      });
    } else if (state === 'write') {
      setBoardState({
        list: false,
        update: false,
      });
    } else if (state === 'update') {
      setBoardState({
        list: false,
        update: true,
      });
    }
  };
  return (
    <>
      {boardState.list ? (
        <>
          <BoardList toBoardPage={toBoardPage} />
        </>
      ) : (
        <>
          <BoardWrite toBoardPage={toBoardPage} />
        </>
      )}
    </>
  );
}

export default Board;
