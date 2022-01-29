import { React, useState, useEffect } from 'react';
import GuestSingle from './GuestSingle';
import { useParams } from 'react-router-dom';
import axiosInstance from '../../../utils/AxiosInstance';

function GuestMain() {
  const [guestList, setGuestList] = useState([]);
  const { roomUser, roomId } = useParams();
  const [guestSlice, SetGuestSlice] = useState({});

  useEffect(() => {
    fetchList();
  }, []);

  const fetchList = async () => {
    await axiosInstance
      .get(
        `/rooms/${roomUser}/${roomId}/guests?page=0&size=10&sort=createDate,desc`
      )
      .then((res) => {
        console.log('guest 불러오기 : ', res);
        console.log('res.data.content', res.data.content);
        // setGuestList(res.data.content);
        SetGuestSlice(res.data);
      });
  };

  return (
    <div className="guest-container">
      <section className="guest-list">
        {guestSlice.content != null ? (
          guestSlice.content.map((item, index) => {
            return (
              <GuestSingle item={item} index={index} fetchList={fetchList} />
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
