package aima.core.environment.Canibales;

import aima.core.search.framework.GoalTest;

//*****************************************************************
// File:   CanibalesGoalTest.java
// Author: Andrés Gavín Murillo 716358
// Date:   Octubre 2018
// Coms:   Inteligencia artificial - Práctica 1
//*****************************************************************

public class CanibalesGoalTest implements GoalTest {
	public static final int i = CanibalesBoard.i;
	public static final int f = CanibalesBoard.f;
	
	CanibalesBoard goal = new CanibalesBoard(new int[] { 0, 0, f, 3, 3 });

	public boolean isGoalState(Object state) {
		CanibalesBoard board = (CanibalesBoard) state;
		return board.equals(goal);
	}

	@Override
	public String toString() {
		return this.goal.toString();
	}
}