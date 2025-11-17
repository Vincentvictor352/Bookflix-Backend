package com.Vicvin.Bookflix.repository;

import com.Vicvin.Bookflix.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentRepository extends JpaRepository<Comment,Long> {


    // Find all comments for a specific book
    List<Comment> findByBookId(Long bookId);


}
