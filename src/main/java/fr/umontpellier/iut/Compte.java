package fr.umontpellier.iut;
import java.util.ArrayList;

public class Compte {
    private String Pseudo;
    private String Email;
    private String Adresse;
    private double Soldecompte = 0.0;
    private ArrayList<OffreEnchere> ListeOffreEnchere = new ArrayList<>();  //Liste d'offre d'enchere de la classe Compte

    public Compte(String pseudo, String email, String adresse) {    //Constructeur de la classe Compte.
        Pseudo = pseudo;
        Email = email;
        Adresse = adresse;
    }

    public void crediter(double somme){ //Methode qui permet d'ajouter une somme à son SoldeCompte
        Soldecompte = Soldecompte + somme;
    }

    public void debiter(double somme){ //Methode qui permet de soustraire une somme à son SoldeCompte
        Soldecompte = Soldecompte - somme;
    }


    @Override
    public String toString() {  //ToString de la classe Compte.
        return "Compte{" +
                "Pseudo='" + Pseudo + '\'' +
                ", Email='" + Email + '\'' +
                ", Adresse='" + Adresse + '\'' +
                ", Soldecompte=" + Soldecompte +
                '}';
    }

    public OffreEnchere creerOffre(Produit produit,double prixpropose,double prixmaxpropose){   //Méthode permettant de créer une offre et de l'ajouter dans la liste des Offres d'enchere de l'utilisateur courant.
        OffreEnchere uneOffre;
        int deuxiemeparticipation = 0;  //Permet de verifier si c'est la deuxiemme participation pour le meme produit.
        for (int i = 0; i < ListeOffreEnchere.size(); i++) {
            if (ListeOffreEnchere.get(i).getProduitproposition().getNumero() == produit.getNumero()){
                if (ListeOffreEnchere.size() != 0){
                    deuxiemeparticipation = 1;
                }
            }
        }
        if (prixmaxpropose + produit.getCoutparticipation() > Soldecompte){
            uneOffre = new OffreEnchere(produit, 0, 0,this);
        }
        else {
            if(deuxiemeparticipation == 0) {
                this.debiter(produit.getCoutparticipation());
            }
            uneOffre = new OffreEnchere(produit, prixpropose, prixmaxpropose,this);
        }
        ListeOffreEnchere.add(uneOffre);    //Si pas assez d'argent retourne une offre non valide.
        return uneOffre;
    }

    public String getPseudo() {
        return Pseudo;
    }

    public double getSoldecompte() {
        return Soldecompte;
    }
}
