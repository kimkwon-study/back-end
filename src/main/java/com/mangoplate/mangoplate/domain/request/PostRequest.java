package com.mangoplate.mangoplate.domain.request;

import com.mangoplate.mangoplate.domain.type.Menu;


public record PostRequest(
        String token,
        String restaurantName,
        String restaurantAddress,
        String phoneNum,
        Menu foodCategory,
        String price,
        Boolean parking,
        String businessTime,
        String breakTime,
        String breakDay,
        String websiteUrl,
        String menu
) {

}
