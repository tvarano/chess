//Thomas Varano
//May 11, 2018

package com.varano.chess.game;

import java.util.ArrayList;

import com.varano.chess.game.pieces.Piece;

public class Board {
   private Space[][] spaces;
   private Game parent;
   
   public Board(Game parent) {
      this.parent = parent;
      build();
   }
   
   private void build() {
      spaces = new Space[ChessConstants.rows][ChessConstants.cols];
      for (byte i = 0; i < spaces.length; i++) 
         for (char j = ChessConstants.colOne; j <= ChessConstants.lastCol; j++) {
            spaces[i][(int)(j-ChessConstants.colOne)] = new Space(j, (byte) (ChessConstants.rows - i));
         }
   }
   
   public Space get(int r, int c) {
      return spaces[r][c];
   }
   
   public Space get(char col, byte row) {
      return get(ChessConstants.rows - row, (int)(col-ChessConstants.colOne));
   }
   
   public Space getFromChess(int row, int col) {
      return get(ChessConstants.rows - row, col-ChessConstants.colOne);
   }
   
   /**
    * finds the spaces between two spaces, a start and end point. 
    * Prerequisite: must be diagonal, vertical, or horizontal
    * @param a start point
    * @param b end point
    * @return an ArrayList of spaces between a and b, inclusive of a and b.
    */
   public ArrayList<Space> spacesBetween(Space a, Space b) {
      ArrayList<Space> ret = new ArrayList<Space>();
      if (a.isVertical(b)) {
         int minIndx = Math.min(a.getRow(), b.getRow());
         int maxIndx = Math.max(a.getRow(), b.getRow());
         for (int r = minIndx; r <= maxIndx; r++)
            ret.add(getFromChess(r, a.getColumn()));
         return ret;
      }
      if (a.isHorizontal(b)) {
         int minIndx = Math.min(a.getColumn(), b.getColumn());
         int maxIndx = Math.max(a.getColumn(), b.getColumn());
         for (int c = minIndx; c <= maxIndx; c++)
            ret.add(getFromChess(a.getRow(), c));
         return ret;
      }
      if (a.isDiagonal(b)) {
         Space min = (Math.min(a.getColumn(), b.getColumn()) == a.getColumn()) ? a : b;
         Space max = (min.equals(a)) ? b : a;
         boolean down = Math.min(min.getRow(), max.getRow()) == max.getRow();
         for (int r = down ? max.getRow() : min.getRow();
            r < (down ? min.getRow() : min.getRow());
            r += down ? -1 : 1) {
            for (int c = min.getColumn(); c <= max.getColumn(); c++)
               ret.add(getFromChess(r, c));
         }
         return ret;
      }
      return ret;
   }
   
   public void put(Piece p, Space s) {
      p.getLocation().setOccupation(false);
      p.setLocation(s);
      s.setOccupation(true);
   }
   
   public int rows() {
      return spaces.length;
   }
   public int cols() {
      return spaces[0].length;
   }
   public Game getParent() {
      return parent;
   }
}
