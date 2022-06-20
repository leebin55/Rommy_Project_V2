import * as React from 'react';
import Backdrop from '@mui/material/Backdrop';
import Box from '@mui/material/Box';
import Modal from '@mui/material/Modal';
import Fade from '@mui/material/Fade';
import Button from '@mui/material/Button';
import Typography from '@mui/material/Typography';
import Join from './Join';
import Cancel from '@mui/icons-material/Cancel';
import Update from './Update';
import Login from './Login';

const style = {
  position: 'absolute',
  top: '50%',
  left: '50%',
  transform: 'translate(-50%, -50%)',
  width: 700,
  height: 500,
  bgcolor: 'background.paper',
  border: '2px solid #000',
  boxShadow: 24,
  p: 4,
};

export default function MainModal({ btnType, user }) {
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
                <Login state="modal" />
              </>
            )}
            {btnType === '회원가입' && (
              <>
                <Join handleClose={handleClose} />
              </>
            )}
            {btnType === '수정하기' && (
              <>
                <Update user={user} />
              </>
            )}
          </Box>
        </Fade>
      </Modal>
    </div>
  );
}
