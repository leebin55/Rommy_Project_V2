import { useState, useEffect } from 'react';
import '../../css/main/ProfileBox.css';
import { useNavigate } from 'react-router-dom';
import axiosInstance from '../../utils/AxiosInstance';
import MainModal from './userForm/MainModal';
import { Button } from '@mui/material';

function ProfileBox() {
  const navigate = useNavigate();
  // user 부분 다 완성되면 false 를 기본값으로 변경하기
  const [checkLogin, setCheckLogin] = useState(true);
  // modal 을 로그인 할때

  // 우선 testid 로  username 지정 > 나중에 변경해주기!~~~~~!!!
  const username = 'testid';

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
            {username && <p>{username} 님</p>}
          </div>
          <div className="profilebox-body">
            <div>팔로우 2.4k 팔로워 10K</div>
          </div>
          <div className="profilebox-footer">
            <div className="profilebox-btns">
              <MainModal btnType="수정하기" />
              <Button onClick={() => setCheckLogin(false)}>로그아웃</Button>
              {/* <button onClick={() => setCheckLogin(false)}> 로그아웃</button> */}
            </div>
            <button
              className="profilebox-room-btn"
              onClick={() => {
                navigate(`/room/${username}`);
              }}
            >
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
