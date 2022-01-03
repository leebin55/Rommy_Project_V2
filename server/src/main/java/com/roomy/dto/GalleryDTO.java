package com.roomy.dto;

import com.roomy.model.othertype.BoardStatus;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

/**
 * gallery를 등록하거나 수정 등등을 할때 Controller 에서 데이터를 파라미터로 받아오고
 * Client 로 보낼때 사용할 DTO
 * => 사용 이유 : 모든 요청은 똑같은 요구사항 을 하지 않고
 * 각 요청마다 별도의 필요한 스펙이 존재
 * 해당 dto를 보면 요청에 필요한 요구사항을 알수 있음
 * 또한 엔티티는 서로 양방향관계이기 때문에 무한루프가 발생할 수 있어
 * 다른 처리를 해줘야 한다. > 서로다른 요청이 들어올때마다 엔티티를 변경해야됨*/
@Getter@Setter@ToString
public class GalleryDTO {


    private Long boardSeq;
    private String username;
    private String title;
    private String content;
    private String createDate;
    private String updateDate;
    private int likeCount;
    private BoardStatus status;
    private List<ImgDTO> imgs;


}
