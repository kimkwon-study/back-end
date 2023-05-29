package com.mangoplate.mangoplate.domain.entity;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;
import java.time.Instant;

@Getter
@Setter
@Entity
@Table(name="review",  uniqueConstraints = {@UniqueConstraint(columnNames = "post_id")})

public class ReviewEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "review_id", unique = true, nullable = false)
    private Long reviewId;


    @Column
    private String content;

    @Column
    private String imageUrl;

    @ManyToOne(fetch = FetchType.LAZY )
    @JoinColumn(name="post_id")
    private PostEntity postEntity;

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



    private ReviewEntity(){}

    private ReviewEntity(Long reviewId, Timestamp registeredAt , Timestamp updatedAt, String content, String imageUrl, PostEntity postEntity ){
        this.reviewId = reviewId;
        this.updatedAt = updatedAt;
        this.registeredAt = registeredAt;
        this.content = content;
        this.imageUrl = imageUrl;
        this.postEntity = postEntity;
    }

    public static ReviewEntity getEntity(Long reviewId, Timestamp registeredAt , Timestamp updatedAt, String content, String imageUrl, PostEntity postEntity){

        ReviewEntity reviewEntity = new ReviewEntity();
        reviewEntity.setReviewId(reviewId);
        reviewEntity.setUpdatedAt(updatedAt);
        reviewEntity.setRegisteredAt(registeredAt);
        reviewEntity.setContent(content);
        reviewEntity.setImageUrl(imageUrl);


        return reviewEntity;
    }


}