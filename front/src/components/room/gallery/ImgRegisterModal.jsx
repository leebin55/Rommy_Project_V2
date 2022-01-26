import { useState, useCallback } from 'react';
import { styled, Box } from '@mui/system';
import ModalUnstyled from '@mui/base/ModalUnstyled';
import { Paper } from '@mui/material';
import AddCircleOutlineIcon from '@mui/icons-material/AddCircleOutline';
import PhotoCameraBackIcon from '@mui/icons-material/PhotoCameraBack';
import { useDropzone } from 'react-dropzone';

const StyledModal = styled(ModalUnstyled)`
  position: fixed;
  z-index: 1300;
  right: 0;
  bottom: 0;
  top: 0;
  left: 0;
  display: flex;
  align-items: center;
  justify-content: center;
`;

const Backdrop = styled('div')`
  z-index: -1;
  position: fixed;
  right: 0;
  bottom: 0;
  top: 0;
  left: 0;
  background-color: rgba(0, 0, 0, 0.5);
  -webkit-tap-highlight-color: transparent;
`;

const style = {
  minWidth: 500,
  height: 500,
  bgcolor: 'white',
  border: '2px solid #000',
  p: 2,
  px: 4,
  pb: 3,
};

const paperStyle = {
  '& > :not(style)': {
    m: 1,
    height: 500,
  },
};

export default function ImgRegisterModal() {
  const [image, setImage] = '';
  const [selectedImgFiles, setSelectedImgFiles] = useState([]);
  const [preview, setPreview] = useState('');

  const onDrop = useCallback((acceptedFiles) => {
    // 0: File
    // type: "image/jpeg"
    console.log(acceptedFiles);
    acceptedFiles.forEach((file) => {
      if (file['type'].split('/')[0] !== 'image') {
        console.log('이미지 파일만 등록 가능합니다.');
        return;
      }
      setSelectedImgFiles(acceptedFiles);
      const previewUrl = URL.createObjectURL(acceptedFiles[0]);
      setPreview(previewUrl);
    });
  }, []);

  const { getRootProps, getInputProps, isDragActive } = useDropzone({ onDrop });
  const { ref, ...rootProps } = getRootProps();

  const [open, setOpen] = useState(false);
  const handleOpen = () => setOpen(true);
  const handleClose = () => setOpen(false);

  return (
    <div>
      <button type="button" onClick={handleOpen}>
        <AddCircleOutlineIcon fontSize="large" color="secondary" />
      </button>
      <StyledModal
        aria-labelledby="unstyled-modal-title"
        aria-describedby="unstyled-modal-description"
        open={open}
        onClose={handleClose}
        BackdropComponent={Backdrop}
      >
        <Box sx={style} {...rootProps}>
          <h3>새 게시물 등록</h3>
          <input {...getInputProps()} />
          <div>
            <PhotoCameraBackIcon sx={{ fontSize: 200 }} />
            <p>사진을 여기에 끝어다 놓거나 클릭해주세요</p>
          </div>
        </Box>
      </StyledModal>
    </div>
  );
}
