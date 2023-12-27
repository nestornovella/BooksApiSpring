package com.market.superMarket.services;

import com.market.superMarket.models.Genre;
import com.market.superMarket.repositories.GenreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;
import java.util.Optional;

@Service
public class GenreService {
    private final GenreRepository genreRepository;
    @Autowired
    public GenreService(GenreRepository genreRepository) {
        this.genreRepository = genreRepository;
    }

    public Optional<?> getGenre(String name) {
        if(name != null && name.isEmpty()){
            return Optional.of(this.genreRepository.findAll());

        }
        else{
            Optional<Genre> genre = this.genreRepository.findByName(name);

            if(genre.isPresent()){
                return genre;
            }
            return  Optional.of("no se ha encontra el genero.");
        }
    }
    public Genre createGenre(Genre genre){
        return this.genreRepository.save(genre);
    }

    public String deleteGenre(Integer id){
        this.genreRepository.deleteById(id);
        return "El genero ha sido eliminado";
    }

    public List<Genre> seedGenre(){
        String[] genresNames={"Terror", "Aventura", "Drama", "Comedia", "Documentales"};
        for (String genre: genresNames) {
            this.genreRepository.save(new Genre(genre, true));
        }
        return this.genreRepository.findAll();
    }

}
