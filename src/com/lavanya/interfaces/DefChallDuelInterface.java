package com.lavanya.interfaces;

import java.io.IOException;
import java.util.List;

import com.lavanya.factoryDesign.GameType;

public interface DefChallDuelInterface {
	int intProperties(String key) throws IOException;
	List<Integer> getRandom(int min, int max) throws IOException;
	List<Character> computerPropositionCheck(List<Integer> x, List<Integer> y) throws IOException;
	boolean winAnswer(List<Character> y) throws IOException;
	void replay(GameType GameType) throws IOException;
	List<Integer> playerCombi();
}
