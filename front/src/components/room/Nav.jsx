import React from 'react';
import { NavLink } from 'react-router-dom';
import CancelPresentationIcon from '@mui/icons-material/CancelPresentation';

function RoomNav({ roomUser, roomId }) {
  return (
    <nav className="room-main-nav">
      <NavLink
        className={({ isActive }) =>
          'room-nav-box' + (isActive ? ' room-nav-click' : '')
        }
        to={`/rooms/${roomUser}/${roomId}`}
      >
        Home
      </NavLink>
      <NavLink
        className={({ isActive }) =>
          'room-nav-box' + (isActive ? ' room-nav-click' : '')
        }
        to={`/rooms/${roomUser}/${roomId}/boards`}
      >
        Board
      </NavLink>
      <NavLink
        className={({ isActive }) =>
          'room-nav-box' + (isActive ? ' room-nav-click' : '')
        }
        to={`/rooms/${roomUser}/${roomId}/galleries`}
      >
        Gallery
      </NavLink>

      <NavLink
        className={({ isActive }) =>
          'room-nav-box' + (isActive ? ' room-nav-click' : '')
        }
        to={`/rooms/${roomUser}/${roomId}/guests`}
      >
        Guest
      </NavLink>
      <NavLink
        className={({ isActive }) =>
          'room-nav-box' + (isActive ? ' room-nav-click' : '')
        }
        to={`/rooms/${roomUser}/${roomId}/follow`}
      >
        Friends
      </NavLink>
      <NavLink
        className={({ isActive }) =>
          'room-nav-box' + (isActive ? ' room-nav-click' : '')
        }
        to={`/rooms/${roomUser}/${roomId}/setting`}
      >
        Setting
      </NavLink>

      <NavLink className="room-nav-box" to="/">
        <CancelPresentationIcon />
      </NavLink>
    </nav>
  );
}
export default RoomNav;
