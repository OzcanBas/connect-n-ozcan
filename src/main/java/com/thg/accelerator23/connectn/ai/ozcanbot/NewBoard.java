package com.thg.accelerator23.connectn.ai.ozcanbot;

import com.thehutgroup.accelerator.connectn.player.Position;
import com.thehutgroup.accelerator.connectn.player.Counter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class NewBoard {

    // make a copy of the current board instance
    private final Counter[][] board;
    private final int width;
    private final int height;
    private final Position position;
    private final Counter counter;

    public NewBoard(Counter[][] board, Position position, Counter counter) {
        this.board = deepCopyBoard(board);
        this.width = board.length;
        this.height = board[0].length;
        this.position = position;
        this.counter = counter;
    }

    // getters
    public Counter[][] getBoard() {
        return deepCopyBoard(this.board);
    }

    public int getHeight() {
        return height;
    }

    public int getWidth(){
        return width;
    }


    // now for the actual class methods

    // deep copy the board to avoid modifying the original board
    private Counter[][] deepCopyBoard(Counter[][] original) {
        // copy the old board
        Counter[][] copy = new Counter[this.width][this.height];
        for (int i = 0; i < this.width; i++) {
            System.arraycopy(original[i], 0, copy[i], 0, this.height);
        }

        // also add the new piece
        copy[this.position.getX()][this.position.getY()] = counter;

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


    // make an array of all the valid positions on the board:
    public ArrayList<Position> getValidPositions() {
        ArrayList<Position> validPositions = new ArrayList<>();

        for (int x = 0; x < width - 1 ; ++x) { // go through each column
            int availableY = getMinFreeY(x, height);
            if (availableY != -1) {
                validPositions.add(new Position(x, availableY));
            }
        }
        return validPositions;
    }

    // Use function above to make a hashmap of all valid positions with key=position value=positionScore
    public Map<Position, Integer> initializePositionScores() {
        Map<Position, Integer> positionScores = new HashMap<>();

        ArrayList<Position> validPositions = getValidPositions();

        for (Position position : validPositions) {
            positionScores.put(position, position.getX() * 20); // Initialize with a default score
        }

        return positionScores;
    }
}




