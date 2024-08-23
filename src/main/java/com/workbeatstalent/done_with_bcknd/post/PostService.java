package com.workbeatstalent.done_with_bcknd.post;

import com.workbeatstalent.done_with_bcknd.post.dto.PostDTO;
import com.workbeatstalent.done_with_bcknd.post.dto.PostRequestDTO;
import com.workbeatstalent.done_with_bcknd.post.dto.PostResponseDTO;
import org.springframework.web.multipart.MultipartFile;

import java.util.Collection;
import java.util.Optional;

public interface PostService {
    Collection<PostResponseDTO> fetchAll();

    PostResponseDTO save(final Long authorId, final PostRequestDTO dto, Collection<MultipartFile> files);

    Boolean update(final Long postId, final PostDTO dto);

    Boolean delete(final Long postId);

    Optional<PostResponseDTO> findById(final Long postId);

    Collection<PostResponseDTO> findByAuthor(final String authorEmail);
}
