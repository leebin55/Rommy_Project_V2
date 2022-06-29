package com.roomy.service.impl;

import com.roomy.entity.Image;
import com.roomy.entity.Board;
import com.roomy.exception.FileException;
import com.roomy.repository.ImgRepository;
import com.roomy.service.FileService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.*;


@Slf4j
@Service
public class FileServiceImpl implements FileService {


    private final ImgRepository fileRepository;

    public FileServiceImpl(ImgRepository fileRepository) {
        this.fileRepository = fileRepository;
    }

    @Value("${file.upload-dir}")
    private String fileLocation;


    @Override
    public String uploadFile(MultipartFile file) {
        if(file.isEmpty()){
            return null;
        }
        File dir = new File(fileLocation);
        if(!dir.exists()){
            log.debug("파일 경로 없음");
            dir.mkdirs();
        }
            String strUUID = UUID.randomUUID().toString();
            String originalFileName = file.getOriginalFilename();
            String newFileName = String.format("%s-%s", strUUID, originalFileName);
            File uploadFile = new File(fileLocation,newFileName);
            try{
                file.transferTo(uploadFile);
            }catch (IllegalStateException | IOException e)  {
                new FileException("파일을 저장할 수 없습니다.");
            }
        return newFileName;
    }

    @Override
    public Set<String> uploadFiles(List<MultipartFile> files) {
        return null;
    }


    @Override
    public void deleteFile(Long imgSeq) {

    }


    public void insertImages (List<String> imgURLs,Long board_seq){
        for(String image:imgURLs){
            Image imageVO= Image.builder().imgUrl(image).build();
          //  imageVO.setImgBoardSeq(board_seq);
            fileRepository.save(imageVO);
        }
    }
    @Override
    public List<Board> selectAllWithImage(List<Board> boardList){
        // 새로운 리스트를 생성 => tbl_board 에는 이미지에 관한 정보가 없기 때문에
        List<Board> boardWithImgList = new ArrayList<>();
        for(Board board:boardList){
            // board_seq 를 뽑아와서
            Long board_seq =board.getBoardSeq();
            // 해당 개시물에 있는 image 를 tbl_board_image 테이블에서 imgURL들을 받아온다
//            List<String>imgURL=fileRepository.findByImgBoardSeq(board_seq);
            // 이미지는 하나만 보여줄 거기 때문에 맨 처음 하나만  board 객체에 담음
          //  board.setImgURL(Collections.singletonList(imgURL.get(0)));
            // 리스트에 넣어줌
            boardWithImgList.add(board);
        }
        //새로만든 리스트 리턴
        return boardWithImgList;
    }

    @Override
    public Resource loadFileAsResource(String fileName) {
        return null;
    }

    @Override
    public void insert(Image imageVO) {
        fileRepository.save(imageVO);
    }
}


