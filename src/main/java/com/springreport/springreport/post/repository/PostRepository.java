package com.springreport.springreport.post.repository;

import com.springreport.springreport.post.domain.Post;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<Post,Long> {
}
