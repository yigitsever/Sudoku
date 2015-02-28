/*
* Preface: Although the sudoku requires (in theory) a "square" board (meaning the
* width and the height of the board should be the same and they should be a
* perfectly square number, at some places in the code I approached as if they can
* be different numbers. It's purely a design restriction that I imposed upon myself
* to ensure that I know how the code works and why it works like so. When you
* run upon the values BOARD_X and _Y, they're differantiated only so you can understand
* the idea behind the code. They can be trimmed down to a singular BOARD_EDGE and everthing
* should work normally.
*
* TL;DR: Sudoku boards are squares but I wrote this as if they may not be,
* to clarify my thought process.
*
* -- * --
*
* This is a basic implementation of the well-known game Sudoku with the premise
* of extensibilty (you can -in theory- play on any size of board as long as the
* x and y (see above) are perfectly square numbers). It will not have an interface
* class as is.
* */
package com.SudokuGame;


import java.io.FileNotFoundException;
import java.io.UnsupportedEncodingException;
import java.util.Stack;
import java.util.Random;

public class Board {

	private Square[] board;
	private final int BOARD_X;  //Width of the board
	private final int BOARD_Y;  //Height of the board

	private final int BOARD_SIZE;

	private final int sqrtX;
	private final int sqrtY;

	public Board(int x, int y) {
		BOARD_X = x;
		BOARD_Y = y;
		sqrtX = (int) Math.sqrt(BOARD_X);
		sqrtY = (int) Math.sqrt(BOARD_Y);

		BOARD_SIZE = BOARD_X * BOARD_Y;

		int[] numbers = new int[BOARD_X];

		for (int t = 1; t <= BOARD_X; ++t) {

			numbers[t - 1] = t;
		}

		squareBuilder(numbers);

	}

	public void layOutBoard() throws FileNotFoundException, UnsupportedEncodingException {

		Move(41, 7);

		for (int x = 0; x < BOARD_X; x++) {

			for (int y = 0; y < BOARD_Y; y++) {

				System.out.print(board[BOARD_X * x + y].getValue());
			}
			System.out.println();
		}

	}

	public void Move(int coor, int val) {

	    /*  You thought about this, the cols/rows go as 0 1 2 3 ... and the squares
	     *  go as 0 1 2 ... 80 (for 9x9 case ofc)
	     */

		int x = coor / BOARD_X;
		int y = coor % BOARD_Y;

		Move(x, y, val);

	}

	public void Move(int x, int y, int val) {

		int coor = x * BOARD_X + y;

		board[coor].setValue(val);

		informSquares(coor, val);

	}

	private void informSquares(int coor, int val) {

		int x = (int) Math.floor(coor / BOARD_X);
		int y = coor % BOARD_Y;
		int por = portionFinder(x, y);


		informSquares(x, y, por, val);

	}

	private void informSquares(int x, int y, int por, int val) {

		informRow(x, val);
		informCol(y, val);
		informPor(por, val);

	}

	//TODO test inform row&col
	private void informRow(int x, int val) {

		int rowHead = BOARD_X * x;

		for (int a = rowHead; a < rowHead + BOARD_Y; a++) {

			board[a].clearValue(val);
		}
	}

	private void informCol(int y, int val) {
		int colHead = y;    //Redundant? I say clearifying!

		for (int a = colHead; a < BOARD_SIZE; a += BOARD_X) {
			board[a].clearValue(val);
		}
	}

	private void informPor(int por, int val) {

		int porHead = portionHeadFinder(por);

		for (int t = 0; t < sqrtY; t++)  //The amount of rows in each portion
		{
			for (int a = porHead; a < porHead + sqrtX; a++)  //Amount of cols in each portion
			{
				board[a].clearValue(val);
			}

			porHead += BOARD_X;         //Shifts the head to the next row

		}
	}

	private Square[] squareBuilder(int[] numbers) {

		board = new Square[BOARD_SIZE];

		for (int x = 0; x < BOARD_SIZE; x++) {

			Square temp = new Square();
			board[x] = temp;
			board[x].setpQueue(shuffle(board[x].getpQueue(), numbers));
		}

		return board;
	}

	private Stack shuffle(Stack s, int[] numbers) {
		Random rnd = new Random();



		for (int i = numbers.length - 1; i > 0; i--) {

			int index = rnd.nextInt(i + 1);

			int a = numbers[index];
			numbers[index] = numbers[i];
			numbers[i] = a;
		}

		for (int z = numbers.length - 1; z > 0; z--) {
			s.push(numbers[z]);
		}

		return s;
	}

	private int portionFinder(int x, int y) {

		int xpp = (int) Math.floor(x / sqrtX);
		int ypp = (int) Math.floor(y / sqrtY);

		return xpp * sqrtX + ypp;


	}

	private int portionHeadFinder(int por)
	{
		int xQ = por / sqrtX;
		int yQ = por % sqrtY;

		return xQ * BOARD_X * sqrtX + yQ * sqrtY;
	}


}
