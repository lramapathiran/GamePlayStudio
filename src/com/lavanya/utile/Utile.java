package com.lavanya.utile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.lavanya.factoryDesign.Game;
import com.lavanya.factoryDesign.GameFactory;
import com.lavanya.factoryDesign.GameLaunch;
import com.lavanya.factoryDesign.GameType;
import com.lavanya.interfaces.DefChallDuelInterface;

public class Utile implements DefChallDuelInterface {

		final static Logger logger = LogManager.getLogger(Utile.class.getName());

		public int intProperties(String key) throws IOException {
			int confValue = Configuration.getInstance().getIntProperty(key);
			return confValue;
		}
	
	//Méthode getRandom: l'ordinateur doit proposer au joueur une combinaison à 4 chiffres
		public List<Integer> getRandom(int min, int max) throws IOException {

			
			List<Integer> randomCombi = new ArrayList<>();
			Random random = new Random();

			for (int i = 0; i < intProperties("digit"); i++) {
				int j = min + random.nextInt(max - min);
				randomCombi.add(j);
				}
			
			

			return randomCombi;
		}
		
//		Méthode pour que le joueur fasse ses propositions de combinaisons
//		et conversion de ses propositions en array
// 		Méthode pour convertir la combinaison secrète du joueur d'un type String-->Chatacter-->Integer
//		@param la combinaison secrète de type String entrée dans la console
//		return: Liste d'Interger qui correspond à la combi secrète du joueur à découvrir par l'ordi
		public List<Integer> playerCombi() throws IOException {

			List<Integer> playerAnswer = new ArrayList<>();
			Scanner sc = new Scanner(System.in);
			System.out.println(
					"Veuillez saisir un nombre à 4 chiffres compris entre 0000 et 9999!");
			
			do {
				String input = sc.nextLine();
				

	//			each character of the string is split and converted into a string array
				String inputAsArray[] = input.split("\\B");
				
				if (inputAsArray.length != intProperties("digit")) {
					logger.error("combinaison entrée par le joueur en mode Defenseur < ou > 4 ou combinaison non numérique invalide < 4!");
					System.out.println("Votre combinaison doit faire 4 chiffres, veuillez entrer un chiffre compris entre 0000 et 9999!");
				}
				else {
		//			each String value of the array is converted into an int and then added in the playerAnswer ArrayList
					try {
						for (String stringValue : inputAsArray) {							
								int stringToInt = Integer.parseInt(stringValue);
								playerAnswer.add(stringToInt);					
						}
					} catch (NumberFormatException e) {
						logger.error("Saisie invalide: NumberFormatException!");
						System.out.println("Votre saisie est invalide! Veuillez saisir de nouveau votre combinaison! Elle ne doit comporter qu'une série de 4 chiffres (entre 0000 et 9999):");
					}
				}
			}while(playerAnswer.size() != intProperties("digit") || false);

			return playerAnswer;

		}
		
//		Méthode de comparaison de la réponse de l'ordi à la combi secrète du joueur par création d'une Liste +,=,-
//		@param: x (list Integer) correspond à la combi secrète à découvrir du joueur et y (list Integer) correspond à la proposition de combi 4 chiffres de l'ordi
//		return la liste Character de comparaison =,+,-
//		Méthode pour comparer la réponse du joueur à celle que l'ordinateur à générer et indication (+,=,-)
//		pour que le joueur puisse proposer une nouvelle réponse
		public List<Character> computerPropositionCheck(List<Integer> x, List<Integer> y) throws IOException {
			
			List<Character> computerCheck = new ArrayList<>();
			for (int i = 0; i < intProperties("digit"); i++) {

				if (x.get(i) > y.get(i)) {
					computerCheck.add('+');
				}

				if (x.get(i) < y.get(i)) {
					computerCheck.add('-');
				}

				if (x.get(i) == y.get(i)) {
					computerCheck.add('=');
				}

			}

			return computerCheck;
			
		}
		
//		Méthode de type booléen pour définir si l'ordinateur a donné une combinaison 4 chiffres gagnante 
		public boolean winAnswer(List<Character> playerAnswer) throws IOException {

			// Création d'une liste qui correspond à la réponse du joueur que l'ordi attend
			// pour gagner
			List<Character> winCombi = new ArrayList<>();
			
			for (int i = 0; i < intProperties("digit"); i++) {
				winCombi.add('=');
			}

			boolean win = playerAnswer.equals(winCombi);
			return win;
		}
		
		public void replay(GameType GameType) throws IOException {
			
			Scanner sc = new Scanner(System.in);
			System.out.println("Souhaitez-vous rejouer? O/N(pour retouner au Menu Principal, choisissez N)");
			String willReplay;
			
			
				do {
					willReplay = sc.nextLine();
					if (willReplay.equalsIgnoreCase("O") || willReplay.equalsIgnoreCase("N")) {
							switch(willReplay.toLowerCase()) {
								case "o":
									Game gameMode = GameFactory.getGame(GameType);
									gameMode.gamePlay();
									break;
								case "n":
									GameLaunch.main(null);
									break;
							}
					}else{
						logger.error("Saisie invalide: O ou N non renseigné");
						System.out.println("Votre saisie est invalide! Veuillez indiquer de nouveau O pour oui et N pour non:");
							
					}
					
				}while(!willReplay.equalsIgnoreCase("O")||!willReplay.equalsIgnoreCase("N"));
		
			
			
			
		}
		
		

}
