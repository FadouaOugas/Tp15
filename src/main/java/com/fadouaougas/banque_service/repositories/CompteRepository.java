package com.fadouaougas.banque_service.repositories;

import com.fadouaougas.banque_service.entities.Compte;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

/**
 * Repository pour la gestion des comptes
 */
@Repository
public interface CompteRepository extends JpaRepository<Compte, Long> {

    /**
     * Calcule la somme des soldes
     */
    @Query("SELECT SUM(c.solde) FROM Compte c")
    double sumSoldes();
}

