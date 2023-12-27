package com.market.superMarket.controllers;

import com.market.superMarket.models.Lenguage;
import com.market.superMarket.services.LengaugeService;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/lenguages")
public class LenguageController {
    private final LengaugeService lengaugeService;

    public LenguageController(LengaugeService lengaugeService) {
        this.lengaugeService = lengaugeService;
    }


    @GetMapping
    public Optional<?> getLenguage(@RequestParam(name = "name", defaultValue = "", required = false) String name){
        return this.lengaugeService.getLenguages(name);
    }

    @PostMapping
    public Lenguage createLenguage(@RequestBody Lenguage lenguage){
        return this.lengaugeService.cretaeLenguage(lenguage);
    }

    @PostMapping("/seed")
    public List<Lenguage> seedInDb(){
        return this.lengaugeService.seedDatabase();
    }

    @DeleteMapping("/{id}")
    public String deleteLenguage(@PathVariable("id")int id){
        return this.lengaugeService.deleteLenguage(id);
    }

    @PutMapping
    public Optional<?> updateLenguage(@RequestBody Lenguage lenguage){
        return this.lengaugeService.updateLenguage(lenguage);
    }



}
