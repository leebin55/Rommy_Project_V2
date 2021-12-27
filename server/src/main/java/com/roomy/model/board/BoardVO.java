package com.roomy.model.board;

import lombok.*;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.DynamicInsert;

import javax.persistence.*;
import java.util.List;

@ToString
@DiscriminatorValue("B")
public class BoardVO extends Board{


}
