package com.roomy.repository;

import com.roomy.model.CommentVO;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository extends JpaRepository<CommentVO,Long> {
}
