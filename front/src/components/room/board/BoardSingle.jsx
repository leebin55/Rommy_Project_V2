import React from 'react';
import { useNavigate } from 'react-router';

export default function BoardSingle({ item, username }) {
  const navigate = useNavigate();
  return (
    <tr>
      <td>{item.boardSeq}</td>
      <td
        className="board-list-title"
        onClick={() => navigate(`/room/${username}/board/${item.boardSeq}`)}
      >
        {item.boardTitle}
      </td>
      <td>날짜</td>
      <td>{item.boardHit}</td>
      <td>{item.boardLike}</td>
    </tr>
  );
}
