package com.springreport.springreport.post.repository;

import com.springreport.springreport.post.domain.Post;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.ArrayList;
import java.util.Optional;

public interface PostRepository extends JpaRepository<Post,Long> {
    Optional<Post> findById(Long id);
    ArrayList<Post> findAllByOrderByRegDateDesc();
}
