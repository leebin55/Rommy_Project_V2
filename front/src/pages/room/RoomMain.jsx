import React, { useEffect, useState } from 'react';
import { useParams } from 'react-router-dom';
import axiosInstance from '../../utils/AxiosInstance';

/**
 * Room 의 레이아웃 중 오른쪽 부분(메인, 갤러리, 게시판,세팅 등등으로 )
 * Room 의 메인 화면
 */
function RoomMain() {
  const [content, setContent] = useState(''); // 입력한 방명록 작성 내용
  const [guestStatus, setGuestStatus] = useState(false); // 입력한 방명록 공개여부
  const [guestList, setGuestList] = useState([]);
  const { userId } = useParams();

  // 미니홈피 메인화면에서 방명록을 보여주기 위함
  const loadGuest = async () => {
    const res = await axiosInstance(`/room/${userId}/guest?limit=4`);
    const data = await res.data;
    setGuestList(data);
  };

  const guestPrivate = () => {
    setGuestStatus((pp) => !pp);
  };

  const onChange = (e) => {
    setContent(e.target.value);
  };

  const guestInsert = async () => {
    if (content.trim() === '') {
      alert('방명록을 입력하세요');
      return;
    }
    await axiosInstance.post(`/room/${userId}/guest`, {
      content,
      status: guestStatus,
    });
    setContent('');
    loadGuest();
  };
  // 화면이 랜더링 되면 우선 localStorage 에 토큰있나 확인 하고
  // 토큰의 정보와 홈페이지 주인의 정보가 일치하는 확인
  useEffect(() => {}, []);

  return (
    <>
      <section className="main-top">
        <div className="main-recent-box"></div>
        <div className="main-list-box"></div>
      </section>
      <section className="main-bottom">
        <div className="main-guest">
          {guestList ? (
            guestList.map((item) => (
              <div className="main-guest-box">
                <img src="/img/postit.png" alt="guest-post" />
                <p>{item.guestContent}</p>
              </div>
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
          <button onClick={() => guestInsert()}>등록</button>
        </div>
      </section>
    </>
  );
}

export default RoomMain;
