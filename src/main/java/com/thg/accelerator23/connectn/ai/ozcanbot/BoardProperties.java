package com.thg.accelerator23.connectn.ai.ozcanbot;

import com.thehutgroup.accelerator.connectn.player.Position;
import com.thehutgroup.accelerator.connectn.player.Counter;
import java.util.HashMap;
import java.util.Map;

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

    // deep copy the board to avoid modifying the original board
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

    public int getMinFreeY(int column, int height) {
        for (int y = 0; y < height - 1 ; ++y) { // number of rows
                if (this.board[column][y] == null) {
                    return y;
                }
        }
        return -1;
    }

    // Make a Hashmap of all empty positions that you can place a piece in - this should at max be 10
    public Map<Position, Integer> initializePositionScores(Counter[][] board) {
        Map<Position, Integer> positionScores = new HashMap<>();
        int width = board.length;
        int height = board[0].length;

        // Traverse the board
        for (int x = 0; x < width - 1 ; ++x) { // go through each column
            int availableY = getMinFreeY(x, height);
            if (availableY != -1) {
                positionScores.put(new Position(x, availableY), x*20); // Initialize with a default score
            }
        }
        return positionScores;
    }


}




