package com.springreport.springreport.repository;

import com.springreport.springreport.entity.Board;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BoardRepository extends JpaRepository<Board,Long> {
}
