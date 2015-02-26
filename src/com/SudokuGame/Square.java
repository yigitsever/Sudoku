package com.SudokuGame;

import java.util.*;

class Square {

    private Stack pQueue;

    public Square()
    {
        pQueue = new Stack();

        for(int x = 1; x < 10; x++)//TODO make this extensible (working not only for 9*9 sudoku)
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

    public String toString()
    {
        return "" + pQueue.peek();
    }
}
