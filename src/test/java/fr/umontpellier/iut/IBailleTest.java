package fr.umontpellier.iut;

import org.junit.Assert;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;

class IBailleTest {
    Compte Jorge, Brigitte, Julien;
    Produit Peluche;

    @BeforeEach
    void Initialisation() {
        Jorge = new Compte("Jorge", "Jorge@gmail.com", "5 rue de la paix");
        Brigitte = new Compte("Brigitte", "Brigitte@gmail.com", "18 avenue de la foix");
        Julien = new Compte("Julien", "Julien@gmail.com", "25 rue du couchant");

        Jorge.crediter(100.0);  //Chaque Utilisateur des Tests possède un Solde de 100 euros
        Brigitte.crediter(100.0);
        Julien.crediter(100.0);

        Peluche = new Produit(0, "Joli petite peluche d'ours qui ne demande qu'a etre caliné", 25.0, 5.0);
    }

    @Disabled //Desactivez car test reste bloqué dans le main à cause du scanner.
    @Test
    public void test_if_main_method_was_implemented() {
        assertDoesNotThrow(() -> IBaille.main(new String[1]));
    }

    @Test
    public void retourne_le_gagnant_avec_prixmax_le_plus_élevée() {
        Peluche.demarrer_enchere();
        OffreEnchere offreJorge = Jorge.creerOffre(Peluche, 30.0, 50.0);
        Peluche.ajouterOffre(offreJorge);
        OffreEnchere offreBrigitte = Brigitte.creerOffre(Peluche, 35.0, 40.0);
        Peluche.ajouterOffre(offreBrigitte);
        OffreEnchere offreJulien = Julien.creerOffre(Peluche, 40.0, 40.0);
        Peluche.ajouterOffre(offreJulien);
        Peluche.arreter_enchere();
        assertEquals(50,Peluche.getgagnant().getCompteOffre().getSoldecompte());    //100 - 45 (le prix courant) plus 5 (le cout de participation) = 50
        assertEquals("Jorge",Peluche.getgagnant().getCompteOffre().getPseudo());
    }

    @Test
    public void retourne_le_gagnant_avec_prixmax_le_plus_élevée_et_qui_propose_un_prix_sup_au_pas() {
        Peluche.demarrer_enchere();
        OffreEnchere offreJorge = Jorge.creerOffre(Peluche, 3.0, 50.0); //Offre invalide
        Peluche.ajouterOffre(offreJorge);
        OffreEnchere offreBrigitte = Brigitte.creerOffre(Peluche, 30.0, 40.0);
        Peluche.ajouterOffre(offreBrigitte);
        OffreEnchere offreJulien = Julien.creerOffre(Peluche, 30.0, 30.0);  //Offre invalide car prixpropose pas supérieur au prix courant (30) + pas.
        Peluche.ajouterOffre(offreJulien);
        Peluche.arreter_enchere();
        assertEquals(65,Peluche.getgagnant().getCompteOffre().getSoldecompte());    //100 - 30 (le prix courant) plus 5 (le cout de participation) = 65
        assertEquals("Brigitte",Peluche.getgagnant().getCompteOffre().getPseudo());
    }

    @Test
    public void retourne_le_message_ERROR_si_pas_d_offres() {
        Peluche.demarrer_enchere();
        Peluche.arreter_enchere();
        assertEquals("ERROR", Peluche.getgagnant().toString());
    }

    @Test
    public void retourne_le_message_ERROR_si_enchere_non_disponible() {
        assertEquals("ERROR", Peluche.getgagnant().toString()); //Enchere non démarré
    }

    @Test
    public void retourne_le_message_ERROR_si_personne_ne_propose_une_offre_valide_un_prix_propose_inferieur_au_pas() {
        Peluche.demarrer_enchere();
        OffreEnchere offreJorge = Jorge.creerOffre(Peluche, 3.0, 50.0);
        Peluche.ajouterOffre(offreJorge);
        OffreEnchere offreBrigitte = Brigitte.creerOffre(Peluche, 3.0, 40.0);
        Peluche.ajouterOffre(offreBrigitte);
        OffreEnchere offreJulien = Julien.creerOffre(Peluche, 3.0, 30.0);
        Peluche.ajouterOffre(offreJulien);
        Peluche.arreter_enchere();
        assertEquals("ERROR", Peluche.getgagnant().toString());
    }

