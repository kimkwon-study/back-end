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

        Post res = postRepository.findByRestaurantName(request.restaurantName()).orElseThrow(() -> {
            throw new ApplicationException(ErrorCode.NO_POST);
        });

        return new PostResponse( res.getRestaurantName(), res.getRestaurantAddress(), res.getPhoneNum(),
                res.getFoodCategory(), res.getPrice(), res.getParking(), res.getBusinessTime(), res.getBreakTime(),
                res.getBreakDay(), res.getWebsiteUrl(), res.getMenu());

    }

    public void write_post(PostRequest request){
//        String userId = JwtTokenUtils.getUserId(request.token(), secretKey);

        Post post = Post.getEntity(
                request.restaurantName(), request.restaurantAddress(), request.phoneNum(),
                request.foodCategory(), request.price(), request.parking(), request.businessTime(), request.breakTime(),
                request.breakDay(), request.websiteUrl(), request.menu()
        );


        postRepository.save(post);

    }

    public  void delete_post(PostRequest request){
        Post res = postRepository.findByRestaurantName(request.restaurantName()).orElseThrow(() -> {
            throw new ApplicationException(ErrorCode.NO_POST);
        });
//        JwtTokenUtils.getUserId(request.token(), secretKey);

        com.mangoplate.mangoplate.domain.entity.Post post = res;

        postRepository.deleteById(post.getPostId());

    }
}
