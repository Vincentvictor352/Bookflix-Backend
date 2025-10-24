package com.Vicvin.Bookflix.service.impl;

import com.Vicvin.Bookflix.entity.Book;
import com.Vicvin.Bookflix.repository.BookRepository;
import com.Vicvin.Bookflix.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.*;
import java.util.List;

@Service
public class BookServiceImpl implements BookService {

    private final BookRepository bookRepository;

    @Autowired
    public BookServiceImpl(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    private final String uploadDir = "uploads/";

    @Override
    public Book saveBook(String title, String description, MultipartFile image, MultipartFile file) throws IOException {
        // Create upload directory if not exists
        Files.createDirectories(Paths.get(uploadDir));

        String imagePath = null;
        String filePath = null;

        // Save image if provided
        if (image != null && !image.isEmpty()) {
            imagePath = uploadDir + System.currentTimeMillis() + "_" + image.getOriginalFilename();
            image.transferTo(new File(imagePath));
        }

        // Save story file if provided
        if (file != null && !file.isEmpty()) {
            filePath = uploadDir + System.currentTimeMillis() + "_" + file.getOriginalFilename();
            file.transferTo(new File(filePath));
        }

        // Save book record
        Book book = Book.builder()
                .title(title)
                .description(description)
                .imageUrl(imagePath)
                .file(filePath)
                .build();

        return bookRepository.save(book);
    }

    @Override
    public List<Book> getAllBooks() {
        return bookRepository.findAll();
    }

    @Override
    public Book getBookById(Long id) {
        return bookRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Book not found with id: " + id));
    }

    @Override
    public void deleteBook(Long id) {
        bookRepository.deleteById(id);
    }
}