    @Test
    public void retourne_le_premier_utilisateur_a_avoir_proposé_une_offre_si_tout_le_monde_a_proposé_le_meme_prix_max_et_le_prix_payé_est_égal_à_prixmax_plus_pas() {
        Peluche.demarrer_enchere();
        OffreEnchere offreJorge = Jorge.creerOffre(Peluche, 30.0, 50.0);
        Peluche.ajouterOffre(offreJorge);
        OffreEnchere offreBrigitte = Brigitte.creerOffre(Peluche, 35.0, 50.0);
        Peluche.ajouterOffre(offreBrigitte);
        OffreEnchere offreJulien = Julien.creerOffre(Peluche, 40.0, 50.0);
        Peluche.ajouterOffre(offreJulien);
        Peluche.arreter_enchere();
        assertEquals(40,Peluche.getgagnant().getCompteOffre().getSoldecompte());    //100 - 55 (le prix courant) plus 5 (le cout de participation) = 40
        assertEquals("Jorge",Peluche.getgagnant().getCompteOffre().getPseudo());
    }

    @Test
    public void retourne_le_premier_a_avoir_proposé_une_offre_valide_meme_si_le_second_propose_une_offre_avec_un_prixmax_plus_élevé() {
        Peluche.demarrer_enchere();
        OffreEnchere offreJorge = Jorge.creerOffre(Peluche, 30.0, 50.0);
        Peluche.ajouterOffre(offreJorge);
        OffreEnchere offreBrigitte = Brigitte.creerOffre(Peluche, 7.0, 55.0);   //Offre invalide prixpropose inferieur prix de base du produit
        Peluche.ajouterOffre(offreBrigitte);
        Peluche.arreter_enchere();
        assertEquals(65,Peluche.getgagnant().getCompteOffre().getSoldecompte());    //100 - 30 (le prix courant) plus 5 (le cout de participation) = 65
        assertEquals("Jorge",Peluche.getgagnant().getCompteOffre().getPseudo());
    }

    @Test
    public void retourne_celui_qui_a_proposé_la_meilleur_offre_avec_le_plus_grand_prixmax() {
        Peluche.demarrer_enchere();
        OffreEnchere offreJorge = Jorge.creerOffre(Peluche, 30.0, 50.0);
        Peluche.ajouterOffre(offreJorge);
        OffreEnchere offreBrigitte = Brigitte.creerOffre(Peluche, 35.0, 50.0);
        Peluche.ajouterOffre(offreBrigitte);
        offreBrigitte = Brigitte.creerOffre(Peluche, 40.0, 60.0);   //Deuxieme participation mais Brigitte n'a pas à payer une deuxieme fois le cout de participation
        Peluche.ajouterOffre(offreBrigitte);
        Peluche.arreter_enchere();
        assertEquals(40,Peluche.getgagnant().getCompteOffre().getSoldecompte());    //100 - 55 (le prix courant) plus  5 (le cout de participation) = 40
        assertEquals("Brigitte",Peluche.getgagnant().getCompteOffre().getPseudo());
    }

    @Test
    public void retourne_celui_qui_a_proposé_la_meilleur_offre_avec_le_plus_grand_prixmax_et_qui_a_de_l_argent_sur_son_compte() {
        Brigitte.debiter(100.0);
        Peluche.demarrer_enchere();
        OffreEnchere offreJorge = Jorge.creerOffre(Peluche, 30.0, 50.0);
        Peluche.ajouterOffre(offreJorge);
        OffreEnchere offreBrigitte = Brigitte.creerOffre(Peluche, 35.0, 60.0);
        Peluche.ajouterOffre(offreBrigitte);
        Peluche.arreter_enchere();
        assertEquals(65,Peluche.getgagnant().getCompteOffre().getSoldecompte());    //100 - 30 (le prix courant) plus  5 (le cout de participation) = 65
        assertEquals("Jorge",Peluche.getgagnant().getCompteOffre().getPseudo());
    }

