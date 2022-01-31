import { useState } from 'react';
import { useParams } from 'react-router-dom';
import axiosInstance from '../../../utils/AxiosInstance';

export default function GuestSingle({ item, index }) {
  const [updating, setUpdating] = useState(false);
  const [content, setContent] = useState();
  const { roomUser, roomId } = useParams();

  const onChange = (e) => {
    setContent(e.target.value);
  };

  const guestDelete = async () => {
    if (window.confirm('방명록을 삭제하시겠습니까?')) {
      await axiosInstance
        .delete(`/rooms/${roomUser}/${roomId}/guests/${item.guestSeq}`)
        .then((res) => {
          alert('삭제되었습니다');
          window.location.reload();
        });
    }
  };

  const clickUpdate = () => {
    if (updating) {
      if (content.trim() === '') {
        alert('방명록을 입력하세요');
        return;
      } else if (content === item.guest_content) {
        alert('변경된 내용이 없습니다');
        return;
      }
      guestUpdate();
    } else if (!updating) {
      setContent(item.guestContent);
    }
    setUpdating(!updating);
  };

  const guestUpdate = async () => {
    await axiosInstance
      .patch(`/rooms/${roomUser}/${roomId}/guests`, {
        guestSeq: item.guestSeq,
        content,
        status: item.status,
      })
      .then((res) => {
        alert('수정되었습니다');
        window.location.reload();
      });
    setContent('');
  };

  return (
    <div className="guest-item-box">
      <section className="guest-head">
        <p>
          <span>NO.</span>
          {index + 1}
        </p>
        <p>{item.nickname}</p>
        <p>&#127968;</p>
        <p>{item.crateDate}</p>
        <p className="guest-list-private">
          {item.status ? '비밀글로 전환' : '공개글로 전환'}
        </p>
        <p className="guest-list-update" onClick={() => clickUpdate()}>
          {updating ? '등록' : '수정'}
        </p>
        <p className="guest-list-delete" onClick={() => guestDelete()}>
          삭제
        </p>
      </section>
      <section className="guest-body">
        <div className="guest-img"></div>
        <div className="guest-content">
          {updating ? (
            <textarea
              className="guest-update-content"
              defaultValue={item.content}
              value={content}
              onChange={onChange}
            ></textarea>
          ) : (
            <>
              {item.status ? ' ' : '🔒'}
              {item.content}
            </>
          )}
        </div>
      </section>
    </div>
  );
}
