import { useState, useEffect } from 'react';
import '../../css/main/ProfileBox.css';
import { useNavigate } from 'react-router-dom';
import { useLoginContext } from '../../context/LoginContextProvider';
import axiosInstance from '../../utils/AxiosInstance';
import MainModal from './userForm/MainModal';

function ProfileBox() {
  const navigate = useNavigate();
  const [user, setUser] = useState({ userId: null, userName: null });
  const [checkLogin, setCheckLogin] = useState(false);
  // modal 을 로그인 할때

  const checkLoggedUser = async () => {
    await axiosInstance.get(``);
  };

  // 로그인 했는지 확인> JWT 를 사용할 예정이기 때문에 cookie 에 토큰이 있는지 확인하고 있으면 토큰이 유효한지 서버에 보내서 확인하기
  useEffect(() => {}, []);

  return (
    <>
      {checkLogin ? (
        <div className="profilebox-container">
          <div className="profilebox-img">
            <img className="logo" src="img/logo.svg" alt="profile_img" />
            {/* user가 있으면 userName을 출력 */}
            {user && <p> *** 님</p>}
          </div>
          <div className="profilebox-body">
            <div>쪽지함 </div>
            <div>팔로우 2.4k</div>
          </div>
          <div className="profilebox-footer">
            <div className="profilebox-btns">
              <button onClick={() => {}}>수정하기</button>
              <button onClick={() => setCheckLogin(false)}> 로그아웃</button>
            </div>
            <button className="profilebox-room-btn" onClick={() => {}}>
              나의 ROOM 가기
            </button>
          </div>
        </div>
      ) : (
        <div className="profilebox-container">
          <div className="profilebox-img">
            <img className="logo" src="img/logo.svg" alt="profile_img" />
            {/* user가 있으면 userName을 출력 */}
            <p>로그인을 해주세요</p>
          </div>

          <div className="profilebox-btns">
            <MainModal btnType="로그인" setCheckLogin={setCheckLogin} />
            <MainModal btnType="회원가입" />
          </div>
        </div>
      )}
    </>
  );
}
export default ProfileBox;
