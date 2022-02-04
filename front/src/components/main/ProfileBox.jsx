import { useState, useEffect } from 'react';
import '../../css/main/ProfileBox.css';
import { useNavigate } from 'react-router-dom';
import axiosInstance from '../../utils/AxiosInstance';
import MainModal from './userForm/MainModal';
import { Button } from '@mui/material';
import returnFileURL from '../../utils/FileUtils';

/**
 * 메인 화면에서 프로필 박스 부분
 */
function ProfileBox() {
  const navigate = useNavigate();
  // 로그인 했으면 true  안했으면 false : 프로필 박스 로그인 전 후 다르게 표시하기 위해
  const [checkLogin, setCheckLogin] = useState(false);
  const [user, setUser] = useState({
    username: '',
    nickname: '',
    profile: '',
    email: '',
    roomId: '',
  });

  // 로그인한 유저 정보 불러오기 : 프로필 박스에 표기하기 위해
  const loadUserDetail = async () => {
    await axiosInstance.get(`/users/detail`).then((res) => {
      console.log('user 받아오기', res.data);

      setUser({
        username: res.data.username,
        nickname: res.data.nickname,
        email: res.data.email,
        profile: res.data.profile,
        roomId: res.data.roomId,
      });
    });
  };

  // logout 클릭 이벤트
  const logoutEvent = () => {
    setCheckLogin(false);
    localStorage.removeItem('token');
    localStorage.removeItem('loggedInUser');
    window.location.reload();
  };
  // 로그인 했는지 확인> JWT 를 사용할 예정이기 때문에 localstorage 에 토큰이 있는지 확인하고 있으면 토큰이 유효한지 서버에 보내서 확인하기
  useEffect(() => {
    if (localStorage.getItem('token')) {
      loadUserDetail();
      setCheckLogin(true);
    }
  }, []);

  return (
    <>
      {checkLogin ? (
        <div className="profilebox-container">
          <div className="profilebox-img">
            {user.profile ? (
              <img
                className="logo"
                src={returnFileURL(user.profile)}
                alt="profile_img"
              />
            ) : (
              <img className="logo" src="img/logo.svg" alt="profile_img" />
            )}
            <h1>{user.nickname} 님</h1>
            <p>{user.email}</p>
          </div>
          <div className="profilebox-body">
            <div>팔로우 2.4k 팔로워 10K</div>
          </div>
          <div className="profilebox-footer">
            <div className="profilebox-btns">
              <MainModal btnType="수정하기" user={user} />
              <Button onClick={logoutEvent}>로그아웃</Button>
            </div>
            <button
              className="profilebox-room-btn"
              onClick={() => {
                navigate(`/rooms/${user.username}/${user.roomId}`);
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
