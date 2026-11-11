package entities;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import java.util.ArrayList;
import java.util.List;

/**
 * Enveloppe utilisée pour la réponse XML de GET /etudiants/option?codeOption=1.
 *
 * Le XML attendu par l'énoncé ne contient PAS l'option de l'étudiant
 * (anneeEtude, email, identifiant, nom, prenom). On utilise donc une petite classe
 * interne (EtudiantXml) plutôt que @XmlTransient sur Etudiant.getOption() :
 * @XmlTransient serait aussi pris en compte par Jackson et ferait disparaître
 * "option" des réponses JSON.
 */
@XmlRootElement(name = "etudiants")
@XmlAccessorType(XmlAccessType.FIELD)
public class EtudiantList {

    @XmlElement(name = "etudiant")
    private List<EtudiantXml> etudiants = new ArrayList<>();

    public EtudiantList() {}

    public EtudiantList(List<Etudiant> etudiants) {
        for (Etudiant e : etudiants) {
            this.etudiants.add(new EtudiantXml(e));
        }
    }

    public List<EtudiantXml> getEtudiants() {
        return etudiants;
    }

    public void setEtudiants(List<EtudiantXml> etudiants) {
        this.etudiants = etudiants;
    }

    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "etudiantXml", propOrder = {"anneeEtude", "email", "identifiant", "nom", "prenom"})
    public static class EtudiantXml {
        private int anneeEtude;
        private String email;
        private String identifiant;
        private String nom;
        private String prenom;

        public EtudiantXml() {}

        public EtudiantXml(Etudiant e) {
            this.anneeEtude = e.getAnneeEtude();
            this.email = e.getEmail();
            this.identifiant = e.getIdentifiant();
            this.nom = e.getNom();
            this.prenom = e.getPrenom();
        }

        public int getAnneeEtude() { return anneeEtude; }
        public String getEmail() { return email; }
        public String getIdentifiant() { return identifiant; }
        public String getNom() { return nom; }
        public String getPrenom() { return prenom; }
    }
}
