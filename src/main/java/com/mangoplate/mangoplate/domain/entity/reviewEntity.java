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
    private String reviewId;

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

    private  reviewEntity(String reviewId, Date uploadAt, String content, String imageUrl){
        this.reviewId = reviewId;
        this.uploadAt = uploadAt;
        this.content = content;
        this.imageUrl = imageUrl;
    }

    public static reviewEntity getEntity(String reviewId, Date uploadAt, String content, String imageUrl){
        return new reviewEntity(reviewId, uploadAt, content, imageUrl);
    }


}
