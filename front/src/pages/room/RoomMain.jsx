import React, { useEffect, useState } from 'react';
import { useParams } from 'react-router-dom';
import axiosInstance from '../../utils/AxiosInstance';
import { useNavigate } from 'react-router-dom';
import GuestPost from '../../components/room/guest/GuestPost';
import MainRecentBoard from '../../components/room/MainRecentBoard';

/**
 * Room 의 레이아웃 중 오른쪽 부분(메인, 갤러리, 게시판,세팅 등등으로 )
 * Room 의 메인 화면
 */
function RoomMain() {
  const [content, setContent] = useState(''); // 입력한 방명록 작성 내용
  const [guestStatus, setGuestStatus] = useState(false); // 입력한 방명록 공개여부
  const [guestList, setGuestList] = useState([]);
  const [boardList, setBoardList] = useState([]);
  const [galleryList, setGalleryList] = useState([]);
  const { roomUser, roomId } = useParams();
  const navigate = useNavigate();

  // 방명록을 등록하면 바로 보여주기
  const loadGuest = async () => {
    await axiosInstance(`/rooms/${roomUser}/${roomId}/guests-recent`).then(
      (res) => {
        setGuestList(res.data);
      }
    );
  };

  const guestPrivate = () => {
    setGuestStatus((status) => !status);
  };

  const onChange = (e) => {
    setContent(e.target.value);
  };

  // 방명록 등록
  const guestResister = async () => {
    if (content.trim() === '') {
      alert('방명록을 입력하세요');
      return;
    }
    await axiosInstance
      .post(`/rooms/${roomUser}/${roomId}/guests`, {
        roomId,
        content,
        status: guestStatus ? 'PRIVATE' : 'PUBLIC',
      })
      .then((res) => {
        setContent('');
        loadGuest();
      });
  };

  const loadRoomMain = async () => {
    await axiosInstance
      .get(`/rooms/${roomUser}/${roomId}/boards-guests`)
      .then((res) => {
        console.log(' room main res : ', res);
        setBoardList(res.data.boardList);
        setGalleryList(res.data.galleryList);
        setGuestList(res.data.guestList);
      });
  };
  // 화면이 랜더링 되면 우선 localStorage 에 토큰있나 확인 하고
  // 토큰의 정보와 홈페이지 주인의 정보가 일치하는 확인
  useEffect(() => {
    loadRoomMain();
  }, []);

  return (
    <>
      <section className="main-top">
        <MainRecentBoard list={boardList} navigate={navigate} />
        <MainRecentBoard list={galleryList} navigate={navigate} />
      </section>
      <section className="main-bottom">
        <div className="main-guest">
          {guestList.length > 0 ? (
            guestList.map((item, index) => (
              <GuestPost item={item} index={index} />
            ))
          ) : (
            <div className="main-guest-empty">
              💬 첫번째 방명록을 남겨보세요 ! 💖
            </div>
          )}
        </div>
        <div className="guest-write">
          <div className="guest-write-private" onClick={() => guestPrivate()}>
            {guestStatus ? '비공개' : '공개'}
          </div>
          <textarea
            name="guest-write-content"
            value={content}
            onChange={onChange}
          />
          <button onClick={() => guestResister()}>등록</button>
        </div>
      </section>
    </>
  );
}

export default RoomMain;
