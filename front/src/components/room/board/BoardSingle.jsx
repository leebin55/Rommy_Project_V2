import React from 'react';
import { useNavigate } from 'react-router';

export default function BoardSingle({ item, index, roomUser, roomId }) {
  const navigate = useNavigate();
  return (
    <tr>
      <td>{index + 1}</td>
      <td
        className="board-list-title"
        onClick={() =>
          navigate(`/rooms/${roomUser}/${roomId}/boards/${item.boardSeq}`)
        }
      >
        {item.title}
      </td>
      <td>{item.createDate}</td>
      <td>{item.boardHit}</td>
      <td>{item.boardLike}</td>
    </tr>
  );
}
