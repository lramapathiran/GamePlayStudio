package com.lavanya.interfaces;

import java.io.IOException;
import java.util.List;

import com.lavanya.factoryDesign.GameType;

public interface DefChallDuelInterface {
	int intProperties(String key) throws IOException;
	List<Integer> getRandom(int min, int max) throws IOException;
	List<Character> computerPropositionCheck(List<Integer> combiToFind, List<Integer> proposition) throws IOException;
	boolean winAnswer(List<Character> playerOrComputerClue) throws IOException;
	void replay(GameType GameType) throws IOException;
	List<Integer> playerCombi() throws IOException;
}
