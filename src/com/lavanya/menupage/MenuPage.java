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
		System.out.println("Bienvenue � GAMEPLAY STUDIO");
		System.out.println("Menu");
		System.out.println("1: D�fenseur");
		System.out.println("2: Challenger");
		System.out.println("3: Duel");
		System.out.println("4: Quitter l'application");
		
		System.out.println("A quel mode souhaitez-vous jouer? Veuillez pr�ciser votre choix en saisissant un chiffre 1 � 4: ");
	}
	
	public void menuStartDefenseur() {
		System.out.println("Mode D�fenseur");
		System.out.println("Bienvenue dans le mode D�fenseur!");
		System.out.println("Veuillez d�finir un nombre � 4 chiffres compris entre 0000 � 9999!");
		System.out.println(
				"C'est fait? Garder bien en t�te votre combinaison � 4 chiffres\nMaintenant c'est � moi de jouer!");
	}
	
	public void menuStartChallenger() {
		System.out.println("Mode Challenger:");
		System.out.println("Bienvenue dans le mode Challenger!");
		System.out.println("Vous devez deviner une combinaison de 4 chiffres comprise entre 0,0,0,0 et 9,9,9,9!");
		System.out.println("Vous avez le droit � 3 tentatives!");
		System.out.println("Vous �tes pr�t? Allez! A vous de jouer!");
	}
	
	
	public void menuStartDuel() {
		System.out.println("Mode Duel");
		System.out.println("Bienvenue dans le mode Duel!");
		System.out.println("Je vais d�finir un nombre secret � 4 chiffres! ex: 8,6,5,1");
		System.out.println("Veuillez d�finir �galement votre nombre secret � 4 chiffres compris entre 0,0,0,0 � 9,9,9,9!");
		System.out.println(
				"C'est fait? Garder bien en t�te votre combinaison � 4 chiffres");
		System.out.println("Nous sommes en mode duel, le premier � d�couvrir la combinaison secr�te de l'autre gagne!");
		System.out.println("Vous �tes pr�t? Allez je commence!");
	}

}
