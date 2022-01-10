package com.roomy.service;

import com.roomy.model.BoardImage;
import com.roomy.model.Board;
import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface FileService {

    public String uploadFile(MultipartFile file);

    public void deleteFile(Long imgSeq);

    //public List<String> uploadMultiFiles(MultipartHttpServletRequest files);

    List<Board> selectAllWithImage(List<Board> boardList);

    public Resource loadFileAsResource(String fileName);

    void insert(BoardImage imageVO);
}
