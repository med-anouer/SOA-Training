# WorkshopGraphQL — API GraphQL « Gestion étudiants et options »

Atelier GraphQL (ESPRIT, 2026-2027) : mêmes entités que l'atelier REST (`Option`, `Etudiant`) exposées en **GraphQL**
avec un backend Java (graphql-java 3.0.0 + graphql-java-tools 3.2.0 + graphql-java-servlet 4.0.0).

## Structure

```
WorkshopGraphQL-Options
├── pom.xml
├── postman/WorkshopGraphQL.postman_collection.json
└── src/main
    ├── java
    │   ├── entities      Option, Etudiant
    │   ├── repository    OptionBusiness, EtudiantBusiness   (fournis, inchangés)
    │   └── graphql
    │       ├── GraphQLEndPoint.java   servlet POST /graphql  (@WebServlet)
    │       ├── Query.java             resolvers de lecture
    │       └── Mutations.java         resolvers d'écriture
    └── resources/schema.graphqls      schéma des données
```

## Déploiement

- IntelliJ : Tomcat 9 → `WorkshopGraphQL:war exploded`
- Endpoint : `http://localhost:8080/<contexte>/graphql` (ex. `.../WorkshopGraphQL_war_exploded/graphql`)
- Postman : *New → HTTP* → méthode **POST** → *Body → GraphQL* (ou importer `postman/WorkshopGraphQL.postman_collection.json`
  et adapter la variable `graphqlUrl`).

## Opérations

| Énoncé | Opération | Type |
|---|---|---|
| A.1 | `getAllOptions` | query |
| A.2 | `addOption(codeOption, libelle, domaine, responsable, credits, semestre, capacite): Boolean!` | mutation |
| A.3 | `updateOption(codeOption, libelle, ...): Boolean!` | mutation |
| A.4 | `getOptionsByDomaine(domaine)` | query |
| A.5 | `getOptionByCode(codeOption)` | query |
| A.6 | `deleteOption(codeOption): Boolean!` | mutation |
| B.1 | `addEtudiant(identifiant, nom, prenom, anneeEtude, email, codeOption): Boolean!` | mutation |
| B.2 | `getEtudiantById(identifiant)` | query |
| B.3 | `getEtudiantsByOption(codeOption)` | query |
| B.4 | `updateEtudiant(identifiant, nom, prenom, anneeEtude, email, codeOption): Boolean!` | mutation |
| B.5 | `deleteEtudiant(identifiant): Boolean!` | mutation |
| — | `getAllEtudiants` | query |

Les mutations renvoient `true` en cas de succès, `false` sinon (code déjà utilisé, élément ou option introuvable).

### Exemples

```graphql
mutation { addOption(codeOption: 6, libelle: "Informatique", domaine: "Informatique",
                     responsable: "M. Responsable Informatique", credits: 30, semestre: 2, capacite: 50) }
# -> { "data": { "addOption": true } }

query { getOptionsByDomaine(domaine: "Informatique") { codeOption libelle domaine responsable } }
```

## Travail demandé n°3 — plusieurs `query` dans une seule requête : que remarque-t-on ?

```graphql
query {
  getAllOptions { codeOption libelle }
  getEtudiantById(identifiant: "I001") { nom prenom }
  getOptionsByDomaine(domaine: "Chimie") { libelle }
}
```

- **Une seule requête HTTP** (un seul `POST /graphql`) et **une seule réponse** `data` qui contient un champ par opération :
  `getAllOptions`, `getEtudiantById`, `getOptionsByDomaine`. En REST il aurait fallu trois appels (`/options`, `/etudiants/I001`,
  `/options?domaine=Chimie`).
- Le client choisit **exactement les champs** qu'il veut (pas de sur-récupération, pas de sous-récupération).
- Pour appeler **deux fois la même opération** avec des arguments différents, il faut des **alias** :
  `info: getOptionsByDomaine(domaine: "Informatique") {...}  maths: getOptionsByDomaine(domaine: "Mathématiques") {...}`.
- Les *queries* d'une même requête sont exécutées indépendamment (lecture seule, ordre sans importance), alors que les
  *mutations* d'une même requête sont exécutées **l'une après l'autre**, dans l'ordre.

## REST vs GraphQL (objectif 1)

| | REST | GraphQL |
|---|---|---|
| Endpoints | un par ressource (`/options`, `/etudiants`...) | un seul (`/graphql`) |
| Forme de la réponse | fixée par le serveur | choisie par le client (sélection de champs) |
| Plusieurs ressources | plusieurs requêtes | une requête |
| Verbes | GET / POST / PUT / DELETE | `query` (lecture) / `mutation` (écriture), toujours POST |
| Codes HTTP | portent le résultat (404, 201...) | 200 + objet `errors` ; ici les mutations renvoient un `Boolean` |
| Contrat | OpenAPI (optionnel) | schéma typé obligatoire, introspectable |
| Limites | sur / sous-récupération, versions d'API | cache HTTP plus difficile, requêtes coûteuses à maîtriser |

## Remarques

- Le fichier du schéma s'appelle `schema.graphqls` (l'énoncé écrit tantôt `.graphql`, tantôt `.graphqls`) ; le nom utilisé dans
  `GraphQLEndPoint` (`.file("schema.graphqls")`) doit être **identique** au nom du fichier de `src/main/resources`.
- Le package s'appelle `graphql`, comme dans l'énoncé (les classes de la bibliothèque sont aussi dans `graphql.*`, sans collision ici).
