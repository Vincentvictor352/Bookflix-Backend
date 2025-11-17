package com.Vicvin.Bookflix.serviceimpl;

import com.Vicvin.Bookflix.dto.CreateCommentRequest;
import com.Vicvin.Bookflix.entity.Book;
import com.Vicvin.Bookflix.entity.Comment;
import com.Vicvin.Bookflix.entity.User;
import com.Vicvin.Bookflix.repository.BookRepository;
import com.Vicvin.Bookflix.repository.CommentRepository;
import com.Vicvin.Bookflix.repository.UserRepository;
import com.Vicvin.Bookflix.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CommentServiceImpl implements CommentService{


    private final CommentRepository commentRepository;
    private final BookRepository bookRepository;
    private final UserRepository userRepository;

    @Override
    public Comment addComment(CreateCommentRequest request, Long userId) {
        // Find user
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        // Find book
        Book book = bookRepository.findById(request.getBookId())
                .orElseThrow(() -> new RuntimeException("Book not found"));

        // Create and save comment
        Comment comment = new Comment();
        comment.setUser(user);
        comment.setBook(book);
        comment.setContent(request.getContent());

        return commentRepository.save(comment);
    }

    @Override
    public List<Comment> getCommentsByBook(Long bookId) {
        return List.of();
    }
}
