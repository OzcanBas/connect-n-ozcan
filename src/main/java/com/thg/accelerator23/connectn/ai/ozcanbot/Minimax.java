package com.thg.accelerator23.connectn.ai.ozcanbot;

import com.thehutgroup.accelerator.connectn.player.Position;
import com.thehutgroup.accelerator.connectn.player.Counter;

public class Minimax {

    private final int depth; // Maximum depth for the search

    // Constructor to set the search depth
    public Minimax(int depth) {
        this.depth = depth;
    }

    // Minimax function with alpha-beta pruning
    public int minimax(NewBoard board, int currentDepth, int alpha, int beta, boolean isMaximizingPlayer, Counter myCounter, Counter opponentsCounter) {
        // Base case: Terminal state or depth limit reached
        if (currentDepth == 0 || new ScorePosition(board, null, myCounter).isGameOver(board.getBoard())) {
            return new ScorePosition(board, null, myCounter).evaluatePositionScore();
        }

        if (isMaximizingPlayer) {
            int maxEval = Integer.MIN_VALUE;
            for (Position position : board.getValidPositions()) {
                NewBoard newBoard = new NewBoard(board.getBoard(), position, myCounter); // Simulate move for AI
                int eval = minimax(newBoard, currentDepth - 1, alpha, beta, false, myCounter, opponentsCounter);
                maxEval = Math.max(maxEval, eval);
                alpha = Math.max(alpha, eval);
                if (beta <= alpha) break; // Alpha-beta pruning
            }
            return maxEval;
        } else {
            int minEval = Integer.MAX_VALUE;
            for (Position position : board.getValidPositions()) {
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

        for (Position position : board.getValidPositions()) {
            NewBoard newBoard = new NewBoard(board.getBoard(), position, myCounter); // Simulate move for AI
            int score = minimax(newBoard, depth - 1, Integer.MIN_VALUE, Integer.MAX_VALUE, false, myCounter, opponentsCounter);
            if (score > bestScore) {
                bestScore = score;
                bestMove = position;
            }
        }

        return bestMove;
    }
}
