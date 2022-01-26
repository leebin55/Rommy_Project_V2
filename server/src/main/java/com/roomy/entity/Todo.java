package com.roomy.entity;

import lombok.*;
import org.hibernate.annotations.ColumnDefault;

import javax.persistence.*;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Builder
@Getter
@Entity
@Table(name = "tbl_todo")
public class Todo extends BaseEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @Column(name = "todo_ok", columnDefinition = "boolean default false")
    private Boolean ok;

    @Column(name = "todo_content", columnDefinition = "VARCHAR(125)")
    private String content;

    @Column(name = "todo_important", columnDefinition = "int")
    @ColumnDefault("0")
    private Integer important;

    @Column(name = "todo_user_id", columnDefinition = "VARCHAR(20)")
    private String userId;



//    @Builder
//    public Todo(String content, LocalDateTime createdAt) {
//        this.content = content;
//        this.createdAt = createdAt;
//    }
}