package com.thg.accelerator23.connectn.ai.ozcanbot;

import com.thehutgroup.accelerator.connectn.player.Board;
import com.thehutgroup.accelerator.connectn.player.Counter;
import com.thehutgroup.accelerator.connectn.player.Player;
import com.thehutgroup.accelerator.connectn.player.Position;


public class JohnBotNeumann extends Player {
  public JohnBotNeumann(Counter counter) {
    //TODO: fill in your name here
    super(counter, JohnBotNeumann.class.getName());
  }

  @Override
  public int makeMove(Board board) {
    //TODO: some crazy analysis
    //TODO: make sure said analysis uses less than 2G of heap and returns within 10 seconds on whichever machine is running it

    boolean validMove = false;
    int selectedColumn = -1;

    while (!validMove) {
      int position = (int)(Math.random() * board.getConfig().getWidth());

      if (!board.hasCounterAtPosition(new Position(position, board.getConfig().getHeight() - 1))) {
        validMove = true;
        selectedColumn = position;
      }
    }

    return selectedColumn;
  }
}
