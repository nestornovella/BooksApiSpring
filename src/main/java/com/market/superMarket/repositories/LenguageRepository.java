package com.market.superMarket.repositories;

import com.market.superMarket.models.Lenguage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface LenguageRepository extends JpaRepository<Lenguage, Integer> {
    Optional<Lenguage> findByName(String name);

}
