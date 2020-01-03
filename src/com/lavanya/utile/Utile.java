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



/**
 * Classe qui contient les m�thodes communes � la classe Defenseur, Challenger et Duel.
 * La classe Utile impl�mente l'interface DefChallDuelInterface.
 * @author lavanya
 *
 */
public class Utile implements DefChallDuelInterface {

		final static Logger logger = LogManager.getLogger(Utile.class.getName());

		
		/**
		 * M�thode qui permet d'instancier Configuration et de r�cup�rer les donn�es de type int contenues dans le fichier properties.
		 * L'acc�s au fichier properties se fait au travers de la classe singleton Configuration dont on r�cup�re l'instance ici.
		 * @see Configuration
		 * @param key
		 *		ceci correspond au nom affect� � la donn�e qu'on souhaite r�cup�rer dans le fichier properties.
		 * @return retourne l'int de la donn�e d'int�r�t stock�e dans le fichier properties via la m�thode getIntProperty() du singleton Configuration.
		 * * @throws IOException Si dans la singleton class Configuration l'instanciation n'a pas pu se faire correctement. 
		 */
		public int intProperties(String key) throws IOException {
			int confValue = Configuration.getInstance().getIntProperty(key);
			return confValue;
		}
	

		/**
		 * M�thode qui permet � l'ordinateur de g�n�rer un chiffre al�atoire.
		 * Cette m�thode est r�p�t� plusieurs fois ("digit" = donn�e d�finie dans le fichier properties).
		 * @param min
		 * 		Le nombre al�atoire cr�� est compris dans un intervalle dont la valeur minimum est d�fini par min(issu du fichier properties).
		 * @param max
		 * 		Idem que min mais ici max d�finit la valeur maximum de l'intervalle.
		 * @return retourne la liste d'Integer qui est la combinaison de 4 chiffres g�n�r�e al�atoirement.
		 * * @throws IOException Si dans la singleton class Configuration l'instanciation n'a pas pu se faire correctement.
		 */
		public List<Integer> getRandom(int min, int max) throws IOException {

			
			List<Integer> randomCombi = new ArrayList<>();
			Random random = new Random();

//			g�n�re un chiffre al�atoire compris entre min et max plusieurs fois et d'ajouter chaque chiffre dans list Integer.
			for (int i = 0; i < intProperties("digit"); i++) {
				int j = min + random.nextInt(max - min);
				randomCombi.add(j);
				}
			
			

			return randomCombi;
		}
		

		/**
		 * M�thode qui permet au joueur d'entrer une combinaison de 4 chiffres et de la convertir en Liste d'Integer
		 * @return retourne une liste d'Integer qui contient la combinaison de 4 chiffres du joueur.
		 * @throws IOException Si dans la singleton class Configuration l'instanciation n'a pas pu se faire correctement.
		 */
		public List<Integer> playerCombi() throws IOException {

			List<Integer> playerAnswer = new ArrayList<>();
			Scanner sc = new Scanner(System.in);
			System.out.println(
					"Saisissez un nombre � 4 chiffres compris entre 0000 et 9999!");
			
//			boucle do/while qui demandera au joueur sa combinaison de 4 chiffres tant que celle-ci sera non valide
			do {
				String input = sc.nextLine();
				

	//			Chaque character du string est s�par� puis converti en array de string
				String inputAsArray[] = input.split("\\B");
				
				if (inputAsArray.length != intProperties("digit")) {
					logger.error("combinaison entr�e par le joueur < ou > � 4 valeurs(que ce soit des chiffres ou des caract�res non num�rique invalides)!");
					System.out.println("Votre combinaison doit faire 4 chiffres, veuillez entrer un nombre compris entre 0000 et 9999!");
				}
				else {
		//			Chaque valeur de String dans l'array est converti en int puis ajout� la liste d'Integer playerAnswer
//					bloc try/catch pour g�rer une exception de type NumberFormatException
					try {
						for (String stringValue : inputAsArray) {							
								int stringToInt = Integer.parseInt(stringValue);
								playerAnswer.add(stringToInt);					
						}
					} catch (NumberFormatException e) {
						playerAnswer.clear();
						logger.error("Saisie invalide: NumberFormatException!");
						System.out.println("Votre saisie est invalide! Veuillez saisir de nouveau votre combinaison! Elle ne doit comporter qu'une s�rie de 4 chiffres (entre 0000 et 9999):");
					}
				}
			}while(playerAnswer.size() != intProperties("digit") || false);

			return playerAnswer;

		}
		
