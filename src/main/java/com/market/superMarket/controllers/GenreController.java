package com.market.superMarket.controllers;

import com.market.superMarket.models.Genre;
import com.market.superMarket.services.GenreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/genre")
public class GenreController {
    private final GenreService genreService;

    @Autowired
    public GenreController(GenreService genreService) {
        this.genreService = genreService;
    }

    @GetMapping
    public Optional<?>getGenre(@RequestParam(name = "name", defaultValue = "")String name){
        return this.genreService.getGenre(name);
    }

    @DeleteMapping("/:id")
    public String deleteGenre(@Param("id")Integer id){
        return this.genreService.deleteGenre(id);
    }

    @PostMapping("/seed")
    public List<Genre> seedGenre(){
        return this.genreService.seedGenre();

    }

}
