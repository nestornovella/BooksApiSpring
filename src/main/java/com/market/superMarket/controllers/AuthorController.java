package com.market.superMarket.controllers;


import com.market.superMarket.models.Author;
import com.market.superMarket.models.AsociateEntities;
import com.market.superMarket.services.AuthorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/v1/author")
public class AuthorController {
    private final AuthorService authorService;
    @Autowired
    public AuthorController(AuthorService authorService) {
        this.authorService = authorService;
    }

    @GetMapping("/all")
    public List<Author> getAllAuthors(){
        return  authorService.getAllAuthors();
    }

    @GetMapping
    public Optional<?> getByName(@RequestParam(name = "name", defaultValue = "")String name){
        ArrayList<Author> listaVacia = new ArrayList<>();
        if(!name.isEmpty()){
            return authorService.getByName(name);
        }
            return Optional.of(listaVacia);
    }
    @GetMapping("/available")
    public Optional<List<Author>> getAllAvailables(){
        return authorService.getAllAvailables();
    }

    @PostMapping
    public Author createAuthord(@RequestBody Author author){
        return authorService.createAuthor(author);
    }

    @PostMapping("/relationate")
    public void asociate(@RequestBody AsociateEntities tablaIntermedia){
        authorService.relationateAuthorBook(tablaIntermedia);
    }
    @PostMapping("/asociate")
    public Optional<?> asociateLenguage(@RequestBody AsociateEntities asociationsId){
        return this.authorService.asociateLenguage(asociationsId);
    }


}
