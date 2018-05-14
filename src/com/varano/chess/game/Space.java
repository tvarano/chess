//Thomas Varano
//May 11, 2018

package com.varano.chess.game;

public class Space {
   private byte row;
   private char col;
   private boolean isWhite, occupied;
   
   /**
    * a space in a chessBoard. simply holds a row and column
    * @param col
    * @param row
    */
   public Space(char col, byte row) {
      this.col = col; this.row = row;
      isWhite = (getColumn() + row) % 2 == 0;
   }
   
   public byte getColumn() {
      return (byte) (col - ChessConstants.colOne + 1);
   }
   public String toString() {
      return (char)col + "" + row;
   }
   public void setOccupation(boolean occupied) {
      this.occupied = occupied;
   }
   public boolean isOccupied() {
      return occupied;
   }
   public boolean isWhite() {
      return isWhite;
   }
   public byte getRow() {
      return row;
   }
}
