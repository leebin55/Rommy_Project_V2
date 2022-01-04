import * as React from 'react';
import Box from '@mui/material/Box';
import TextField from '@mui/material/TextField';
import Button from '@mui/material/Button';
import Radio from '@mui/material/Radio';
import RadioGroup from '@mui/material/RadioGroup';
import FormControlLabel from '@mui/material/FormControlLabel';
import FormControl from '@mui/material/FormControl';
import { bgcolor } from '@mui/system';

export default function JoinModal({ setCheckLogin }) {
  const btnStyle = {
    width: '200px',
    p: 1,
    mt: 2,
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
          />
          <TextField
            id="standard-error-helper-text"
            label="PW 확인"
            type="password"
            variant="standard"
          />
        </div>
        <div>
          <TextField
            id="standard-error-helper-text"
            label="닉네임"
            variant="standard"
          />
          <TextField
            id="standard-error-helper-text"
            label="Email"
            variant="standard"
          />
        </div>
        <div>
          <FormControl>
            <RadioGroup
              aria-label="gender"
              defaultValue="FEMALE"
              name="radio-buttons-group"
            >
              <div>
                <FormControlLabel
                  value="FEMALE"
                  control={<Radio />}
                  label="여"
                />
                <FormControlLabel value="MALE" control={<Radio />} label="남" />
              </div>
            </RadioGroup>
          </FormControl>
          <TextField
            id="standard-error-helper-text"
            type="date"
            helperText="생년월일"
            variant="standard"
          />
        </div>

        <Button
          sx={btnStyle}
          /* 주의 : ()=>{} 안에서 넣지 않으면 랜더링 되자마자 실행됨 */
          onClick={() => {
            setCheckLogin(true);
          }}
          variant="contained"
        >
          회원가입
        </Button>
      </div>
    </Box>
  );
}
