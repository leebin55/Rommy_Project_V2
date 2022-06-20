import React, { createContext, useContext, useState } from 'react';

const AppContext = createContext();
export const useLoginContext = () => useContext(AppContext);

function LoginContextProvider({ children }) {
  const [userProfile, setUserProfile] = useState('');

  const [check_login, setCheck_login] = useState(false);

  const [modal, setModal] = useState({
    login: false,
    join: false,
    find: false,
  });

  const loginClick = () => {
    setModal({ login: true, join: false, find: false });
  };
  const joinClick = () => {
    setModal({ login: false, join: true, find: false });
  };
  const findClick = () => {
    setModal({ login: false, join: false, find: true });
  };
  const deleteClick = () => {
    setModal({ login: false, join: false, find: false });
  };

  const data = {
    modal,
    setModal,
    check_login,
    setCheck_login,
    loginClick,
    joinClick,
    findClick,
    deleteClick,
    userProfile,
    setUserProfile,
  };

  return <AppContext.Provider value={data}>{children}</AppContext.Provider>;
}

export default LoginContextProvider;
