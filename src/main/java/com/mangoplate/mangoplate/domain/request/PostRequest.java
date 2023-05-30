package com.mangoplate.mangoplate.domain.request;

import com.mangoplate.mangoplate.domain.type.Menu;

import java.util.List;


public record PostRequest(
        Long postId,
        String postCode,
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
        List<String> menu
) {

}
