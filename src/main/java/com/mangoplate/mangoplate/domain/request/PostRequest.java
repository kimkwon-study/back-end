package com.mangoplate.mangoplate.domain.request;

import com.mangoplate.mangoplate.domain.entity.Post;
import com.mangoplate.mangoplate.domain.type.Menu;


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
        Menu menu
) {

    public Post getEntity(){
                   return Post.getEntity(postId,restaurantName,restaurantAddress,phoneNum,
                    foodCategory,price, parking,businessTime,breakTime, breakDay,websiteUrl,menu);
    }
}
