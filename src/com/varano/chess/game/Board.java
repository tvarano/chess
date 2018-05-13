//Thomas Varano
//May 11, 2018

package com.varano.chess.game;

public class Board {
   private Space[][] spaces;
   private Board() {
      initSpaces();
   }
   
   private void initSpaces() {
      spaces = new Space[ChessConstants.rows][ChessConstants.cols];
      for (byte i = 0; i < spaces.length; i++) 
         for (char j = ChessConstants.colOne; j <= ChessConstants.lastCol; j++) {
            spaces[i][(int)(j-ChessConstants.colOne)] = new Space(j, (byte) (ChessConstants.rows - i));
         }
   }
   
   
   public static Board build() {
      Board ret = new Board();
      return ret;
   }
   
   public Space get(char col, byte row) {
      return spaces[ChessConstants.rows - row][(int)(col-ChessConstants.colOne)];
   }
   
   public Space get(int r, int c) {
      return spaces[r][c];
   }
   
   /**
    * a preliminary check for moves to see if they pass rudimentary fundamentals applicable for all pieces.
    * @param m
    * @return
    */
   public boolean moveLegal(Move m) {
      if (m.getEnd().isOccupied()) return false;
      return true;
   }
   
   public int rows() {
      return spaces.length;
   }
   public int cols() {
      return spaces[0].length;
   }
}
