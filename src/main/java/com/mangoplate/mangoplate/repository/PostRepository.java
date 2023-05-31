package com.mangoplate.mangoplate.repository;

import com.mangoplate.mangoplate.domain.entity.Post;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


import java.util.Optional;


public interface PostRepository extends JpaRepository<Post,Long> {
    Optional<Post> findByPostCode(String postCode);
}
