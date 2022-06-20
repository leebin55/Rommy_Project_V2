import React, { useEffect, useState, useCallback } from 'react';
import { useParams } from 'react-router-dom';
import BoardSearch from '../shared/BoardSearch';
import BoardSingle from './BoardSingle';
import axiosInstance from '../../../utils/AxiosInstance';
import { checkUser } from '../../../utils/JwtUtils';

function BoardList({ toBoardPage }) {
  const [boardList, setBoardList] = useState([]);
  const [select, setSelect] = useState('0');
  const [search, setSearch] = useState('');
  const [isOwn, setIsOwn] = useState(false);
  // const [start_page, setStart_page] = useState("1");
  // const [end_page, setEnd_page] = useState("1");
  const { roomUser, roomId } = useParams();

  const loadBoardList = async () => {
    await axiosInstance
      .get(`/rooms/${roomUser}/${roomId}/boards?size=20,sort=roomId,desc`)
      .then((res) => {
        console.log(res.data.content);
        setBoardList(res.data.content);
      });
  };

  const selectHandler = (e) => {
    setSelect(e.target.value);
  };

  const searchText = (e) => {
    setSearch(e.target.value);
  };

  const fetchSearch = useCallback(async () => {
    if (search.trim() === '') {
      alert('검색어를 입력하세요');
      return;
    }
    const res = await axiosInstance(
      `/rooms/${roomUser}/${roomId}/boards/search?query=${search}&select=${select}`
    );
    const result = await res;
    setBoardList(result);
    setSelect('0');
    setSearch('');
  });

  useEffect(() => {
    setIsOwn(checkUser(roomUser));
    loadBoardList();
  }, []);

  return (
    <div className="board-container">
      <div className="btn-write-box">
        {isOwn && (
          <button
            onClick={() => {
              toBoardPage('write');
            }}
          >
            글쓰기
          </button>
        )}
      </div>
      <div className="board-table-container">
        <table>
          <thead>
            <tr>
              <th width="10%">No.</th>
              <th width="55%">제목</th>
              <th width="15%">작성일</th>
              <th width="10%">조회</th>
              <th width="10%">좋아요</th>
            </tr>
          </thead>
          <tbody>
            {boardList.length > 0 ? (
              boardList.map((item, index) => (
                <BoardSingle
                  item={item}
                  index={index}
                  roomUser={roomUser}
                  roomId={roomId}
                />
              ))
            ) : (
              <td colSpan="5">게시물이 없습니다</td>
            )}
          </tbody>
        </table>
      </div>
      <BoardSearch roomUser={roomUser} boardType="board" />
    </div>
  );
}

export default BoardList;
