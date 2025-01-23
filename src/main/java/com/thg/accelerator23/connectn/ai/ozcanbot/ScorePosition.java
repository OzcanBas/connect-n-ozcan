package com.thg.accelerator23.connectn.ai.ozcanbot;

import com.thehutgroup.accelerator.connectn.player.Position;
import com.thehutgroup.accelerator.connectn.player.Counter;

import java.util.ArrayList;

// Given a board state and an open position this class will evaluate the score of the open position
public class ScorePosition {
    private final Position position;
    private final int height;
    private final int width;
    private final NewBoard currentBoard;

    // constructor
    public ScorePosition(NewBoard board, Position position) {
        this.currentBoard = board;
        this.height = board.getBoard()[0].length;
        this.width = board.getBoard().length;
        this.position = position;
    }


    // count the number of pieces in a given window
    public int count(Counter[] window, Counter currentPlayer) {
        int count = 0;
        for (Counter piece : window) {
            if (piece == currentPlayer) { // Match the given piece
                count++;
            }
        }
        return count;
    }


    // evaluate the current window
    public int evaluateWindow(Counter[] window) {
        Counter currentPlayer = window[0];
        int score = 0;

        // the absolute value of the score determines the hierarchy of the moves
        if (count(window, currentPlayer) == 3 && count(window, null) == 1) { // there is one empty space take it and win the game
            score = 100;
        } else if (count(window, currentPlayer) == 2 && count(window, null) == 2) { // there are two empty space so connect 3 in a row
            score = 5;
        } else if (count(window, currentPlayer) == 1 && count(window, null) == 3) { // connecting two in a row gives one point
            score = 1;
        } else if (count(window, currentPlayer) == 0 && count(window, null) == 1) { // this just means that the opponent has three pieces in this window and they will win next move
            score = -10;
        }
        return score;
    }


    // create a vertical window at the given position
    public int scoreVertical(Position position) {
        int x = position.getX();
        int y = position.getY();

        int vertScore = 0;
        if (y < height - 3) {
            Counter[] window = new Counter[4];

            for (int i = 0; i < 4; i++) {
                window[i] = currentBoard.getBoard()[x][y+i];
            }
            vertScore = evaluateWindow(window);
        }
        return vertScore;
    }


    // create a horizontal window at the current position
    public int scoreHorizontal(Position position) {
        int x = position.getX();
        int y = position.getY();

        int horizontalScore = 0;
        if (x < width - 3) {
            Counter[] window = new Counter[4];

            for (int i = 0; i < 4; i++) {
                window[i] = currentBoard.getBoard()[x+i][y];
            }
            horizontalScore = evaluateWindow(window);
        }
        return horizontalScore;
    }


    // create a positive diagonal window at the current position
    public int scorePositiveDiagonal(Position position) {
        int x = position.getX();
        int y = position.getY();

        int positiveDiagScore = 0;
        if (x < width - 3 && y < height - 3) {
            Counter[] window = new Counter[4];

            for (int i = 0; i < 4; i++) {
                window[i] = currentBoard.getBoard()[x+i][y+i];
            }
            positiveDiagScore = evaluateWindow(window);
        }
        return positiveDiagScore;
    }


    // create a negative diagonal window at the current position
    public int scoreNegativeDiagonal(Position position) {
        int x = position.getX();
        int y = position.getY();

        int negativeDiagScore = 0;
        if (x < width - 3 && y < height - 3) {
            Counter[] window = new Counter[4];

            for (int i = 0; i < 4; i++) {
                window[i] = currentBoard.getBoard()[x+i][y-i];
            }
            negativeDiagScore = evaluateWindow(window);
        }
        return negativeDiagScore;
    }


    // evaluate total score for a position
    public int evaluatePositionScore() {
        int totalScore = 0;

        // Aggregate scores for the position
        totalScore += scoreVertical(position);
        totalScore += scoreHorizontal(position);
        totalScore += scorePositiveDiagonal(position);
        totalScore += scoreNegativeDiagonal(position);

        return totalScore;
    }

}
