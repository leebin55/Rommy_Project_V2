package com.roomy.service.impl;

import com.roomy.dto.LikeDTO;
import com.roomy.model.Board;
import com.roomy.model.Like;
import com.roomy.model.User;
import com.roomy.repository.BoardRepository;
import com.roomy.repository.LikeRepository;
import com.roomy.repository.UserRepository;
import com.roomy.service.LikeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Slice;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Slf4j @Transactional
public class LikeServiceImpl implements LikeService {

    private final LikeRepository likeRepository;
    private final UserRepository userRepository;
    private final BoardRepository boardRepository;

    public LikeServiceImpl(LikeRepository likeRepository, UserRepository userRepository, BoardRepository boardRepository) {
        this.likeRepository = likeRepository;
        this.userRepository = userRepository;
        this.boardRepository = boardRepository;
    }

    @Override @Transactional(readOnly = true)
    public Slice<Like> getUserLikeSlice(Long userId) {
        return null;
    }

    @Override
    public void likeOrUnLike(LikeDTO likeDto) {
        Boolean existCheck = checkLike(likeDto);
        if(existCheck != null){
            // user 와 board 모두 존재
            Optional<User> user = userRepository.findById(likeDto.getUsername());
            Optional<Board> board = boardRepository.findById(likeDto.getBoardSeq());
            if(existCheck == true){
                like(user.get(), board.get());
            }
        }

    }

    @Override @Transactional(readOnly = true)
    public Boolean checkLike(LikeDTO likeDto) {
        Optional<User> user = userRepository.findById(likeDto.getUsername());
        Optional<Board> board = boardRepository.findById(likeDto.getBoardSeq());
        if(user.isPresent() == true & board.isPresent() == true){
            return likeRepository.existsByBoardAndAndUser(board.get(),user.get());
        }
        // 회원 또는 보드 가 없으면
        return null;
    }

    private Long like(User user, Board board){
// tbl_like 에 Insert
        Like like= Like.builder().board(board).user(user).build();
            likeRepository.save(like);
            return likeRepository.countByBoard(board);
    }

    private Long unLike(User user, Board board){

        return null;
    }


}
