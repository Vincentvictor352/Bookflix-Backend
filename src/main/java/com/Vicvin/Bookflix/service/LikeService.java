package com.Vicvin.Bookflix.service;

public interface LikeService {

    // Like or unlike a book (toggle)
    String toggleLike(Long userId, Long bookId);

    // Count how many likes a book has
    Long countLikes(Long bookId);
}
