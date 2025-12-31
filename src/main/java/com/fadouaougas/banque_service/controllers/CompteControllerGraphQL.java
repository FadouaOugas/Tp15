package com.fadouaougas.banque_service.controllers;

import com.fadouaougas.banque_service.dto.TransactionRequest;
import com.fadouaougas.banque_service.entities.Compte;
import com.fadouaougas.banque_service.entities.Transaction;
import com.fadouaougas.banque_service.enums.TypeTransaction;
import com.fadouaougas.banque_service.repositories.CompteRepository;
import com.fadouaougas.banque_service.repositories.TransactionRepository;
import lombok.AllArgsConstructor;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Map;

/**
 * Contrôleur GraphQL pour la gestion des comptes
 */
@Controller
@AllArgsConstructor
public class CompteControllerGraphQL {
    private CompteRepository compteRepository;
    private TransactionRepository transactionRepository;

    /**
     * Récupère tous les comptes
     */
    @QueryMapping
    public List<Compte> allComptes() {
        return compteRepository.findAll();
    }

    /**
     * Récupère un compte par identifiant
     */
    @QueryMapping
    public Compte compteById(@Argument Long id) {
        Compte compte = compteRepository.findById(id).orElse(null);
        if (compte == null)
            throw new RuntimeException(String.format("Compte %s not found", id));
        else
            return compte;
    }

    /**
     * Sauvegarde un compte
     */
    @MutationMapping
    public Compte saveCompte(@Argument Compte compte) {
        return compteRepository.save(compte);
    }

    /**
     * Calcule les statistiques des soldes
     */
    @QueryMapping
    public Map<String, Object> totalSolde() {
        long count = compteRepository.count();
        double sum = compteRepository.sumSoldes();
        double average = count > 0 ? sum / count : 0;

        return Map.of(
                "count", count,
                "sum", sum,
                "average", average);
    }

    /**
     * Ajoute une transaction
     */
    @MutationMapping
    public Transaction addTransaction(@Argument TransactionRequest transaction) {
        Compte compte = compteRepository.findById(transaction.getCompteId())
                .orElseThrow(() -> new RuntimeException("Compte not found"));

        Transaction newTransaction = new Transaction();
        newTransaction.setMontant(transaction.getMontant());

        try {
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
            newTransaction.setDate(dateFormat.parse(transaction.getDate()));
        } catch (ParseException e) {
            throw new RuntimeException("Invalid date format. Use yyyy/MM/dd");
        }

        newTransaction.setType(transaction.getType());
        newTransaction.setCompte(compte);

        return transactionRepository.save(newTransaction);
    }

    /**
     * Récupère les transactions d'un compte
     */
    @QueryMapping
    public List<Transaction> compteTransactions(@Argument Long id) {
        Compte compte = compteRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Compte not found"));
        return transactionRepository.findByCompte(compte);
    }

    /**
     * Récupère toutes les transactions
     */
    @QueryMapping
    public List<Transaction> allTransactions() {
        return transactionRepository.findAll();
    }

    /**
     * Calcule les statistiques des transactions
     */
    @QueryMapping
    public Map<String, Object> transactionStats() {
        long count = transactionRepository.count();
        double sumDepots = transactionRepository.sumByType(TypeTransaction.DEPOT);
        double sumRetraits = transactionRepository.sumByType(TypeTransaction.RETRAIT);

        return Map.of(
                "count", count,
                "sumDepots", sumDepots,
                "sumRetraits", sumRetraits);
    }
}

