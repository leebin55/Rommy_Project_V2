import { Avatar } from '@mui/material';
import returnFileURL from '../../../utils/FileUtils';

const avatarStyle = {
  width: 30,
  height: 30,
};

function ProfileAvatar({ profile }) {
  return (
    <>
      {profile ? (
        <Avatar sx={avatarStyle} aria-label="avatar">
          <input
            type="image"
            src={returnFileURL(profile)}
            alt="profile"
            style={{ width: '100%' }}
          />
        </Avatar>
      ) : (
        <Avatar sx={avatarStyle} aria-label="avatar">
          <input
            type="image"
            src="/img/noimage.png"
            alt="profile"
            style={{ width: '100%' }}
          />
        </Avatar>
      )}
    </>
  );
}

export default ProfileAvatar;
