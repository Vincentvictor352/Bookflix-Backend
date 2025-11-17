package com.Vicvin.Bookflix.controller;


import com.Vicvin.Bookflix.service.LikeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/books/like")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")

public class LikeController {

    private final LikeService LikeService;

    //  Like or Unlike a Book (Toggle)
    @PostMapping("/{userId}/{bookId}")
    public ResponseEntity<String> toggleLike(@PathVariable Long userId,
                                             @PathVariable Long bookId) {
        String message = LikeService.toggleLike(userId, bookId);
        return ResponseEntity.ok(message);
    }

    // Get Total Likes for a Book
    @GetMapping("/count/{bookId}")
    public ResponseEntity<Long> countLikes(@PathVariable Long bookId) {
        Long count = LikeService.countLikes(bookId);
        return ResponseEntity.ok(count);
    }
}
