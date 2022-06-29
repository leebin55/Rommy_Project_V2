package com.roomy.controller;

import com.roomy.service.FileService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;


@RequiredArgsConstructor
@RestController @Slf4j
public class FileController {

    private final FileService fileService;

    @PostMapping("/files")
    public ResponseEntity<?> uploadImgFile(@RequestParam MultipartFile img){
        return ResponseEntity.ok(fileService.uploadFile(img));
    }
}
