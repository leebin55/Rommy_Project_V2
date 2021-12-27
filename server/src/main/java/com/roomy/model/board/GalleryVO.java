package com.roomy.model.board;

import com.roomy.model.BoardImageVO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.CascadeType;
import javax.persistence.DiscriminatorValue;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@ToString
@DiscriminatorValue("G")
public class GalleryVO extends Board{

    //BoardImg 테이블에 있는 board 필드에 의해 매칭
    @OneToMany(mappedBy = "board")
    private List<BoardImageVO> boardImgs = new ArrayList<>();

}
