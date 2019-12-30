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
 * Classe qui contient les méthodes communes à la classe Defenseur et Duel.
 * La classe UtileDefenseurDuel implémente l'interface DefDuelInterface.
 * @author lavanya
 *
 */
public class UtileDefenseurDuel implements DefDuelInterface{
	

		static DefChallDuelInterface iDefChallDuel = new Utile();
		static Logger logger = Logger.getLogger("GameLaunch");
		
		/**
		 * Méthode qui permet à l'ordinateur de générer un chiffre aléatoire compris entre min et max
		 * @param min
		 * 			min est soit définit par le fichier properties
		 * 			soit redéfini dans la méthode runConditions() en fonction du chiffre proposé dans la combinaison de l'ordi lors du tour précédent et de l'indication + = - donné par le joueur.
		 * @param max
		 * 			idemn que min, sa valeur est défini par le fichier properties ou runConditions().
		 * @see runConditions(ci dessous)
		 * @return retourne le chiffre aléatoire compris dans l'intervalle min et max
		 */
		public Integer newRandom(int min, int max) {

			Random random = new Random();
			int j = min + random.nextInt(max - min);
			return j;
		}
		

		/**
		 * Méthode où le joueur à partir de la combinaison proposée par l'ordi va pouvoir donner des indications + = ou -.
		 * Sa réponse est convertie en Character puis ajoutée dans une liste pour que l'ordi puisse l'utiliser et corriger sa proposition au tour prochain.
		 * @throws génère une exception si dans la singleton class Configuration, l'instanciation n'a pas pu se faire correctement.
		 * @return retourne la liste de Character issue de la conversion de la réponse en String --> Character du joueur.
		 */
		public List<Character> playerAnswer() throws IOException {
			
			List<Character> playerClue= new ArrayList<>();
			Scanner sc = new Scanner(System.in);
			
			System.out.println(
					"Veuillez indiquer + = ou - pour chaque chaque valeur de la combinaison proposée: ex +,=,=,-(merci d'utiliser le même format de réponse que dans l'exemple)");
			
//			sc.close();	
			
//			Création d'un array de Character vide pour pouvoir y entrer la réponse du joueur une fois que celle-ci sera convertie en Character
			char[] charArray;
			
			
			String strPlus = "+";
			String strMinus = "-";
			String strEqual = "=";
			
//			conversion des strings ci dessus en Character  pour permettre la comparaison dans le if de la boucle do/while 
			char charPlus = strPlus.charAt(0);
			char charMinus = strMinus.charAt(0);
			char charEqual = strEqual.charAt(0);
			
//			boucle do/while pour permettre au joueur d'entrer une combinaison + = ou - jusqu'à que sa saisie soit valide
			do {
				String clue = sc.nextLine();
				
//				La réponse du joueur est convertie en Character et intégrée dans l'array de Character vide créé plus haut
				charArray = clue.toCharArray();
//				condition pour gérer les erreurs de saisies avec affichage de messages/logs d'erreurs si le joueur entre une combinaison inféreur ou supérieur à 4 éléments.
				if (charArray.length != iDefChallDuel.intProperties("digit")) {
					logger.error("erreur de saisie: les valeurs + = ou - entrées sont > ou < à 4!");
					System.out.println("Votre saisie est supérieur ou inférieur à 4 valeurs! Veuillez rentrer 4 valeurs de nouveau (+ = ou -)!: ");
				}
				
//				condition pour gérer les erreurs de saisies avec affichage de messages/logs d'erreurs si le joueur entre une combinaison autre que + = -. 
//				Si aucune erreur détectée, l'array de Character contenant la réponse du joueur passe dans une boucle for où le charArray est converti en liste de Character 
				else {
					for(Character ch: charArray) {
						
					
						if (ch.equals(charPlus) || ch.equals(charMinus) || ch.equals(charEqual)) {
							playerClue.add(ch);
						}
						else {
							playerClue.clear();
							logger.error("Erreur: saisie invalide avec un ou plusieurs charactères différent de + = ou -");
							System.out.println("Votre saisie est invalide, elle ne doit contenir que des valeurs de type + = ou -! veuillez renseigner de nouveau votre indice! :");
						}
					}
				}
			}while(charArray.length != iDefChallDuel.intProperties("digit") || playerClue.size() != iDefChallDuel.intProperties("digit"));
				
			return playerClue;

		}
		

