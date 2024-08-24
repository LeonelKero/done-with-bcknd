package com.workbeatstalent.done_with_bcknd.post;

import com.workbeatstalent.done_with_bcknd.post.dto.AuthorDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
@RequestMapping(path = {"/api/v1/authors"})
public record AuthorController(AuthorService authorService) {

    @PostMapping
    public ResponseEntity<Author> add(final @RequestBody AuthorDTO dto) {
        return new ResponseEntity<>(this.authorService.create(dto), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<Collection<Author>> allAuthors() {
        return new ResponseEntity<>(this.authorService.fetchAll(), HttpStatus.OK);
    }

    @GetMapping(path = {"/{id}"})
    public ResponseEntity<?> author(final @PathVariable(name = "id") Long authorId) {
        final var optionalAuthor = this.authorService.findById(authorId);
        if (optionalAuthor.isEmpty()) return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        return new ResponseEntity<>(optionalAuthor.get(), HttpStatus.OK);
    }
}
