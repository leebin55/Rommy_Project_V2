package com.roomy.service;

import com.roomy.dto.CommentDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Slice;

public interface CommentService {

    Long saveComment(CommentDTO comment);

    Long updateComment(CommentDTO comment);

    Long deleteComment(Long commentSeq);

    // 해당 유저가 쓴 댓글 보기
    Slice<CommentDTO> getUserCommentList(Long userId);

    // 해당 게시글의 댓글
    Page<CommentDTO> getBoardCommentList(Long boardSeq);
}
