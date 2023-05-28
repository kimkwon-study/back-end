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

    
}
