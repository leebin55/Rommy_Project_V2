package com.roomy.service.impl;

import com.roomy.model.BoardVO;

import com.roomy.repository.BoardRepository;
import com.roomy.repository.FileRepository;
import com.roomy.service.BoardService;
import com.roomy.service.FileService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service("galleryService")
public class GalleryServiceImpl implements BoardService{


    private final BoardRepository boardRepository;
    private final FileService fileService;

    public GalleryServiceImpl(BoardRepository boardRepository
            , FileService fileService) {
        this.boardRepository = boardRepository;

        this.fileService = fileService;
    }

    @Override
    public List<BoardVO> search(String select, String query) {
        return null;
    }

    @Override
    public List<BoardVO> selectAllByUserId(String userId) {

        return boardRepository.findAllByBoardCodeAndUserIdOrderByBoardSeqDesc(1,userId);
    }



    @Override
    public List<BoardVO> selectAll() {

        return boardRepository.findAllByBoardCode(1);
    }

    @Override
    public BoardVO findById(Long userId) {
        return null;
    }

    @Override
    public void insert(BoardVO boardVO) {

    }

    @Override
    public void update(BoardVO boardVO) {

    }


    @Override
    public void delete(Long aLong) {

    }
}