		/**
		 * M�thode qui compare chaque chiffre de la combi secr�te � d�couvrir (du joueur ou de l'ordinateur) � celle propos�e (par le joueur ou l'ordi selon le mde de jeu)
		 * Cette comparaison va permettre de sp�cifier si lors du tour prochain, le joueur/l'ordi devra donner un chiffre sup�rieur(+), inf�rieur(-) ou �gal(=) au chiffre propos� au tour pr�c�dent.
		 * @param combiToFind
		 * 			Une liste qui contient la combinaison de 4 chiffres qui doit �tre d�couverte(selon le mode de jeu, ce sera celle de l'ordi ou du joueur)
		 * @param proposition
		 * 			Une liste qui contient la combinaison de 4 chiffres propos�e pour d�couvrir la combi scr�te (combiToFind), la proposition est faite par le joueur ou l'ordi
		 * @return retourne une combinaison de caract�res qui sont + = ou - pour indiquer au joueur/ordi quoi faire au tour prochain.
		 * @throws IOException Si dans la singleton class Configuration l'instanciation n'a pas pu se faire correctement.
		 */
		public List<Character> computerPropositionCheck(List<Integer> combiToFind, List<Integer> proposition) throws IOException {
			
			List<Character> computerCheck = new ArrayList<>();
			
//			boucle for qui affecte + si le chiffre de la combinaison secr�te est > � la proposition, - si c'est inf�rieur ou = si c'est identique
//			chaque valeur + = ou - est ensuite rajout� � une liste de type Character
			for (int i = 0; i < intProperties("digit"); i++) {

				if (combiToFind.get(i) > proposition.get(i)) {
					computerCheck.add('+');
				}

				if (combiToFind.get(i) < proposition.get(i)) {
					computerCheck.add('-');
				}

				if (combiToFind.get(i) == proposition.get(i)) {
					computerCheck.add('=');
				}

			}

			return computerCheck;
			
		}
		
		 
		/**
		 * M�thode de type bool�en pour d�finir si l'ordinateur/joueur a donn� une combinaison de 4 chiffres gagnante
		 * @param playerOrComputerClue
		 * 			ceci est la combinaison de valeurs + = ou - g�n�r�e par l'ordi ou le joueur pour aider l'adversaire � ajuster sa proposition au tour prochain
		 * 			elle sera compar�e � la combinaison gagnante (====).
		 * @return retourne le boolean true si playerOrComputerClue est ==== sinon false dans le cas contraire.
		 * @throws IOException Si dans la singleton class Configuration l'instanciation n'a pas pu se faire correctement.
		 */
		public boolean winAnswer(List<Character> playerOrComputerClue) throws IOException {

			List<Character> winCombi = new ArrayList<>();
			
// 			Cr�ation d'une liste qui correspond � la r�ponse attendue par l'ordi/joueur pour gagner (====)
//			Le nombre de tour dans la boucle est d�fini par le fichier properties ("digit")
			for (int i = 0; i < intProperties("digit"); i++) {
				winCombi.add('=');
			}

//			la r�ponse du joueur/ordi est compar�e � la r�ponse gagnante (====)
			boolean win = playerOrComputerClue.equals(winCombi);
			return win;
		}
		
		/**
		 * M�thode qui permet au joueur � la fin du jeu de rejouer ou de retourner au menu principal.
		 * @param GameType
		 * 		ceci correspond au mode de jeu qui en cours d'utilisation par le joueur(Duel, Defenseur ou Challenger) pour qu'il soit rejou� s'il le souhaite.		 * 
		 * @throws IOException Si dans la singleton class Configuration l'instanciation n'a pas pu se faire correctement.
		 */
		public void replay(GameType GameType) throws IOException {
			
			Scanner sc = new Scanner(System.in);
			System.out.println("Souhaitez-vous rejouer? O/N(pour retouner au Menu Principal, choisissez N)");
			String willReplay;
			
//				bouche do/while qui demandera au joueur s'il veut rejouer ou non jsuqu'� ce que sa saisie soit correcte(ex: "O" ou "N")
				do {
					willReplay = sc.nextLine();
					if (willReplay.equalsIgnoreCase("O") || willReplay.equalsIgnoreCase("N")) {
//							switch qui relance le jeu en cours si le joueur dit "oui"("o") ou renvoie au menu principal si "non"("n")
							switch(willReplay.toLowerCase()) {
								case "o":
									Game gameMode = GameFactory.getGame(GameType);
									gameMode.gamePlay();
									break;
								case "n":
									GameLaunch.main(null);
									break;
							}
//							message d'erreur si la saisie du joueur est incorrecte!
					}else{
						logger.error("Saisie invalide: O ou N non renseign�");
						System.out.println("Votre saisie est invalide! Veuillez indiquer de nouveau O pour oui et N pour non:");
							
					}
					
				}while(!willReplay.equalsIgnoreCase("O")||!willReplay.equalsIgnoreCase("N"));
		
			
			
			
		}
		
		

}
