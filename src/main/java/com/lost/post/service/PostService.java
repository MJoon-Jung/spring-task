package com.lost.post.service;

import com.lost.common.domain.exception.ResourceNotFoundException;
import com.lost.post.domain.Post;
import com.lost.post.service.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PostService {

    private final PostRepository postRepository;

    public Post postDetails(Long postId) {
        return postRepository.findById(postId)
                .orElseThrow(() -> new ResourceNotFoundException("POST", postId));
    }
}