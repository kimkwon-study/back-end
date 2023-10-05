package com.mangoplate.mangoplate.repository;


import com.mangoplate.mangoplate.domain.entity.Review;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;


public interface ReviewRepository extends JpaRepository <Review,Long> {

    Optional<Review> findByReviewCode(String reviewCode);
}
