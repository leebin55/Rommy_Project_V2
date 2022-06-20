import { Button } from '@mui/material';
import React from 'react';
import Login from '../components/userForm/Login';
import '../css/ReqLogin.css';

function ReqLogin() {
  const mainBtnHandler = () => {
    window.location.href = '/';
  };
  return (
    <div className="req-login-container">
      <Button onClick={mainBtnHandler}>메인화면으로</Button>
      <Login state="page" />
    </div>
  );
}

export default ReqLogin;
