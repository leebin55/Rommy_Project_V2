import React from 'react';

import { useNavigate, useParams } from 'react-router-dom';
// import { useTodoContext } from "../../../context/TodoContextProvider";

function TodoMain() {
  const navigate = useNavigate();
  const { userId } = useParams();

  const onClick = () => {
    navigate('/');
  };
  return (
    <div className="mainContainer">
      <div className="headerContainer">
        <h1 onClick={onClick}>TODO LIST</h1>

        <p>write down all the things to do!</p>
      </div>
      <div className="formContainer"></div>
      <div className="listContainer"></div>
    </div>
  );
}

export default TodoMain;
