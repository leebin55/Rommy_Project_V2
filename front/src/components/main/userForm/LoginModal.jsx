import * as React from 'react';
import Box from '@mui/material/Box';
import TextField from '@mui/material/TextField';
import Stack from '@mui/material/Stack';
import Button from '@mui/material/Button';

export default function LoginModal({ setCheckLogin }) {
  const btnStyle = {
    width: '200px',
    p: 1,
    m: 'auto',
    mt: 2,
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
          helperText="required"
        />
      </div>
      <div>
        <TextField
          //   error
          id="standard-error-helper-text"
          label="PW"
          type="password"
          variant="standard"
        />
      </div>
      <Stack sx={{ margin: '30px' }}>
        <Button
          sx={btnStyle}
          /* 주의 : ()=>{} 안에서 넣지 않으면 랜더링 되자마자 실행됨 */
          onClick={() => {
            setCheckLogin(true);
          }}
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
