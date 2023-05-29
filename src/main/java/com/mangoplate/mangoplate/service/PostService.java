package com.mangoplate.mangoplate.service;


import com.mangoplate.mangoplate.domain.request.PostRequest;
import com.mangoplate.mangoplate.domain.type.ErrorCode;
import com.mangoplate.mangoplate.exception.ApplicationException;
import com.mangoplate.mangoplate.repository.PostRepository;
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


    public PostRequest get_post(PostRequest request) {

        com.mangoplate.mangoplate.domain.entity.Post res = postRepository.findById(request.postId()).orElseThrow(() -> {
            throw new ApplicationException(ErrorCode.NO_POST);
        });

        //jwtTokenUtils.getUserId(res.getUserEntity().getUserId(),secretKey);

        PostRequest postRequest = request;

        return postRequest;
    }

    public void write_post(PostRequest postRequest){
        com.mangoplate.mangoplate.domain.entity.Post post = postRequest.getEntity();

        //jwtTokenUtils.getUserId(postEntity.getUserEntity().getUserId(), secretKey);

        postRepository.save(post);

    }

    public  void delete_post(Long postId){
        com.mangoplate.mangoplate.domain.entity.Post res = postRepository.findById(postId).orElseThrow(() -> {
            throw new ApplicationException(ErrorCode.NO_POST);
        });
        //jwtTokenUtils.getUserId(postEntity.getUserEntity().getUserId(), secretKey);

        com.mangoplate.mangoplate.domain.entity.Post post = res;

        postRepository.deleteById(post.getPostId());

    }
}
