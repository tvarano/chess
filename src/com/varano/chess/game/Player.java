//Thomas Varano
//May 13, 2018

package com.varano.chess.game;

public class Player {
   private boolean white;
   private Game parentGame;
   
   public Player(Game parentGame, boolean white) {
      this.parentGame = parentGame; this.white = white;
   }
   
   public void submitMove(Move m) {
      
   }
}
