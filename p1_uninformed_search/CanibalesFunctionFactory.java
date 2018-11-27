package aima.core.environment.Canibales;

import java.util.LinkedHashSet;
import java.util.Set;

import aima.core.agent.Action;
import aima.core.search.framework.ActionsFunction;
import aima.core.search.framework.ResultFunction;

//*****************************************************************
// File:   CanibalesFunctionFactory.java
// Author: Andrés Gavín Murillo 716358
// Date:   Octubre 2018
// Coms:   Inteligencia artificial - Práctica 1
//*****************************************************************

public class CanibalesFunctionFactory {
	public static final int i = CanibalesBoard.i; // Orilla inicial
	public static final int f = CanibalesBoard.f; // Orilla final
	
	private static ActionsFunction _actionsFunction = null;
	private static ResultFunction _resultFunction = null;

	public static ActionsFunction getActionsFunction() {
		if (null == _actionsFunction) {
			_actionsFunction = new EPActionsFunction();
		}
		return _actionsFunction;
	}

	public static ResultFunction getResultFunction() {
		if (null == _resultFunction) {
			_resultFunction = new EPResultFunction();
		}
		return _resultFunction;
	}

	private static class EPActionsFunction implements ActionsFunction {
		public Set<Action> actions(Object state) {
			CanibalesBoard board = (CanibalesBoard) state;

			Set<Action> actions = new LinkedHashSet<Action>();

			if (board.canMove(CanibalesBoard.Mover1M(i))) {
				actions.add(CanibalesBoard.Mover1M(i));
			}
			if (board.canMove(CanibalesBoard.Mover2M(i))) {
				actions.add(CanibalesBoard.Mover2M(i));
			}
			if (board.canMove(CanibalesBoard.Mover1C(i))) {
				actions.add(CanibalesBoard.Mover1C(i));
			}
			if (board.canMove(CanibalesBoard.Mover2C(i))) {
				actions.add(CanibalesBoard.Mover2C(i));
			}
			if (board.canMove(CanibalesBoard.Mover1M1C(i))) {
				actions.add(CanibalesBoard.Mover1M1C(i));
			}
			if (board.canMove(CanibalesBoard.Mover1M(f))) {
				actions.add(CanibalesBoard.Mover1M(f));
			}
			if (board.canMove(CanibalesBoard.Mover2M(f))) {
				actions.add(CanibalesBoard.Mover2M(f));
			}
			if (board.canMove(CanibalesBoard.Mover1C(f))) {
				actions.add(CanibalesBoard.Mover1C(f));
			}
			if (board.canMove(CanibalesBoard.Mover2C(f))) {
				actions.add(CanibalesBoard.Mover2C(f));
			}
			if (board.canMove(CanibalesBoard.Mover1M1C(f))) {
				actions.add(CanibalesBoard.Mover1M1C(f));
			}

			return actions;
		}
	}

	private static class EPResultFunction implements ResultFunction {
		public Object result(Object s, Action a) {
			CanibalesBoard board = (CanibalesBoard) s;

			if (CanibalesBoard.Mover1M(i).equals(a)
					&& board.canMove(CanibalesBoard.Mover1M(i))) {
				CanibalesBoard newBoard = new CanibalesBoard(board);
				newBoard.move1M(i);
				return newBoard;
			} else if (CanibalesBoard.Mover2M(i).equals(a)
					&& board.canMove(CanibalesBoard.Mover2M(i))) {
				CanibalesBoard newBoard = new CanibalesBoard(board);
				newBoard.move2M(i);
				return newBoard;
			} else if (CanibalesBoard.Mover1C(i).equals(a)
					&& board.canMove(CanibalesBoard.Mover1C(i))) {
				CanibalesBoard newBoard = new CanibalesBoard(board);
				newBoard.move1C(i);
				return newBoard;
			} else if (CanibalesBoard.Mover2C(i).equals(a)
					&& board.canMove(CanibalesBoard.Mover2C(i))) {
				CanibalesBoard newBoard = new CanibalesBoard(board);
				newBoard.move2C(i);
				return newBoard;
			} else if (CanibalesBoard.Mover1M1C(i).equals(a)
					&& board.canMove(CanibalesBoard.Mover1M1C(i))) {
				CanibalesBoard newBoard = new CanibalesBoard(board);
				newBoard.move1M1C(i);
				return newBoard;
			} else if (CanibalesBoard.Mover1M(f).equals(a)
					&& board.canMove(CanibalesBoard.Mover1M(f))) {
				CanibalesBoard newBoard = new CanibalesBoard(board);
				newBoard.move1M(f);
				return newBoard;
			} else if (CanibalesBoard.Mover2M(f).equals(a)
					&& board.canMove(CanibalesBoard.Mover2M(f))) {
				CanibalesBoard newBoard = new CanibalesBoard(board);
				newBoard.move2M(f);
				return newBoard;
			} else if (CanibalesBoard.Mover1C(f).equals(a)
					&& board.canMove(CanibalesBoard.Mover1C(f))) {
				CanibalesBoard newBoard = new CanibalesBoard(board);
				newBoard.move1C(f);
				return newBoard;
			} else if (CanibalesBoard.Mover2C(f).equals(a)
					&& board.canMove(CanibalesBoard.Mover2C(f))) {
				CanibalesBoard newBoard = new CanibalesBoard(board);
				newBoard.move2C(f);
				return newBoard;
			} else if (CanibalesBoard.Mover1M1C(f).equals(a)
					&& board.canMove(CanibalesBoard.Mover1M1C(f))) {
				CanibalesBoard newBoard = new CanibalesBoard(board);
				newBoard.move1M1C(f);
				return newBoard;
			}

			// The Action is not understood or is a NoOp
			// the result will be the current state.
			return s;
		}
	}
}