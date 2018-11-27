package aima.gui.demo.search;

import java.util.List;
import aima.core.agent.Action;
import aima.core.environment.Canibales.CanibalesBoard;
import aima.core.environment.Canibales.CanibalesFunctionFactory;
import aima.core.environment.Canibales.CanibalesGoalTest;
import aima.core.search.framework.GraphSearch;
import aima.core.search.framework.Problem;
import aima.core.search.framework.ResultFunction;
import aima.core.search.framework.Search;
import aima.core.search.framework.SearchAgent;
import aima.core.search.framework.TreeSearch;
import aima.core.search.uninformed.BreadthFirstSearch;
import aima.core.search.uninformed.DepthFirstSearch;
import aima.core.search.uninformed.DepthLimitedSearch;
import aima.core.search.uninformed.IterativeDeepeningSearch;
import aima.core.search.uninformed.UniformCostSearch;

//*****************************************************************
// File:   CanibalesPract1.java
// Author: Andrés Gavín Murillo 716358
// Date:   Octubre 2018
// Coms:   Inteligencia artificial - Práctica 1
//*****************************************************************

public class CanibalesPract1 {
	static CanibalesBoard board = new CanibalesBoard();

	public static void main(String[] args) {
		canibalesSearch(new BreadthFirstSearch(new GraphSearch()), board, "BFS-G", true, "");
		canibalesSearch(new BreadthFirstSearch(new TreeSearch()), board, "BFS-T", true, "");
		canibalesSearch(new DepthFirstSearch(new GraphSearch()), board, "DFS-G", false, "(1)");
		canibalesSearch(new DepthFirstSearch(new TreeSearch()), board, "DFS-T", false, "(1)");
		canibalesSearch(new DepthLimitedSearch(9), board, "DLS-9", true, "");
		canibalesSearch(new DepthLimitedSearch(3), board, "DLS-3", true, "");
		canibalesSearch(new IterativeDeepeningSearch(), board, "IDS", true, "");
		canibalesSearch(new UniformCostSearch(new GraphSearch()), board, "UCS-G", true, "");
		canibalesSearch(new UniformCostSearch(new TreeSearch()), board, "UCS-T", true, "");
	}

	private static void canibalesSearch(Search busqueda, CanibalesBoard estadoInicial, String nombre, Boolean exec, String errorInfo) {
		if (exec) {
			try {
				Problem problem = new Problem(estadoInicial, CanibalesFunctionFactory
						.getActionsFunction(), CanibalesFunctionFactory
						.getResultFunction(), new CanibalesGoalTest());
				
				long tiempo = System.currentTimeMillis();
				SearchAgent agent = new SearchAgent(problem, busqueda);
				tiempo = System.currentTimeMillis() - tiempo;
				
				int depth, expandedNodes, queueSize, maxQueueSize;
				String pathcostM = agent.getInstrumentation().getProperty("pathCost");
				
				if (pathcostM != null) depth = (int)Float.parseFloat(pathcostM);
				else depth = 0;
				if (agent.getInstrumentation().getProperty("nodesExpanded") == null) expandedNodes = 0;
				else expandedNodes = 
						(int)Float.parseFloat(agent.getInstrumentation().getProperty("nodesExpanded"));
				if (agent.getInstrumentation().getProperty("queueSize") == null) queueSize = 0;
				else queueSize = (int)Float.parseFloat(agent.getInstrumentation().getProperty("queueSize"));
				if (agent.getInstrumentation().getProperty("maxQueueSize") == null) maxQueueSize = 0;
				else maxQueueSize = 
						(int)Float.parseFloat(agent.getInstrumentation().getProperty("maxQueueSize"));
				
				System.out.println("Misioneros y canibales " + nombre + " -->");
				System.out.println("pathCost: " + depth);
				System.out.println("nodesExpanded: " + expandedNodes);
				System.out.println("queueSize: " + queueSize);
				System.out.println("maxQueueSize: " + maxQueueSize);
				System.out.println("Tiempo: " + tiempo + "ms");
				System.out.println("");
				
				System.out.println("SOLUCIÓN:");
				System.out.println("GOAL STATE");
				System.out.println(problem.getGoalTest());
				System.out.println("CAMINO ENCONTRADO");
				
				executeActions(agent.getActions(), problem);
				System.out.println("");
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		else {
			System.out.println("Misioneros y canibaes " + nombre + " --> " + errorInfo);
			System.out.println("");
		}
	}
	
	private static void executeActions(List<Action> actions, Problem problem) {
		Object initialState = problem.getInitialState();
		ResultFunction resultFunction = problem.getResultFunction();
		
		Object state = initialState;
		System.out.format("%25s%3s", "ESTADO INICIAL", "   ");
		System.out.println(state);
		
		for (Action action : actions) {
			System.out.format("%25s%3s", action.toString(), "   ");
			state = resultFunction.result(state, action);
			System.out.println(state);
		}
	}

}