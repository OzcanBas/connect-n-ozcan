package com.thg.accelerator23.connectn.ai.ozcanbot;

import com.thehutgroup.accelerator.connectn.player.Position;
import com.thehutgroup.accelerator.connectn.player.Counter;
import java.util.ArrayList;


public class Minimax {

    private final int depth; // Maximum depth for the search

    // Constructor to set the search depth
    public Minimax(int depth) {
        this.depth = depth;
    }


    // Minimax function with alpha-beta pruning
    public int minimax(NewBoard board, int currentDepth, int alpha, int beta, boolean isMaximizingPlayer, Counter myCounter, Counter opponentsCounter) {
        // Base case: Terminal state or depth limit reached
        if (currentDepth == 0 || isGameOver(board.getBoard(), getValidPositions(board.getBoard()), myCounter)) {
            if (checkWinningMove(board.getBoard(), myCounter)){
                return 10000;
            } else if (checkWinningMove(board.getBoard(), opponentsCounter)) {
                return -10000;
            } else {
                return 0;
            }
        }

        if (isMaximizingPlayer) {
            int maxEval = Integer.MIN_VALUE;
            for (Position position : getValidPositions(board.getBoard())) {
                NewBoard newBoard = new NewBoard(board.getBoard(), position, myCounter); // Simulate move for AI
                int eval = minimax(newBoard, currentDepth - 1, alpha, beta, false, myCounter, opponentsCounter);
                maxEval = Math.max(maxEval, eval);
                alpha = Math.max(alpha, eval);
                if (beta <= alpha) break; // Alpha-beta pruning
            }
            return maxEval;
        } else {
            int minEval = Integer.MAX_VALUE;
            for (Position position : getValidPositions(board.getBoard())) {
                NewBoard newBoard = new NewBoard(board.getBoard(), position, opponentsCounter); // Simulate move for opponent
                int eval = minimax(newBoard, currentDepth - 1, alpha, beta, true, myCounter, opponentsCounter);
                minEval = Math.min(minEval, eval);
                beta = Math.min(beta, eval);
                if (beta <= alpha) break; // Alpha-beta pruning
            }
            return minEval;
        }
    }

    // Function to get the best move for the AI
    public Position getBestMove(NewBoard board, Counter myCounter) {
        Counter opponentsCounter = myCounter.getOther(); // Get the opponent's counter dynamically
        int bestScore = Integer.MIN_VALUE;
        Position bestMove = null;

        for (Position position : getValidPositions(board.getBoard())) {
            NewBoard newBoard = new NewBoard(board.getBoard(), position, myCounter); // Simulate move for AI
            int score = minimax(newBoard, depth - 1, Integer.MIN_VALUE, Integer.MAX_VALUE, false, myCounter, opponentsCounter);
            if (score > bestScore) {
                bestScore = score;
                bestMove = position;
            }
        }

        return bestMove;
    }


    public int getMinFreeY(Counter[][] board, int column, int height) {
        for (int y = 0; y < height - 1 ; ++y) { // number of rows
            if (board[column][y] == null) {
                return y;
            }
        }
        return -1;
    }


    // make an array of all the valid positions on the board:
    public ArrayList<Position> getValidPositions(Counter[][] board) {
        ArrayList<Position> validPositions = new ArrayList<>();
        int width = board.length;
        int height = board[0].length;

        for (int x = 0; x < width; ++x) { // go through each column
            int availableY = getMinFreeY(board, x, height);
            if (availableY != -1) {
                validPositions.add(new Position(x, availableY));
            }
        }
        return validPositions;
    }

    // this function checks all necessary squares to see if a given player has won
    public boolean checkWinningMove(Counter[][] board, Counter currentPlayer) {

        int width = board.length;
        int height = board[0].length;

        // check vertically
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height - 3; j++) {
                if (board[i][j] == currentPlayer &&
                        board[i][j+1] == currentPlayer &&
                        board[i][j+2] == currentPlayer &&
                        board[i][j+3] == currentPlayer) { return true; }
            }
        }

        // check horizontally
        for (int i = 0; i < width - 3; i++) {
            for (int j = 0; j < height; j++) {
                if (board[i][j] == currentPlayer && board[i+1][j] == currentPlayer && board[i+2][j] == currentPlayer && board[i+3][j] == currentPlayer) { return true; }
            }
        }

        // check positive diag
        for (int i = 0; i < width - 3; i++) {
            for (int j = 0; j < height - 3; j++) {
                if (board[i][j] == currentPlayer && board[i+1][j+1] == currentPlayer && board[i+2][j+2] == currentPlayer && board[i+3][j+3] == currentPlayer) { return true; }
            }
        }

        // check negative diag
        for (int i = 0; i < width - 3; i++) {
            for (int j = 3; j < height; j++) {
                if (board[i][j] == currentPlayer && board[i+1][j-1] == currentPlayer && board[i+2][j-2] == currentPlayer && board[i+3][j-3] == currentPlayer) { return true; }
            }
        }

        return false;
    }


    // this function calls the previous function for each player and also checks if the board is full to know if the game is over
    public boolean isGameOver(Counter[][] board, ArrayList<Position> validPos, Counter player) {
        return checkWinningMove(board, player) || checkWinningMove(board, player.getOther()) || validPos.isEmpty();
    }
}
