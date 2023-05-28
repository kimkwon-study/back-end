package com.mangoplate.mangoplate.domain.entity;


import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name="review",  uniqueConstraints = {@UniqueConstraint(columnNames = "post_id")})

public class reviewEntity {
}
