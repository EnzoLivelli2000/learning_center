package com.acme.learningcenter.learning.api;

import com.acme.learningcenter.learning.domain.service.PostService;
import com.acme.learningcenter.learning.mapping.PostMapper;
import com.acme.learningcenter.learning.resource.CreatePostResource;
import com.acme.learningcenter.learning.resource.PostResource;
import com.acme.learningcenter.learning.resource.UpdatePostResource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/v1/posts")
public class PostsController {
    private final PostService postService;

    private final PostMapper mapper;

    public PostsController(PostService postService, PostMapper postMapper) {
        this.postService = postService;
        this.mapper = postMapper;
    }

    @GetMapping
    public Page<PostResource> getAllPost(Pageable pageable) {
        return mapper.modelListToPage(postService.getAll(), pageable);
    }

    @GetMapping("{postId}")
    public PostResource getPostById(@PathVariable Long postId){
        return mapper.toResource(postService.getById(postId));
    }

    @PostMapping
    public PostResource cratePost(@Valid @RequestBody CreatePostResource resource) {
        return mapper.toResource(postService.create(mapper.toModel(resource)));
    }

    @PutMapping("{postId}")
    public PostResource updatePost(@PathVariable Long postId, @Valid @RequestBody UpdatePostResource resource) {
        return mapper.toResource(postService.update(postId, mapper.toModel(resource)));
    }

    @DeleteMapping("{postId}")
    public ResponseEntity<?> deletePost(@PathVariable Long postId){
        return postService.delete(postId);
    }
}
