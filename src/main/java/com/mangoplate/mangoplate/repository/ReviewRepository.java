package com.mangoplate.mangoplate.repository;

import com.mangoplate.mangoplate.domain.entity.Post;
import com.mangoplate.mangoplate.domain.entity.Review;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReviewRepository extends JpaRepository<Review,Long> {
}
