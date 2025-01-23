package com.thg.accelerator23.connectn.ai.ozcanbot;

import com.thehutgroup.accelerator.connectn.player.Counter;
import com.thehutgroup.accelerator.connectn.player.Position;

public class NewBoard {
    private final Counter[][] board;

    // Primary constructor: Creates a NewBoard from an existing board.
    public NewBoard(Counter[][] board) {
        this.board = deepCopyBoard(board);
    }

    // Overloaded constructor: Creates a NewBoard with a new counter added at a specific position.
    public NewBoard(Counter[][] board, Position position, Counter counter) {
        this.board = deepCopyBoardWithCounter(board, position, counter);
    }

    public Counter[][] getBoard() {
        return deepCopyBoard(this.board);
    }

    // Helper method to deep copy the board.
    private Counter[][] deepCopyBoard(Counter[][] original) {
        Counter[][] copy = new Counter[original.length][original[0].length];
        for (int i = 0; i < original.length; i++) {
            System.arraycopy(original[i], 0, copy[i], 0, original[i].length);
        }
        return copy;
    }

    // Helper method to deep copy the board and add a counter at a specific position.
    private Counter[][] deepCopyBoardWithCounter(Counter[][] original, Position position, Counter counter) {
        Counter[][] copy = deepCopyBoard(original);
        if (position != null) {
            int x = position.getX();
            int y = position.getY();
            copy[x][y] = counter;
        }
        return copy;
    }
}




