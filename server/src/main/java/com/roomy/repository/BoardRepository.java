package com.roomy.repository;

import com.roomy.model.board.Board;
import com.roomy.model.board.BoardVO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface BoardRepository extends JpaRepository<Board,Long> {



}