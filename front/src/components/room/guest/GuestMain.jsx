import { React, useState, useEffect } from 'react';
import GuestItem from './GuestItem';
import { useParams } from 'react-router-dom';
import axiosInstance from '../../../utils/AxiosInstance';

function GuestMain() {
  const [guestList, setGuestList] = useState([]);
  const { roomUser, roomId } = useParams();

  useEffect(() => {
    fetchList();
  }, []);

  const fetchList = async () => {
    const response = await fetch(
      `http://localhost:8080/room/${roomUser}/guest`
    );
    const data = await response.json();
    setGuestList(data);
  };

  // No 표시 하기 위해 리스트 개수 세기
  let list_length = guestList.length;

  return (
    <div className="guest-container">
      <section className="guest-list">
        {guestList.length > 0 ? (
          guestList.map((item) => {
            return (
              <GuestItem
                data={item}
                index={--list_length}
                fetchList={fetchList}
              />
            );
          })
        ) : (
          <div className="guest-item-box">
            <p>아직 등록된 방명록이 없습니다</p>
          </div>
        )}
      </section>
    </div>
  );
}

export default GuestMain;
