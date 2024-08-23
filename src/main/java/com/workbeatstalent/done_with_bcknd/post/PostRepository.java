package com.workbeatstalent.done_with_bcknd.post;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Collection;

public interface PostRepository extends JpaRepository<Post, Long> {
    Collection<Post> findByAuthor_Email(final String email);
}
