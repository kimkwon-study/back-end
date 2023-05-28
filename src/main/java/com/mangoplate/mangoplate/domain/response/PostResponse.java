package com.mangoplate.mangoplate.domain.response;

public record PostResponse(
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

}
