import React, { useState } from 'react';
import { useEffect } from 'react';
import TextField from '@mui/material/TextField';
import { useNavigate, useParams } from 'react-router-dom';
import axiosInstance from '../../utils/AxiosInstance';
import { Button } from '@mui/material';
import { jwtDecoder } from '../../utils/JwtUtils';

function Setting() {
  const navigate = useNavigate();
  const [roomName, setRoomName] = useState('');
  const [intro, setIntro] = useState('');
  const [tokenUser, setTokenUser] = useState('');
  const { roomUser, roomId } = useParams();

  // 미니홈피 정보들(미니홈피명, 소개글) 불러오기
  const fetchSetting = async () => {
    axiosInstance.get(`/rooms/${roomUser}/${roomId}`).then((res) => {
      console.log('room 정보 불러오기 : ', res.data);
      setRoomName(res.data.roomName);
      setIntro(res.data.intro);
    });
  };

  // 미니홈피 정보 수정
  const clickUpdate = async () => {
    await axiosInstance
      .patch(`/rooms/${roomUser}/${roomId}`, {
        roomId,
        roomName,
        intro,
      })
      .then((res) => {
        alert('수정되었습니다');
        window.location.reload();
      });
  };

  useEffect(() => {
    fetchSetting();
    const token = localStorage.getItem('token');
    if (token) {
      const { sub } = jwtDecoder(token);
      setTokenUser(sub);
    }
  }, []);

  return (
    <div className="setting-container">
      <h1>ROOM 설정</h1>
      <div>
        <TextField
          sx={{ width: '500px' }}
          id="room-name"
          value={roomName}
          helperText="Room의 이름"
          margin="normal"
          onChange={(e) => {
            setRoomName(e.target.value);
          }}
        />
      </div>
      <div>
        <TextField
          id="outlined-multiline-static"
          multiline
          rows={10}
          value={intro}
          helperText="Room 소개글"
          margin="normal"
          fullWidth="true"
          onChange={(e) => {
            setIntro(e.target.value);
          }}
        />
      </div>
      {roomUser === tokenUser && (
        <>
          <Button onClick={clickUpdate}>수정</Button>
          <Button
            onClick={() => {
              fetchSetting();
            }}
          >
            취소
          </Button>
        </>
      )}
    </div>
  );
}

export default Setting;
