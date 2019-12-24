package com.lavanya.utile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;
import org.apache.log4j.Logger;
import org.apache.logging.log4j.util.Chars;

import static org.junit.Assert.assertTrue;
//import org.apache.commons.lang.StringUtils;

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
		public List<Character> playerAnswer() {
			
			List<Character> playerClue= new ArrayList<>();
			Scanner sc = new Scanner(System.in);
			
			System.out.println(
					"Veuillez indiquer + = ou - pour chaque chaque valeur de la combinaison propos�e: ex +,=,=,-(merci d'utiliser le m�me format de r�ponse que dans l'exemple)");
			
//			sc.close();	
			
			
			char[] charArray;
			int i=0;
			do {
				String clue = sc.nextLine();
				charArray = clue.toCharArray();
				
				if (charArray.length != 4) {
					logger.error("erreur de saisie: les valeurs + = ou - entr�es sont > ou < � 4!");
					System.out.println("Votre saisie est sup�rieur ou inf�rieur � 4 valeurs! Veuillez rentrer 4 valeurs de nouveau (+ = ou -)!: ");
				}
				
				else {
					for(Character ch: charArray) {
						
						playerClue.add(ch);
					
//						if (!ch.equals('+') || !ch.equals('-') || !ch.equals('=')) {
//							playerClue.clear();
//							logger.error("Erreur: saisie invalide avec un ou plusieurs charact�res diff�rent de + = ou -");
//							System.out.println("Votre saisie est invalide, elle ne doit contenir que des valeurs de type + = ou -! veuillez renseigner de nouveau votre indice! :");
//						}
//						else {
//							i++;
							playerClue.add(ch);
//						}
					}
				}
			}while(charArray.length != 4 || playerClue.size() != 4);
				
			return playerClue;

		}
		
//		M�thode pour comparer la r�ponse du joueur (suite � la proposition de l'ordi) � la r�ponse attendue +,=,-
//		@param Listes de type Character avec x �tant la r�ponse du joueur et y celle attendue
		public void validationPlayerClue(List<Character> x, List<Character> y) {
			
			
			
			boolean comparison = x.equals(y);
			while(!comparison) {
				Scanner sc = new Scanner(System.in);
				System.out.println("Votre r�ponse est incorrecte! Veuillez v�rifier votre saisie et la corriger ci-dessous:");
				String clue = sc.nextLine();
				
				int i=0;
				for(Character ch: clue.toCharArray()) {
					
					x.set(i,ch);
					i++;
				}
				
				comparison = x.equals(y);
			}
			
		
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
		public List<Integer> runConditions(List<Character> y, List<Integer> x, List<Proposition> range) {
			
					
			List<Integer> randomCombiNewTry = new ArrayList<>();
			
			for (int j = 0; j < y.size(); j++) {
				 int z=0;
				if (y.get(j).equals('+')) {
					range.get(j).setMin(x.get(j) + 1);
					int min = range.get(j).getMin();
					int max = range.get(j).getMax();
					
					z = newRandom(min, max);
				}

				if (y.get(j).equals('-')) {
					range.get(j).setMax(x.get(j));
					int min = range.get(j).getMin();
					int max = range.get(j).getMax();
					
					z = newRandom(min, max);
				}
				
				

				if (y.get(j).equals('=')) {
					z = x.get(j);
				}
				
				randomCombiNewTry.add(z);
			}
			
			
			return randomCombiNewTry;

		}
}
