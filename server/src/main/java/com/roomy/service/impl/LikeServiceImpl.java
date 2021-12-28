package com.roomy.service.impl;

import com.roomy.model.BoardVO;
import com.roomy.model.LikeVO;
import com.roomy.repository.BoardRepository;
import com.roomy.repository.LikeRepository;
import com.roomy.service.LikeService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class LikeServiceImpl implements LikeService {

    private final LikeRepository likeRepository;
    private final BoardRepository boardRepository;

    @Override
    public List<LikeVO> findByUserSeq(String user_id) {
        List<LikeVO> likeVO = likeRepository.findAllByUserId(user_id);
        return likeVO;

    }


    // board seq  와 userSeq 로 체크를눌렀는지 확인
    @Override
    public Boolean likeCheck(LikeVO likeVO) {
        String user_id = likeVO.getUserId();
        boolean checkExist = likeRepository
                .existsByBoardAndUserId(likeVO.getBoard(),user_id);
        log.debug("exists: {}", checkExist);

        return checkExist;
    }

    @Override
    public int insertOrDelete(LikeVO likeVO){
        // 데이터가 이미 존재 > 데이터 삭제후 좋아요 수 리턴
        Boolean checkExist = likeCheck(likeVO);
        if(checkExist==true){
            return delete(likeVO);
        }else{
            // 데이터 존재하지 않음 > 데이터 Insert후 좋아요수 리턴
            return insert(likeVO);
        }
    }



    private int insert(LikeVO likeVO) {
        // tbl_board_like 테이블에 데이터 저장
        likeRepository.save(likeVO);
return 0;
    }


    private int delete(LikeVO likeVO){

        // 좋아요 데이터 삭제
        likeRepository.deleteByUserIdAndBoard(likeVO.getUserId(),likeVO.getBoard());
        // 게시물에서 board_seq 로 해당 게시물 찾기

       // return countLike;
return 0;
    }





}

