import * as React from 'react';
import Backdrop from '@mui/material/Backdrop';
import Box from '@mui/material/Box';
import Modal from '@mui/material/Modal';
import Fade from '@mui/material/Fade';
import Button from '@mui/material/Button';
import Typography from '@mui/material/Typography';
import JoinModal from './JoinModal';
import LoginModal from './LoginModal';
import Cancel from '@mui/icons-material/Cancel';
import UpdateModal from './UpdateModal';

const style = {
  position: 'absolute',
  top: '50%',
  left: '50%',
  transform: 'translate(-50%, -50%)',
  width: 600,
  height: 500,
  bgcolor: 'background.paper',
  border: '2px solid #000',
  boxShadow: 24,
  p: 4,
};

export default function MainModal({ btnType, setCheckLogin }) {
  const [open, setOpen] = React.useState(false);
  const handleOpen = () => setOpen(true);
  const handleClose = () => setOpen(false);

  return (
    <div>
      <Button onClick={handleOpen}>{btnType}</Button>
      <Modal
        aria-labelledby="transition-modal-title"
        aria-describedby="transition-modal-description"
        open={open}
        onClose={handleClose}
        closeAfterTransition
        BackdropComponent={Backdrop}
        BackdropProps={{
          timeout: 500,
        }}
      >
        <Fade in={open}>
          <Box sx={style}>
            <Box sx={{ display: 'flex' }}>
              <Typography
                id="transition-modal-title"
                variant="h6"
                component="h2"
              >
                {btnType}
              </Typography>
              <Cancel
                onClick={handleClose}
                sx={{
                  ml: 'auto',
                  fontSize: '30px',
                  ':hover': { cursor: 'pointer', color: '#ff1744' },
                }}
              />
            </Box>
            {btnType === '로그인' && (
              <>
                <LoginModal />
              </>
            )}
            {btnType === '회원가입' && (
              <>
                <JoinModal />
              </>
            )}
            {btnType === '수정하기' && (
              <>
                <UpdateModal />
              </>
            )}
          </Box>
        </Fade>
      </Modal>
    </div>
  );
}
