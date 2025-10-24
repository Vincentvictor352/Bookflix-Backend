package com.Vicvin.Bookflix.service;

import com.Vicvin.Bookflix.entity.Book;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;
import java.util.List;

public interface BookService {
    Book saveBook(String title, String description, MultipartFile image, MultipartFile file) throws IOException;
    List<Book> getAllBooks();
    Book getBookById(Long id);
    void deleteBook(Long id);
}
