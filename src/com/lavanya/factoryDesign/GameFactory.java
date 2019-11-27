package com.lavanya.factoryDesign;

import com.lavanya.challenger.Challenger;
import com.lavanya.defenseur.Defenseur;
import com.lavanya.duel.Duel;

public class GameFactory {
	
	public static Game getGame(String type){
		
		if("Defenseur".equalsIgnoreCase(type)){
            return new Defenseur();
        }
        else if("Challenger".equalsIgnoreCase(type)){
            return new Challenger();
        }
        else if("Duel".equalsIgnoreCase(type)){
            return new Duel();
        }

		return null;
	}

}
