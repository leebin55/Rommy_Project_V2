import React, { useEffect, useState, useCallback } from 'react';
import { useParams } from 'react-router-dom';
import BoardSearch from '../commonComp/BoardSearch';
import BoardSingle from './BoardSingle';
import axiosInstance from '../../../utils/AxiosInstance';
import { checkUser } from '../../../utils/JwtUtils';

function BoardList({ setBoardState }) {
  const [boardList, setBoardList] = useState([]); // 화면에 출력될 일반 게시판 글 list
  const [select, setSelect] = useState('0'); // 검색 select box 선택한 것
  const [search, setSearch] = useState(''); // 검색 input box 에 입력한 내용
  const [isOwn, setIsOwn] = useState(false); // 본인의 room인지 확인
  // const [start_page, setStart_page] = useState("1");
  // const [end_page, setEnd_page] = useState("1");
  const { roomUser, roomId } = useParams();

  const loadBoardList = async () => {
    const res = await axiosInstance
      .get(`/rooms/${roomUser}/${roomId}/boards?size=20,sort=roomId,desc`)
      .then((res) => {
        console.log(res.data.content);
        setBoardList(res.data.content);
      });
  };

  // 검색 select box 선택하면 실행
  const selectHandler = (e) => {
    setSelect(e.target.value);
  };

  // 검색 input 입력하면 실행
  const searchText = (e) => {
    setSearch(e.target.value);
  };

  // 검색 버튼 클릭하면 실행
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
        {isOwn ? (
          <button
            onClick={() => {
              setBoardState({ list: false, write: true, update: false });
            }}
          >
            글쓰기
          </button>
        ) : null}
      </div>
      <div className="board-table-container">
        <table>
          <thead>
            <tr>
              <th width="10%"></th>
              <th width="55%">제목</th>
              <th width="15%">작성일</th>
              <th width="10%">조회</th>
              <th width="10%">좋아요</th>
            </tr>
          </thead>
          <tbody>
            {boardList.length > 0 ? (
              boardList.map((item) => (
                <BoardSingle item={item} roomUser={roomUser} roomId={roomId} />
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
