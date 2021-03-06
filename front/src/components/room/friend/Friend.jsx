import React, { useState, useEffect } from 'react';
import axiosInstance from '../../../utils/AxiosInstance';
import TableList from './TableList';
import { useParams } from 'react-router-dom';

function Friend() {
  const { roomUser, roomId } = useParams();
  // 처음 Friend 에 들어가면 followList를 기본으로 보이게 하기위해
  const [showFollowList, setShowFollowList] = useState(true);
  const [followList, setFollowList] = useState([]);
  const [followerList, setFollowerList] = useState([]);

  useEffect(() => {
    // Promise.all([getFollowUserList(), getFollowerUserList()]).then((res) => {
    //   setFollowList(res[0].data);
    //   setFollowerList(res[1].data);
    //   console.log('follow list : ', res[0].data);
    //   console.log('follower list : ', res[1].data);
    // });
  }, []);

  return (
    <div>
      <div className="friend-header">
        <h1>FOLLOWLIST</h1>
        <button>FOLLOWER 보기 </button>
      </div>
      <div className="friend-list-container">
        <TableList dataList={showFollowList ? followList : followerList} />
      </div>
    </div>
  );
}

export default Friend;