    @Test
    public void retourne_le_message_ERROR_si_la_seul_personne_qui_propose_une_offre_ne_possede_pas_assez_d_argent() {
        Brigitte.debiter(100.0);
        Peluche.demarrer_enchere();
        OffreEnchere Offre = Brigitte.creerOffre(Peluche, 30.0, 40.0);
        Peluche.ajouterOffre(Offre);
        Peluche.arreter_enchere();
        assertEquals("ERROR", Peluche.getgagnant().toString());
    }

    @Test
    public void retourne_le_message_ERROR_si_la_seul_personne_qui_propose_une_offre_ne_possede_pas_la_somme_du_prix_courant_plus_coutparticipation() {
        Brigitte.debiter(78.0);
        Peluche.demarrer_enchere();
        OffreEnchere Offre = Brigitte.creerOffre(Peluche, 30.0, 40.0);
        Peluche.ajouterOffre(Offre);
        Peluche.arreter_enchere();
        assertEquals("ERROR", Peluche.getgagnant().toString());
    }

    @Test
    public void retourne_le_message_ERROR_si_la_seul_personne_qui_propose_une_offre_propose_une_offre_ayant_un_prix_se_situant_entre_le_prix_courant_et_le_prix_courant_plus_le_pas() {
        Peluche.demarrer_enchere();
        OffreEnchere Offre = Brigitte.creerOffre(Peluche, 28.0, 40.0);   //Offre avec une valeur invalide (se situe entre prix courant de base et prix courant de base plus le pas)
        Peluche.ajouterOffre(Offre);
        Peluche.arreter_enchere();
        assertEquals("ERROR", Peluche.getgagnant().toString());
    }

    @Test
    public void retourne_le_message_ERROR_si_la_seul_personne_qui_propose_une_offre_propose_une_offre_ayant_un_prixpropose_egal_au_prix_courant() {
        Peluche.demarrer_enchere();
        OffreEnchere Offre = Brigitte.creerOffre(Peluche, 25.0, 40.0);   //Offre avec une valeur invalide (se situe entre prix courant de base et prix courant de base plus le pas)
        Peluche.ajouterOffre(Offre);
        Peluche.arreter_enchere();
        assertEquals("ERROR", Peluche.getgagnant().toString());
    }

    @Test
    public void retourne_le_solde_du_gaganant_avec_la_valeur_du_solde_de_base_moins_la_valeur_du_prix_proposé() {
        Peluche.demarrer_enchere();
        OffreEnchere Offre = Brigitte.creerOffre(Peluche, 30.0, 40.0);
        Peluche.ajouterOffre(Offre);
        Peluche.arreter_enchere();
        assertEquals(65,Peluche.getgagnant().getCompteOffre().getSoldecompte());    //100 - 30 (le prix courant) plus  5 (le cout de participation) = 65
    }

    @Test
    public void retourne_le_solde_du_gaganant_avec_la_valeur_du_solde_de_base_moins_la_valeur_du_prix_proposé_par_la_derniere_offre_plus_le_pas() {
        Peluche.demarrer_enchere();
        OffreEnchere offreJorge = Jorge.creerOffre(Peluche, 30.0, 50.0);
        Peluche.ajouterOffre(offreJorge);
        OffreEnchere offreBrigitte = Brigitte.creerOffre(Peluche, 35.0, 50.0);
        Peluche.ajouterOffre(offreBrigitte);
        Peluche.arreter_enchere();
        assertEquals(40,Peluche.getgagnant().getCompteOffre().getSoldecompte());    //100 - 55 (le prix courant) plus  5 (le cout de participation) = 40
        assertEquals("Jorge",Peluche.getgagnant().getCompteOffre().getPseudo());
    }

