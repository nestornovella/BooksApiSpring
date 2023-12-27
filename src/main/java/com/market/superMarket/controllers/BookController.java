package com.market.superMarket.controllers;

import com.market.superMarket.models.AsociateEntities;
import com.market.superMarket.models.Book;
import com.market.superMarket.services.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping("api/v1/book")
public class BookController {
    private final BookService bookService;

    @Autowired
    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping
    public List<Book> getAllBooks(){
        return this.bookService.getAllBooks();
    }

    @PostMapping("/getBook")
    public Mono<List<Book>> seedBooks(@RequestParam(name = "name", defaultValue = "", required = false) String bookName) {
        try {
            if (!bookName.isEmpty()) {
                return this.bookService.seedBooks(bookName);
            } else {
                return Mono.error(new IllegalStateException("parametro requerido"));
            }
        } catch (Exception e) {
            return Mono.error(new RuntimeException(e.getMessage()));
        }
    }

    @PostMapping("/prueba")
    public List<Book> getBookListName(String name){
        return this.bookService.getBookListName(name);
    }

    @PostMapping
    public Book createBook(@RequestBody Book book){
        return this.bookService.createBook(book);
    }

    @PostMapping("/asociate")
    public String asociateGenre(@RequestBody AsociateEntities asociationsID){
        return this.bookService.asociateGenre(asociationsID);
    }

}
