package com.market.superMarket.repositories;

import com.market.superMarket.models.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


@Repository
public interface BookRepository extends JpaRepository<Book, Integer> {
    Optional<Book> findBookByName(String name);
    List<Book> findByNameContaining(String name);
}
