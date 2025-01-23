package com.thg.accelerator23.connectn.ai.ozcanbot;

import com.thehutgroup.accelerator.connectn.player.Board;
import com.thehutgroup.accelerator.connectn.player.Counter;
import com.thehutgroup.accelerator.connectn.player.Player;
import com.thehutgroup.accelerator.connectn.player.Position;

// these are my classes

public class JohnBotNeumann extends Player {
  long startTime;

  // this is the constructor
  public JohnBotNeumann(Counter counter) {
    //TODO: fill in your name here
    super(counter, JohnBotNeumann.class.getName());
    startTime = System.currentTimeMillis();
  }

  @Override // overriding the makeMove function found in the Player class
  public int makeMove(Board board) {
    //TODO: some crazy analysis
    //TODO: make sure said analysis uses less than 2GB of heap and returns within 10 seconds on whichever machine is running it

    System.out.println("This is the time since JohnBotNeumann was made: " + startTime);

    Counter myCounter = getCounter();
    Counter opponentsCounter = getCounter().getOther();
    System.out.println("This is my counter: " + myCounter);
    System.out.println("This is my opponents counter: " + opponentsCounter);



    // when minimax is built all the code below should be replaced by the following lines
    // int selectedColumn = minimax(copiedBoard, myPiece)
    // return selectedColumn
    // Convert the board into a NewBoard object

    NewBoard copiedBoard = new NewBoard(board.getCounterPlacements());

    // Create an instance of Minimax with desired depth
    Minimax minimax = new Minimax(20); // Adjust depth based on performance constraints

    // Get the best move
    Position bestMove = minimax.getBestMove(copiedBoard, myCounter);

    // Return the column index of the best move
    return bestMove.getX();
  }
}
