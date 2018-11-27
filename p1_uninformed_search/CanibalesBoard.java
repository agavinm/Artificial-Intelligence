package aima.core.environment.Canibales;

import java.util.Arrays;
import aima.core.agent.Action;
import aima.core.agent.impl.DynamicAction;

//*****************************************************************
// File:   CanibalesBoard.java
// Author: Andrés Gavín Murillo 716358
// Date:   Octubre 2018
// Coms:   Inteligencia artificial - Práctica 1
//*****************************************************************

public class CanibalesBoard {
	public static final int i = 0;
	public static final int f = 1;
	
	public static Action Mover1M(int from) {
		if (from == i) return new DynamicAction("M1M_IF");
		else return new DynamicAction("M1M_FI");
	}
	
	public static Action Mover2M(int from) {
		if (from == i) return new DynamicAction("M2M_IF");
		else return new DynamicAction("M2M_FI");
	}
	
	public static Action Mover1C(int from) {
		if (from == i) return new DynamicAction("M1C_IF");
		else return new DynamicAction("M1C_FI");
	}
	
	public static Action Mover2C(int from) {
		if (from == i) return new DynamicAction("M2C_IF");
		else return new DynamicAction("M2C_FI");
	}
	
	public static Action Mover1M1C(int from) {
		if (from == i) return new DynamicAction("M1M1C_IF");
		else return new DynamicAction("M1M1C_FI");
	}
	

	private int[] state;
	
	//
	// PUBLIC METHODS
	//

	public CanibalesBoard() {
		state = new int[] { 3, 3, i, 0, 0 }; // Mi, Ci, B, Mf, Cf
	}

	public CanibalesBoard(int[] state) {
		this.state = new int[state.length];
		System.arraycopy(state, 0, this.state, 0, state.length);
	}

	public CanibalesBoard(CanibalesBoard copyBoard) {
		this(copyBoard.getState());
	}

	public int[] getState() {
		return state;
	}
	
	public void move1M(int from) {
		moveXMYC(1, 0, from);
	}
	
	public void move2M(int from) {
		moveXMYC(2, 0, from);
	}
	
	public void move1C(int from) {
		moveXMYC(0, 1, from);
	}
	
	public void move2C(int from) {
		moveXMYC(0, 2, from);
	}
	
	public void move1M1C(int from) {
		moveXMYC(1, 1, from);
	}

	public boolean canMove(Action where) {
		boolean retVal = true;
		
		if (where.equals(Mover1M(i)))
			retVal = (getBPos() == i && getMNumber(i) >= 1);
		
		else if (where.equals(Mover2M(i)))
			retVal = (getBPos() == i && getMNumber(i) >= 2);
					
		else if (where.equals(Mover1C(i)))
			retVal = (getBPos() == i && getCNumber(i) >= 1);
		
		else if (where.equals(Mover2C(i)))
			retVal = (getBPos() == i && getCNumber(i) >= 2);
		
		else if (where.equals(Mover1M1C(i)))
			retVal = (getBPos() == i && getMNumber(i) >= 1 && getCNumber(i) >= 1);
		
		else if (where.equals(Mover1M(f)))
			retVal = (getBPos() == f && getMNumber(f) >= 1);
		
		else if (where.equals(Mover2M(f)))
			retVal = (getBPos() == f && getMNumber(f) >= 2);
					
		else if (where.equals(Mover1C(f)))
			retVal = (getBPos() == f && getCNumber(f) >= 1);
		
		else if (where.equals(Mover2C(f)))
			retVal = (getBPos() == f && getCNumber(f) >= 2);
		
		else if (where.equals(Mover1M1C(f)))
			retVal = (getBPos() == f && getMNumber(f) >= 1 && getCNumber(f) >= 1);

		return retVal;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + Arrays.hashCode(state);
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		CanibalesBoard other = (CanibalesBoard) obj;
		if (!Arrays.equals(state, other.state))
			return false;
		return true;
	}

	@Override
	public String toString() {
		int maxM = 3;
		int maxC = 3;
		String retVal = "RIBERA-IZQ ";
		
		for (int j = maxM; j > 0; j--) {
			if (this.state[0] >= j) retVal += "M ";
			else retVal += "  ";
		}
		
		for (int j = maxC; j > 0; j--) {
			if (this.state[1] >= j) retVal += "C ";
			else retVal += "  ";
		}
		
		if (this.state[2] == i) retVal += " BOTE --RIO--      ";
		else retVal += "      --RIO-- BOTE ";
		
		for (int j = maxM; j > 0; j--) {
			if (this.state[3] >= j) retVal += "M ";
			else retVal += "  ";
		}
		
		for (int j = maxC; j > 0; j--) {
			if (this.state[4] >= j) retVal += "C ";
			else retVal += "  ";
		}
		
		retVal += " RIBERA-DCH";
		
		return retVal;
	}

	//
	// PRIVATE METHODS
	//

	private int getMNumber(int where) {
		if (where == i) return this.state[0];
		else return this.state[3];
	}
	
	private int getCNumber(int where) {
		if (where == i) return this.state[1];
		else return this.state[4];
	}
	
	private int getBPos() {
		return this.state[2];
	}
	
	/*
	 * Mueve x misioneros e y canibales desde la orilla from a la otra orilla.
	 */
	private void moveXMYC(int x, int y, int from) {
		if (from == i) {
			this.state[0] -= x;
			this.state[1] -= y;
			this.state[2] = f;
			this.state[3] += x;
			this.state[4] += y;
		}
		else {
			this.state[0] += x;
			this.state[1] += y;
			this.state[2] = i;
			this.state[3] -= x;
			this.state[4] -= y;
		}
	}
}