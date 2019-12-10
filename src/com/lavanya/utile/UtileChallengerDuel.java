package com.lavanya.utile;

import java.io.IOException;


import com.lavanya.interfaces.ChallDuelInterface;

public class UtileChallengerDuel implements ChallDuelInterface {
	
	public boolean booleanProperties(String key) throws IOException {
		boolean confValue = Configuration.getInstance().getBooleanProperty(key);
		return confValue;
	}

}
