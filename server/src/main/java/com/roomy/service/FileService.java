package com.roomy.service;

import com.roomy.entity.BoardImage;
import com.roomy.entity.Board;
import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Set;

public interface FileService {

    public Set<String> uploadFiles(List<MultipartFile> files);

    public void deleteFile(Long imgSeq);

    //public List<String> uploadMultiFiles(MultipartHttpServletRequest files);

    List<Board> selectAllWithImage(List<Board> boardList);

    public Resource loadFileAsResource(String fileName);

    void insert(BoardImage imageVO);
}
