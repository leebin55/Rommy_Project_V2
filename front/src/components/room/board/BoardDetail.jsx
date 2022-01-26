import React, { useEffect, useState } from 'react';
import FavoriteBorderIcon from '@mui/icons-material/FavoriteBorder';
import FavoriteIcon from '@mui/icons-material/Favorite';
import { useNavigate, useParams } from 'react-router';
import BoardWrite from './BoardWrite';
import axiosInstance from '../../../utils/AxiosInstance';
import '../../../css/room/Board.css';

function BoardDetail() {
  const navigate = useNavigate();
  const { roomUser, roomId, boardSeq } = useParams();

  const [detail, setDetail] = useState({});
  const [isDetail, setIsDetail] = useState(true);
  const [heart, setHeart] = useState(false); // 하트 눌렀었는지. 비회원이면 false 모양이 기본값으로 들어가도록 false로 초기값 지정
  const [heartNum, setHeartNum] = useState(''); // 이 글의 하트수

  const fetchDetail = async () => {
    await axiosInstance
      .get(`/rooms/${roomId}/boards/${boardSeq}`)
      .then((res) => {
        setDetail(res.data);
        setHeart(res.data.checkLike);
        // [위] 사용자가 하트를 눌렀었는지 표시하기 위함
        setHeartNum(res.data.likeCount);
      });
  };

  const clickUpdate = async () => {
    if (window.confirm('글을 수정하시겠습니까?')) {
      setIsDetail(false);
    }
  };

  const clickBackBtn = () => {
    navigate(`rooms/${roomUser}/${roomId}/boards`);
  };

  const fetchDelete = async () => {
    if (window.confirm('글을 삭제하시겠습니까?')) {
      await axiosInstance
        .delete(`/rooms/${roomUser}/${roomId}/boards/${boardSeq}`)
        .then((res) => {
          if (res?.ok) {
            alert('삭제되었습니다');
            navigate(`/rooms/${roomUser}/${roomId}/boards`);
          }
        });
    }
  };

  const clickHeart = () => {
    setHeart(!heart);
    fetchHeart();
  };

  const fetchHeart = async () => {
    const res = await axiosInstance.post(`/room/${roomId}/board/like`, {
      boardSeq: detail.boardSeq,
    });
    setHeartNum(res.data);
  };

  useEffect(() => {
    fetchDetail();
  }, []);

  return (
    <div className="board-detail-container">
      {isDetail ? (
        <div>
          <div className="board-detail-title">
            <button onClick={() => clickBackBtn()}>뒤로</button>
            <p>
              {detail.profile} {detail.nickname}
            </p>
            <p>{detail.boardTitle}</p>
            <button onClick={() => clickUpdate()}>수정</button>
            <button onClick={() => fetchDelete()}>삭제</button>
          </div>
          <div className="board-detail-head-box">
            <p>{detail.title}</p>
            <p className="board-detail-head-date">{detail.createDate}</p>
          </div>

          <div
            className="board-detail-content"
            dangerouslySetInnerHTML={{ __html: detail.content }}
          />
          <div className="board-heart-box">
            {heart ? (
              <FavoriteIcon
                className="board-heart"
                onClick={() => clickHeart()}
              />
            ) : (
              <FavoriteBorderIcon
                className="board-heart"
                onClick={() => clickHeart()}
              />
            )}
            <p>{heartNum}</p>
          </div>
          {/* 정확히 하트를 눌러야 클릭되게 할 수 있게 아이콘에 onClick */}
        </div>
      ) : (
        <>
          <BoardWrite upData={detail} />
        </>
      )}
    </div>
  );
}

export default BoardDetail;
