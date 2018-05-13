//Thomas Varano
//May 11, 2018

package com.varano.chess.game;

public class MoveRestriction {
   public static final int INFINITE = -1;
   private int amtLeft, amtUp;
   
   public MoveRestriction(int amtLeft, int amtUp) {
      this.amtLeft = amtLeft; this.amtUp = amtUp;
   }
   public int getAmtLeft() {
      return amtLeft;
   }
   public int getAmtUp() {
      return amtUp;
   }
}
