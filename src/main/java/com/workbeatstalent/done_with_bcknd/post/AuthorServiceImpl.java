package com.workbeatstalent.done_with_bcknd.post;

import com.workbeatstalent.done_with_bcknd.exception.ResourceConflictException;
import com.workbeatstalent.done_with_bcknd.post.dto.AuthorDTO;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Optional;

@Service
public class AuthorServiceImpl implements AuthorService {

    private final AuthorRepository authorRepository;

    public AuthorServiceImpl(AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }

    @Override
    public Author create(final AuthorDTO dto) {
        if (this.findByEmail(dto.email()).isPresent()) throw new ResourceConflictException("Email already taken.");
        final var author = new Author(dto.firstname(), dto.lastname(), dto.email());
        return this.authorRepository.save(author);
    }

    @Override
    public Optional<Author> findByEmail(final String email) {
        return this.authorRepository.findByEmail(email);
    }

    @Override
    public Optional<Author> findById(final Long id) {
        return this.authorRepository.findById(id);
    }

    @Override
    public Collection<Author> fetchAll() {
        return this.authorRepository.findAll();
    }

    @Override
    public Boolean update(final Long authorId, final AuthorDTO dto) {
        final var optionalAuthor = this.authorRepository.findById(authorId);
        if (optionalAuthor.isEmpty()) return false;
        final var author = optionalAuthor.get();
        // TODO: Must check for nullable fields, and only update the ones which are not.
        author.setFirstname(dto.firstname());
        author.setLastname(dto.lastname());
        author.setEmail(dto.email());
        this.authorRepository.save(author);
        return true;
    }

    @Override
    public Boolean delete(final Long authorId) {
        if (this.authorRepository.findById(authorId).isEmpty()) return false;
        this.authorRepository.deleteById(authorId);
        return true;
    }
}
