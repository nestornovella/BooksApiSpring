package com.market.superMarket.services;

import com.market.superMarket.models.Lenguage;
import com.market.superMarket.repositories.LenguageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.Optional;

@Service
public class LengaugeService {
    private final LenguageRepository lenguageRepository;

    @Autowired
    public LengaugeService(LenguageRepository lenguageRepository) {
        this.lenguageRepository = lenguageRepository;
    }

    public Optional<?> getLenguages(String name){
        if(name.isEmpty()){
            return Optional.of(this.lenguageRepository.findAll());
        }
        Optional<?> lenguage = this.lenguageRepository.findByName(name);
        if(lenguage != null && lenguage.isPresent()){
            return lenguage;
        }

        return Optional.of("no se encontro el lenguaje con ese nombre");
    }

    public Lenguage cretaeLenguage(Lenguage lenguage){
        return this.lenguageRepository.save(lenguage);
    }

    public String deleteLenguage(int id){
        this.lenguageRepository.deleteById(id);
        return "se ha eliminado";
    }

    public List<Lenguage>seedDatabase(){
        String[] lenguages = {"Espanol", "Ingles", "Protugues", "Mandarin", "Frances"};
        for (String name: lenguages) {
            this.lenguageRepository.save(new Lenguage(name, true));
        }
        return this.lenguageRepository.findAll();
    }

    public Optional<?> updateLenguage(Lenguage leng){
        Optional<Lenguage> lenguage = this.lenguageRepository.findById(leng.getId());
        if(lenguage.isPresent()){
            String name = leng.getName();
            Boolean available = leng.getAvailable();
            if(name != null){
                lenguage.get().setName(name);
            }
            if(available != null){
                lenguage.get().setAvailable(available);
            }

            return Optional.of(this.lenguageRepository.save(lenguage.get()));
        }

        return Optional.of("no se pudo actualizar porque no se encontro lenguage con ese ID");
    }


}
