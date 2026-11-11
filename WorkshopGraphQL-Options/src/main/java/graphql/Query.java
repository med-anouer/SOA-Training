package graphql;

import com.coxautodev.graphql.tools.GraphQLQueryResolver;
import entities.Etudiant;
import entities.Option;
import repository.EtudiantBusiness;
import repository.OptionBusiness;

import java.util.ArrayList;
import java.util.List;

/**
 * Resolver des opérations de LECTURE. Chaque méthode correspond à un champ du type Query
 * du fichier schema.graphqls (même nom, mêmes paramètres).
 */
public class Query implements GraphQLQueryResolver {

    private final OptionBusiness optionBusiness;
    private final EtudiantBusiness etudiantBusiness;

    public Query(OptionBusiness optionBusiness, EtudiantBusiness etudiantBusiness) {
        this.optionBusiness = optionBusiness;
        this.etudiantBusiness = etudiantBusiness;
    }

    // ---------- Options ----------
    public List<Option> getAllOptions() {
        return optionBusiness.getListeOptions();
    }

    public List<Option> getOptionsByDomaine(String domaine) {
        return optionBusiness.getOptionsByDomaine(domaine);
    }

    public Option getOptionByCode(int codeOption) {
        return optionBusiness.getOptionByCode(codeOption);
    }

    // ---------- Etudiants ----------
    public List<Etudiant> getAllEtudiants() {
        return etudiantBusiness.getAllEtudiants();
    }

    public Etudiant getEtudiantById(String identifiant) {
        return etudiantBusiness.getEtudiantByIdentifiant(identifiant);
    }

    public List<Etudiant> getEtudiantsByOption(int codeOption) {
        Option option = optionBusiness.getOptionByCode(codeOption);
        if (option == null) {
            return new ArrayList<>();   // option inconnue : aucun étudiant (évite un NullPointerException)
        }
        return etudiantBusiness.getEtudiantsByOption(option);
    }
}
