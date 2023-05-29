package com.mangoplate.mangoplate.domain.entity;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;
import java.time.Instant;

@Getter
@Setter
@Entity
@Table(uniqueConstraints = {@UniqueConstraint(columnNames = "post_id")})
public class Review {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "review_id", unique = true, nullable = false)
    private Long reviewId;


    @Column
    private String content;

    @Column
    private String imageUrl;

    @ManyToOne(fetch = FetchType.LAZY,optional = false )
    @JoinColumn(name="post_id")
    private Post post;

    @Column
    private Timestamp registeredAt;
    @Column
    private Timestamp updatedAt;


    @PrePersist
    void registeredAt() {
        this.registeredAt = Timestamp.from(Instant.now());
    }

    @PreUpdate
    void updatedAt() {
        this.updatedAt = Timestamp.from(Instant.now());
    }



    protected Review(){}

    private Review(Long reviewId, Timestamp registeredAt , Timestamp updatedAt, String content, String imageUrl, Post post){
        this.reviewId = reviewId;
        this.updatedAt = updatedAt;
        this.registeredAt = registeredAt;
        this.content = content;
        this.imageUrl = imageUrl;
        this.post = post;
    }

    public static Review getEntity(Long reviewId, Timestamp registeredAt , Timestamp updatedAt, String content, String imageUrl, Post post){

        Review review = new Review();
        review.setReviewId(reviewId);
        review.setUpdatedAt(updatedAt);
        review.setRegisteredAt(registeredAt);
        review.setContent(content);
        review.setImageUrl(imageUrl);


        return review;
    }


}
