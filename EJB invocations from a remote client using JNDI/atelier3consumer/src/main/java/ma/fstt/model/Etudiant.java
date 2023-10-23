package ma.fstt.model;

import jakarta.persistence.*;

import java.io.Serializable;

@Entity
public class Etudiant implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    private Long id_etudiant;

    @Column(nullable = true)
    private String nom;
    @Column(nullable = true)
    private String prenom;
    @Column(nullable = true)
    private String cne;
    @Column(nullable = true)
    private String adresse;
    @Column(nullable = true)
    private String niveau;

    public Etudiant(){}
    public Etudiant(Long id_etudiant, String nom, String prenom, String cne, String adresse, String niveau) {
        this.id_etudiant = id_etudiant;
        this.nom = nom;
        this.prenom = prenom;
        this.cne = cne;
        this.adresse = adresse;
        this.niveau = niveau;
    }

    public Long getId_etudiant() {
        return id_etudiant;
    }

    public void setId_etudiant(Long id_etudiant) {
        this.id_etudiant = id_etudiant;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getCne() {
        return cne;
    }

    public void setCne(String cne) {
        this.cne = cne;
    }

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public String getNiveau() {
        return niveau;
    }

    public void setNiveau(String niveau) {
        this.niveau = niveau;
    }

    @Override
    public String toString() {
        return "Etudiant{" +
                "id_etudiant=" + id_etudiant +
                ", nom='" + nom + '\'' +
                ", prenom='" + prenom + '\'' +
                ", cne='" + cne + '\'' +
                ", adresse='" + adresse + '\'' +
                ", niveau='" + niveau + '\'' +
                '}';
    }
}
