import { useState } from 'react';
import Box from '@mui/material/Box';
import TextField from '@mui/material/TextField';
import Button from '@mui/material/Button';
import axiosInstance from '../../utils/AxiosInstance';
import {
  idValidate,
  pwValidate,
  emailValidate,
  nickValidate,
} from '../../utils/validate';

export default function Join({ handleClose }) {
  const [username, setUsername] = useState('');
  const [password, setPassword] = useState('');
  const [email, setEmail] = useState('');
  const [rePassword, setRePassword] = useState('');
  const [nickname, setNickname] = useState('');
  const [usernameCheck, setUsernameCheck] = useState(false);
  const [idHelper, setIdHelper] = useState('Required');

  const btnStyle = {
    width: '200px',
    p: 1,
    mt: 2,
  };

  const joinClickEvent = async () => {
    const result = joinFormValidation();
    if (result) {
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
    if (
      idValidate(username) &&
      pwValidate(password, rePassword) &&
      emailValidate(email) &&
      nickValidate(nickname)
    ) {
      if (!usernameCheck) {
        alert('아이디 중복검사를 해주세요');
        return false;
      }
      return true;
    }
  };

  const duplicateValidation = async (tempId) => {
    if (idValidate(tempId)) {
      await axiosInstance.get(`users/valid/${tempId}`).then((res) => {
        if (res.status) {
          if (res.data) {
            setUsernameCheck(false);
            setIdHelper('사용 불가능한 아이디 입니다.');
          } else {
            setUsernameCheck(true);
            setIdHelper('사용가능한 아이디 입니다.');
          }
        }
      });
    }
  };

  return (
    <Box
      component="form"
      sx={{
        padding: '30px',
        textAlign: 'center',
        '& .MuiTextField-root': { m: 2 },
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
            helperText={idHelper}
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
            onClick={() => duplicateValidation(username)}
            variant="contained"
          >
            중복확인
          </Button>
        </div>
        <div>
          <TextField
            id="standard-error-helper-text"
            label="PW"
            helperText="비밀번호는 8글자 이상"
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

        <Button sx={btnStyle} onClick={joinClickEvent} variant="contained">
          회원가입
        </Button>
      </div>
    </Box>
  );
}
