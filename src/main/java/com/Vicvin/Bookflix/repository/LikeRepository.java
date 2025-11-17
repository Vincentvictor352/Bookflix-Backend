package com.Vicvin.Bookflix.repository;

import com.Vicvin.Bookflix.entity.Like;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface LikeRepository extends JpaRepository<Like,Long> {


    // Find a like by user and book (to check if user has already liked it)
    Optional<Like> findByUserIdAndBookId(Long userId, Long bookId);

    // Count total likes for a specific book
    Long countByBookId(Long bookId);
}
