package aima.gui.demo.search;

import aima.core.environment.eightpuzzle.EightPuzzleBoard;
import aima.core.environment.eightpuzzle.EightPuzzleFunctionFactory;
import aima.core.environment.eightpuzzle.EightPuzzleGoalTest2;
import aima.core.environment.eightpuzzle.ManhattanHeuristicFunction2;
import aima.core.environment.eightpuzzle.MisplacedTilleHeuristicFunction2;
import aima.core.search.framework.GraphSearch;
import aima.core.search.framework.Problem;
import aima.core.search.framework.Search;
import aima.core.search.framework.SearchAgent;
import aima.core.search.informed.AStarSearch;
import aima.core.search.uninformed.BreadthFirstSearch;
import aima.core.search.uninformed.IterativeDeepeningSearch;
import aima.core.util.math.Biseccion;

//*****************************************************************
// File:   EightPuzzlePract2.java
// Author: Andrés Gavín Murillo 716358
// Date:   Octubre 2018
// Coms:   Inteligencia artificial - Práctica 2
//*****************************************************************

public class EightPuzzlePract2 {
	public static void main(String[] args) {
		System.out.println("------------------------------------------------------------------------------------------");
		System.out.format("||%4s||%20s%19s||%20s%19s||\n", "", "Nodos Generados", "", "b*", "");
		System.out.println("------------------------------------------------------------------------------------------");
		System.out.format("||%4s||%7s%2s|%7s%2s|%7s%2s|%7s%2s||%7s%2s|%7s%2s|%7s%2s|%7s%2s||\n",
							"d", "BFS", "", "IDS", "", "A*h(1)", "", "A*h(2)", "", "BFS", "", "IDS", "", "A*h(1)", "", "A*h(2)", "");
		System.out.println("------------------------------------------------------------------------------------------");
		System.out.println("------------------------------------------------------------------------------------------");
		
		Integer k;
		Integer generatedNodes[]; // { bfs, ids, a1, a2 }
		Integer a1;
		EightPuzzleBoard board;
		EightPuzzleGoalTest2 goal;
		Biseccion bf;
		
		for (Integer i=2; i<25; i++) {
			k = 0;
			generatedNodes = new Integer[] { 0, 0, 0, 0 };
			
			while (k < 100) {
				board = GenerateInitialEightPuzzleBoard.randomIni();
				goal = new EightPuzzleGoalTest2();
				goal.setGoalState(GenerateInitialEightPuzzleBoard.random(i, board));
				
				a1 = eightPuzzleSearch(new AStarSearch(new GraphSearch(), new ManhattanHeuristicFunction2(goal)), board, "A*-M-G", i, goal, true, "");
				
				if (a1 != 0) {
					generatedNodes[3] += a1;
					generatedNodes[0] += eightPuzzleSearch(new BreadthFirstSearch(new GraphSearch()), board, "BFS-G", i, goal, true, "");
					if (i>10) generatedNodes[1] += eightPuzzleSearch(new IterativeDeepeningSearch(), board, "IDS", i, goal, false, "");
					else generatedNodes[1] += eightPuzzleSearch(new IterativeDeepeningSearch(), board, "IDS", i, goal, true, "");
					generatedNodes[2] += eightPuzzleSearch(new AStarSearch(new GraphSearch(), new MisplacedTilleHeuristicFunction2(goal)), board, "A*-FD-G", i, goal, true, "");
					
					k++;
				}
			}
			
			System.out.format("||%4s||", i);
			for (int j=0; j<4; j++) {
				if (generatedNodes[j]/100 == -1) System.out.format("%7s%2s|", "---", "");
				else System.out.format("%7s%2s|", generatedNodes[j]/100, "");
			}
			
			for (int j=0; j<4; j++) {
				bf = new Biseccion();
				bf.setDepth(i);
				bf.setGeneratedNodes(generatedNodes[j]/100);
				if (generatedNodes[j]/100 == -1) System.out.format("|%7s%2s", "---", "");
				else System.out.format("|%3s%.2f%2s", "", bf.metodoDeBiseccion(1.00000001, 4.0, 1E-10), "");
			}
			
			System.out.println("||");
		}
		System.out.println("------------------------------------------------------------------------------------------");
	}

	// Normal case: return generated nodes if expectedDepth is equal to real depth (>0), else return 0
	// Error or not exec case: return -1
	private static Integer eightPuzzleSearch(Search busqueda, EightPuzzleBoard estadoInicial, String nombre, Integer expectedDepth, EightPuzzleGoalTest2 goal, Boolean exec, String errorInfo) {
		if (exec) {
			try {
				Problem problem = new Problem(estadoInicial, EightPuzzleFunctionFactory
						.getActionsFunction(), EightPuzzleFunctionFactory
						.getResultFunction(), goal);
				
				SearchAgent agent = new SearchAgent(problem, busqueda);
				
				Integer depth, generatedNodes;
				String pathcostM = agent.getInstrumentation().getProperty("pathCost");
				
				if (pathcostM != null) depth = (int)Float.parseFloat(pathcostM);
				else depth = 0;
				if (agent.getInstrumentation().getProperty("nodesGenerated") == null) generatedNodes = 0;
				else generatedNodes = 
						(int)Float.parseFloat(agent.getInstrumentation().getProperty("nodesGenerated"));
				
				if (depth == expectedDepth) return generatedNodes;
				else return 0;
			}
			catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		return -1;
	}
}