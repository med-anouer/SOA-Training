# 🌐 Atelier SOA — Mise en place d’un Service Web RESTful

**Module : Architecture Orientée Services (SOA)**  
**Année Universitaire : 2026–2027**  
**École : ESPRIT – École d’Ingénieurs**

---

## 🎯 Objectifs de l’atelier

- Comprendre les principes fondamentaux des **services web RESTful**
- Mettre en œuvre une **API REST** pour la gestion des modules pédagogiques
- Appliquer les bonnes pratiques de conception des services REST
- Manipuler les opérations CRUD via des ressources REST

---

## 📚 Pré-requis

- Notions de base en **Java**
- Connaissances en **programmation orientée objet**
- Compréhension des concepts SOA
- Bases du protocole **HTTP**

---

## 🛠️ Outils à installer

- [**JDK (Java Development Kit)**](https://www.oracle.com/in/java/technologies/downloads/)
- [**IntelliJ IDEA**](https://www.jetbrains.com/idea/download/?section=windows)
- [**Apache Maven**](https://maven.apache.org)
- [**Serveur d’applications**](https://tomcat.apache.org)
- [**Postman** ](https://tomcat.apache.org)ou tout autre outil de test d’API REST

---

## 📂 Ressources

- [Énoncé de l’atelier (PDF)](https://github.com/badi3a/SOA-Training/blob/workshop-RestApi/Atelier_REST.pdf)
- [Projet de base fourni (code métier et entités)](https://github.com/badi3a/SOA-Training/tree/workshop-RestApi/Gestion_UE_Etudiant)
- [Supports de cours Web service REST](https://github.com/badi3a/SOA-Training/blob/workshop-RestApi/chapitre_Service%20Web%20REST.pdf)

📎 **Énoncé de l’atelier** :  
➡️ [*Atelier REST n°1 – Mise en place d’un service web RESTful*](https://github.com/badi3a/SOA-Training/blob/workshop-RestApi/Atelier_REST.pdf)

---

## 📦 Travail demandé

Les étudiants doivent :

- Mettre en place un **service web RESTful**
- Implémenter les ressources nécessaires à la gestion pédagogique
- Tester les services REST à l’aide d’un client HTTP
- Vérifier le bon fonctionnement des opérations principales
- Respecter l’architecture et les conventions REST

---

## 🏫 Cadre pédagogique
### Enseignante:- **[Badia Bouhdid](https://www.linkedin.com/in/badiabouhdid)**
Cet atelier est dispensé dans le cadre du **module Architecture Orientée Services (SOA)**  à l’[**École d’Ingénieurs ESPRIT**.](https://www.esprit.tn)

---

## 🗂️ Contenu du dépôt

| Dossier | Atelier | Description |
|---|---|---|
| [`Gestion_Options_Etudiants/`](Gestion_Options_Etudiants) | **REST n°1** + **REST n°5 (JWT + Refresh Token)** | Service web RESTful JAX-RS (Jersey) de gestion des **options** et des **étudiants**, sécurisé par JWT |
| [`WorkshopGraphQL-Options/`](WorkshopGraphQL-Options) | **Atelier GraphQL** | Même domaine métier exposé en **GraphQL** (queries + mutations) |
| [`Gestion_UE_Etudiant/`](Gestion_UE_Etudiant) | Séance 1 (ancienne version) | Premier projet REST (UE / Modules), conservé pour historique |
| [`docs/`](docs) | — | Énoncés des ateliers (PDF / DOCX) |

Chaque projet a son propre `README.md` (structure, déploiement, jeu de tests Postman) et une collection
Postman prête à importer dans son dossier `postman/`.

## ⚙️ Prérequis d'exécution

- JDK 17, Maven 3.8+
- Apache Tomcat **9.x** (javax.*, pas Tomcat 10)
- IntelliJ IDEA (config. Tomcat : *Deployment → War exploded*)
- Postman

> ⚠️ Tomcat n'est **pas** versionné dans ce dépôt (voir `.gitignore`) : installez-le localement.
