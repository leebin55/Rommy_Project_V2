import React, { useEffect, useState } from 'react';
import Box from '@mui/material/Box';
import TextField from '@mui/material/TextField';
import axiosInstance from '../../../utils/AxiosInstance';

export default function UpdateModal() {
  const [preview, setPreview] = useState(); //프로필 미리보기 설정
  const [userProfile, setUserProfile] = useState('');
  const [nickname, setNickname] = useState('');
  const [Profile, setProfile] = useState('');
  const [email, setEmail] = useState('');
  const [tempFile, setTempFile] = useState({}); // 프로필 파일 변경될때 임시로

  const textStyle = {
    width: 400,
    margin: 1,
  };

  // 사진을 선택할때
  const imgChange = (event) => {
    // input에서 파일을 한개를 선택하더라도 배열로 저장된다.
    //  console.log(URL.createObjectURL(event.target.files[0]));
    // input 에서 선택한 파일을 서버에 아직 올리지 않은 상태에서 미리보기 하기위해
    // URL.createObjectURL()에서 반환된 값을 img src 에 넘겨주면 미리보기 가능
    setPreview(URL.createObjectURL(event.target.files[0]));
    const file = event.target.files[0];
    if (file.size > 2097152) {
      alert('파일용량을 초과하였습니다.');
      return;
    }
    setTempFile(file);
  };

  return (
    <Box component="form" sx={{ width: '25ch' }} noValidate autoComplete="off">
      <div id="modal-update-description">
        <div>
          <input
            type="file"
            id="profile_img"
            name="profile_img"
            accept="image/png, image/jpeg image/jpg"
            defaultValue={userProfile}
            onChange={imgChange}
          />
          <div className="profile-update-img-container">
            <img
              onLoad={() => URL.revokeObjectURL(preview)}
              src={preview || 'https://via.placeholder.com/250'}
              alt="img"
            />
          </div>
        </div>
        <div className="profile-update-text-container">
          <TextField
            id="filled-basic"
            variant="filled"
            value={nickname}
            sx={textStyle}
            helperText="이름"
            onChange={(event) => {
              setNickname(event.target.value);
            }}
          />
          <TextField
            id="filled-basic"
            variant="filled"
            value={email}
            sx={textStyle}
            helperText="이메일"
            onChange={(event) => {
              setEmail(event.target.value);
            }}
          />
          <TextField
            id="filled-basic"
            variant="filled"
            sx={textStyle}
            helperText="비밀번호"
            type="password"
          />
          <TextField
            id="filled-basic"
            variant="filled"
            sx={textStyle}
            helperText="비밀번호확인"
            type="password"
          />
        </div>
      </div>
    </Box>
  );
}
