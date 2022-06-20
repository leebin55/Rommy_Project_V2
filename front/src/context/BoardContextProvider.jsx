import React, { createContext, useContext, useState } from 'react';

const AppContext = createContext();
export const useBoardContext = () => useContext(AppContext);

function BoardContextProvider({ children }) {
  const [boardState, setBoardState] = useState({
    list: true,
    update: false,
  });

  const toBoardPage = (state) => {
    if (state === 'list') {
      setBoardState({
        list: true,
        update: false,
      });
    } else if (state === 'write') {
      setBoardState({
        list: false,
        update: false,
      });
    } else if (state === 'update') {
      setBoardState({
        list: false,
        update: true,
      });
    }
  };
  const propsData = {
    boardState,
    toBoardPage,
  };

  return (
    <AppContext.Provider value={propsData}>{children}</AppContext.Provider>
  );
}

export default BoardContextProvider;
