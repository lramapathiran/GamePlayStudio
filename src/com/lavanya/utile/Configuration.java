package com.lavanya.utile;
import java.util.*;
import java.io.*;



/**
 * Singleton class Configuration qui permet d'accéder à toutes les données du fichier gameConfig.properties dans les classes où c'est nécessaire.
 * @author lavanya
 *
 */
public class Configuration {
	
//	Variable statique  _instance de type singleton
	private static Configuration _instance = null;

    private Properties props = null;

    /**
     * Méthode qui va accéder au fichier gameConfi.properties et le charger pour pouvoir utiliser ses données.
     * @throws IOException Si dans la singleton class Configuration l'instanciation n'a pas pu se faire correctement.
     */
    private Configuration() throws IOException {
         props = new Properties();
    	
    		
    		InputStream first = new FileInputStream("src/gameConfig.properties");
    		props.load(first);   
    	
    }

    
    /**
     * Méthode statique qui crée une instance de la classe Configuration seulement si _instance n'a jamais été instancié auparavant
     * @return l'unique instance de Configuration
     * @throws IOException Si Configuration a mal été instancié
     */
    public synchronized static Configuration getInstance() throws IOException {
    	
//    	Bloc if qui vérifie si _instance de Configuration a déja été instancié
//    	si non, il est instancié il passe de null à une instanciation en un objet de Configuration
//    	si oui (_intance n'avait pas pour valeur nulle) il ne s'instancie pas de nouveau, il retourne la valeur que l'objet avait déjà
        if (_instance == null)
            _instance = new Configuration();
        
        return _instance;
    }

    /**
     * Méthode qui récupère la valeur de la donnée stockée par son nom (ici key). cette valeur est de type int.
     * @param key
     * 		ceci représente le nom de la valeur que l'on souhaite récupérer dans le fichier gameConfig.properties
     * @return retourne la valeur dont le nom a été précisé en tant que paramètre.
     */
    public int getIntProperty(String key) {
        int combiDigit = 0;
        if (props.containsKey(key))
//        	convertit la valeur string en int
        	combiDigit = Integer.parseInt(props.getProperty(key));

        return combiDigit;
    }
    
    /**
     * Méthode qui récupère la valeur de la donnée stockée par son nom (ici key). cette valeur est de type boolean.
     * @param key
     * 		ceci représente le nom de la valeur que l'on souhaite récupérer dans le fichier gameConfig.properties
     * @return retourne la valeur dont le nom a été précisé en tant que paramètre.
     */
    public boolean getBooleanProperty(String key) {
    	boolean booleanProperty = true;
    	if (props.containsKey(key))
//    		convertit la valeur String en type boolean
    		booleanProperty = Boolean.valueOf(props.getProperty(key));
    	return booleanProperty;
    }
}
