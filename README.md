<<<<<<< HEAD
# 🌐 Atelier SOA — Mise en place d’un Service Web RESTful

**Module : Architecture Orientée Services (SOA)**  
**Année Universitaire : 2026–2027**  
=======
# 🧩 Architecture Orientée Services (SOA)

**Module : Architecture Orientée Services (SOA)**  
**Année Universitaire : 2025–2026**  
>>>>>>> c24ef6f34c57327cd45cb1dd2b29eff8bed378df
**École : ESPRIT – École d’Ingénieurs**

---

<<<<<<< HEAD
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
=======
## 📘 Présentation générale du module

Le module **Architecture Orientée Services (SOA)** vise à doter les étudiants des compétences nécessaires pour concevoir, développer et sécuriser des **applications distribuées modernes**, basées sur des services interopérables.

Il couvre l’évolution du Web vers les **services web**, les paradigmes architecturaux associés, ainsi que les technologies majeures utilisées dans les architectures orientées services, telles que **SOAP**, **REST** et **GraphQL**.

Le module adopte une approche **progressive et pratique**, combinant cours théoriques, ateliers techniques et workshops applicatifs.

---

## 🎯 Objectifs pédagogiques du module

À l’issue de ce module, l’étudiant sera capable de :

- Comprendre les **fondements des architectures orientées services**
- Concevoir et exposer des **services web** selon différents styles architecturaux
- Comparer et choisir entre **SOAP, REST et GraphQL**
- Sécuriser des services web à l’aide de mécanismes standards
- Mettre en œuvre des **API interopérables et évolutives**

---

## 📚 Contenu du module

### 🔹 Cours

### 📌 Cours 1 — Introduction aux Architectures orientées services
➡️ [`Workshop-ProjectStudy`](https://github.com/badi3a/SOA-Training/tree/Workshop-ProjectStudy)

- Analyse d’une application existante
- Étude des limites architecturales
- Proposition d’une refonte orientée services

---

### 📌 Cours 2— Découverte des services Web étendus: SOAP
➡️ [`Workshop-SOAP`](https://github.com/badi3a/SOA-Training/tree/workshop-soap)

- Analyse du WSDL
- Compréhension des messages SOAP
- Consommation d’un service web via SoapUI

---

### 📌 Cours 3— Services Web REST
➡️ [`Workshop-REST`](https://github.com/badi3a/SOA-Training/tree/workshop-RestApi)

- Conception de ressources REST
- Implémentation des opérations CRUD
- Tests des services REST

---

### 📌 Cours —4 Sécurisation des Services REST
➡️ [`Workshop-REST-Security`](https://github.com/badi3a/SOA-Training/tree/workshop-security)

- Authentification basée sur les jetons (JWT)
- Autorisation d’accès aux ressources
- Sécurisation des API REST avec JAX-RS

---

### 📌 Workshop 5— Mise en place d’une API GraphQL
➡️ [`Workshop-GraphQL`](https://github.com/badi3a/SOA-Training/tree/workshop-graphQL)

- Introduction à GraphQL
- Conception du schéma GraphQL
- Implémentation des queries et mutations
- Comparaison avec REST

---

## 📊 Acquis d’Apprentissage du Module (AAP)

Les ateliers et workshops du module permettent de couvrir les **AAP suivants** :

- **AAP1** : Analyser une architecture logicielle existante
- **AAP2** : Concevoir une architecture orientée services
- **AAP3** : Développer et exposer des services web
- **AAP4** : Consommer et intégrer des services web
- **AAP5** : Sécuriser des services web
- **AAP6** : Comparer et justifier le choix d’un style architectural

---

## 📂 Organisation du repository

```text
SOA-Training/
│
├── Workshop-1-ProjectStudy/
├── Workshop-SOAP/
├── Workshop-REST/
├── Workshop-REST-Security/
├── Workshop-GraphQL/
│
└── README.md
```
## 🏫 Cadre pédagogique

### Enseignante : [Badia Bouhdid](https://www.linkedin.com/in/badiabouhdid)


Ce cours a été développé dans le cadre du **module Architecture Orientée Services (SOA)**,  
en collaboration avec d’autres enseignants, à l’**[École d’Ingénieurs ESPRIT](https://www.esprit.tn)**.



<p align="center">
  <img src="https://www.esprit.tn/wp-content/uploads/2025/03/Entree-campus-scaled.jpg" alt="Entrée du campus ESPRIT" />
  <br>
  <em>École d’Ingénieurs ESPRIT – Campus</em>
</p>


>>>>>>> c24ef6f34c57327cd45cb1dd2b29eff8bed378df