    @Test
    public void retourne_le_solde_du_gaganant_avec_la_valeur_du_solde_de_base_moins_la_valeur_du_prix_proposé_par_la_derniere_offre_plus_le_pas_v2() {
        Peluche.demarrer_enchere();
        OffreEnchere offreJorge = Jorge.creerOffre(Peluche, 30.0, 50.0);
        Peluche.ajouterOffre(offreJorge);
        OffreEnchere offreBrigitte = Brigitte.creerOffre(Peluche, 35.0, 60.0);
        Peluche.ajouterOffre(offreBrigitte);
        Peluche.arreter_enchere();
        assertEquals(40,Peluche.getgagnant().getCompteOffre().getSoldecompte());    //100 - 55 (le prix courant) plus  5 (le cout de participation) = 40
        assertEquals("Brigitte",Peluche.getgagnant().getCompteOffre().getPseudo());
    }

    @Test
    public void retourne_le_solde_du_gaganant_avec_la_valeur_du_solde_de_base_moins_la_valeur_du_prix_proposé_par_la_derniere_offre_plus_le_pas_v3() {
        Peluche.demarrer_enchere();
        OffreEnchere offreJorge = Jorge.creerOffre(Peluche, 30.0, 50.0);
        Peluche.ajouterOffre(offreJorge);
        OffreEnchere offreBrigitte = Brigitte.creerOffre(Peluche, 35.0, 60.0);
        Peluche.ajouterOffre(offreBrigitte);
        OffreEnchere offreJulien = Julien.creerOffre(Peluche, 40.0, 50.0);
        Peluche.ajouterOffre(offreJulien);
        Peluche.arreter_enchere();
        assertEquals(40,Peluche.getgagnant().getCompteOffre().getSoldecompte());    //100 - 55 (le prix courant) plus  5 (le cout de participation) = 40
        assertEquals("Brigitte",Peluche.getgagnant().getCompteOffre().getPseudo());
    }

    @Test
    public void retourne_le_solde_du_gaganant_avec_la_valeur_du_solde_de_base_moins_la_valeur_du_prix_proposé_par_la_derniere_offre_plus_le_pas_v4() {
        Peluche.demarrer_enchere();
        OffreEnchere offreJorge = Jorge.creerOffre(Peluche, 30.0, 50.0);
        Peluche.ajouterOffre(offreJorge);
        OffreEnchere offreBrigitte = Brigitte.creerOffre(Peluche, 35.0, 60.0);
        Peluche.ajouterOffre(offreBrigitte);
        OffreEnchere offreJulien = Julien.creerOffre(Peluche, 40.0, 60.0);
        Peluche.ajouterOffre(offreJulien);
        Peluche.arreter_enchere();
        assertEquals(30,Peluche.getgagnant().getCompteOffre().getSoldecompte());    //100 - 65 (le prix courant) plus  5 (le cout de participation) = 30
        assertEquals("Brigitte",Peluche.getgagnant().getCompteOffre().getPseudo());
    }

    @Test
    public void retourne_le_message_ERROR_si_la_seul_personne_qui_propose_une_offre_propose_une_offre_ayant_un_prix_maxpropose_inferieur_au_prix_propose() {
        Peluche.demarrer_enchere();
        OffreEnchere Offre = Brigitte.creerOffre(Peluche, 50.0, 40.0);   //Offre invalide prixmax inferieur a prixpropose
        Peluche.ajouterOffre(Offre);
        Peluche.arreter_enchere();
        assertEquals("ERROR", Peluche.getgagnant().toString());
    }

    @Test
    public void trois_offres_identiques_retourn_le_premier() {
        Peluche.demarrer_enchere();
        OffreEnchere offreJorge = Jorge.creerOffre(Peluche, 30.0, 50.0);
        Peluche.ajouterOffre(offreJorge);
        OffreEnchere offreBrigitte = Brigitte.creerOffre(Peluche, 30.0, 50.0);  //Offre invalide
        Peluche.ajouterOffre(offreBrigitte);
        OffreEnchere offreJulien = Julien.creerOffre(Peluche, 30.0, 50.0);  //Offre invalide
        Peluche.ajouterOffre(offreJulien);
        Peluche.arreter_enchere();
        assertEquals(65,Peluche.getgagnant().getCompteOffre().getSoldecompte());    //100 - 30 (le prix courant) plus  5 (le cout de participation) = 65
        assertEquals("Jorge",Peluche.getgagnant().getCompteOffre().getPseudo());
    }
}