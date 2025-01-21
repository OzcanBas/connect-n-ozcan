package com.thg.accelerator23.connectn.ai.ozcanbot;

import com.thehutgroup.accelerator.connectn.player.Board;
import com.thehutgroup.accelerator.connectn.player.Position;
import com.thehutgroup.accelerator.connectn.player.Counter;

public class BoardProperties {

    // make a copy of the current board instance
    private final Counter[][] board;
    private final boolean isOpponentsTurn;


    public BoardProperties(Counter[][] board, boolean isOpponentsTurn) {
        this.board = deepCopyBoard(board);
        this.isOpponentsTurn = isOpponentsTurn;
    }


    // getters
    public Counter[][] getBoard() {
        return deepCopyBoard(this.board);
    }

    public boolean isOpponentsTurn() {
        return isOpponentsTurn;
    }

    // Deep copy the board to avoid modifying the original board
    private Counter[][] deepCopyBoard(Counter[][] original) {
        int width = original.length;
        int height = original[0].length;
        Counter[][] copy = new Counter[width][height];
        for (int i = 0; i < width; i++) {
            System.arraycopy(original[i], 0, copy[i], 0, height);
        }
        return copy;
    }

    // Check if a column is full or not, checks the top row of the column to see if there is a piece
    public boolean columnFree(int column) {
        int height = this.board[0].length; // Get the height of the board
        return this.board[column][height - 1] == null; // Check if the top row is empty
    }

    // check if there is a chance to win
    //    public Position horizontalWinningPosition(Position position) {
    //
    //    }

}




