package com.lavanya.menupage;

import java.io.IOException;
import java.util.List;

import com.lavanya.interfaces.DefChallDuelInterface;
import com.lavanya.utile.Utile;

public class MenuPage {
	
	private static MenuPage _instance = null;
	DefChallDuelInterface iDefChallDuel = new Utile();
	
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
		
		System.out.println("Quel mode de jeu vous int�resse? Veuillez pr�ciser votre choix en saisissant un chiffre 1 � 4: ");
	}
	
	public List<Integer> menuStartDefenseur() throws IOException {
		
		System.out.println("Mode D�fenseur");
		System.out.println("Bienvenue dans le mode D�fenseur!");	
		System.out.println("Vous devez me faire d�couvrir une combinaison de 4 chiffres comprise entre 0000 et 9999!");
		System.out.println("J'ai 5 tentatives pour y arriver");
		System.out.println("Pour m'aider; � chaque proposition que je ferai, vous devrez indiquer pour chaque chiffre de cette combinaison si le chiffre est plus grand(+), moins grand(-) ou exact(=) par rapport � votre combinaison � d�couvrir!");
		System.out.println("Pour cela vous renseignerez une combinaison de 4 caract�res dont les valeurs seront + = ou -!");
		System.out.println("C'est parti!!");
		
		List<Integer> combiToFind = iDefChallDuel.playerCombi();
        
        System.out.println("Votre combinaison secr�te � d�couvrir est " + combiToFind);
		System.out.println(
				"Maintenant c'est � moi de la deviner!");
		
		return combiToFind;
	}
	
	public void menuStartChallenger() {
		
		System.out.println("Mode Challenger:");
		System.out.println("Bienvenue dans le mode Challenger!");
		System.out.println("Vous devez deviner une combinaison de 4 chiffres comprise entre 0000 et 9999!");
		System.out.println("Vous avez le droit � 5 tentatives!");
		System.out.println("Pour vous aider; � chaque proposition que vous ferez, j'indiquerai pour chaque chiffre de cette combinaison si le chiffre est plus grand(+), moins grand(-) ou exact(=) par rapport � la combinaison � d�couvrir!");
		System.out.println("Pour cela je renseignerai une combinaison de 4 caract�res dont les valeurs seront + = ou -!");
		System.out.println("Vous �tes pr�t? Allez! A vous de jouer!");
	}
	
	
	public List<Integer> menuStartDuel() throws IOException {
		System.out.println("Mode Duel");
		System.out.println("Bienvenue dans le mode Duel!");
		System.out.println("Je vais d�finir un nombre secret � 4 chiffres compris entre 0000 � 9999 que vous devrez deviner! ex: 8651");
		List<Integer> playerCombiToFind = iDefChallDuel.playerCombi();
        
        System.out.println("Votre combinaison secr�te � d�couvrir est " + playerCombiToFind);
		System.out.println("Nous sommes en mode duel, le premier � d�couvrir la combinaison secr�te de l'autre gagne!");
		System.out.println("A tour de r�le nous ferons nos propositions et l'adversaire(vous ou moi) devra pour chaque combinaison propos�e si le chiffre est plus grand(+), moins grand(-) ou exact(=) par rapport � la combinaison � d�couvrir!");
		System.out.println("Pour cela nous renseignerons une combinaison de 4 caract�res dont les valeurs seront + = ou -!");
		System.out.println("Vous �tes pr�t? Allez je commence!");
		
		return playerCombiToFind;
	}

	
}
