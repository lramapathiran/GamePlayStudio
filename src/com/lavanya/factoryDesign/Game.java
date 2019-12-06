package com.lavanya.factoryDesign;

import java.io.IOException;
 
enum GameType {
	defenseur,challenger,duel
}

public abstract class Game {
	
	
	public abstract void gamePlay() throws IOException;
	public abstract void replay() throws IOException;
	
	
}
