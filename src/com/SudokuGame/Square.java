package com.SudokuGame;

import java.util.*;

public class Square {

	private Stack pQueue;
	private int value;

	public Square() {

		pQueue = new Stack();

		for (int x = 1; x < 10; x++)//TODO make this extensible (working not only for 9*9 sudoku)
		{
			pQueue.push(x);
		}

	}

	public Stack getpQueue() {
		return pQueue;
	}

	public void setpQueue(Stack pQueue) {
		this.pQueue = pQueue;
	}

	public String toString() {
		return "" + pQueue.peek();
	}

	public int getValue() {
		return value;
	}

	public void setValue(int val) {

			pQueue.clear();
			this.value = val;
	}

	public void clearValue(int val) {
		try {
			this.pQueue.remove(val);
		} catch (Exception e) {

		}


	}
}
