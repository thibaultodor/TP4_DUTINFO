# ![](ressources/logo.jpeg) Bases de la programmation orientée objet 

### IUT Montpellier-Sète – Département Informatique

* **Cours:** [M2103](http://cache.media.enseignementsup-recherche.gouv.fr/file/25/09/7/PPN_INFORMATIQUE_256097.pdf) - support [ici](https://github.com/IUTInfoMontp-M2103/Ressources)
* **Enseignants:** [Marin Bougeret](mailto:marin.bougeret@umontpellier.fr), [Sébastien Gagné](mailto:sebastien.gagne@umontpellier.fr), [Sophie Nabitz](mailto:sophie.nabitz@univ-avignon.fr), [Jérôme Palaysi](mailto:jerome.palaysi@umontpellier.fr), [Victor Poupet](mailto:victor.poupet@umontpellier.fr), [Petru Valicov](mailto:petru.valicov@umontpellier.fr)
* Le [forum Piazza](https://piazza.com/class/k52c8w685w3210) de ce cours pour poser vos questions
* [Email](mailto:petru.valicov@umontpellier.fr) pour une question d'ordre privée concernant le cours.
* Le [sujet du TP](http://pageperso.lis-lab.fr/~petru.valicov/Cours/M2103/TP4.pdf) en format .pdf téléchargeable et imprimable.

Avant de démarrer le TP, vérifiez que vous n'avez pas atteint votre quota d'espace de stockage autorisé :

* placez-vous dans votre `$HOME` et utilisez les commandes suivantes :
    * `du -sh` pour voir combien d'espace vous avez déjà utilisé
    * `du -sh *` pour voir combien d'espace vous avez déjà utilisé pour chaque fichier (sans fichiers cachés)
    * `du -sch .[!.]* *` pour voir combien d'espace vous avez déjà utilisé pour chaque fichier, y compris les fichiers cachés
* Supprimez les fichiers inutiles.
* Pour éviter des problèmes durant vos TPs d'informatique, vous devriez toujours **garder 300-400 Mo d'espace libre**.


### Consignes
- Sauf indication contraire, **tous** les attributs que vous allez déclarer dans ce TP (et dans les TPs qui suivent) doivent être privés (`private`).
- A priori, la plupart des méthodes devraient être déclarées publiques (`public`). Vous pouvez tout de même déclarer et utiliser des méthodes `private` du moment qu'elles vous sont utiles et que votre programme fonctionne correctement.
- Pensez à respecter les conventions de nommage *Java* (vues en [cours](http://pageperso.lis-lab.fr/~petru.valicov/Cours/M2103/BPOO_Generalites_x4.pdf) ou disponibles sur le site d'Oracle)
- Date limite de rendu de votre code sur le dépôt GitHub : **Dimanche 16 février à 23h00**


## TP 4 : Systèmes d'enchères
#### _Thème : encapsulation, cycle de vie d'une application orientée objet_

Comme d'habitude, cliquez sur le lien ci-dessous pour faire votre fork privé du TP :

https://classroom.github.com/a/aT6i1xXH


Vous êtes chargé de développer le système d'enchères **iBaille**. Pour avoir une idée globale du système,
voici le principe général de fonctionnement :

* des produits sont mis en vente avec un prix initial (le prix de base) ;
* des utilisateurs peuvent enchérir sur les produits jusqu'à ce que l'enchère soit arrêtée ;
* pour pouvoir participer les utilisateurs doivent payer un coût de participation (différent pour chaque produit) ;
* à chaque enchérissement, le prix de base du produit augmente ;
* à la fin de la vente, l'utilisateur ayant proposé le prix le plus élevé, remporte le produit ;
* pour éviter des enchères inutiles (de 1 centime par exemple), le même pas d’enchère minimal est défini pour tous les produits ;
* lorsqu'un utilisateur propose un prix pour un produit, il propose également un prix maximal qu'il est prêt à débourser
en cas d'enchère concurrente; ainsi, si par la suite, un autre utilisateur fait une enchère supplémentaire valide (c'est-à-dire avec un montant au moins égale au prix courant du produit + le pas d'enchere), le prix courant du produit augmente automatiquement. Par définition, le gagnant est celui dont le prix courant est supérieur aux prix maximaux proposés par les autres utilisateurs.

**Remarque** : Un utilisateur peut déposer une nouvelle offre d'enchère sur le même produit sur lequel il a déjà déposé une offre d'enchère.
Par exemple, il pourra le faire si son offre a été "battue" par un un autre enchérisseur.



1. Implémentez une classe `Produit` avec les attributs suivants : numéro, description (un texte), prix courant,
date de début d'enchère (type `LocalDate` du package `java.time`), heure de début d'enchère (type `LocalTime` du package
 `java.time`), montant du pas d'enchère minimal, coût de participation. Ajoutez un constructeur avec comme paramètres : le numéro,
  la description, le prix courant et le coût de participation.
 
2. Comme indiqué précédemment, le pas d'enchère doit être le même pour tous les produits mais modifiable par l'utilisateur.
Proposez une solution dans votre programme pour satisfaire cette contrainte.

    **Remarque** : Ne pas confondre la notion d'_utilisateur du logiciel_ (non-informaticien) et l'_utilisateur-programmeur_
qui est censé de se servir de votre application pour poursuivre son développement et pour la maintenance, le débuggage etc.
Ici l'utilisateur c'est l'informaticien.

3. Ajoutez à la classe  `Produit` une méthode `demarrerEnchere(...)` qui rendra l'objet disponible à l'enchère.
Vous ajouterez à cette classe les autres éléments qui vous paraissent nécessaires pour que cette méthode fonctionne.
Vous pouvez supposer que la date et l'heure courantes représentent le début. Pour cela vous pouvez utiliser les méthodes
`LocalDate.now()` et `LocalTime.now()`, respectivement.

    Ajoutez également la méthode réciproque `arreterEnchere(...)`.
 
4. Implémentez une classe `Compte`  avec les attributs suivants : pseudo, email, adresse, solde de compte.
Ajoutez un constructeur approprié.

5. Ajoutez à la classe `Compte` une méthode qui permet de créditer le compte avec une somme donnée.

6. Implémentez une classe `OffreEnchere` qui représentera une enchère proposée par un utilisateur pour un produit donné.
Cette classe aura entre autres comme attributs : une date, une heure, un prix "_en cours_", et un prix maximal autorisé (en cas
d'enchère automatique). Ajoutez un constructeur approprié.

    **Remarque :** nul besoin de passer la date et l'heure en paramètres
     du constructeur. Vous vous contenterez d'utiliser la date et l'heure courantes.
     
    **Remarque :** ici, dans le constructeur, il n'est pas demandé de vérifier que les attributs de l'offre créé sont cohérents avec celles du produit (ce n'est pas la responsabilité de l'objet `OffreEnchere`)
     
7. Implémentez les méthodes `toString()` pour chacune des 3 classes que vous avez écrites.

8. Ajoutez dans la classe `OffreEnchere`, des méthodes accesseurs (_getters_) pour le prix et le prix maximal.

9. Ajoutez dans la classe `OffreEnchere`, une méthode modifieur (_setter_) pour le prix.

10. Ajoutez à la classe `Compte` le code nécessaire afin que cette classe possède une méthode `creerOffre(...)`, qui :
prend en paramètres un produit, un prix courant et un prix maximal;
crée une offre d'enchère et ajoute cette offre à sa liste d'offres d'enchères. Pour vous simplifier la tâche,
on vous conseille d'utiliser une structure de données de type liste prédéfinie en _Java_,
comme `java.util.ArrayList` ou `java.util.LinkedList` (mais vous êtes libres d'utiliser d'autres solutions).

11. Ajoutez à la classe `Produit` une méthode `ajouterOffre(...)`, qui prend comme paramètre une offre d'enchère,
vérifie si cette offre est __valide__ (en vérifiant le pas d'enchère, le fait que la session d'enchère du produit n'est
pas arrêtée, etc) et l'ajoute à la liste d'offres d'enchères de la classe `Produit`.

    **Sans modifier les autres classes**, pensez à mettre à jour correctement les valeurs de prix des différentes entités de votre application.

12. Ajoutez à la classe `Produit` une méthode `getGagnant()` qui renvoie la meilleure offre d'enchère.

13. Simulez votre application dans le programme principal (la classe `IBaille`). Pour cela, vous instancierez un produit et plusieurs comptes (3 au minimum). Pour chacun des comptes vous proposerez à l'utilisateur du logiciel
(non-informaticien donc) de déposer des enchères pour ce produit en affichant les informations sur le produit
et l'offre gagnante en cours. Pensez à **tester** que les offres d'enchère non-valides ne puissent pas être déposées. Vous pouvez effectuer cette simulation par des simples
affichages sur la console. Pour récupérer les données saisies par l'utilisateur à la console, vous pouvez utiliser la
classe `java.util.Scanner` qui permet de "parser" de manière intelligente une chaîne de caractères.
Voici un petit exemple de ce que vous pouvez faire avec :

    ```java
    
    import java.util.Scanner;
    
    class IBaille {
    
        public static void main(String args[]) {
        
            // on attache un objet Scanner au flux d'entrée associée à la console
            Scanner saisie = new Scanner(System.in);
            System.out.println("Veuillez écrire quelque chose :");
        
            // récupération de la chaîne de caractères saisie par l'utilisateur
            String réponse = saisie.next();
        
            // récupération de la chaîne de caractères saisie par l'utilisateur sous forme d'un nombre entier
            int entier = saisie.nextInt();
           
        }
    
    }
    ```
Pour plus de détails sur cette classe, voir l'API : https://docs.oracle.com/javase/8/docs/api/java/util/Scanner.html
