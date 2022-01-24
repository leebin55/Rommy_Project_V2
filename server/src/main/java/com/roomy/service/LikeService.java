package com.roomy.service;


import com.roomy.dto.LikeDTO;
import com.roomy.entity.Like;
import org.springframework.data.domain.Slice;

public interface LikeService {

    // userSeq 로 조회> 자신이 좋아요한 게시물 볼수 있게
    Slice<Like> getUserLikeSlice(Long userId);

    void likeOrUnLike(LikeDTO likeDto);

    Boolean checkLike(LikeDTO likeDTO);

    //board 와 user seq 로 해당 데이터가 tbl_like에 있는지 확인
  //  Boolean likeCheck(LikeVO likeVO);

    //좋아요 테이블에 해당 데이터를 조회하고 그에 따른 insert 또는 delete 실행
    // 좋아요 수 리턴
   // int insertOrDelete(LikeVO likeVO);


}