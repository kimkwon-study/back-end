package com.mangoplate.mangoplate.domain.response;

import com.mangoplate.mangoplate.domain.type.Menu;

import java.awt.*;
import java.util.List;

public record PostResponse(

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
