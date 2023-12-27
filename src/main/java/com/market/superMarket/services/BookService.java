package com.market.superMarket.services;

import com.market.superMarket.models.AsociateEntities;
import com.market.superMarket.models.Book;
import com.market.superMarket.models.Genre;
import com.market.superMarket.repositories.BookRepository;
import com.market.superMarket.repositories.GenreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.Optional;

@Service
public class BookService {
    private final BookRepository bookRepository;
    private final GenreRepository genreRepository;

    private final ExternalApi externalApi;
    @Autowired
    public BookService(BookRepository bookRepository, GenreRepository genreRepository, ExternalApi externalApi) {
        this.bookRepository = bookRepository;
        this.genreRepository = genreRepository;
        this.externalApi = externalApi;
    }
    public List<Book> getAllBooks(){
        return this.bookRepository.findAll();
    }

    public Book createBook(Book book){
        return this.bookRepository.save(book);
    }

    public String asociateGenre(AsociateEntities asociation){
        Optional<Book> book = this.bookRepository.findById(asociation.getBookId());
        Optional<Genre> genre = this.genreRepository.findById(asociation.getGenreId());

        if(book.isPresent() && genre.isPresent()){
            book.get().setGenre(genre.get());
            this.bookRepository.save(book.get());
            return "se asocio con exito";
        }else{
            return "no se asociar el genero";
        }
    }
    public Mono<List<Book>> seedBooks(String query) {
        return externalApi.getData(query)
                .onErrorMap(error -> new RuntimeException("error: " + error));
    }

    public List<Book> getBookListName(String name){
        return bookRepository.findByNameContaining(name);
    }
}
