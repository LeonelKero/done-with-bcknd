package com.workbeatstalent.done_with_bcknd.post;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.workbeatstalent.done_with_bcknd.post.dto.PostRequestDTO;
import com.workbeatstalent.done_with_bcknd.post.dto.PostResponseDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.Collection;

@RestController
@RequestMapping(path = {"/api/v1/posts"})
public record PostController(PostService postService) {

    @GetMapping
    public ResponseEntity<Collection<PostResponseDTO>> allPosts() {
        return new ResponseEntity<>(this.postService.fetchAll(), HttpStatus.OK);
    }

    @GetMapping(path = {"/{id}"})
    public ResponseEntity<?> getPost(final @PathVariable(name = "id") Long id) {
        final var optionalPost = this.postService.findById(id);
        if (optionalPost.isPresent()) return new ResponseEntity<>(optionalPost.get(), HttpStatus.OK);
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping(path = {"/authors/{id}"})
    public ResponseEntity<?> savePost(final @PathVariable(name = "id") Long id, final @RequestPart String postRequest, final @RequestPart Collection<MultipartFile> images) throws JsonProcessingException {
        final var postRequestDTO = new ObjectMapper().readValue(postRequest, PostRequestDTO.class);
        final var post = this.postService.save(id, postRequestDTO, images);
        return new ResponseEntity<>(post, HttpStatus.CREATED);
    }
}
