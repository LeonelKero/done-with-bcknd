package com.workbeatstalent.done_with_bcknd.post;

import com.workbeatstalent.done_with_bcknd.exception.ResourceNotFoundException;
import com.workbeatstalent.done_with_bcknd.file.FileService;
import com.workbeatstalent.done_with_bcknd.post.dto.*;
import com.workbeatstalent.done_with_bcknd.util.Utils;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Service
public class PostServiceImpl implements PostService {

    private final PostRepository postRepository;
    private final AuthorService authorService;
    private final FileService fileService;
    private final Utils utils;

    public PostServiceImpl(PostRepository postRepository, AuthorService authorService, FileService fileService, Utils utils) {
        this.postRepository = postRepository;
        this.authorService = authorService;
        this.fileService = fileService;
        this.utils = utils;
    }

    @Override
    public Collection<PostResponseDTO> fetchAll() {
        return this.postRepository
                .findAll()
                .stream()
                .map(PostServiceImpl::buildPostResponseDto)
                .toList();
    }

    @Override
    public PostResponseDTO save(final Long authorId, final PostRequestDTO dto, Collection<MultipartFile> files) {
        // Check if author exists
        final var optionalAuthor = this.authorService.findById(authorId);
        // If not thrown an exception
        if (optionalAuthor.isEmpty()) throw new ResourceNotFoundException("Author not found.");
        // If the author exists, proceed to Post construction
        // Save files (images) first
        final var localFilePath = this.utils.getLocalFilePath();
        // TODO: Could check - remove files
        final var savedFilenames = files.stream().map(file -> {
            try {
                return this.fileService.upload(localFilePath, file);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }).toList();
        // Construct complete file path
        final var filesFullPaths = savedFilenames
                .stream()
                .map(filename -> localFilePath + File.pathSeparator + filename)
                .toList();
        // Construct Post object
        final var post = new Post(
                dto.title(),
                BigDecimal.valueOf(dto.price()),
                filesFullPaths.stream().map(Image::new).toList(),
                Category.valueOf(dto.categoryName().toUpperCase()),
                optionalAuthor.get(),
                new Location(dto.latitude(), dto.longitude()));
        // Persist Post
        final var savedPost = this.postRepository.save(post);
        // Return expected data type
        return buildPostResponseDto(savedPost);
    }

    private static PostResponseDTO buildPostResponseDto(Post savedPost) {
        return new PostResponseDTO(
                savedPost.getId(),
                savedPost.getTitle(),
                savedPost.getImages().stream().map(image -> new ImageDTO(image.getUrl(), image.getThumbnailUrl())).toList(),
                savedPost.getPrice(),
                savedPost.getCategory().ordinal(),
                savedPost.getAuthor().getId(),
                new LocationDTO(savedPost.getLocation().getLatitude(), savedPost.getLocation().getLongitude())
        );
    }

    @Override
    public Boolean update(final Long postId, final PostDTO dto) {
        // TODO: Implement Post update feature
        return null;
    }

    @Override
    public Boolean delete(final Long postId) {
        final var optionalPost = this.postRepository.findById(postId);
        if (optionalPost.isPresent()) {
            this.postRepository.deleteById(postId);
            return true;
        }
        return false;
    }

    @Override
    public Optional<PostResponseDTO> findById(final Long postId) {
        final var optionalPost = this.postRepository.findById(postId);
        if (optionalPost.isEmpty()) return Optional.empty();
        return optionalPost.map(PostServiceImpl::buildPostResponseDto);
    }

    @Override
    public Collection<PostResponseDTO> findByAuthor(final String authorEmail) {
        final var optionalAuthor = this.authorService.findByEmail(authorEmail);
        if (optionalAuthor.isPresent()) {

        }
        return List.of();
    }
}
