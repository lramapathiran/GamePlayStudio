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


/**
 * Classe qui contient les m�thodes communes � la classe Defenseur et Duel.
 * La classe UtileDefenseurDuel impl�mente l'interface DefDuelInterface.
 * @author lavanya
 *
 */
public class UtileDefenseurDuel implements DefDuelInterface{
	

		static DefChallDuelInterface iDefChallDuel = new Utile();
		static Logger logger = Logger.getLogger("GameLaunch");
		
		/**
		 * M�thode qui permet � l'ordinateur de g�n�rer un chiffre al�atoire compris entre min et max
		 * @param min
		 * 			min est soit d�finit par le fichier properties
		 * 			soit red�fini dans la m�thode runConditions() en fonction du chiffre propos� dans la combinaison de l'ordi lors du tour pr�c�dent et de l'indication + = - donn� par le joueur.
		 * @param max
		 * 			idemn que min, sa valeur est d�fini par le fichier properties ou runConditions().
		 * @see runConditions(ci dessous)
		 * @return retourne le chiffre al�atoire compris dans l'intervalle min et max
		 */
		public Integer newRandom(int min, int max) {

			Random random = new Random();
			int j = min + random.nextInt(max - min);
			return j;
		}
		

		/**
		 * M�thode o� le joueur � partir de la combinaison propos�e par l'ordi va pouvoir donner des indications + = ou -.
		 * Sa r�ponse est convertie en Character puis ajout�e dans une liste pour que l'ordi puisse l'utiliser et corriger sa proposition au tour prochain.
		 * @throws g�n�re une exception si dans la singleton class Configuration, l'instanciation n'a pas pu se faire correctement.
		 * @return retourne la liste de Character issue de la conversion de la r�ponse en String --> Character du joueur.
		 */
		public List<Character> playerAnswer() throws IOException {
			
			List<Character> playerClue= new ArrayList<>();
			Scanner sc = new Scanner(System.in);
			
			System.out.println(
					"Veuillez indiquer + = ou - pour chaque chaque valeur de la combinaison propos�e: ex +,=,=,-(merci d'utiliser le m�me format de r�ponse que dans l'exemple)");
			
//			sc.close();	
			
//			Cr�ation d'un array de Character vide pour pouvoir y entrer la r�ponse du joueur une fois que celle-ci sera convertie en Character
			char[] charArray;
			
			
			String strPlus = "+";
			String strMinus = "-";
			String strEqual = "=";
			
//			conversion des strings ci dessus en Character  pour permettre la comparaison dans le if de la boucle do/while 
			char charPlus = strPlus.charAt(0);
			char charMinus = strMinus.charAt(0);
			char charEqual = strEqual.charAt(0);
			
//			boucle do/while pour permettre au joueur d'entrer une combinaison + = ou - jusqu'� que sa saisie soit valide
			do {
				String clue = sc.nextLine();
				
//				La r�ponse du joueur est convertie en Character et int�gr�e dans l'array de Character vide cr�� plus haut
				charArray = clue.toCharArray();
//				condition pour g�rer les erreurs de saisies avec affichage de messages/logs d'erreurs si le joueur entre une combinaison inf�reur ou sup�rieur � 4 �l�ments.
				if (charArray.length != iDefChallDuel.intProperties("digit")) {
					logger.error("erreur de saisie: les valeurs + = ou - entr�es sont > ou < � 4!");
					System.out.println("Votre saisie est sup�rieur ou inf�rieur � 4 valeurs! Veuillez rentrer 4 valeurs de nouveau (+ = ou -)!: ");
				}
				
//				condition pour g�rer les erreurs de saisies avec affichage de messages/logs d'erreurs si le joueur entre une combinaison autre que + = -. 
//				Si aucune erreur d�tect�e, l'array de Character contenant la r�ponse du joueur passe dans une boucle for o� le charArray est converti en liste de Character 
				else {
					for(Character ch: charArray) {
						
					
						if (ch.equals(charPlus) || ch.equals(charMinus) || ch.equals(charEqual)) {
							playerClue.add(ch);
						}
						else {
							playerClue.clear();
							logger.error("Erreur: saisie invalide avec un ou plusieurs charact�res diff�rent de + = ou -");
							System.out.println("Votre saisie est invalide, elle ne doit contenir que des valeurs de type + = ou -! veuillez renseigner de nouveau votre indice! :");
						}
					}
				}
			}while(charArray.length != iDefChallDuel.intProperties("digit") || playerClue.size() != iDefChallDuel.intProperties("digit"));
				
			return playerClue;

		}
		

