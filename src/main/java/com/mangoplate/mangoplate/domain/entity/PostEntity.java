package com.mangoplate.mangoplate.domain.entity;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name="album", schema="photo_album", uniqueConstraints = {@UniqueConstraint(columnNames = "post_id")})
public class PostEntity {

    @Id
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


}
