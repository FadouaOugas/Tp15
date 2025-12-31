package com.fadouaougas.banque_service.dto;

import com.fadouaougas.banque_service.enums.TypeCompte;
import lombok.Data;

/**
 * DTO pour les requêtes de création de compte
 */
@Data
public class CompteRequest {
    private double solde;
    private String dateCreation;
    private TypeCompte type;
}

