package com.ismaildrs;

import metier.Compte;
import metier.IMetierBanque;
import metier.MetierBanqueImpl;

import java.util.Scanner;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        new Main().start();

    }

    public void start(){
        System.out.println("=========================================");
        System.out.println("  DÉMARRAGE DE L'APPLICATION BANCAIRE  ");
        System.out.println("=========================================");

        Scanner sc = new Scanner(System.in);
        IMetierBanque metierBanque = new MetierBanqueImpl();

        // --- 1. Création du compte initial ---
        System.out.print("Donner le numero de compte: ");
        long code = sc.nextLong();
        System.out.print("Donner le solde initial: ");
        double solde = sc.nextDouble();

        // Consomme le retour à la ligne après nextDouble/nextLong
        sc.nextLine();

        Compte nouveauCompte = new Compte(code, solde);
        metierBanque.addCompte(nouveauCompte);
        System.out.println("-> Compte " + code + " créé avec succès.");

        // --- 2. Boucle d'opérations ---
        while (true){
            System.out.println("\n-----------------------------------------");
            System.out.println("Opérations disponibles:");
            System.out.println("V : Verser (Ajouter montant)");
            System.out.println("R : Retirer (Débiter montant)");
            System.out.println("C : Consulter (Afficher solde)");
            System.out.println("Q : Quitter l'application");
            System.out.print("Choisissez une opération (V/R/C/Q): ");

            String type = sc.nextLine().trim().toUpperCase(); // Lit l'opération et la met en majuscules

            switch (type) {
                case "V":
                    // Opération de versement
                    System.out.print("Montant à verser: ");
                    double montantVersement = sc.nextDouble();
                    metierBanque.verser(code, montantVersement);
                    System.out.println("-> Versement de " + montantVersement + " effectué.");
                    break;

                case "R":
                    // Opération de retrait
                    System.out.print("Montant à retirer: ");
                    double montantRetrait = sc.nextDouble();

                    if (metierBanque.consulter(code).getSolde() >= montantRetrait) {
                        metierBanque.retirer(code, montantRetrait);
                        System.out.println("-> Retrait de " + montantRetrait + " effectué.");
                    } else {
                        System.err.println("!!! Solde insuffisant. Opération annulée.");
                    }
                    break;

                case "C":
                    // Opération de consultation
                    Compte cp = metierBanque.consulter(code);
                    System.out.println("-> Solde actuel du compte " + code + ": " + cp.getSolde() + " DH.");
                    break;

                case "Q":
                    // Quitter l'application
                    System.out.println("\n=========================================");
                    System.out.println("Merci d'avoir utilisé notre application. Au revoir.");
                    System.out.println("=========================================");
                    sc.close();
                    return; // Sort de la méthode start() et termine l'application

                default:
                    System.err.println("!!! Choix invalide. Veuillez saisir V, R, C ou Q.");
                    break;
            }

            // Consomme le retour à la ligne après nextDouble/nextLong si nécessaire
            if (type.equals("V") || type.equals("R")) {
                sc.nextLine();
            }
        }
    }
}

