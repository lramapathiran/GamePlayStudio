package com.lavanya.factoryDesign;

import com.lavanya.challenger.Challenger;
import com.lavanya.defenseur.Defenseur;
import com.lavanya.duel.Duel;

public class GameFactory {
	
	public static Game getGame(GameType name){
		
		if(GameType.defenseur == name){
            return new Defenseur();
        }
        else if(GameType.challenger == name){
            return new Challenger();
        }
        else if(GameType.duel == name){
            return new Duel();
        }

		return null;
	}

}
