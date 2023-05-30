package com.mangoplate.mangoplate.repository;

import com.mangoplate.mangoplate.domain.entity.Post;
import org.springframework.data.domain.Example;
import org.springframework.data.jpa.repository.JpaRepository;


import java.util.Optional;


public interface PostRepository extends JpaRepository<Post,Long> {
    Optional<Post> findByPostCode(String postCode);
}
