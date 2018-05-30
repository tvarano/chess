//Thomas Varano
//May 11, 2018

package com.varano.chess.game;

public class Space {
   private byte row;
   private char col;
   private boolean isWhite, occupied;

   public static final Space DEAD_SPACE = new Space((char)(0), (byte)-1);
   
   /**
    * a space in a chessBoard. simply holds a row and column
    * @param col
    * @param row
    */
   public Space(char col, byte row) {
      this.col = col; this.row = row;
      isWhite = (getColumn() + row) % 2 == 0;
   }
   
   public boolean equals(Space o) {
      return col == o.col && row == o.row;
   }
   
   public boolean isVertical(Space s) {
      return s.getColumn() == getColumn();
   }
   
   public boolean isHorizontal(Space s) {
      return s.getRow() == getRow();
   }
   
   public boolean isDiagonal(Space s) {
      return (Math.abs(s.getColumn() - getColumn()) == Math.abs(s.getRow() - getRow()));
   }
   
   public double distanceTo(Space o) {
      return Math.sqrt(Math.pow(row - o.row, 2) + Math.pow(col - o.col, 2));
   }
   
   public byte getColumn() {
      return (byte) (col - ChessConstants.colOne + 1);
   }
   public char colChar() {
      return col;
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
   public byte getRowIndex() {
      return (byte) (ChessConstants.rows - row + 1);
   }
}
