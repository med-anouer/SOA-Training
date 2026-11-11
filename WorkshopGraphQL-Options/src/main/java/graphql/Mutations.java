package graphql;

import com.coxautodev.graphql.tools.GraphQLMutationResolver;
import entities.Etudiant;
import entities.Option;
import repository.EtudiantBusiness;
import repository.OptionBusiness;

/**
 * Resolver des opérations d'ECRITURE. Chaque méthode correspond à un champ du type Mutation
 * du fichier schema.graphqls. Toutes retournent un boolean (true = succès).
 */
public class Mutations implements GraphQLMutationResolver {

    private final OptionBusiness optionBusiness;
    private final EtudiantBusiness etudiantBusiness;

    public Mutations(OptionBusiness optionBusiness, EtudiantBusiness etudiantBusiness) {
        this.optionBusiness = optionBusiness;
        this.etudiantBusiness = etudiantBusiness;
    }

    // ---------- Options ----------
    public boolean addOption(int codeOption, String libelle, String domaine, String responsable,
                             int credits, int semestre, int capacite) {
        if (optionBusiness.getOptionByCode(codeOption) != null) {
            return false;   // ce code existe déjà
        }
        return optionBusiness.addOption(
                new Option(codeOption, libelle, domaine, responsable, credits, semestre, capacite));
    }

    public boolean updateOption(int codeOption, String libelle, String domaine, String responsable,
                                int credits, int semestre, int capacite) {
        return optionBusiness.updateOption(codeOption,
                new Option(codeOption, libelle, domaine, responsable, credits, semestre, capacite));
    }

    public boolean deleteOption(int codeOption) {
        return optionBusiness.deleteOption(codeOption);
    }

    // ---------- Etudiants ----------
    public boolean addEtudiant(String identifiant, String nom, String prenom, int anneeEtude,
                               String email, int codeOption) {
        Option option = optionBusiness.getOptionByCode(codeOption);
        if (option == null) {
            return false;   // option inexistante
        }
        if (etudiantBusiness.getEtudiantByIdentifiant(identifiant) != null) {
            return false;   // identifiant déjà utilisé
        }
        return etudiantBusiness.addEtudiant(new Etudiant(identifiant, nom, prenom, option, anneeEtude, email));
    }

    public boolean updateEtudiant(String identifiant, String nom, String prenom, int anneeEtude,
                                  String email, int codeOption) {
        Option option = optionBusiness.getOptionByCode(codeOption);
        if (option == null) {
            return false;
        }
        return etudiantBusiness.updateEtudiant(identifiant,
                new Etudiant(identifiant, nom, prenom, option, anneeEtude, email));
    }

    public boolean deleteEtudiant(String identifiant) {
        return etudiantBusiness.deleteEtudiant(identifiant);
    }
}
