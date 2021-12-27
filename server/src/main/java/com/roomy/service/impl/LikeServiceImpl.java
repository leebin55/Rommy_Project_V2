package com.roomy.service.impl;

import com.roomy.model.board.BoardVO;
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
        return null;
    }

    @Override
    public Boolean likeCheck(LikeVO likeVO) {
        return null;
    }

    @Override
    public int insertOrDelete(LikeVO likeVO) {
        return 0;
    }

    @Override
    public int insert(LikeVO likeVO) {
        return 0;
    }

    @Override
    public int delete(LikeVO likeVO) {
        return 0;
    }
}

