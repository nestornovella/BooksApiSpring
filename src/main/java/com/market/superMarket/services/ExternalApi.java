package com.market.superMarket.services;

import com.market.superMarket.models.Author;
import com.market.superMarket.models.Book;
import com.market.superMarket.repositories.AuthorRepository;
import com.market.superMarket.repositories.BookRepository;
import jakarta.transaction.Transactional;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.converter.json.GsonBuilderUtils;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.CountDownLatch;
@Service
public class ExternalApi {

    private final WebClient webClient;

    private final BookRepository bookRepository;
    private final AuthorRepository authorRepository;

    @Autowired
    public ExternalApi(WebClient.Builder webClient, BookRepository bookRepository, AuthorRepository authorRepository) {
        this.webClient = webClient.baseUrl("https://www.googleapis.com/books/v1/volumes?key=AIzaSyCt-vNuj7ZOGaCAi-30YKxZ3PvIaPJi6Ts").build();
        this.bookRepository = bookRepository;
        this.authorRepository = authorRepository;
    }





    @Transactional
    public Mono<List<Book>> getData(String query){
           return webClient.get()
                    .uri(uriBuilder -> uriBuilder.queryParam("q", query).build())
                    .retrieve()
                    .bodyToMono(String.class)
                    .flatMap(data -> processData(data)
                            .then(getBooksByName(query))
                            .onErrorResume(throwable -> Mono.empty())
                    );
    }



    public Mono<List<Book>> getBooksByName(String name){
        return  Mono.just(this.bookRepository.findByNameContaining(name));
    }


    public Mono<Void> processData(String data) {
        JSONObject firstObject = new JSONObject(data);
        JSONArray items = firstObject.getJSONArray("items");
        items.forEach(book -> {
            JSONObject aBook = (JSONObject) book;
            JSONObject volumenInfo = aBook.getJSONObject("volumeInfo");

            String title = volumenInfo.getString("title");
            Optional<Book> bookExist = this.bookRepository.findBookByName(title);
            if (bookExist.isEmpty() || bookExist == null) {
                Book newBook = new Book(title);
                this.bookRepository.save(newBook);
                if (volumenInfo.has("authors")) {
                    JSONArray authors = volumenInfo.getJSONArray("authors");
                    String authorName = authors.getString(0);
                    Author newAuthor = new Author(authorName);
                    this.authorRepository.save(newAuthor);
                    //Asociate Entities
                    Optional<Author> auth = this.authorRepository.findById(newAuthor.getId());
                    Optional<Book> boo = this.bookRepository.findById(newBook.getId());

                    if (auth.isPresent() && boo.isPresent()) {
                        boo.get().setAutor(auth.get());
                        this.bookRepository.save(boo.get());
                    }
                }
            }
        });
        return Mono.empty();
    }

}
