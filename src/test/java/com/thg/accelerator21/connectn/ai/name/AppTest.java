package com.thg.accelerator21.connectn.ai.name;

import com.thehutgroup.accelerator.connectn.player.*;
import com.thg.accelerator23.connectn.ai.ozcanbot.JohnBotNeumann;
import com.thg.accelerator23.connectn.ai.ozcanbot.Minimax;
import com.thg.accelerator23.connectn.ai.ozcanbot.NewBoard;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit test for simple App.
 */
public class AppTest {
  /**
   * Rigorous Test :-)
   */
  @Test
  public void shouldAnswerWithTrue() {
    assertTrue(true);
  }

  @Test
  void testGetValidPositions_emptyBoard() {
    Counter[][] emptyBoard = new Counter[10][8]; // 7 columns x 6 rows
    NewBoard newBoard = new NewBoard(emptyBoard);
    Minimax minimax = new Minimax(5); // Depth is irrelevant here, but we need access to Minimax methods.

    ArrayList<Position> validPositions = minimax.getValidPositions(newBoard.getBoard());

    assertEquals(10, validPositions.size());
    for (int i = 0; i < 10; i++) {
      assertEquals(i, validPositions.get(i).getX());
      assertEquals(0, validPositions.get(i).getY());
    }
  }

  @Test
  void testDeepCopyBoard() {
    Counter[][] board = new Counter[10][8];
    board[0][0] = Counter.X;
    NewBoard newBoard = new NewBoard(board);

    Counter[][] copiedBoard = newBoard.getBoard();
    assertEquals(Counter.X, copiedBoard[0][0]);

    copiedBoard[0][0] = Counter.O;
    assertNotEquals(Counter.O, newBoard.getBoard()[0][0]); // Ensure deep copy.
  }

  @Test
  void testGetMinFreeY() {
    Counter[][] board = new Counter[10][8];
    board[0][0] = Counter.X;
    NewBoard newBoard = new NewBoard(board);
    Minimax minimax = new Minimax(5); // We rely on Minimax's `getMinFreeY`.

    assertEquals(1, minimax.getMinFreeY(newBoard.getBoard(), 0, 8));
    assertEquals(0, minimax.getMinFreeY(newBoard.getBoard(), 1, 8));
    assertEquals(-1, minimax.getMinFreeY(newBoard.getBoard(), 0, 1)); // Invalid case.
  }

  @Test
  void testBotGoesForWinningMove() {
    // Create a 10x8 board
    Counter[][] board = new Counter[10][8];

    // Set up the board where the bot (X) has a winning move
    // Bot's counters (X) are in a horizontal line except for one slot
    board[4][0] = Counter.X;
    board[5][0] = Counter.X;
    board[6][0] = Counter.X;
    // Empty spot for the winning move
    // This is where the bot should play
    board[7][0] = null;

    // Add some irrelevant counters (O) to the board
    board[1][0] = Counter.O;
    board[2][0] = Counter.O;
    board[3][0] = Counter.O;

    // Convert the board into a NewBoard object
    NewBoard newBoard = new NewBoard(board);

    // Instantiate the bot's logic with desired depth
    Minimax minimax = new Minimax(5); // Depth 5, adjust if necessary

    // Get the bot's move
    Position bestMove = minimax.getBestMove(newBoard, Counter.X);

    // Assert that the bot chooses the winning move (7, 0)
    assertNotNull(bestMove, "The bot should choose a position.");
    assertEquals(7, bestMove.getX(), "The bot should choose column 7 to win.");
    assertEquals(0, bestMove.getY(), "The bot should choose row 0 to win.");
  }

  @Test
  void testBotGoesForWinningMove2() {
    Counter[][] board = new Counter[10][8];

    board[0][0] = Counter.X;
    board[1][1] = Counter.X;
    board[1][3] = Counter.X;
    board[4][0] = Counter.X;
    board[5][1] = Counter.X;
    board[6][0] = Counter.X;

    board[1][0] = Counter.O;
    board[1][2] = Counter.O;
    board[2][0] = Counter.O;
    board[3][0] = Counter.O;
    board[5][0] = Counter.O;
    board[6][1] = Counter.O;

    NewBoard newBoard = new NewBoard(board);

    Minimax minimax = new Minimax(5);

    Position bestMove = minimax.getBestMove(newBoard, Counter.X);

    assertEquals(3, bestMove.getX(), "The bot should choose column 3 to win.");
    assertEquals(1, bestMove.getY(), "The piece should be placed in row 2.");
  }


  @Test
  void testBotGoesForThreeInARow() {
    Counter[][] board = new Counter[10][8];

  }
}


