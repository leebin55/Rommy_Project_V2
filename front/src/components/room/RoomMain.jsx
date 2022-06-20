import React, { useEffect, useState } from 'react';
import { useParams } from 'react-router-dom';
import axiosInstance from '../../utils/AxiosInstance';
import { useNavigate } from 'react-router-dom';
import GuestPostBox from './guest/GuestPostBox';
import MainRecentBoard from './MainRecentBoard';

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
              <GuestPostBox item={item} index={index} />
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
