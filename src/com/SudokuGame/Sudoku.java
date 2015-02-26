package com.SudokuGame;

import java.util.Stack;
import java.util.Random;

class Sudoku {
    /**
     * TODO create a difficulty level to allow users play on 3*3 9*9 16*16 boards
     */

    private static Square[] board;
    private final static int BOARD_X = 9;
    private final static int BOARD_Y = 9;
    private final static int BOARD_SIZE = BOARD_X * BOARD_Y;

    public static void main(String[] args) {

        squareBuilder();

        layOutBoard();


    }


    private static Square[] squareBuilder() {
        board = new Square[BOARD_SIZE];

        for (int x = 0; x < BOARD_SIZE; x++) {

            Square temp = new Square();
            board[x]    = temp;
            board[x].setpQueue(shuffle(board[x].getpQueue()));
        }


        return board;
    }

    private static Stack shuffle(Stack s) {
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

    private static void layOutBoard()
    {
        for(int x = 0; x < BOARD_X; x++)
        {
            for(int y = 0; y < BOARD_Y; y++)
            {
                System.out.print(board[x * BOARD_X + y].toString());
            }
            System.out.println("");
        }

    }


}
