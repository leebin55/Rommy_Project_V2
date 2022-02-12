package com.roomy.service.impl;

import com.roomy.dto.BoardDTO;
import com.roomy.dto.GalleryDTO;
import com.roomy.entity.Board;
import com.roomy.repository.BoardRepository;
import com.roomy.service.GalleryService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class GalleryServiceImpl implements GalleryService {

    private final BoardRepository boardRepository;

    @Override
    public Long saveGallery(GalleryDTO gallery) {
        return null;
    }

    @Override
    public Long updateGallery(GalleryDTO gallery) {
        return null;
    }

    @Override
    public void deleteBoard(Long boardSeq) {

    }

    @Override
    public BoardDTO findById(Long boardSeq) {
        return null;
    }

    @Override
    public List<BoardDTO> getBoardList() {
        return null;
    }

    @Override
    public List<Board> search(String select, String query) {
        return null;
    }

    @Override
    public List<Board> selectAllByUserId(String userId) {
        return null;
    }
}
