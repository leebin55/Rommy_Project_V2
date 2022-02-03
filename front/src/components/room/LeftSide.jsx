import React, { useEffect, useState } from 'react';
import '../../css/room/Room.css';
import axiosInstance from '../../utils/AxiosInstance';
import FollowSelectBox from './FollowSelectBox';
import returnFileURL from '../../utils/FileUtils';
import { useParams } from 'react-router-dom';

function LeftSide({ roomData, followingList }) {
  // select Box (follow 가 기본값으로 설정)> followSelect 가 false면 follower가 select
  const [followSelect, setFollowSelect] = useState('true');
  const [followList, setFollowList] = useState([]);
  const [followerList, setFollowerList] = useState([]);
  const [selectBoxMenu, setSelectBoxMenu] = useState([]); // selectBox Menu List
  /** userInfo를 넘겨 받지만 처음 랜더링 할때는 userInfo에 아직 값이 안담겨 있음 > LeftSide의 return 문에서는 사용가능
   * 그전에는 아직 값이 안담겨 있음> userParam을 이용해서 가져옴
   */
  //   const userId = userInfo.userId;

  //selectBox 위의 follow 나 follwer 버튼 클릭
  const selectBtnClick = (event) => {
    const btnValue = event.target.value;
    console.log(event.target.value);
    // followSelect 가 false 가 아닐때 (follow로 설정되어 있을때)follower 버튼 클릭
    if (followSelect !== false && btnValue === 'follower') {
      console.log('follower실행');
      setSelectBoxMenu(followerList);
      setFollowSelect(false);
      return;
    }
    if (followSelect === false && btnValue === 'follow') {
      //followSelect 가 false 일때 follow 버튼 클릭(followSelect가 true일때 누르면 원래 followList가 selectMenu이므로 변화 없게)
      setSelectBoxMenu(followList);
      setFollowSelect(true);
      return;
    }
  };

  return (
    <div className="leftside-container">
      <div className="leftside-profile-container">
        {roomData.profile ? (
          <>
            <img src={returnFileURL(roomData.profile)} alt="user_profile" />
          </>
        ) : (
          <>
            <img src="/img/default_profile.png" alt="default_user_profile" />
          </>
        )}
      </div>
      <div className="leftside-room-intro">
        <p>{roomData.intro}</p>
      </div>
      <div className="leftside-room-username">
        <h3>{roomData.username} 님</h3>
      </div>
      <div className="leftside-room-friend-select">
        <div className="leftside-room-friend-select-btns">
          <button
            id={followSelect ? 'select-btn-active' : ''}
            onClick={selectBtnClick}
            value="follow"
          >
            follow
          </button>
          <button
            id={followSelect ? '' : 'select-btn-active'}
            onClick={selectBtnClick}
            value="follower"
          >
            follower
          </button>
        </div>
        <FollowSelectBox selectBoxMenu={followingList} />
      </div>
    </div>
  );
}

export default LeftSide;
