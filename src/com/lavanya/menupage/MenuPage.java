package com.lavanya.menupage;

import java.io.IOException;

public class MenuPage {
	
	private static MenuPage _instance = null;
	
	public synchronized static MenuPage getInstance() throws IOException {
        if (_instance == null)
            _instance = new MenuPage();
        
        return _instance;
    }
	
	public void menuStart() {
		System.out.println("Bienvenue à GAMEPLAY STUDIO");
		System.out.println("Menu");
		System.out.println("1: Défenseur");
		System.out.println("2: Challenger");
		System.out.println("3: Duel");
		System.out.println("4: Quitter l'application");
		
		System.out.println("A quel mode souhaitez-vous jouer? Veuillez préciser votre choix en saisissant un chiffre 1 à 4: ");
	}
	
	public void menuStartDefenseur() {
		System.out.println("Mode Défenseur");
		System.out.println("Bienvenue dans le mode Défenseur!");
		System.out.println("Veuillez définir un nombre à 4 chiffres compris entre 0000 à 9999!");
		System.out.println(
				"C'est fait? Garder bien en tête votre combinaison à 4 chiffres\nMaintenant c'est à moi de jouer!");
	}
	
	public void menuStartChallenger() {
		System.out.println("Mode Challenger:");
		System.out.println("Bienvenue dans le mode Challenger!");
		System.out.println("Vous devez deviner une combinaison de 4 chiffres comprise entre 0,0,0,0 et 9,9,9,9!");
		System.out.println("Vous avez le droit à 3 tentatives!");
		System.out.println("Vous êtes prêt? Allez! A vous de jouer!");
	}
	
	
	public void menuStartDuel() {
		System.out.println("Mode Duel");
		System.out.println("Bienvenue dans le mode Duel!");
		System.out.println("Je vais définir un nombre secret à 4 chiffres! ex: 8,6,5,1");
		System.out.println("Veuillez définir également votre nombre secret à 4 chiffres compris entre 0,0,0,0 à 9,9,9,9!");
		System.out.println(
				"C'est fait? Garder bien en tête votre combinaison à 4 chiffres");
		System.out.println("Nous sommes en mode duel, le premier à découvrir la combinaison secrète de l'autre gagne!");
		System.out.println("Vous êtes prêt? Allez je commence!");
	}

}
