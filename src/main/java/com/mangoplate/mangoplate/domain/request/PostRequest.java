package com.mangoplate.mangoplate.domain.request;

import com.mangoplate.mangoplate.domain.entity.PostEntity;


public record PostRequest(
        Long postId,
        String restaurantName,
        String restaurantAddress,
        String phoneNum,
        String foodCategory,
        String price,
        Boolean parking,
        String businessTime,
        String breakTime,
        String breakDay,
        String websiteUrl,
        String menu
) {

    public  PostEntity getEntity(){
                   return PostEntity.getEntity(postId,restaurantName,restaurantAddress,phoneNum,
                    foodCategory,price, parking,businessTime,breakTime, breakDay,websiteUrl,menu);
    }
}
