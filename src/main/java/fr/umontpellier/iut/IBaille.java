package fr.umontpellier.iut;
import java.util.Scanner;


public class IBaille {
    public static void main(String[] args) {
        int z = 1;
        Compte Jorge = new Compte("Jorge", "Jorge@gmail.com", "5 rue de la paix");
        Compte Brigitte = new Compte("Brigitte", "Brigitte@gmail.com", "18 avenue de la foix");
        Compte Julien = new Compte("Julien", "Julien@gmail.com", "25 rue du couchant");

        Jorge.crediter(800.0);  //Chaque Utilisateur possède un Solde de 100 euros
        Brigitte.crediter(800.0);
        Julien.crediter(800.0);

        Produit AilPheuneX = new Produit(0, "Le meilleur de ce qui se fait en téléphonie mobile.", 550, 5.0);

        AilPheuneX.demarrer_enchere();

        while(1==1) {
            Scanner saisie = new Scanner(System.in);

            System.out.println("Quel prix proposez vous (Jorge) ? :");
            int prixpropose = saisie.nextInt();
            System.out.println("Quel prix max proposez vous (Jorge) ? :");
            int prixmax = saisie.nextInt();
            OffreEnchere offreJorge = Jorge.creerOffre(AilPheuneX, prixpropose, prixmax);
            AilPheuneX.ajouterOffre(offreJorge);

            System.out.println("Quel prix proposez vous (Brigitte) ? :");
            prixpropose = saisie.nextInt();
            System.out.println("Quel prix max proposez vous (Brigitte) ? :");
            prixmax = saisie.nextInt();
            OffreEnchere offreBrigitte = Brigitte.creerOffre(AilPheuneX, prixpropose, prixmax);
            AilPheuneX.ajouterOffre(offreBrigitte);

            System.out.println("Quel prix proposez vous (Julien) ? :");
            prixpropose = saisie.nextInt();
            System.out.println("Quel prix max proposez vous (Julien) ? :");
            prixmax = saisie.nextInt();
            OffreEnchere offreJulien = Julien.creerOffre(AilPheuneX, prixpropose, prixmax);
            AilPheuneX.ajouterOffre(offreJulien);

            AilPheuneX.arreter_enchere();
            System.out.println("Le gagnant de l'enchere est " + AilPheuneX.getgagnant().getCompteOffre().getPseudo()+"\nIl a payé "+ AilPheuneX.getPrixcourant());
        }
    }
}