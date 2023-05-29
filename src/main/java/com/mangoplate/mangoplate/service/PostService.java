//package com.mangoplate.mangoplate.service;
//
//
//import com.mangoplate.mangoplate.domain.entity.PostEntity;
//import com.mangoplate.mangoplate.domain.request.PostRequest;
//import com.mangoplate.mangoplate.domain.response.PostResponse;
//import com.mangoplate.mangoplate.domain.type.ErrorCode;
//import com.mangoplate.mangoplate.exception.ApplicationException;
//import com.mangoplate.mangoplate.repository.PostRepository;
//import com.mangoplate.mangoplate.utill.JwtTokenUtils;
//import jakarta.transaction.Transactional;
//import lombok.RequiredArgsConstructor;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.stereotype.Service;
//
//import java.util.Optional;
//
//@Service
//@Slf4j
//@RequiredArgsConstructor
//public class PostService {
//
//
//    private final PostRepository postRepository;
//    private final JwtTokenUtils jwtTokenUtils;
//
//    @Value("${jwt.secret-key}")
//    private String secretKey;
//
//
//    public PostRequest get_post(PostRequest request) {
//
//        PostEntity res = postRepository.findById(request.postId()).orElseThrow(() -> {
//            throw new ApplicationException(ErrorCode.NO_POST);
//        });
//
//        jwtTokenUtils.getUserId(res.getUserEntity().getUserId(),secretKey);
//
//        PostRequest postRequest = request;
//
//        return postRequest;
//    }
//
//    public void post_write(PostRequest postRequest){
//        PostEntity postEntity = postRequest.getEntity();
//
//        jwtTokenUtils.getUserId(postEntity.getUserEntity().getUserId(), secretKey);
//
//        postRepository.save(postEntity);
//
//    }
//}
