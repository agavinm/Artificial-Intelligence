package aima.core.environment.eightpuzzle;

import aima.core.search.framework.HeuristicFunction;
import aima.core.util.datastructure.XYLocation;

//*****************************************************************
//File:   ManhattanHeuristicFunction2.java
//Author: Andrés Gavín Murillo 716358
//Date:   Octubre 2018
//Coms:   Inteligencia artificial - Práctica 2
//*****************************************************************

public class ManhattanHeuristicFunction2 implements HeuristicFunction {

	private EightPuzzleGoalTest2 goalState;
	
	public ManhattanHeuristicFunction2(EightPuzzleGoalTest2 goalState) {
		this.goalState = goalState;
	}
	
	public double h(Object state) {
		EightPuzzleBoard board = (EightPuzzleBoard) state;
		int retVal = 0;
		for (int i = 1; i < 9; i++) {
			XYLocation loc = board.getLocationOf(i);
			XYLocation goalLoc = this.goalState.goal.getLocationOf(i);
			retVal += evaluateManhattanDistanceOf(i, loc, goalLoc);
		}
		return retVal;

	}

	public int evaluateManhattanDistanceOf(int i, XYLocation loc, XYLocation goalLoc) {
		int retVal = -1;
		int xpos = loc.getXCoOrdinate();
		int ypos = loc.getYCoOrdinate();
		int goalXpos = goalLoc.getXCoOrdinate();
		int goalYpos = goalLoc.getYCoOrdinate();
		retVal = Math.abs(xpos - goalXpos) + Math.abs(ypos - goalYpos);
		return retVal;
	}
}