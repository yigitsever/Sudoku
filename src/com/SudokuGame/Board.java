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
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.Stack;
import java.util.Random;

public class Board {

    private Square[] board;
    private final int BOARD_X;  //Width of the board
    private final int BOARD_Y;  //Height of the board
    private final int BOARD_SIZE;

    public Board(int x, int y) {
        BOARD_X = x;
        BOARD_Y = y;
        BOARD_SIZE = BOARD_X * BOARD_Y;
        squareBuilder();
    }

    public void layOutBoard() throws FileNotFoundException, UnsupportedEncodingException {

        PrintWriter writer = new PrintWriter("board.txt", "UTF-8");

        for (int x = 0; x < BOARD_X; x++) {
            for (int y = 0; y < BOARD_Y; y++) {

                writer.print(x * BOARD_X + y + "," + portionFinder(x,y) + "\t");

            }
            writer.println();
        }
        writer.close();
    }

    public void Move(int coor, int val) {

	    /*  You thought about this, the cols/rows go as 0 1 2 3 ... and the squares
         *  go as 0 1 2 ... 80 (for 9x9 case ofc)
	     */

        int x = (int) Math.floor(coor / BOARD_X);
        int y = coor % BOARD_Y;


        Move(x, y, val);

    }

    public void Move(int x, int y, int val) {
        /**
         * TODO Cant move, dont know what portion does the squares lie (like, I put 5 to 71, who am I
         * informing here? (!SOLVED BY PORTIONFINDER!)
         * For 0 1 2 - 3 4 5 - 6 7 8 rows and 0 1 2 ... cols will declare our portion
         * via a portion finder, basically pp = sqrt(BOARD_X), x/pp
         */

        int coor = x * BOARD_X + y * BOARD_Y;

        if(board[coor].setValue(val)) {
            System.out.println(val + "successfully added to square #" + coor);
        } else {
            System.out.println("There was a problem with adding " + val + " to square " + coor);
        }

        informSquares(coor, val);

    }

    private void informSquares(int coor, int val) {

        int x = (int) Math.floor(coor / BOARD_X);
        int y = coor % BOARD_Y;
        int por = portionFinder(x,y);


        informSquares(x, y, por, val);

    }

    private void informSquares(int x, int y, int por, int val) {

        informRow(x, val);
        informCol(y, val);
        informPor(por, val);

    }

    //TODO test inform row&col
    private void informRow(int x, int val)
    {
        int rowHead = BOARD_X * x;
        for(int a = rowHead; a < rowHead + BOARD_Y; a++)
        {
            board[a].clearValue(val);
        }
    }

    private void informCol(int y, int val)
    {
        int colHead = y;
        for(int a = y; a < BOARD_SIZE; a += BOARD_X)
        {
            board[a].clearValue(val);
        }
    }

    private void informPor(int por, int val)
    {
        //TODO implement informpor which will inform the guys on the same
        int sqrtY = (int) Math.sqrt(BOARD_Y);
//        int porHead = (por / )
    }

    private Square[] squareBuilder() {
        board = new Square[BOARD_SIZE];

        for (int x = 0; x < BOARD_SIZE; x++) {

            Square temp = new Square();
            board[x] = temp;
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

    private int portionFinder(int x, int y) {
        int xSqrt = (int) Math.sqrt(BOARD_X);
        int ySqrt = (int) Math.sqrt(BOARD_Y);

        int xpp = (int) Math.floor(x / xSqrt);
        int ypp = (int) Math.floor(y / ySqrt);

        return xpp * xSqrt + ypp; //We'll test this


    }


}
