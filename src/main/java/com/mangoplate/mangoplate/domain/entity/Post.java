package com.mangoplate.mangoplate.domain.entity;


import com.mangoplate.mangoplate.domain.type.Menu;
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
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "post_id", unique = true, nullable = false)
    private Long postId;

    @Column(name = "post_code", unique = true, nullable = false)
    private String postCode;

    @Column
    private String restaurantName;

    @Column
    private String restaurantAddress;

    @Column
    private String phoneNum;

    @Column
    @Enumerated
    private Menu foodCategory;

    @Column
    private String price;

    @Column
    private Boolean parking;

    @Column
    private String businessTime;

    @Column
    private String breakTime;

    @Column
    private String breakDay;

    @Column
    private String websiteUrl;


    @Column
    private List <String> menu;

    @Column
    private Timestamp registeredAt;
    @Column
    private Timestamp updatedAt;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "post" ,cascade = CascadeType.ALL)
    private List<Review> reviewList;

    @ManyToOne(fetch = FetchType.LAZY )
    @JoinColumn(name="user_id")
    private User user;

    @PrePersist
    void registeredAt() {
        this.registeredAt = Timestamp.from(Instant.now());
    }

    @PreUpdate
    void updatedAt() {
        this.updatedAt = Timestamp.from(Instant.now());
    }


    protected Post(){}

    private Post(String postCode, String restaurantName, String restaurantAddress, String phoneNum,
                 Menu foodCategory, String price, Boolean parking, String businessTime, String breakTime,
                 String breakDay, String websiteUrl, List<String> menu){

        //this.postId = postId;
        this.postCode = postCode;
        this.restaurantName = restaurantName;
        this.restaurantAddress = restaurantAddress;
        this.phoneNum = phoneNum;
        this.foodCategory = foodCategory;
        this.price = price;
        this.parking = parking;
        this.businessTime = businessTime;
        this.breakTime = breakTime;
        this.breakDay = breakDay;
        this.websiteUrl = websiteUrl;
        this.menu = menu;


    }

    public static Post getEntity(  String postCode,String restaurantName, String restaurantAddress, String phoneNum,
                                 Menu foodCategory, String price, Boolean parking, String businessTime, String breakTime,
                                 String breakDay, String websiteUrl, List<String> menu){

        return new Post(postCode,restaurantName,restaurantAddress,phoneNum,
                foodCategory,price, parking,businessTime,breakTime, breakDay,websiteUrl,menu);

    }

//    public int getReviewCount() {
//        // 데이터베이스에서 리뷰 개수를 조회하는 쿼리 실행
//        // 예를 들어, JPA의 EntityManager를 사용하여 쿼리를 실행하는 방법:
//        EntityManager entityManager = ...; // EntityManager 생성
//        Query query = entityManager.createQuery("SELECT COUNT(r) FROM reviewEntity r WHERE r.postEntity = :post");
//        query.setParameter("post", this);
//        Long count = (Long) query.getSingleResult();
//        return count.intValue();
//    }


}
