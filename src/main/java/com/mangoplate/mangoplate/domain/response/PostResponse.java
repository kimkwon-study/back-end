package com.mangoplate.mangoplate.domain.response;

import com.mangoplate.mangoplate.domain.type.Menu;

public record PostResponse(
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
