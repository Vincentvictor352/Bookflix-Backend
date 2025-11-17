package com.Vicvin.Bookflix.controller;

import com.Vicvin.Bookflix.dto.CreateCommentRequest;
import com.Vicvin.Bookflix.entity.Comment;
import com.Vicvin.Bookflix.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/comments")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class CommentController {

    private final CommentService commentService;

    // Add a comment
    @PostMapping("/{userId}")
    public ResponseEntity<Comment> addComment(@PathVariable Long userId,
                                              @RequestBody CreateCommentRequest request) {
        Comment comment = commentService.addComment(request, userId);
        return ResponseEntity.ok(comment);
    }

    // Get all comments for a specific book
    @GetMapping("/book/{bookId}")
    public ResponseEntity<List<Comment>> getCommentsByBook(@PathVariable Long bookId) {
        List<Comment> comments = commentService.getCommentsByBook(bookId);
        return ResponseEntity.ok(comments);
    }

}
