package com.mangoplate.mangoplate.domain.request;

import com.mangoplate.mangoplate.domain.type.Menu;


public record PostRequest(
        String token,
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

<<<<<<< HEAD

=======
    public com.mangoplate.mangoplate.domain.entity.Post getEntity(){
                   return com.mangoplate.mangoplate.domain.entity.Post.getEntity(postId,restaurantName,restaurantAddress,phoneNum,
                    foodCategory,price, parking,businessTime,breakTime, breakDay,websiteUrl,menu);
    }
>>>>>>> 7268b8fba6b2841cc51fbb73bf99ce30eb193748
}
