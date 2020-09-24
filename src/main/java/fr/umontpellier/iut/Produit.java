package fr.umontpellier.iut;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;

public class Produit {
    private int Numero;
    private String Description;
    private double Prixcourant;
    private LocalDate Datedebut;
    private LocalTime Heuredebut;
    private LocalDate Datefin;
    private LocalTime Heurefin;
    private static double Pasmini = 5.0;  //Vu que l'on ne souhaite pas que l'utilisateur non informaticien y touche on doit le déclarer en Static.
    private double Coutparticipation;
    private boolean EstDisponible = false;  //Bool qui permet d'indiquer si on peut encore encherir ou pas.
    private ArrayList<OffreEnchere> ListeOffreEnchere = new ArrayList<>();

    public Produit(int numero, String description, double prixcourant, double coutparticipation) {  //Constructeur de la classe Produit
        Numero = numero;
        Description = description;
        Prixcourant = prixcourant;
        Coutparticipation = coutparticipation;
    }

    public void demarrer_enchere(){ //Méthode qui permet de démarrer l'enchere.
        Datedebut = LocalDate.now();
        Heuredebut = LocalTime.now();
        EstDisponible = true;
    }

    public void arreter_enchere(){ //Méthode qui permet d'arrèter l'enchere.
        Datefin = LocalDate.now();
        Heurefin = LocalTime.now();
        EstDisponible = false;
    }

    @Override
    public String toString() {  //ToString de la classe Produit.
        return "Produit{" +
                "Numero=" + Numero +
                ", Description='" + Description + '\'' +
                ", Prixcourant=" + Prixcourant +
                ", Datedebut=" + Datedebut +
                ", Heuredebut=" + Heuredebut +
                ", Datefin=" + Datefin +
                ", Heurefin=" + Heurefin +
                ", Coutparticipation=" + Coutparticipation +
                ", EstDisponible=" + EstDisponible +
                '}';
    }

    public int getNumero() {    //Getter pour le numéro de produit.
        return Numero;
    }

    public void ajouterOffre(OffreEnchere Offre){   //Methode qui permet de vérifier si l'offre est valide selon le produit et l'ajoute à la liste d'offre courante.
        boolean OffreInvalide = false;
        if(ListeOffreEnchere.size() > 0){   //Permet de vérifier que la Liste d'enchere n'est pas vide.
            if (Offre.getPrixpropose() < ListeOffreEnchere.get(ListeOffreEnchere.size()-1).getPrixpropose() + Pasmini){ //Vérifie que la nouvel enchere est supérieur à la valeur de l'enchere précédente + le pas
                OffreInvalide = true;
            }
            if (Offre.getCompteOffre().getSoldecompte() < Offre.getPrixmaxpropose() + Coutparticipation + Pasmini){ //Vérifie que le compte peux faire l'offre (prixcourant + pas + cout de participation est inferieur au solde)
                OffreInvalide = true;
            }
        }
        if(EstDisponible == true && OffreInvalide == false){
            if (Offre.getPrixmaxpropose() >= Pasmini + Prixcourant){    //Verifie que le prix max de l'offre est supérieur ou egal au prix courant plus le pas.
                if(Offre.getPrixpropose() >= Pasmini + Prixcourant && Offre.getPrixpropose() <= Offre.getPrixmaxpropose()){ //Verifie que le prix proposé de l'offre est supérieur ou egal
                    Prixcourant = Offre.getPrixpropose();
                    ListeOffreEnchere.add(Offre);
                }
            }
        }
    }

    public OffreEnchere getgagnant(){   //Methode qui retourne l'offre gagnante.
        OffreEnchere OffreMAX;
        OffreEnchere OffrePrixMAX;
        int z = 0;

        if(ListeOffreEnchere.isEmpty()){
            OffreMAX = new OffreEnchere(null,0,0,null);  //Renvoie une offre nul si il n'y a pas d'offre dans la liste d'offres.
        }
        else {

            OffreMAX = ListeOffreEnchere.get(0);
            for (int i = 0; i < ListeOffreEnchere.size(); i++) {
                if (ListeOffreEnchere.get(i).getPrixmaxpropose() == OffreMAX.getPrixmaxpropose()) {
                    Prixcourant = ListeOffreEnchere.get(i).getPrixmaxpropose() + Pasmini;
                    if (ListeOffreEnchere.size() == 1){
                        Prixcourant = ListeOffreEnchere.get(i).getPrixpropose();
                    }
                }
                else{
                    if (ListeOffreEnchere.get(i).getPrixmaxpropose() < OffreMAX.getPrixmaxpropose()){
                        Prixcourant = ListeOffreEnchere.get(i).getPrixmaxpropose() + Pasmini;
                    }
                    else{
                        Prixcourant = OffreMAX.getPrixmaxpropose() + Pasmini;
                        OffreMAX = ListeOffreEnchere.get(i);
                    }
                }
            }
            OffreMAX.getCompteOffre().debiter(Prixcourant);
        }
        return OffreMAX;
    }


    public double getPrixcourant() {
        return Prixcourant;
    }

    public double getCoutparticipation() {
        return Coutparticipation;
    }
}