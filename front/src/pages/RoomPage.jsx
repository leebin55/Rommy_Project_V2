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
  const [showFollowBtn, setShowFollowBtn] = useState(true);
  let tokenUser = null;

  const loadRoomInfo = async () => {
    await axiosInstance
      .get(`/rooms/${roomUser}/${roomId}/users-room`)
      .then((res) => {
        console.log('room res : ', res);
        setRoomData(res.data.userWithRoom);
        setFollowingList(res.data.followingList);
        setIsFollow(res.data.follow);
      });
  };

  useEffect(() => {
    const tokenDe = jwtDecoder(localStorage.getItem('token'));
    tokenUser = tokenDe.sub;
    loadRoomInfo();
    if (tokenUser === roomUser) {
      setShowFollowBtn(false);
    }
  }, []);

  const followBtnEvent = async () => {
    if (isFollow) {
      // true일때 > follow한 상태일때
      await axiosInstance.delete(`/follows/${roomUser}`).then((res) => {
        console.log('unfollow :', res);
        setIsFollow(false);
      });
    } else {
      await axiosInstance.post(`/follows/${roomUser}`).then((res) => {
        console.log('follow  : ', res);
        setIsFollow(true);
      });
    }
  };
  return (
    <div id="room-background">
      <main id="room-main-container">
        <aside id="room-left-section">
          <p className="room-visit">
            today<span>0</span>total<span>{roomData.roomTotal}</span>
          </p>
          <div id="room-left-side-container">
            <div id="room-left-side">
              <LeftSide roomData={roomData} followingList={followingList} />
            </div>
          </div>
        </aside>
        <div id="room-right-section">
          <section id="room-right-header">
            <p>{roomData.roomName}</p>
            {showFollowBtn && (
              <button onClick={followBtnEvent}>
                {' '}
                {isFollow ? <>✖ UNFOLLOW</> : <>➕ FOLLOW </>}
              </button>
            )}
          </section>
          <section id="room-right-side-container">
            <div id="room-right-side">
              <Outlet />
            </div>
            <nav id="room-main-nav">
              <RoomNav roomUser={roomUser} roomId={roomId} />
            </nav>
          </section>
        </div>
      </main>
    </div>
  );
}

export default Room;