		/**
		 * M�thode pour v�rifier et corriger la r�ponse du joueur si celui-ci a renseign� les mauvais character + = -.
		 * @param playerClue
		 * 			ceci correspond � la r�ponse du joueur sous forme de liste de Character issue de la m�thode playerAnswer()
		 * @param playerAnswerExpected
		 * 			R�ponse que le joueur (+ = -) aurait du avoir rentr�. Elle a �t� g�n�r�e par l'ordi et doit �tre �gale � playerClue.
		 * @return retourne la r�ponse corrig�e ou non du joueur si ce dernier avait mal renseign� sa r�ponse. 
		 */
		public List<Character> validationPlayerClue(List<Character> playerClue, List<Character> playerAnswerExpected) {
			
			
//			si le contenu de playerClue est diff�rent de playerAnswer Expected, le joueur devra corriger sa r�ponse + = -
			boolean comparison = playerClue.equals(playerAnswerExpected);
			
//			boucle while qui demande au joueur de rectifier sa r�ponse + = - jusqu'� ce qu'elle soit identique � la r�ponse attendue playerAnswerExpected
			while(!comparison) {
				Scanner sc = new Scanner(System.in);
				System.out.println("Votre r�ponse est incorrecte! Veuillez v�rifier votre saisie et la corriger ci-dessous:");
				String clue = sc.nextLine();
				
//				Sa nouvelle r�ponse est alors de nouveau convertie en Character et les nouvelles valeurs remplacent les valeurs erron�es dans la liste playerClue
				int i=0;
				for(Character ch: clue.toCharArray()) {
					
					playerClue.set(i,ch);
					i++;
				}
				
//				Son contenu est de nouveau compar� � playerAnswerExpected
				comparison = playerClue.equals(playerAnswerExpected);
			}
			
			return playerClue;
		}
		
		
		/**
		 * M�thode qui cr�e une liste de Proposition.Chaque valeur de cette liste est un set de min et max d�fini dans la classe Proposition.
		 * Ceci permet � chaque �l�ment de la combinaison � d�couvrir d'avoir son propre intervalle min/max pour que l'ordi puisse faire �voluer cet intervalle ind�pendemment des autres au cours de chaque tentative
		 * @see Proposition
		 * le nombre de valeur de cette liste est d�fini par le fichier properties ("digit")
		 * @throws g�n�re une exception si dans la singleton class Configuration, l'instanciation n'a pas pu se faire correctement.
		 * @return retourne la liste de Proposition contenant tous les sets min/max associ�s � chaque valeur de la combi secr�te
		 */
		public List<Proposition> rangeArray() throws IOException {
			
			List<Proposition> limit = new ArrayList<Proposition>();
					
//			boucle for qui cr�e une liste de Proposition avec le fichier properties qui d�finit le nombre de tour ("digit") via le singleton Configuration
//			Chaque �l�ment de la liste Proposition a pour valeur initiale min=0 et max=10 comme d�finie dans le fichier properties
//			Au cours des diff�rentes tentatives de l'ordi pour deviner la combi secr�te, l'intervale min/max va �voluer de mani�re plus restreinte pour que l'ordi puisse trouver la bonne r�ponse pour chaque chiffre.
			for (int i = 0; i < iDefChallDuel.intProperties("digit"); i++) {
				limit.add(new Proposition(iDefChallDuel.intProperties("min"),iDefChallDuel.intProperties("max")));
			}
			
			return limit;
		}
		
		
		/**
		 * M�thode qui permet � l'ordi de proposer une nouvelle combinaison (de plusieurs chiffres d�finit par le fichier properties).
		 * Pour cela il va se servir des indications(+ = -) que le joueur a fourni dans la m�thode validationPlayeClue() pour rajuster les diff�rents intervalles min/max de la liste de Proposition. 
		 * @param validPlayerClue
		 * 			ceci est la r�ponse du joueur (+ = -) pour renseigner l'ordi de si sa proposition �tait correcte, inf�rieur ou sup�rieur � la combi secr�te pour que ce dernier la corrige au tour prochain.
		 * @param computerProposition
		 * 			ceci est la combinaison que l'ordinateur a propos� au tour pr�c�dent pour deviner la combi secr�te du joueur
		 * @param range
		 * 			ceci est une liste contenant plusieurs sets d'intervalle min/max pour chaque chiffre de la combinaison. 
		 * 			Ces intervalles � l'aide de validPlayerClue va �tre plus restreint pour accroitre les chances de l'ordi de deviner chaque chiffre de la combi secr�te lors de sa nouvelle tentative
		 * @see Proposition
		 * @return retourne la nouvelle combinaison propos�e par l'ordi qui sera annonc�e au tour prochain
		 */
		public List<Integer> runConditions(List<Character> validPlayerClue, List<Integer> computerProposition, List<Proposition> range) {
			
					
			List<Integer> randomCombiNewTry = new ArrayList<>();
	
//			Boucle for o� chaque indication (+ = -) donn� par le joueur pour chaque �l�ment de la combinaison va permettre de repr�ciser leur min/max
//			Ce nouveaux sets de min/max vont permettre de restreindre l'intervalle
//			et de proposer un nouveau chiffre al�atoire(avec la m�thode newRandom()) dont les chances d'�tre correctes sont plus �lev�es.
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
