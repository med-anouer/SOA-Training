# Gestion des Options et des Étudiants — Service web RESTful + JWT

Ateliers SOA : **REST n°1** (mise en place du service) et **REST n°5** (sécurisation JWT + Refresh Token).

## Structure

```
Gestion_Options_Etudiants
├── pom.xml                                  (packaging war, Jersey 2.27, jjwt 0.6.0)
├── postman/Gestion_Options_Etudiants.postman_collection.json
└── src/main
    ├── java
    │   ├── entities
    │   │   ├── Option.java          equals()/hashCode() sur codeOption
    │   │   ├── Etudiant.java        equals()/hashCode() sur identifiant
    │   │   ├── EtudiantList.java    enveloppe XML <etudiants>
    │   │   ├── Credentials.java     (JWT) login + mot de passe
    │   │   └── TokenResponse.java   (JWT) accessToken + refreshToken
    │   ├── metiers                  OptionBusiness, EtudiantBusiness (fournis, inchangés)
    │   ├── ressources
    │   │   ├── OptionRessources.java        @Path("options")   @Secured
    │   │   ├── EtudiantRessources.java      @Path("etudiants") @Secured
    │   │   └── AuthenticationEndPoint.java  @Path("authentication") (public)
    │   ├── filter
    │   │   ├── Secured.java                 annotation @NameBinding
    │   │   └── AuthenticationFilter.java    vérifie l'access token (401 sinon)
    │   └── utilities
    │       ├── RestActivator.java   @ApplicationPath("rest")
    │       ├── JwtService.java      génération / validation / révocation des jetons
    │       └── Metiers.java         singletons des classes métier (voir « Pièges »)
    └── webapp/WEB-INF/web.xml
```

## Déploiement

1. IntelliJ → *Run/Debug Configurations* → **Tomcat Server (Local)** (Tomcat 9) → *Deployment* →
   `Gestion_Options_Etudiants:war exploded` (Application context : `/Gestion_Options_Etudiants_war_exploded`).
2. URL de base : `http://localhost:8080/Gestion_Options_Etudiants_war_exploded/rest`
3. Ou en ligne de commande : `mvn clean package` puis copier `target/Gestion_Options_Etudiants.war`
   dans `webapps/` de Tomcat (URL de base alors `http://localhost:8080/Gestion_Options_Etudiants/rest`).

Importer ensuite `postman/Gestion_Options_Etudiants.postman_collection.json` dans Postman
(adapter la variable `baseUrl` si besoin) et lancer **Login** en premier.

## Ressources (atelier n°1)

Toutes les ressources `options` et `etudiants` exigent `Authorization: Bearer <accessToken>`.

| Méthode | URL | Succès | Erreurs |
|---|---|---|---|
| POST | `/options` | 201 + option créée (JSON) | 409 code déjà utilisé |
| GET | `/options` | 200 liste JSON | 404 liste vide |
| GET | `/options?domaine=Mathématiques` | 200 liste JSON | 404 aucun résultat |
| GET | `/options/{code}` | 200 JSON | 404 |
| PUT | `/options/{code}` | 200 + option modifiée | 404 |
| DELETE | `/options/{code}` | 200 | 404 |
| POST | `/etudiants` | 201 + étudiant créé (JSON) | 404 option inexistante, 400 corps invalide, 409 identifiant déjà utilisé |
| GET | `/etudiants` (`?option=1` facultatif) | 200 liste JSON | 404 liste vide |
| GET | `/etudiants/{identifiant}` | 200 JSON | 404 |
| PUT | `/etudiants/{identifiant}` | 200 + étudiant modifié | 404 étudiant ou option inexistant |
| DELETE | `/etudiants/{identifiant}` | 200 | 404 |
| GET | `/etudiants/option?codeOption=1` | 200 **XML** (`<etudiants>`) | 404 |

## Sécurité JWT + Refresh Token (atelier n°5)

| Méthode | URL | Corps | Résultat |
|---|---|---|---|
| POST | `/authentication` | JSON `{"username":"I001","password":"pwd001"}` | 200 `{accessToken, refreshToken, tokenType, expiresIn}` / 403 |
| POST | `/authentication/refresh` | form-urlencoded `refreshToken` | 200 nouveau couple (l'ancien refresh token est consommé) / 401 |
| POST | `/authentication/logout` | form-urlencoded `refreshToken` | 204 (refresh token révoqué) |

Scénarios à dérouler (tableaux B4 et C4 de l'énoncé) :

| # | Scénario | Résultat attendu |
|---|---|---|
| 1 | `GET /options` sans jeton | 401 |
| 2 | `GET /options` avec access token valide | 200 |
| 3 | jeton modifié d'une lettre | 401 |
| 4 | access token expiré (mettre `ACCESS_MINUTES = 1`, attendre 1 min) | 401 |
| 5 | `GET /etudiants/I001` sans puis avec jeton | 401 puis 200 |
| 6 | `POST /authentication` sans jeton | 200 (seule ressource publique) |
| 7 | refresh avec le refresh token | 200, nouveau couple |
| 8 | rejeu de l'ancien refresh token | 401 (rotation) |
| 9 | refresh token collé dans *Bearer Token* d'une ressource | 401 |
| 10 | logout puis refresh avec le même jeton | 204 puis 401 |

> Le contenu d'un jeton se lit sur <https://jwt.io> (claims `sub`, `iss`, `iat`, `exp`, `type`, `jti`).

## Choix d'implémentation et pièges évités

- **`utilities/Metiers.java`** : les constructeurs de `OptionBusiness` / `EtudiantBusiness` *réinitialisent* leurs listes
  statiques, et `new EtudiantBusiness()` crée lui-même un `OptionBusiness`. Si chaque ressource faisait `new ...` au premier
  appel, un `POST /options` suivi du premier appel à `/etudiants` effaçait l'option ajoutée. Les deux objets sont donc
  créés **une seule fois** dans `Metiers`.
- **XML sans `option`** : pour obtenir le XML de l'énoncé (anneeEtude, email, identifiant, nom, prenom), on passe par
  `EtudiantList.EtudiantXml` et non par `@XmlTransient` sur `Etudiant.getOption()` : Jackson tient compte des annotations
  JAXB et l'`option` disparaîtrait aussi des réponses **JSON**.
- **`PUT`** : le code / l'identifiant de l'URL fait foi (l'exemple de l'énoncé envoie `codeOption: 2` sur `/options/1`).
- L'énoncé écrit `GET /UE/1` pour récupérer une option : c'est une coquille, la route est `GET /options/{code}`.
- `authenticate()` de l'atelier n°5 accepte tout couple non vide (simplification pédagogique). La clé secrète est dans le
  code uniquement pour l'atelier ; en production : variable d'environnement / coffre de secrets, et table en base pour les refresh tokens.
