import { useState } from 'react';
import Box from '@mui/material/Box';
import TextField from '@mui/material/TextField';
import Button from '@mui/material/Button';
import axiosInstance from '../../../utils/AxiosInstance';

export default function JoinModal({ handleClose }) {
  const [username, setUsername] = useState('');
  const [password, setPassword] = useState('');
  const [email, setEmail] = useState('');
  const [rePassword, setRePassword] = useState('');
  const [nickname, setNickname] = useState('');

  const btnStyle = {
    width: '200px',
    p: 1,
    mt: 2,
  };

  const joinClickEvent = async () => {
    const result = joinFormValidation();
    if (result === 'ok') {
      await axiosInstance
        .post('/users/sign_up', {
          username,
          password,
          email,
          nickname,
        })
        .then((res) => {
          alert(`${res.data} 님 가입을 축하드립니다.`);
          window.location.reload();
        });
    }
  };
  const joinFormValidation = () => {
    if (username === null || username.trim() === '') {
      alert(`ID 를 다시 입력해 주세요`);
      return;
    }
    if (password === null || password.trim() === '') {
      alert(`password 를 다시 입력해 주세요`);
      return;
    }
    if (password !== rePassword) {
      alert('password 가 일치하지 않습니다.');
      return;
    }
    if (email === null || email.trim() === '') {
      alert(`email 을 다시 입력해 주세요`);
      return;
    }
    if (nickname === null || nickname.trim() === '') {
      alert(`닉네임을 다시 입력해 주세요`);
      return;
    }
    return 'ok';
  };
  return (
    <Box
      component="form"
      sx={{
        padding: '30px',

        '& .MuiTextField-root': { m: 1 },
      }}
      noValidate
      autoComplete="off"
    >
      <div className="modal-input-container">
        <div>
          <TextField
            id="standard-required"
            label="ID"
            variant="standard"
            helperText="required"
            onChange={(e) => {
              setUsername(e.target.value);
            }}
          />
          <Button
            sx={{
              margin: '20px',
              fontSize: '12px',
              backgroundColor: 'gray',
            }}
            /* 주의 : ()=>{} 안에서 넣지 않으면 랜더링 되자마자 실행됨 */
            onClick={() => {}}
            variant="contained"
          >
            중복확인
          </Button>
        </div>
        <div>
          <TextField
            id="standard-error-helper-text"
            label="PW"
            helperText="비밀번호는 어찌고 저찌고"
            type="password"
            variant="standard"
            onChange={(e) => {
              setPassword(e.target.value);
            }}
          />
          <TextField
            id="standard-error-helper-text"
            label="PW 확인"
            type="password"
            variant="standard"
            onChange={(e) => {
              setRePassword(e.target.value);
            }}
          />
        </div>
        <div>
          <TextField
            id="standard-error-helper-text"
            label="닉네임"
            variant="standard"
            onChange={(e) => {
              setNickname(e.target.value);
            }}
          />
          <TextField
            id="standard-error-helper-text"
            label="Email"
            variant="standard"
            onChange={(e) => {
              setEmail(e.target.value);
            }}
          />
        </div>

        <Button
          sx={btnStyle}
          /* 주의 : ()=>{} 안에서 넣지 않으면 랜더링 되자마자 실행됨 */
          onClick={joinClickEvent}
          variant="contained"
        >
          회원가입
        </Button>
      </div>
    </Box>
  );
}
