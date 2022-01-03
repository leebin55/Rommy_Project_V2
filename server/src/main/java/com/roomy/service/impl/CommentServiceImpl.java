package com.roomy.service.impl;

import com.roomy.dto.CommentDTO;
import com.roomy.service.CommentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Slice;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Slf4j
@Transactional
public class CommentServiceImpl implements CommentService {
    @Override
    public Long saveComment(CommentDTO comment) {
        return null;
    }

    @Override
    public Long updateComment(CommentDTO comment) {
        return null;
    }

    @Override
    public Long deleteComment(Long commentSeq) {
        return null;
    }

    @Override @Transactional(readOnly = true)
    public Slice<CommentDTO> getUserCommentList(Long userId) {
        return null;
    }

    @Override @Transactional(readOnly = true)
    public Page<CommentDTO> getBoardCommentList(Long boardSeq) {
        return null;
    }
}
