package aima.gui.demo.search;

import java.util.Locale;
import java.util.Random;
import aima.core.environment.nqueens.AttackingPairsHeuristic;
import aima.core.environment.nqueens.NQueensBoard;
import aima.core.environment.nqueens.NQueensFunctionFactory;
import aima.core.environment.nqueens.NQueensGoalTest;
import aima.core.search.framework.Problem;
import aima.core.search.framework.SearchAgent;
import aima.core.search.local.HillClimbingSearch;
import aima.core.util.datastructure.XYLocation;

//*****************************************************************
//File:   NQueensLocal.java
//Author: Andrés Gavín Murillo 716358
//Date:   Diciembre 2018
//Coms:   Inteligencia artificial - TP6
//*****************************************************************

public class NQueensLocal {

	private static final int _boardSize = 8;
	
	public static void main(String[] args) {

		nQueensHillClimbingSearch_Statistics(10000);
		System.out.println("");
		System.out.println("");
		nQueensRandomRestartHillClimbing();
	}

	private static void nQueensHillClimbingSearch_Statistics(int numExperiments) {
		System.out.println("NQueens HillClimbing con " + numExperiments + " estados"
				+ " iniciales diferentes -->");
		try {
			int fallos = 0;
			int costeFallos = 0;
			int exitos = 0;
			int costeExitos = 0;
			int expandedNodes;
			NQueensGoalTest goal = new NQueensGoalTest();
			NQueensBoard boards[] = new NQueensBoard[numExperiments];
			
			for (int i = 0; i < numExperiments; i++) {
				NQueensBoard board = randomBoard();
				boolean repetida = true;
				
				while (repetida) {
					repetida = false;
					
					for (int j = 0; j < i; j++) {
						if (board.equals(boards[j]))
							repetida = true;
					}
					
					if (repetida) {
						board = randomBoard();
					}
					else {
						boards[i] = board;
					}
				}
				
				Problem problem = new Problem(board,
						NQueensFunctionFactory.getCActionsFunction(),
						NQueensFunctionFactory.getResultFunction(),
						goal);
				HillClimbingSearch search = new HillClimbingSearch(
						new AttackingPairsHeuristic());
				SearchAgent agent = new SearchAgent(problem, search);
				

				
				if (agent.getInstrumentation().getProperty("nodesExpanded") == null) expandedNodes = 0;
				else expandedNodes = 
						(int)Float.parseFloat(agent.getInstrumentation().getProperty("nodesExpanded"));
				
				if (goal.isGoalState(search.getLastSearchState())) {
					exitos++;
					costeExitos += expandedNodes;
					//System.out.println("Exito: " + i);
				}
				else {
					fallos++;
					costeFallos += expandedNodes;
					//System.out.println("Fallo: " + i);
				}
			}
			
			System.out.format(Locale.ENGLISH, "Fallos: %.2f%n", ((double)fallos / ((double)fallos + 
					(double)exitos)) * 100.00);
			System.out.format(Locale.ENGLISH, "Coste medio fallos: %.2f%n", (double)costeFallos / 
					(double)fallos);
			System.out.format(Locale.ENGLISH, "Exitos: %.2f%n", ((double)exitos / ((double)fallos + 
					(double)exitos)) * 100.00);
			System.out.format(Locale.ENGLISH, "Coste medio Exitos: %.2f%n", (double)costeExitos / 
					(double)exitos);
		}
		
		catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private static void nQueensRandomRestartHillClimbing() {
		try {
			int intentos = 0;
			int fallos = 0;
			int costeFallos = 0;
			int costeExito = 0;
			int expandedNodes;
			NQueensGoalTest goal = new NQueensGoalTest();
			HillClimbingSearch search = new HillClimbingSearch(
					new AttackingPairsHeuristic());
			SearchAgent agent = null;
			
			boolean encontrada = false;
			while (!encontrada) {
				intentos++;
				Problem problem = new Problem(randomBoard(),
						NQueensFunctionFactory.getCActionsFunction(),
						NQueensFunctionFactory.getResultFunction(),
						goal);
				agent = new SearchAgent(problem, search);
				
				if (agent.getInstrumentation().getProperty("nodesExpanded") == null) expandedNodes = 0;
				else expandedNodes = 
						(int)Float.parseFloat(agent.getInstrumentation().getProperty("nodesExpanded"));
				
				if (goal.isGoalState(search.getLastSearchState())) {
					encontrada = true;
					costeExito = expandedNodes;
				}
				else {
					fallos++;
					costeFallos += expandedNodes;
				}
			}

			System.out.println("Search Outcome=" + search.getOutcome());
			System.out.println("Final State=\n" + search.getLastSearchState());
			
			System.out.format(Locale.ENGLISH, "Numero de intentos: %.1f%n", (double)intentos);
			System.out.format(Locale.ENGLISH, "Fallos: %.1f%n", (double)fallos);
			if (fallos == 0) fallos = 1;
			System.out.format(Locale.ENGLISH, "Coste medio fallos: %.2f%n", (double)costeFallos / 
					(double)fallos);
			System.out.format(Locale.ENGLISH, "Coste medio Exitos: %.2f%n", (double)costeExito);
		}
		
		catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private static NQueensBoard randomBoard() {
		Random r;
		NQueensBoard board = new NQueensBoard(_boardSize);
		
		for (int i = 0; i < _boardSize; i++) {
			r = new Random();
			board.addQueenAt(new XYLocation(i, r.nextInt(_boardSize)));
		}
		
		return board;
	}
}