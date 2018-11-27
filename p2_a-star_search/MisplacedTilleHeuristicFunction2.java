package aima.core.environment.eightpuzzle;

import aima.core.search.framework.HeuristicFunction;
import aima.core.util.datastructure.XYLocation;

//*****************************************************************
//File:   MisplacedTilleHeuristicFunction2.java
//Author: Andrés Gavín Murillo 716358
//Date:   Octubre 2018
//Coms:   Inteligencia artificial - Práctica 2
//*****************************************************************

public class MisplacedTilleHeuristicFunction2 implements HeuristicFunction {

	private EightPuzzleGoalTest2 goalState;
	
	public MisplacedTilleHeuristicFunction2(EightPuzzleGoalTest2 goalState) {
		this.goalState = goalState;
	}
	
	public double h(Object state) {
		EightPuzzleBoard board = (EightPuzzleBoard) state;
		return getNumberOfMisplacedTiles(board);
	}

	private int getNumberOfMisplacedTiles(EightPuzzleBoard board) {
		int numberOfMisplacedTiles = 0;
		for (int i=0; i<9; i++) {
			if (!(board.getLocationOf(i).equals(this.goalState.goal.getLocationOf(i)))) {
				numberOfMisplacedTiles++;
			}
		}
		
		// Subtract the gap position from the # of misplaced tiles
		// as its not actually a tile (see issue 73).
		if (numberOfMisplacedTiles > 0) {
			numberOfMisplacedTiles--;
		}
		return numberOfMisplacedTiles;
	}
}