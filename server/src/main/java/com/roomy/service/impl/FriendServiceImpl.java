package com.roomy.service.impl;

import com.roomy.dto.CheckFollowDTO;
import com.roomy.model.FollowVO;
import com.roomy.model.UserVO;
import com.roomy.repository.FollowRepository;
import com.roomy.repository.FollowerRepository;
import com.roomy.repository.UserRepository;
import com.roomy.service.FriendService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
public class FriendServiceImpl implements FriendService {

    private final FollowRepository followRepository;
    private final FollowerRepository followerRepository;
    private final UserRepository userRepository;

    public FriendServiceImpl(FollowRepository followRepository, FollowerRepository followerRepository, UserRepository userRepository) {
        this.followRepository = followRepository;
        this.followerRepository = followerRepository;
        this.userRepository = userRepository;
    }

    // 팔로우 (친구추가)
    // tbl_follow : 내가 친구 추가한 회원 데이터 테이블
    // tbl_follower : 나를 친구 추가한 회원 데이터 테이블 ( 누가 나를 팔로우 했나 알기 위해)
    @Override
    public void followFriend(FollowVO follow) {


    }

    // unFollow
    @Override
  //  @Transactional
    public void unfollowFriend(FollowVO followVO) {
    }
//    select * from tbl_follow where userid= and followUserId=
//    select * form tbl_follower where userid and followerUserId=
    // delete where followSeq 실행

    // 팔로우 조회 (내가 친구추가한 )
    @Override
    public List<String> findAllFollow(String userId) {

        //return  followRepository.findFollowListByUserId(userId);
        return null;
    }

    // 팔로워 조회 (나를 친구추가한)
    @Override
    public List<String> findAllFollower(String userId) {

        //return followerRepository.findFollowerListByUserId(userId);
        return null;
    }

    @Override
    public Boolean checkFollow(String userId, String checkFollowUserId) {

           // return followRepository.existsByUserIdAndAndFollowUserId(userId, checkFollowUserId);
        return true;

    }

    @Override
    public CheckFollowDTO checkFollowAndUser(String loggedUser, String roomUserId){

        CheckFollowDTO checkDTO = new CheckFollowDTO();

        if(loggedUser.equals(roomUserId)){
            // userId == checkFollowUserId
            checkDTO.setSameUser(true);

        }else {
            // 서로 다른 회원일경우
            Boolean existResult = this.checkFollow(loggedUser,roomUserId);
            checkDTO.setSameUser(false);
            checkDTO.setCheckFollow(existResult);
        }
        return checkDTO;
    }

    @Override
    public List<UserVO> findAllFollowWithUserInfo(String roomUserId) {
        List<UserVO> userInfoList= new ArrayList<>();

        List<String> followUserIdList=this.findAllFollow(roomUserId);
        log.debug("followUserIdList : {}",followUserIdList.toString());
        for(String userId : followUserIdList){
            UserVO userInfo = new UserVO();
            userInfo=userRepository.findById(userId).get();
           userInfoList.add(userInfo);
        }

        return userInfoList;
    }

    @Override
    public List<UserVO> findAllFollowerWithUserInfo(String roomUserId) {
        List<UserVO> userInfoList= new ArrayList<>();

        List<String> followerUserIdList=this.findAllFollower(roomUserId);
        log.debug("followerUserIdList : {}",followerUserIdList.toString());
        for(String userId : followerUserIdList){
            UserVO userInfo = new UserVO();
            userInfo=userRepository.findById(userId).get();
            userInfoList.add(userInfo);
        }
        return userInfoList;
    }


}