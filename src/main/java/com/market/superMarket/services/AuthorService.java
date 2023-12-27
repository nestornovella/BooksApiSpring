package com.market.superMarket.services;

import com.market.superMarket.models.Author;
import com.market.superMarket.models.AsociateEntities;
import com.market.superMarket.models.Book;
import com.market.superMarket.models.Lenguage;
import com.market.superMarket.repositories.AuthorRepository;
import com.market.superMarket.repositories.BookRepository;
import com.market.superMarket.repositories.LenguageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AuthorService {
    private final AuthorRepository authorRepository;
    private final BookRepository bookRepository;
    private final LenguageRepository lenguageRepository;

    @Autowired
    public AuthorService(AuthorRepository authorRepository, BookRepository bookRepository, LenguageRepository lenguageRepository) {
        this.authorRepository = authorRepository;
        this.bookRepository = bookRepository;
        this.lenguageRepository = lenguageRepository;
    }

    public List<Author> getAllAuthors(){
        return authorRepository.findAll();
    }

    public Optional<Author> getById(int id){
        return authorRepository.findById(id);
    }

    public Optional<Author>getByName(String name){
        return authorRepository.findByName(name);
    }

    public Optional<List<Author>> getAllAvailables(){
        return authorRepository.findByAvailable(true);
    }

    public Author createAuthor(Author author){
        return authorRepository.save(author);
    }

    public void relationateAuthorBook(AsociateEntities relationate){
        Optional<Book> book = this.bookRepository.findById(relationate.getBookId());
        Optional<Author> author = this.authorRepository.findById(relationate.getAuthorId());


        if(book.isPresent() && author.isPresent()){
            book.get().setAutor(author.get());
            bookRepository.save(book.get());
        }else{
            System.out.println("_________________");
            System.out.println(relationate.getAuthorId());
            System.out.println(relationate.getBookId());
        }
    }

    public Optional<?> asociateLenguage(AsociateEntities asociation){
        Optional<Lenguage> lenguage = this.lenguageRepository.findById(asociation.getLenguageId());
        Optional<Author> author = this.authorRepository.findById(asociation.getAuthorId());// null @H543.get() -> Author

        if(lenguage.isPresent() && author.isPresent()){
            author.get().getLenguagesList().add(lenguage.get());
            this.authorRepository.save(author.get());
            return Optional.of(author.get());
        }else{
            return Optional.of("no se pudo asociar");
        }
    }

}
