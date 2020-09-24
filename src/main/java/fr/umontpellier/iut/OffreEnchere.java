package fr.umontpellier.iut;
import java.time.LocalDate;
import java.time.LocalTime;

public class OffreEnchere {
    private Produit Produitproposition;
    private LocalDate Dateproposition;
    private LocalTime Heureproposition;
    private double Prixpropose;
    private double Prixmaxpropose;
    private Compte CompteOffre;

    public OffreEnchere(Produit produitproposition, double prixpropose, double prixmaxpropose, Compte compteOffre) {
        Produitproposition = produitproposition;
        Dateproposition = LocalDate.now();
        Heureproposition = LocalTime.now();
        Prixpropose = prixpropose;
        Prixmaxpropose = prixmaxpropose;
        CompteOffre = compteOffre;
    }

    @Override
    public String toString() {  //ToString de la classe OffreEnchere.
        if (Produitproposition == null){
            return "ERROR";
        }
        else {
            return "OffreEnchere{" +
                    "Numéro de Produit=" + Produitproposition.getNumero() +
                    ", Dateproposition=" + Dateproposition +
                    ", Heureproposition=" + Heureproposition +
                    ", Prixpropose=" + Prixpropose +
                    ", Prixmaxpropose=" + Prixmaxpropose +
                    '}';
        }
    }

    public double getPrixpropose() {    //Getter pour le prix proposé.
        return Prixpropose;
    }

    public double getPrixmaxpropose() { //Getter pour le prix max proposé.
        return Prixmaxpropose;
    }

    public void setPrixpropose(double prixpropose) {    //Setter pour le prix proposé.
        Prixpropose = prixpropose;
    }

    public Compte getCompteOffre() {
        return CompteOffre;
    }

    public Produit getProduitproposition() {
        return Produitproposition;
    }
}
