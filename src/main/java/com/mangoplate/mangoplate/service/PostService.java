package com.mangoplate.mangoplate.service;


import com.mangoplate.mangoplate.domain.entity.PostEntity;
import com.mangoplate.mangoplate.domain.request.PostRequest;
import com.mangoplate.mangoplate.domain.response.PostResponse;
import com.mangoplate.mangoplate.domain.type.ErrorCode;
import com.mangoplate.mangoplate.exception.ApplicationException;
import com.mangoplate.mangoplate.repository.PostRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Slf4j
@RequiredArgsConstructor
public class PostService {


    private final PostRepository postRepository;


    public PostRequest get_post(PostRequest request) {

        PostEntity res = postRepository.findById(request.postId()).orElseThrow(() -> {
            throw new ApplicationException(ErrorCode.NO_POST);
        });

        PostRequest postRequest = request;

        return postRequest;
    }

    public PostResponse post_write(PostRequest postRequest){
        PostEntity postEntity = postRequest.getEntity();
        postRepository.save(postEntity);
        return new PostResponse()
    }
}
