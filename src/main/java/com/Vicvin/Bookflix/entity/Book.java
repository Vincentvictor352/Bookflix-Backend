package com.Vicvin.Bookflix.entity;

import jakarta.persistence.*;
import lombok.*;

import java.io.File;

@Entity
@Table(name = "books")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String title;

    @Column(length = 1000)
    private String description;

    private File coverImage;      // file path or cloud URL for image

    private File  novelFile;     // file path or cloud URL for story
}
