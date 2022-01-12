import React, { useState } from 'react';
import Box from '@mui/material/Box';
import TextField from '@mui/material/TextField';
import Stack from '@mui/material/Stack';
import Button from '@mui/material/Button';
import axiosInstance from '../../../utils/AxiosInstance';

export default function LoginModal() {
  const [username, setUsername] = useState('');
  const [password, setPassword] = useState('');

  const btnStyle = {
    width: '200px',
    p: 1,
    m: 'auto',
    mt: 2,
  };

  const loginBtnClick = async () => {
    if (username.trim() !== '' && password.trim() !== '') {
      try {
        await axiosInstance
          .post('/user/login', {
            username,
            password,
          })
          .then((res) => {
            console.log(res.data);
            window.location.reload();
          });
      } catch (error) {
        alert('해당정보가 없습니다. 회원가입해주세요');

        return;
      }
    }
    alert('ID 또는 비밀번호를 입력해주세요');
  };

  return (
    <Box
      component="form"
      sx={{
        padding: '30px',
        textAlign: 'center',
        '& .MuiTextField-root': { m: 1, width: '400px' },
      }}
      noValidate
      autoComplete="off"
    >
      <div className="modal-input-container">
        <TextField
          id="standard-required"
          label="ID"
          variant="standard"
          onChange={(e) => {
            setUsername(e.target.value);
          }}
        />
      </div>
      <div>
        <TextField
          //   error
          id="standard-error-helper-text"
          label="PW"
          type="password"
          variant="standard"
          onChange={(e) => {
            setPassword(e.target.value);
          }}
        />
      </div>
      <Stack sx={{ margin: '30px' }}>
        <Button
          sx={btnStyle}
          /* 주의 : ()=>{} 안에서 넣지 않으면 랜더링 되자마자 실행됨 */
          onClick={loginBtnClick}
          variant="contained"
        >
          로그인
        </Button>
        <Button sx={btnStyle} variant="contained">
          아이디/비밀번호 찾기
        </Button>
      </Stack>
    </Box>
  );
}
