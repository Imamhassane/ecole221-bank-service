package com.ecole221.compte.transaction.service.service;


import com.ecole221.compte.transaction.service.exception.compte.CompteException;
import com.ecole221.compte.transaction.service.model.Frais;
import com.ecole221.compte.transaction.service.model.Pourcentage;
import com.ecole221.compte.transaction.service.repository.FraisRepository;
import com.ecole221.compte.transaction.service.repository.PourcentageRepository;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Data
@Slf4j
public class ParametrageService {

    private final PourcentageRepository pourcentageRepository;
    private final FraisRepository fraisRepository;

    public Pourcentage save(Pourcentage pourcentage){
        if (pourcentage.getPourcentage() <= 0) throw new CompteException("pourcentage invalide!");
        pourcentageRepository.findAll().stream().filter(Pourcentage::isEtat)
                        .forEach(p -> p.setEtat(false));
        pourcentage.setEtat(true);
        return pourcentageRepository.save(pourcentage);
    }
    public Pourcentage getPourcentage(){
        Optional<Pourcentage> optionalPourcentage =
                pourcentageRepository.findAll().stream().filter(Pourcentage::isEtat).findFirst();
        if(optionalPourcentage.isEmpty()) throw new CompteException("Aucun pourcentage trouvé");
        return optionalPourcentage.get();
    }
    public Frais getFraisTotal(){
        return fraisRepository.findAll().stream().findFirst().orElseThrow(()->new CompteException("Aucun frais trouvé!"));
    }
    public void saveFrais(Frais frais){
        fraisRepository.save(frais);
    }

}
