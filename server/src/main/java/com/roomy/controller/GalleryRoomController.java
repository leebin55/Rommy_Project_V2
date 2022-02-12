package com.roomy.controller;

import com.roomy.dto.BoardDTO;
import com.roomy.service.GalleryService;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/rooms")
public class GalleryRoomController {

    private final GalleryService galleryService;

    @GetMapping("/{username}/{roomId}/gallerys")
    public ResponseEntity<?> list(@PathVariable("username")String username, @PathVariable("roomId") Long roomId, Pageable pageable) {
        return null;
    }

}
