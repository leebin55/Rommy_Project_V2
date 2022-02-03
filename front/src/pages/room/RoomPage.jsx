import React, { useEffect, useState } from 'react';
import { Outlet, useParams } from 'react-router-dom';
import LeftSide from '../../components/room/LeftSide';
import axiosInstance from '../../utils/AxiosInstance';
import RoomNav from '../../components/room/RoomNav';
import '../../css/room/Room.css';
import { jwtDecoder } from '../../utils/JwtUtils';

/**
 * 미니홈페이지(Room)의 메인 레이아웃 우측 메뉴 클릭 시 <Outlet> 부분만 변함
 */
function Room() {
  // 현재 접속해있는 미니홈피 주인회원id URL에서 잘라오기
  const { roomUser, roomId } = useParams();
  const [roomData, setRoomData] = useState({});
  const [followingList, setFollowingList] = useState([]);
  const [checkFollow, setCheckFollow] = useState(false);
  const [showFollowBtn, setShoFollowBtn] = useState(true);
  let tokenUser = null; // token 이 존재할 때 토큰에서 username(sub) 를 가져옴 > 본인의 room 인지 확인 하기 위해

  //------------------------------------------------------------------
  // 이전에는 axios 를 이용하여 3번 요청(user, room, friend 정보) >  join 을 이용해서 한번의 요청으로 불러오기

  const loadRoomInfo = async () => {
    await axiosInstance
      .get(`/rooms/${roomUser}/${roomId}/users-room`)
      .then((res) => {
        console.log('room res : ', res);
        setRoomData(res.data.userWithRoom);
        setFollowingList(res.data.followingList);
      });
  };

  useEffect(() => {
    const tokenDe = jwtDecoder(localStorage.getItem('token'));
    tokenUser = tokenDe.sub;
    loadRoomInfo();
    if (tokenUser === roomUser) {
      setShoFollowBtn(false);
    }
  }, []);
  //-----------------------------------------------------------------

  const followBtnEvent = () => {
    if (checkFollow) {
      // true일때 > follow한 상태일때
      setCheckFollow(false);
      // Unfollow 실행 > table에서 delete
      axiosInstance.delete(`/friend/unfollow`, {
        data: {
          // userId: 'testId', // 로그인한 유저
          followUserId: roomUser, // 미니홈피 주인
        },
      });
      return;
    }
    setCheckFollow(true);
    // follow 실행
    axiosInstance.post(`/friend/follow`, {
      //  userId: 'testId', // 로그인한 유저
      followUserId: roomUser, // 미니홈피 주인
    });
  };
  return (
    <div className="room-background">
      <div className="room-main-container">
        <div className="room-left-1">
          <p className="room-visit">
            today<span>0</span>total<span>{roomData.roomTotal}</span>
          </p>
          <div className="room-left-2">
            <section className="room-left-side">
              <LeftSide roomData={roomData} followingList={followingList} />
            </section>
          </div>
        </div>
        <div className="room-right-1">
          <div className="room-right-header">
            <p className="room-name">{roomData.roomName}</p>
            {showFollowBtn && (
              <button onClick={followBtnEvent}>
                {' '}
                {checkFollow ? <>✖ UNFOLLOW</> : <>➕ FOLLOW </>}
              </button>
            )}
          </div>
          <div className="room-right-2">
            <section className="room-right-side">
              <Outlet />
            </section>
            <section className="room-main-nav">
              <RoomNav roomUser={roomUser} roomId={roomId} />
            </section>
          </div>
        </div>
      </div>
    </div>
  );
}

export default Room;
