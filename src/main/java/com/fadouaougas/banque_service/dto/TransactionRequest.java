package com.fadouaougas.banque_service.dto;

import com.fadouaougas.banque_service.enums.TypeTransaction;
import lombok.Data;

/**
 * DTO pour les requêtes de transaction
 */
@Data
public class TransactionRequest {
    private Long compteId;
    private double montant;
    private String date;
    private TypeTransaction type;
}

