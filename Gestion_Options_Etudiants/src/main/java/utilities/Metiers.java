package utilities;

import metiers.EtudiantBusiness;
import metiers.OptionBusiness;

/**
 * Point unique de création des classes métier (singletons).
 *
 * POURQUOI ? Les constructeurs de OptionBusiness et EtudiantBusiness REINITIALISENT leurs listes
 * statiques avec les données de test, et new EtudiantBusiness() crée lui-même un OptionBusiness.
 * Si chaque ressource faisait "new ..." quand Jersey la charge (au premier appel), un
 * POST /options suivi du premier appel à /etudiants effacerait l'option qu'on vient d'ajouter.
 * En créant les deux objets une seule fois, au démarrage, la liste n'est initialisée qu'une fois.
 */
public final class Metiers {

    public static final OptionBusiness OPTIONS;
    public static final EtudiantBusiness ETUDIANTS;

    static {
        OPTIONS = new OptionBusiness();
        ETUDIANTS = new EtudiantBusiness();   // doit rester créé en dernier (voir ci-dessus)
    }

    private Metiers() {
    }
}
