package com.thg.accelerator23.connectn.ai.ozcanbot;

import com.thehutgroup.accelerator.connectn.player.Board;
import com.thehutgroup.accelerator.connectn.player.Counter;
import com.thehutgroup.accelerator.connectn.player.Player;
import com.thehutgroup.accelerator.connectn.player.Counter;

import java.util.Arrays;

// these are my classes


public class JohnBotNeumann extends Player {
  public JohnBotNeumann(Counter counter) {
    //TODO: fill in your name here
    super(counter, JohnBotNeumann.class.getName());
  }

  @Override
  public int makeMove(Board board) {
    //TODO: some crazy analysis
    //TODO: make sure said analysis uses less than 2GB of heap and returns within 10 seconds on whichever machine is running it

    BoardProperties copiedBoard = new BoardProperties(board.getCounterPlacements(), true);
    boolean validMove = false;
    int selectedColumn = -1;

    while (!validMove) {
      int column = (int)(Math.random() * board.getConfig().getWidth());

      if (copiedBoard.columnFree(column)) {
        validMove = true;
        selectedColumn = column;
      }
    }
    return selectedColumn;
  }
}
