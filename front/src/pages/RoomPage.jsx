import React, { useEffect, useState } from 'react';
import { Outlet, useParams } from 'react-router-dom';
import LeftSide from '../components/room/LeftSide';
import axiosInstance from '../utils/AxiosInstance';
import RoomNav from '../components/room/Nav';
import '../css/room/Room.css';
import { jwtDecoder } from '../utils/JwtUtils';

function Room() {
  const { roomUser, roomId } = useParams();
  const [roomData, setRoomData] = useState({});
  const [followingList, setFollowingList] = useState([]);
  const [isFollow, setIsFollow] = useState(false);
  const [showFollowBtn, setShoFollowBtn] = useState(true);
  let tokenUser = null;

  const loadRoomInfo = async () => {
    await axiosInstance
      .get(`/rooms/${roomUser}/${roomId}/users-room`)
      .then((res) => {
        console.log('room res : ', res);
        setRoomData(res.data.userWithRoom);
        setFollowingList(res.data.followingList);
      });
  };

  const checkFollow = async () => {
    await axiosInstance.get().then((res) => {});
  };

  useEffect(() => {
    const tokenDe = jwtDecoder(localStorage.getItem('token'));
    tokenUser = tokenDe.sub;
    loadRoomInfo();
    if (tokenUser === roomUser) {
      setShoFollowBtn(false);
    } else {
      checkFollow();
    }
  }, []);

  const followBtnEvent = () => {
    if (checkFollow) {
      // true일때 > follow한 상태일때
      axiosInstance.delete(`/follows/${roomUser}`).then((res) => {
        console.log(res);
        setIsFollow(false);
      });
      return;
    }
    axiosInstance
      .post(`/follows`, {
        roomUser,
      })
      .then((res) => {
        console.log(res);
        setIsFollow(true);
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
