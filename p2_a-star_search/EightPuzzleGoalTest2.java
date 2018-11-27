package aima.core.environment.eightpuzzle;

import aima.core.search.framework.GoalTest;

//*****************************************************************
//File:   EightPuzzleGoalTest2.java
//Author: Andrés Gavín Murillo 716358
//Date:   Octubre 2018
//Coms:   Inteligencia artificial - Práctica 2
//*****************************************************************

public class EightPuzzleGoalTest2 implements GoalTest {
	EightPuzzleBoard goal = new EightPuzzleBoard(new int[] { 0, 1, 2, 3, 4, 5,
			6, 7, 8 });

	public void setGoalState(Object state) {
		this.goal = (EightPuzzleBoard) state;
	}
	
	public EightPuzzleBoard getGoalState() {
		return this.goal;
	}
	
	public boolean isGoalState(Object state) {
		EightPuzzleBoard board = (EightPuzzleBoard) state;
		return board.equals(goal);
	}
}