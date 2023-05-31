package com.mangoplate.mangoplate.service;


import com.mangoplate.mangoplate.domain.entity.Post;
import com.mangoplate.mangoplate.domain.request.PostRequest;
import com.mangoplate.mangoplate.domain.response.PostResponse;
import com.mangoplate.mangoplate.domain.type.ErrorCode;
import com.mangoplate.mangoplate.exception.ApplicationException;
import com.mangoplate.mangoplate.repository.PostRepository;
import com.mangoplate.mangoplate.utill.JwtTokenUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class PostService {


    private final PostRepository postRepository;

    @Value("${jwt.secret-key}")
    private String secretKey;


    public PostResponse get_post(PostRequest request) {
//        String userId = JwtTokenUtils.getUserId(request.token(),secretKey);

        Post res = postRepository.findByPostCode(request.postCode()).orElseThrow(() -> {
            throw new ApplicationException(ErrorCode.NO_POST);
        });


        return new PostResponse(res.getPostCode(), res.getRestaurantName(),
                res.getRestaurantAddress(), res.getPhoneNum(),
                res.getFoodCategory(), res.getPrice(),
                res.getParking(), res.getBusinessTime(), res.getBreakTime(), res, res.getBreakDay(), res.getWebsiteUrl(), res.getMenu());


    }

    public void write_post(PostRequest request){
//        String userId = JwtTokenUtils.getUserId(request.token(), secretKey);
        if(request.postCode() == null || request.postCode().isEmpty()){
            throw new ApplicationException(ErrorCode.NO_POSTCODE);
        }else if(request.restaurantAddress() == null || request.restaurantAddress().isEmpty()) {
            throw new ApplicationException(ErrorCode.NO_SHOP_ADDRESS);
        }else if(request.restaurantName() == null || request.restaurantName().isEmpty()) {
            throw new ApplicationException(ErrorCode.NO_SHOP_NAME);
        }
        else if(request.foodCategory()== null){
            throw new ApplicationException(ErrorCode.NO_CATEGORY);
        }
        else if(request.menu() == null || request.menu().isEmpty()){
            throw new ApplicationException(ErrorCode.NO_MENU);
        }else if(request.price() == null || request.price().isEmpty()){
            throw new ApplicationException(ErrorCode.NO_PRICE);
        }
        Post post = Post.getEntity(request.postCode(),
                request.restaurantName(), request.restaurantAddress(), request.phoneNum(),
                request.foodCategory(), request.price(), request.parking(), request.businessTime(), request.breakTime(),
                request.breakDay(), request.websiteUrl(), request.menu()
        );
        postRepository.save(post);

    }

    public PostResponse change_post(PostRequest request) {
//        String userId = JwtTokenUtils.getUserId(request.token(),secretKey);

        Post res = postRepository.findByPostCode(request.postCode()).orElseThrow(() -> {
            throw new ApplicationException(ErrorCode.NO_POST);
        });

        if(request.postCode() == null || request.postCode().isEmpty()){
            throw new ApplicationException(ErrorCode.NO_POSTCODE);
        }else if(request.restaurantAddress() == null || request.restaurantAddress().isEmpty()) {
            throw new ApplicationException(ErrorCode.NO_SHOP_ADDRESS);
        }else if(request.restaurantName() == null || request.restaurantName().isEmpty()) {
            throw new ApplicationException(ErrorCode.NO_SHOP_NAME);
        }
        else if(request.foodCategory()== null){
            throw new ApplicationException(ErrorCode.NO_CATEGORY);
        }
        else if(request.menu() == null || request.menu().isEmpty()){
            throw new ApplicationException(ErrorCode.NO_MENU);
        }else if(request.price() == null || request.price().isEmpty()){
            throw new ApplicationException(ErrorCode.NO_PRICE);
        }

        Post updatePost = res;
        //System.out.println(updatePost.getRestaurantName());


        updatePost.setRestaurantName(request.restaurantName());
        updatePost.setRestaurantAddress(request.restaurantAddress());
        updatePost.setPhoneNum(request.phoneNum());
        updatePost.setFoodCategory(request.foodCategory());
        updatePost.setPrice(request.price());
        updatePost.setParking(request.parking());
        updatePost.setBusinessTime(request.businessTime());
        updatePost.setBreakTime(request.breakTime());
        updatePost.setBreakDay(request.breakDay());
        updatePost.setWebsiteUrl(request.websiteUrl());
        updatePost.setMenu(request.menu());

        postRepository.save(updatePost);



        return new PostResponse(updatePost.getPostCode(), updatePost.getRestaurantName(), updatePost.getRestaurantAddress(), updatePost.getPhoneNum(),
                updatePost.getFoodCategory(), updatePost.getPrice(), updatePost.getParking(), updatePost.getBusinessTime(), updatePost.getBreakTime(),
                updatePost.getBreakDay(), updatePost.getWebsiteUrl(), updatePost.getMenu());

    }

    public  void delete_post(PostRequest request){
        Post res = postRepository.findByPostCode(request.postCode()).orElseThrow(() -> {
            throw new ApplicationException(ErrorCode.NO_POST);
        });
//        JwtTokenUtils.getUserId(request.token(), secretKey);

        Post post = res;

        postRepository.deleteById(post.getPostId());

    }
}
