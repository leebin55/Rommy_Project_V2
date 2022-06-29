import React, { useEffect, useState, useCallback } from 'react';
import { useParams } from 'react-router-dom';
import BoardSearch from '../shared/BoardSearch';
import Pagination from '@mui/material/Pagination';
import Stack from '@mui/material/Stack';
// import PaginationBtn from '../shared/PaginationBtn';
import BoardSingle from './BoardSingle';
import axiosInstance from '../../../utils/AxiosInstance';
import { checkUser } from '../../../utils/JwtUtils';

function BoardList({ toBoardPage }) {
  const [boardList, setBoardList] = useState([]);
  const [select, setSelect] = useState('0');
  const [search, setSearch] = useState('');
  const [isOwn, setIsOwn] = useState(false);
  const [page, setPage] = useState(1);
  const [endPage, setEndPage] = useState(1);
  const { roomUser, roomId } = useParams();

  const pageHandleChange = (event, value) => {
    setPage(value);
  };

  const loadBoardList = async () => {
    await axiosInstance
      .get(`/rooms/${roomUser}/${roomId}/boards?size=10`)
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
        <BoardSearch roomUser={roomUser} boardType="board" />
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
      <Stack spacing={2} alignItems="center">
        <Pagination
          count={10}
          page={page}
          onChange={pageHandleChange}
          boundaryCount={5}
          color="primary"
        />
      </Stack>
    </div>
  );
}

export default BoardList;
