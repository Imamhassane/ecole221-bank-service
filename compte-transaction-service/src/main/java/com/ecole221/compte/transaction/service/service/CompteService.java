package com.ecole221.compte.transaction.service.service;

import com.ecole221.common.service.dto.*;
import com.ecole221.common.service.event.*;
import com.ecole221.compte.transaction.service.mapper.CompteTransactionMapper;
import com.ecole221.compte.transaction.service.model.Compte;
import com.ecole221.compte.transaction.service.model.Frais;
import com.ecole221.compte.transaction.service.model.Pourcentage;
import com.ecole221.compte.transaction.service.repository.CompteRepository;
import com.ecole221.compte.transaction.service.repository.FraisRepository;
import com.ecole221.compte.transaction.service.repository.PourcentageRepository;
import jakarta.transaction.Transactional;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Slf4j
@Data
public class CompteService {
    private final CompteRepository compteRepository;
    private final CompteTransactionMapper mapper;
    private final ParametrageService parametrageService;

    public List<CompteDTO> getAllComptes() {
        return compteRepository.findAll()
                .stream()
                .map(mapper::compteToCompteDTO)
                .toList();
    }

    @Transactional
    public CompteEvent saveCompte(ClientEvent clientEvent){
        ClientDTO clientDTO = clientEvent.getClientDTO();
        CompteDTO compteDTO = this.findByClient(clientDTO.getId());
        CompteEvent compteEvent = null;
        try{
            if (compteDTO!=null){
                BigDecimal newSolde = compteDTO.getSolde().add(clientDTO.getSolde());
                compteDTO.setSolde(newSolde);
            }else{
                compteDTO = new CompteDTO(clientDTO.getId(), clientDTO.getSolde());
            }
            compteRepository.save(mapper.compteDTOToCompte(compteDTO));
            compteEvent = new CompteEvent(CompteStatus.UPDATED , compteDTO);
        }catch(Exception ex){
            compteEvent = new CompteEvent(CompteStatus.ERREUR_SOLDE , compteDTO);
        }
        return compteEvent;
    }

    public CompteDTO findByClient(UUID clientId){
        return compteRepository.findById(clientId)
                .map(mapper::compteToCompteDTO).orElse(null);
    }


    @Transactional
    public CompteEvent checkSolde(ServiceEvent serviceEvent){
        //get purcentage
        Pourcentage pourcentage = parametrageService.getPourcentage();
        //get l'événement du paiement
        PaiementEvent paiementEvent = serviceEvent.getPaiementEvent();
        //get compte du client
        CompteDTO compteDTO = findByClient(paiementEvent.getClientDTO().getId());
        //get paiement
        PaiementDTO paiementDTO = paiementEvent.getPaiementDTO();
        //get solde du compte
        BigDecimal solde = compteDTO.getSolde();
        //get service
        ServiceDTO serviceDTO = serviceEvent.getServiceDTO();
        //get prix du service
        BigDecimal prixService = serviceDTO.getPrix();

        CompteEvent compteEvent;

        paiementDTO.setMontant(prixService);

        if (prixService.compareTo(solde) > 0) {
            compteEvent = new CompteEvent(CompteStatus.ERREUR_SOLDE,compteDTO, paiementDTO);

        }else if (serviceDTO.getServiceStatus().equals(ServiceStatus.INACTIF.toString())) {
            compteEvent = new CompteEvent(CompteStatus.ERREUR_SERVICE ,compteDTO,paiementDTO);

        } else {
            //frais paiement
                BigDecimal fraisPaiement = prixService.multiply(BigDecimal.valueOf(pourcentage.getPourcentage()))
                        .divide(BigDecimal.valueOf(100),2 , RoundingMode.HALF_UP);
                //set new value of frais total
                Frais frais = parametrageService.getFraisTotal();
                frais.setTotal(frais.getTotal().add(fraisPaiement));
                parametrageService.saveFrais(frais);
            //fraisdto
            FraisDTO fraisDTO = new FraisDTO(fraisPaiement , pourcentage.getPourcentage());
            //
            BigDecimal newSolde = solde.subtract(prixService).subtract(fraisPaiement);
            compteRepository.save(new Compte(compteDTO.getClientId(), newSolde));
            compteEvent = new CompteEvent(CompteStatus.SOLDE_OK ,compteDTO,paiementDTO,fraisDTO);
        }
        return compteEvent;
    }
}
