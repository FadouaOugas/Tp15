package com.fadouaougas.banque_service.repositories;

import com.fadouaougas.banque_service.entities.Compte;
import com.fadouaougas.banque_service.entities.Transaction;
import com.fadouaougas.banque_service.enums.TypeTransaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Repository pour la gestion des transactions
 */
@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Long> {

    /**
     * Recherche des transactions par compte
     */
    List<Transaction> findByCompte(Compte compte);

    /**
     * Calcule la somme des montants par type
     */
    @Query("SELECT COALESCE(SUM(t.montant), 0) FROM Transaction t WHERE t.type = :type")
    double sumByType(@Param("type") TypeTransaction type);
}

