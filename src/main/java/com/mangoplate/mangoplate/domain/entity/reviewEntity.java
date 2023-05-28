package com.mangoplate.mangoplate.domain.entity;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import java.sql.Date;
import java.sql.Timestamp;

@Getter
@Setter
@Entity
@Table(name="review",  uniqueConstraints = {@UniqueConstraint(columnNames = "post_id")})

public class reviewEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "review_id", unique = true, nullable = false)
    private Long reviewId;

    @Column
    @CreationTimestamp
    private Date uploadAt;

    @Column
    private String content;

    @Column
    private String imageUrl;

    @ManyToOne(fetch = FetchType.LAZY )
    @JoinColumn(name="post_id")
    private PostEntity postEntity;



    private  reviewEntity(){}

    private  reviewEntity(Long reviewId, Date uploadAt, String content, String imageUrl, PostEntity postEntity ){
        this.reviewId = reviewId;
        this.uploadAt = uploadAt;
        this.content = content;
        this.imageUrl = imageUrl;
        this.postEntity = postEntity;
    }

    public static reviewEntity getEntity(Long reviewId, Date uploadAt, String content, String imageUrl){

        reviewEntity reviewEntity = new reviewEntity();
        reviewEntity.setReviewId(reviewId);
        reviewEntity.setUploadAt(uploadAt);
        reviewEntity.setContent(content);
        reviewEntity.setImageUrl(imageUrl);


        return reviewEntity;
    }


}
