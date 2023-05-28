package com.mangoplate.mangoplate.domain.entity;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Entity
@Table(name="post", uniqueConstraints = {@UniqueConstraint(columnNames = "post_id")})
public class PostEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "post_id", unique = true, nullable = false)
    private String postId;

    @Column
    private String restaurantName;

    @Column
    private String restaurantAddress;

    @Column
    private String phoneNum;

    @Column
    private String foodCategory;

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
    private String menu;

    @OneToMany(fetch = FetchType.LAZY,mappedBy = "post",cascade = CascadeType.ALL)
    private List<reviewEntity> reviewEntityList;



    private PostEntity(){}

    private PostEntity(String postId,String restaurantName,String restaurantAddress,String phoneNum,
                       String foodCategory,String price,Boolean parking,String businessTime,String breakTime,
                       String breakDay,String websiteUrl,String menu){

        this.postId = postId;
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

    public static PostEntity getEntity(String postId,String restaurantName,String restaurantAddress,String phoneNum,
                       String foodCategory,String price,Boolean parking,String businessTime,String breakTime,
                       String breakDay,String websiteUrl,String menu){

        return new PostEntity(postId,restaurantName,restaurantAddress,phoneNum,
                foodCategory,price, parking,businessTime,breakTime, breakDay,websiteUrl,menu)

    }


}
