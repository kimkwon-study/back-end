package com.mangoplate.mangoplate.domain.entity;


import com.mangoplate.mangoplate.domain.type.Menu;
import com.mangoplate.mangoplate.domain.type.TasteRating;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.List;

@Getter
@Setter
@Entity
@Table(uniqueConstraints = {@UniqueConstraint(columnNames = "post_id")})
public class Review {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "review_id", unique = true, nullable = false)
    private Long reviewId;

    @Column(name = "review_code", unique = true, nullable = false)
    private String reviewCode;

    @Column
    private String content;

    @Column
    private List<String> imageUrl;

    @Column
    @Enumerated
    private TasteRating taste;

    @ManyToOne(fetch = FetchType.LAZY,optional = false)
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

    private Review( String reviewCode,Timestamp registeredAt , Timestamp updatedAt, String content, List <String> imageUrl,TasteRating taste, Post post){
//        this.reviewId = reviewId;
        this.reviewCode = reviewCode;
        this.updatedAt = updatedAt;
        this.registeredAt = registeredAt;
        this.content = content;
        this.imageUrl = imageUrl;
        this.taste = taste;
        this.post = post;
    }

    public static Review getEntity(String reviewCode,Timestamp registeredAt , Timestamp updatedAt, String content, List <String> imageUrl, TasteRating taste ,Post post){

        Review review = new Review();
//        review.setReviewId(reviewId);

        review.setReviewCode(reviewCode);
        review.setUpdatedAt(updatedAt);
        review.setRegisteredAt(registeredAt);
        review.setContent(content);
        review.setImageUrl(imageUrl);
        review.setTaste(taste);
        review.setPost(post);


        return review;
    }


}