		/**
		 * Méthode pour vérifier et corriger la réponse du joueur si celui-ci a renseigné les mauvais character + = -.
		 * @param playerClue
		 * 			ceci correspond à la réponse du joueur sous forme de liste de Character issue de la méthode playerAnswer()
		 * @param playerAnswerExpected
		 * 			Réponse que le joueur (+ = -) aurait du avoir rentré. Elle a été générée par l'ordi et doit être égale à playerClue.
		 * @return retourne la réponse corrigée ou non du joueur si ce dernier avait mal renseigné sa réponse. 
		 */
		public List<Character> validationPlayerClue(List<Character> playerClue, List<Character> playerAnswerExpected) {
			
			
//			si le contenu de playerClue est différent de playerAnswer Expected, le joueur devra corriger sa réponse + = -
			boolean comparison = playerClue.equals(playerAnswerExpected);
			
//			boucle while qui demande au joueur de rectifier sa réponse + = - jusqu'à ce qu'elle soit identique à la réponse attendue playerAnswerExpected
			while(!comparison) {
				Scanner sc = new Scanner(System.in);
				System.out.println("Votre réponse est incorrecte! Veuillez vérifier votre saisie et la corriger ci-dessous:");
				String clue = sc.nextLine();
				
//				Sa nouvelle réponse est alors de nouveau convertie en Character et les nouvelles valeurs remplacent les valeurs erronées dans la liste playerClue
				int i=0;
				for(Character ch: clue.toCharArray()) {
					
					playerClue.set(i,ch);
					i++;
				}
				
//				Son contenu est de nouveau comparé à playerAnswerExpected
				comparison = playerClue.equals(playerAnswerExpected);
			}
			
			return playerClue;
		}
		
		
		/**
		 * Méthode qui crée une liste de Proposition.Chaque valeur de cette liste est un set de min et max défini dans la classe Proposition.
		 * Ceci permet à chaque élément de la combinaison à découvrir d'avoir son propre intervalle min/max pour que l'ordi puisse faire évoluer cet intervalle indépendemment des autres au cours de chaque tentative
		 * @see Proposition
		 * le nombre de valeur de cette liste est défini par le fichier properties ("digit")
		 * @throws génère une exception si dans la singleton class Configuration, l'instanciation n'a pas pu se faire correctement.
		 * @return retourne la liste de Proposition contenant tous les sets min/max associés à chaque valeur de la combi secrète
		 */
		public List<Proposition> rangeArray() throws IOException {
			
			List<Proposition> limit = new ArrayList<Proposition>();
					
//			boucle for qui crée une liste de Proposition avec le fichier properties qui définit le nombre de tour ("digit") via le singleton Configuration
//			Chaque élément de la liste Proposition a pour valeur initiale min=0 et max=10 comme définie dans le fichier properties
//			Au cours des différentes tentatives de l'ordi pour deviner la combi secrète, l'intervale min/max va évoluer de manière plus restreinte pour que l'ordi puisse trouver la bonne réponse pour chaque chiffre.
			for (int i = 0; i < iDefChallDuel.intProperties("digit"); i++) {
				limit.add(new Proposition(iDefChallDuel.intProperties("min"),iDefChallDuel.intProperties("max")));
			}
			
			return limit;
		}
		
		
		/**
		 * Méthode qui permet à l'ordi de proposer une nouvelle combinaison (de plusieurs chiffres définit par le fichier properties).
		 * Pour cela il va se servir des indications(+ = -) que le joueur a fourni dans la méthode validationPlayeClue() pour rajuster les différents intervalles min/max de la liste de Proposition. 
		 * @param validPlayerClue
		 * 			ceci est la réponse du joueur (+ = -) pour renseigner l'ordi de si sa proposition était correcte, inférieur ou supérieur à la combi secrète pour que ce dernier la corrige au tour prochain.
		 * @param computerProposition
		 * 			ceci est la combinaison que l'ordinateur a proposé au tour précédent pour deviner la combi secrète du joueur
		 * @param range
		 * 			ceci est une liste contenant plusieurs sets d'intervalle min/max pour chaque chiffre de la combinaison. 
		 * 			Ces intervalles à l'aide de validPlayerClue va être plus restreint pour accroitre les chances de l'ordi de deviner chaque chiffre de la combi secrète lors de sa nouvelle tentative
		 * @see Proposition
		 * @return retourne la nouvelle combinaison proposée par l'ordi qui sera annoncée au tour prochain
		 */
		public List<Integer> runConditions(List<Character> validPlayerClue, List<Integer> computerProposition, List<Proposition> range) {
			
					
			List<Integer> randomCombiNewTry = new ArrayList<>();
	
//			Boucle for où chaque indication (+ = -) donné par le joueur pour chaque élément de la combinaison va permettre de repréciser leur min/max
//			Ce nouveaux sets de min/max vont permettre de restreindre l'intervalle
//			et de proposer un nouveau chiffre aléatoire(avec la méthode newRandom()) dont les chances d'être correctes sont plus élevées.
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
