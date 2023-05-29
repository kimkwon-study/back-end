package com.mangoplate.mangoplate.domain.request;

import com.mangoplate.mangoplate.domain.entity.PostEntity;
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


}
