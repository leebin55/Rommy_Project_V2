import { Button } from '@mui/material';
import React from 'react';

function ErrorPage() {
  return (
    <div className="error-page-container">
      <Button
        onClick={() => {
          window.history.go(-2);
        }}
      >
        뒤로가기
      </Button>
      <h2>메시지를 보여줘야 되는데.. </h2>
      <p>어떻게 보여주는게 좋을지</p>
    </div>
  );
}

export default ErrorPage;
