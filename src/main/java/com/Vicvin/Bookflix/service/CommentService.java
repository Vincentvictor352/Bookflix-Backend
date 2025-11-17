package com.Vicvin.Bookflix.service;

import com.Vicvin.Bookflix.dto.CreateCommentRequest;
import com.Vicvin.Bookflix.entity.Comment;

import java.util.List;

public interface CommentService {

    // Add a new comment
    Comment addComment(CreateCommentRequest request, Long userId);

    // Get all comments for a specific book
    List<Comment> getCommentsByBook(Long bookId);
}
