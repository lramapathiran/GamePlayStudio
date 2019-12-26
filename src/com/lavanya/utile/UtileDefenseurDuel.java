package com.lavanya.utile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;
import org.apache.log4j.Logger;
import com.lavanya.interfaces.DefChallDuelInterface;
import com.lavanya.interfaces.DefDuelInterface;
import com.lavanya.utile.Utile;

public class UtileDefenseurDuel implements DefDuelInterface{
	

		static DefChallDuelInterface iDefChallDuel = new Utile();
		static Logger logger = Logger.getLogger("GameLaunch");
		
		
//		M�thode pour cr�er une nouvelle combinaison � 4 chiffres par l'ordinateur
		public Integer newRandom(int min, int max) {

			Random random = new Random();
			int j = min + random.nextInt(max - min);
			return j;
		}
		

		
//		M�thode o� Le joueur va comparer la combi propos�e � la sienne avec les valeurs + = ou -
//		Conversion de la r�ponse du joueur en String List ArrayList
		public List<Character> playerAnswer() throws IOException {
			
			List<Character> playerClue= new ArrayList<>();
			Scanner sc = new Scanner(System.in);
			
			System.out.println(
					"Veuillez indiquer + = ou - pour chaque chaque valeur de la combinaison propos�e: ex +,=,=,-(merci d'utiliser le m�me format de r�ponse que dans l'exemple)");
			
//			sc.close();	
			
			
			char[] charArray;
			String strPlus = "+";
			String strMinus = "-";
			String strEqual = "=";
			
			char charPlus = strPlus.charAt(0);
			char charMinus = strMinus.charAt(0);
			char charEqual = strEqual.charAt(0);
			
			do {
				String clue = sc.nextLine();
				charArray = clue.toCharArray();
				
				if (charArray.length != iDefChallDuel.intProperties("digit")) {
					logger.error("erreur de saisie: les valeurs + = ou - entr�es sont > ou < � 4!");
					System.out.println("Votre saisie est sup�rieur ou inf�rieur � 4 valeurs! Veuillez rentrer 4 valeurs de nouveau (+ = ou -)!: ");
				}
				
				else {
					for(Character ch: charArray) {
						
					
//						if (ch != charPlus || ch != charMinus || ch != charEqual) {
//							playerClue.clear();
//							logger.error("Erreur: saisie invalide avec un ou plusieurs charact�res diff�rent de + = ou -");
//							System.out.println("Votre saisie est invalide, elle ne doit contenir que des valeurs de type + = ou -! veuillez renseigner de nouveau votre indice! :");
//						}
//						else {
							playerClue.add(ch);
//						}
					}
				}
			}while(charArray.length != iDefChallDuel.intProperties("digit") || playerClue.size() != iDefChallDuel.intProperties("digit"));
				
			return playerClue;

		}
		
//		M�thode pour comparer la r�ponse du joueur (suite � la proposition de l'ordi) � la r�ponse attendue +,=,-
//		@param Listes de type Character avec x �tant la r�ponse du joueur et y celle attendue
		public List<Character> validationPlayerClue(List<Character> playerClue, List<Character> playerAnswerExpected) {
			
			
			
			boolean comparison = playerClue.equals(playerAnswerExpected);
			while(!comparison) {
				Scanner sc = new Scanner(System.in);
				System.out.println("Votre r�ponse est incorrecte! Veuillez v�rifier votre saisie et la corriger ci-dessous:");
				String clue = sc.nextLine();
				
				int i=0;
				for(Character ch: clue.toCharArray()) {
					
					playerClue.set(i,ch);
					i++;
				}
				
				comparison = playerClue.equals(playerAnswerExpected);
			}
			
			return playerClue;
		}
		
		public List<Proposition> rangeArray() throws IOException {
			
			List<Proposition> limit = new ArrayList<Proposition>();
					
			for (int i = 0; i < iDefChallDuel.intProperties("digit"); i++) {
				limit.add(new Proposition(0,10));
			}
			
			return limit;
		}
		
//		L'ordinateur va g�n�rer une nouvelle combinaison en fonction des indices +,= ou - 
//		indiqu� par le joueur
		public List<Integer> runConditions(List<Character> validPlayerClue, List<Integer> computerProposition, List<Proposition> range) {
			
					
			List<Integer> randomCombiNewTry = new ArrayList<>();
			
			for (int j = 0; j < validPlayerClue.size(); j++) {
				 int z=0;
				if (validPlayerClue.get(j).equals('+')) {
					range.get(j).setMin(computerProposition.get(j) + 1);
					int min = range.get(j).getMin();
					int max = range.get(j).getMax();
					
					z = newRandom(min, max);
				}

				if (validPlayerClue.get(j).equals('-')) {
					range.get(j).setMax(computerProposition.get(j));
					int min = range.get(j).getMin();
					int max = range.get(j).getMax();
					
					z = newRandom(min, max);
				}
				
				

				if (validPlayerClue.get(j).equals('=')) {
					z = computerProposition.get(j);
				}
				
				randomCombiNewTry.add(z);
			}
			
			
			return randomCombiNewTry;

		}
}
