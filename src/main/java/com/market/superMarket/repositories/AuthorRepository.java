package com.market.superMarket.repositories;

import com.market.superMarket.models.Author;
import org.springframework.data.jpa.repository.JpaRepository;


import java.util.List;
import java.util.Optional;

public interface AuthorRepository extends JpaRepository<Author, Integer> {
    Optional<Author> findByName(String name);
    Optional<List<Author>> findByAvailable(boolean status);

}
