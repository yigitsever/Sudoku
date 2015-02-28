package com.SudokuGame;


import java.io.FileNotFoundException;
import java.io.UnsupportedEncodingException;

public class Play {

    public static void main(String args[]) throws FileNotFoundException, UnsupportedEncodingException {
        Board gameBoard = new Board(9, 9);

        gameBoard.layOutBoard();

    }
}
