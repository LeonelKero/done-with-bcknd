package com.workbeatstalent.done_with_bcknd.post;

import com.workbeatstalent.done_with_bcknd.post.dto.AuthorDTO;

import java.util.Collection;
import java.util.Optional;

public interface AuthorService {

    Author create(final AuthorDTO dto);

    Optional<Author> findByEmail(final String email);

    Optional<Author> findById(final Long id);

    Collection<Author> fetchAll();

    Boolean update(final Long authorId, final AuthorDTO dto);

    Boolean delete(final Long authorId);

}
