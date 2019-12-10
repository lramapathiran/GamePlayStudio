package com.lavanya.menupage;

import java.io.IOException;
import java.util.List;

import com.lavanya.interfaces.DefChallDuelInterface;
import com.lavanya.utile.Utile;

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
	
	public List<Integer> menuStartDefenseur() {
		
		DefChallDuelInterface iDefChallDuel = new Utile();
		
		System.out.println("Mode D�fenseur");
		System.out.println("Bienvenue dans le mode D�fenseur!");		
		
		List<Integer> x = iDefChallDuel.playerCombi();
        
        System.out.println("Votre combinaison secr�te � d�couvrir est " + x);
		System.out.println(
				"Maintenant c'est � moi de la deviner!");
		
		return x;
	}
	
	public void menuStartChallenger() {
		
		System.out.println("Mode Challenger:");
		System.out.println("Bienvenue dans le mode Challenger!");
		System.out.println("Vous devez deviner une combinaison de 4 chiffres comprise entre 0000 et 9999!");
		System.out.println("Vous avez le droit � 5 tentatives!");
		System.out.println("Vous �tes pr�t? Allez! A vous de jouer!");
	}
	
	
	public void menuStartDuel() {
		System.out.println("Mode Duel");
		System.out.println("Bienvenue dans le mode Duel!");
		System.out.println("Je vais d�finir un nombre secret � 4 chiffres compris entre 0000 � 9999 que vous devrez deviner! ex: 8651");
		System.out.println("Veuillez saisir ci-dessous votre nombre secret � 4 chiffres �galement compris entre 0000 � 9999 !");
		System.out.println("Nous sommes en mode duel, le premier � d�couvrir la combinaison secr�te de l'autre gagne!");
		System.out.println("Vous �tes pr�t? Allez je commence!");
	}
	
public void menuStartDev() {
		
		System.out.println("Mode Dev:");
		System.out.println("Bienvenue dans le mode Dev!");
		System.out.println("Vous devez deviner une combinaison de 4 chiffres comprise entre 0000 et 9999!");
		System.out.println("Vous avez le droit � 5 tentatives!");
		System.out.println("Vous �tes pr�t? Allez! A vous de jouer!");
	}

	
}
