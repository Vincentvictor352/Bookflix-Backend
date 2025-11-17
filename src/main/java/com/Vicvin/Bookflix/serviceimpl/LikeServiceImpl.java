package com.Vicvin.Bookflix.serviceimpl;

import com.Vicvin.Bookflix.entity.Book;
import com.Vicvin.Bookflix.entity.Like;
import com.Vicvin.Bookflix.entity.User;
import com.Vicvin.Bookflix.repository.BookRepository;
import com.Vicvin.Bookflix.repository.LikeRepository;
import com.Vicvin.Bookflix.repository.UserRepository;
import com.Vicvin.Bookflix.service.LikeService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class LikeServiceImpl implements LikeService {

    private final LikeRepository bookLikeRepository;
    private final UserRepository userRepository;
    private final BookRepository bookRepository;

    @Override
    public String toggleLike(Long userId, Long bookId) {
        // Check if user already liked this book
        var existingLike = bookLikeRepository.findByUserIdAndBookId(userId, bookId);

        if (existingLike.isPresent()) {
            // If like exists, remove it (unlike)
            bookLikeRepository.delete(existingLike.get());
            return "Unliked";
        } else {
            // Else, create a new like
            User user = userRepository.findById(userId)
                    .orElseThrow(() -> new RuntimeException("User not found"));
            Book book = bookRepository.findById(bookId)
                    .orElseThrow(() -> new RuntimeException("Book not found"));

            Like like = new Like();
            like.setUser(user);
            like.setBook(book);
            bookLikeRepository.save(like);

            return "Liked";
        }
    }

    @Override
    public Long countLikes(Long bookId) {
        return bookLikeRepository.countByBookId(bookId);
    }
}


