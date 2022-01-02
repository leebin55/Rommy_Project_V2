package com.roomy.service;

import com.roomy.dto.BoardDTO;
import com.roomy.dto.GalleryDTO;
import com.roomy.model.BoardVO;

import java.util.List;

public interface GalleryService {


    public Long saveGallery(GalleryDTO gallery);
    public Long updateGallry(GalleryDTO gallery);

    public  void deleteBoard(Long boardSeq);

    public BoardDTO findById(Long boardSeq);

    public List<BoardDTO> getBoardList();

    public List<BoardVO> search(String select, String query);

    public List<BoardVO> selectAllByUserId(String userId);

}