package com.Vicvin.Bookflix.dto;

import lombok.Data;

@Data
public class CreateCommentRequest {

    private Long bookId;   // The ID of the book being commented on
    private String content; // The text of the comment
}
