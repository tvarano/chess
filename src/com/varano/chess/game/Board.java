//Thomas Varano
//May 11, 2018

package com.varano.chess.game;

import java.util.ArrayList;

import com.varano.chess.game.pieces.Piece;
import com.varano.chess.information.logging.Logger;

public class Board {
   private Space[][] spaces;
   private Game parent;
   private static final Logger log = Logger.getLogger(Board.class.getName());
   
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
      log.config("r-"+(ChessConstants.rows - row) + "col-"+ (col-ChessConstants.colOne));
      return get(ChessConstants.rows - row, col-ChessConstants.colOne);
   }
   
   public Space getFromChess(char col, byte row) {
      return get(ChessConstants.rows - row, (int)(col-ChessConstants.colOne));
   }
   
   public Space getFromIndex(int row, int c) {
//      log.severe("");
      return get(row - 1, c - 1);
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
         int minIndx = Math.min(a.getRowIndex(), b.getRowIndex());
         int maxIndx = Math.max(a.getRowIndex(), b.getRowIndex());
         log.fine("max, "+maxIndx+", min "+minIndx);
         for (int r = minIndx + 1; r < maxIndx; r++)
            ret.add(getFromIndex(r, a.getColumn()));
      }
      if (a.isHorizontal(b)) {
         int minIndx = Math.min(a.getColumn(), b.getColumn());
         int maxIndx = Math.max(a.getColumn(), b.getColumn());
         for (int c = minIndx + 1; c < maxIndx; c++)
            ret.add(getFromIndex(a.getRowIndex(), c));
      }
      if (a.isDiagonal(b)) {
         Space min = (Math.min(a.getColumn(), b.getColumn()) == a.getColumn()) ? a : b;
         Space max = (min.equals(a)) ? b : a;
         boolean down = Math.min(min.getRowIndex(), max.getRowIndex()) == max.getRowIndex();
         log.fine("Dpwm"+down);
         for (int i = 1; i < max.getColumn() - min.getColumn(); i++) {
            ret.add(getFromIndex(down ? min.getRowIndex() - i : min.getRowIndex() + i, min.getColumn() + i));
         }
      }
      log.config("spaces between. "+ret);
      return ret;
   }
   
   public void put(Piece p, Space s) {
      p.getLocation().setOccupation(false);
      if (s.isOccupied())
         parent.pieceAt(s).die();
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
