package com.lavanya.utile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

import com.lavanya.factoryDesign.Game;
import com.lavanya.factoryDesign.GameFactory;
import com.lavanya.factoryDesign.GameLaunch;
import com.lavanya.factoryDesign.GameType;
import com.lavanya.interfaces.DefChallDuelInterface;

public class Utile implements DefChallDuelInterface {


		public int intProperties(String key) throws IOException {
			int confValue = Configuration.getInstance().getIntProperty(key);
			return confValue;
		}
	
	//M�thode getRandom: l'ordinateur doit proposer au joueur une combinaison � 4 chiffres
		public List<Integer> getRandom(int min, int max) throws IOException {

			
			List<Integer> randomCombi = new ArrayList<>();
			Random random = new Random();

			for (int i = 0; i < intProperties("digit"); i++) {
				int j = min + random.nextInt(max - min);
				randomCombi.add(j);
				}
			
			

			return randomCombi;
		}
		
//		M�thode pour que le joueur fasse ses propositions de combinaisons
//		et conversion de ses propositions en array
// 		M�thode pour convertir la combinaison secr�te du joueur d'un type String-->Chatacter-->Integer
//		@param la combinaison secr�te de type String entr�e dans la console
//		return: Liste d'Interger qui correspond � la combi secr�te du joueur � d�couvrir par l'ordi
		public List<Integer> playerCombi() {

			List<Integer> playerAnswer = new ArrayList<>();
			Scanner sc = new Scanner(System.in);
			System.out.println(
					"Veuillez saisir un nombre � 4 chiffres compris entre 0000 � 9999!");
			String input = sc.nextLine();
			
			// For each character in the String 
	        // add it to the List 
	        for (char ch : input.toCharArray()) { 
	        	
	        	int i = Character.getNumericValue(ch);
	        	playerAnswer.add(i); 
	        } 
	  

			return playerAnswer;

		}
		
//		M�thode de comparaison de la r�ponse de l'ordi � la combi secr�te du joueur par cr�ation d'une Liste +,=,-
//		@param: x (list Integer) correspond � la combi secr�te � d�couvrir du joueur et y (list Integer) correspond � la proposition de combi 4 chiffres de l'ordi
//		return la liste Character de comparaison =,+,-
//		M�thode pour comparer la r�ponse du joueur � celle que l'ordinateur � g�n�rer et indication (+,=,-)
//		pour que le joueur puisse proposer une nouvelle r�ponse
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
		
//		M�thode de type bool�en pour d�finir si l'ordinateur a donn� une combinaison 4 chiffres gagnante 
		public boolean winAnswer(List<Character> playerAnswer) throws IOException {

			// Cr�ation d'une liste qui correspond � la r�ponse du joueur que l'ordi attend
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
			String willReplay = sc.nextLine();
			
			switch(willReplay) {
				case "O":
					Game gameMode = GameFactory.getGame(GameType);
					gameMode.gamePlay();
					break;
				case "N":
					GameLaunch.main(null);
					break;
		
			}
		}
		
		

}
