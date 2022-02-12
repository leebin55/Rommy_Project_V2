import { Button } from '@mui/material';
import React from 'react';
import '../css/error.css';

function ErrorPage() {
  return (
    <div className="error-page-container">
      <div className="error-page-sub-container">
        <Button
          variant="contained"
          size="large"
          onClick={() => {
            window.history.go(-2);
          }}
        >
          뒤로가기
        </Button>
        <h1>잘못된 주소입니다. </h1>
        <p>다시 확인해주세요</p>
      </div>
    </div>
  );
}

export default ErrorPage;
