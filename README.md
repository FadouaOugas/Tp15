# Service Bancaire GraphQL

Application Spring Boot moderne exposant une API GraphQL complète pour la gestion de comptes bancaires et de transactions.

## Description

Ce projet est une application bancaire refactorisée et personnalisée permettant de gérer des comptes bancaires (courant et épargne) et leurs transactions via une API GraphQL. L'application utilise une base de données H2 en mémoire pour le développement et le test.

## Stack Technique

- Spring Boot 3.5.7 - Framework Java
- Spring GraphQL - API GraphQL
- Spring Data JPA - Persistance des données
- H2 Database - Base de données en mémoire
- Lombok - Réduction du code boilerplate
- Java 17 - Version du langage

## Installation

### Prérequis

- Java 17 ou supérieur
- Maven 3.6+

### Étapes d'installation

1. Cloner le projet (ou télécharger les sources)
2. Ouvrir un terminal dans le répertoire du projet
3. Compiler le projet :
```bash
mvn clean install
```

4. Lancer l'application :
```bash
mvn spring-boot:run
```

L'application sera accessible sur le port 8082.

## Endpoints

- GraphQL API : `http://localhost:8082/graphql`
- GraphiQL (Interface interactive) : `http://localhost:8082/graphiql`
- H2 Console : `http://localhost:8082/h2-console`

## Requêtes GraphQL disponibles

### Queries

- `allComptes` - Récupère la liste de tous les comptes
- `compteById(id: ID)` - Récupère un compte par son identifiant
- `totalSolde` - Calcule les statistiques des soldes (nombre, somme, moyenne)
- `allTransactions` - Récupère toutes les transactions
- `compteTransactions(id: ID)` - Récupère les transactions d'un compte spécifique
- `transactionStats` - Calcule les statistiques des transactions (nombre, somme des dépôts, somme des retraits)

### Mutations

- `saveCompte(compte: CompteRequest)` - Crée ou met à jour un compte bancaire
- `addTransaction(transaction: TransactionRequest)` - Ajoute une nouvelle transaction à un compte

## Exemples d'utilisation

### Créer un compte

```graphql
mutation {
  saveCompte(compte: {
    solde: 1000.0
    dateCreation: "2024/01/15"
    type: COURANT
  }) {
    id
    solde
    type
  }
}
```

### Ajouter une transaction

```graphql
mutation {
  addTransaction(transaction: {
    compteId: 1
    montant: 500.0
    date: "2024/01/20"
    type: DEPOT
  }) {
    id
    montant
    type
  }
}
```

### Récupérer tous les comptes

```graphql
query {
  allComptes {
    id
    solde
    dateCreation
    type
  }
}
```

## Structure du projet

```
src/
├── main/
│   ├── java/
│   │   └── com/fadouaougas/banque_service/
│   │       ├── controllers/     # Contrôleurs GraphQL
│   │       ├── entities/         # Entités JPA
│   │       ├── repositories/     # Repositories Spring Data
│   │       ├── dto/              # Objets de transfert de données
│   │       ├── enums/            # Énumérations
│   │       └── exceptions/       # Gestion des exceptions
│   └── resources/
│       ├── application.properties
│       └── graphql/
│           └── schema.graphqls  # Schéma GraphQL
└── test/
    └── java/                     # Tests unitaires
```

## Auteur

Fadoua Ougas

---

Projet refactorisé et personnalisé
