package com.roomy.dto;

import lombok.*;

import java.util.List;


@ToString
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@Getter @Builder
public class RecentBoardAndGuestDTO {

    private List<BoardDTO> boardList;
    private List<BoardDTO> GalleryList;
    private List<GuestDTO> guestList;

    private RecentBoardAndGuestDTO(List<BoardDTO> boardList, List<BoardDTO> galleryList, List<GuestDTO> guestList) {
        this.boardList = boardList;
        GalleryList = galleryList;
        this.guestList = guestList;
    }

    public static RecentBoardAndGuestDTO RecentBoardAndGuest(List<BoardDTO> boardList, List<BoardDTO> galleryList, List<GuestDTO> guestList){

        return new RecentBoardAndGuestDTO(boardList,galleryList,guestList);
    }
}
