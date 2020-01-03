package com.lavanya.utile;
import java.util.*;
import java.io.*;



/**
 * Singleton class Configuration qui permet d'acc�der � toutes les donn�es du fichier gameConfig.properties dans les classes o� c'est n�cessaire.
 * @author lavanya
 *
 */
public class Configuration {
	
//	Variable statique  _instance de type singleton
	private static Configuration _instance = null;

    private Properties props = null;

    /**
     * M�thode qui va acc�der au fichier gameConfi.properties et le charger pour pouvoir utiliser ses donn�es.
     * @throws IOException Si dans la singleton class Configuration l'instanciation n'a pas pu se faire correctement.
     */
    private Configuration() throws IOException {
         props = new Properties();
    	
    		
    		InputStream first = new FileInputStream("src/gameConfig.properties");
    		props.load(first);   
    	
    }

    
    /**
     * M�thode statique qui cr�e une instance de la classe Configuration seulement si _instance n'a jamais �t� instanci� auparavant
     * @return l'unique instance de Configuration
     * @throws IOException Si Configuration a mal �t� instanci�
     */
    public synchronized static Configuration getInstance() throws IOException {
    	
//    	Bloc if qui v�rifie si _instance de Configuration a d�ja �t� instanci�
//    	si non, il est instanci� il passe de null � une instanciation en un objet de Configuration
//    	si oui (_intance n'avait pas pour valeur nulle) il ne s'instancie pas de nouveau, il retourne la valeur que l'objet avait d�j�
        if (_instance == null)
            _instance = new Configuration();
        
        return _instance;
    }

    /**
     * M�thode qui r�cup�re la valeur de la donn�e stock�e par son nom (ici key). cette valeur est de type int.
     * @param key
     * 		ceci repr�sente le nom de la valeur que l'on souhaite r�cup�rer dans le fichier gameConfig.properties
     * @return retourne la valeur dont le nom a �t� pr�cis� en tant que param�tre.
     */
    public int getIntProperty(String key) {
        int combiDigit = 0;
        if (props.containsKey(key))
//        	convertit la valeur string en int
        	combiDigit = Integer.parseInt(props.getProperty(key));

        return combiDigit;
    }
    
    /**
     * M�thode qui r�cup�re la valeur de la donn�e stock�e par son nom (ici key). cette valeur est de type boolean.
     * @param key
     * 		ceci repr�sente le nom de la valeur que l'on souhaite r�cup�rer dans le fichier gameConfig.properties
     * @return retourne la valeur dont le nom a �t� pr�cis� en tant que param�tre.
     */
    public boolean getBooleanProperty(String key) {
    	boolean booleanProperty = true;
    	if (props.containsKey(key))
//    		convertit la valeur String en type boolean
    		booleanProperty = Boolean.valueOf(props.getProperty(key));
    	return booleanProperty;
    }
}
