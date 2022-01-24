package com.roomy.dto;

import lombok.*;

import java.util.List;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@Getter @Builder
public class RecentBoardAndGuestDTO {

    private List<BoardDTO> boardList;
    private List<GalleryDTO> galleryList;
    private List<GuestDTO> guestList;
}
