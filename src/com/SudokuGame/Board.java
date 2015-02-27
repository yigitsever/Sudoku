package com.SudokuGame;

import java.util.Stack;
import java.util.Random;

public class Board {
    /**
     * TODO create a difficulty level to allow users play on 3*3 9*9 16*16 boards
     */

    private Square[] board;
    private final int BOARD_X;
    private final int BOARD_Y;
    private final int BOARD_SIZE;

    public Board(int x, int y)
    {
        BOARD_X = x;
        BOARD_Y = y;
        BOARD_SIZE = BOARD_X * BOARD_Y;
        squareBuilder();
    }

    public void layOutBoard()
    {
        for(int x = 0; x < BOARD_X; x++)
        {
            for(int y = 0; y < BOARD_Y; y++)
            {
                System.out.print(board[x * BOARD_X + y].toString() + "\t");
            }
            System.out.println("");
        }

    }

    public void Move(int x, int y, int val)
    {
        /**
         * TODO Cant move, dont know what portion does the squares lie (like, I put 5 to 71, who am I
         * informing here?
         * For 0 1 2 - 3 4 5 - 6 7 8 rows and 0 1 2 ... cols will declare our portion
         *
         */

	    int portion = portionFinder(x,y);

    }
    public void Move(int coor, int val)
    {

	    /*  You thought about this, the cols/rows go as 0 1 2 3 ... and the squares
	     *  go as 0 1 2 ... 80 (for 9x9 case ofc)
	     */

	    int x   = (int) Math.floor(coor / BOARD_X);
	    int y   = coor % BOARD_Y;


	    Move(x, y, val);

    }

	private void informPoorSquares(int x, int y, int val)
	{

	}

    private void informPoorSquares(int coor, int val)
    {

    }

    private Square[] squareBuilder() {
        board = new Square[BOARD_SIZE];

        for (int x = 0; x < BOARD_SIZE; x++) {

            Square temp = new Square();
            board[x]    = temp;
            board[x].setpQueue(shuffle(board[x].getpQueue()));
        }


        return board;
    }

    private Stack shuffle(Stack s) {
        Random rnd = new Random();

        int[] numbers = new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9};//TODO Make this extensible (not working just with 9*9)

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

	private int portionFinder(int x, int y)
	{
		int por;



	}




}
