//Thomas Varano
//May 11, 2018

package com.varano.chess.game.pieces;

import com.varano.chess.game.Board;
import com.varano.chess.game.Space;

public abstract class Piece {
   private byte id;
   private boolean white, alive;
   private Space location;
   
   public Piece(byte id, boolean isWhite, Space location) {
      this.id = id; setWhite(isWhite); setLocation(location);
   }
   
   public Piece(byte id, boolean isWhite) {
      this (id, isWhite, null);
   }
   
   public Piece() {
      this((byte) 0, false);
   }
   
   public static Piece[] createStart(Board b) {
      byte id = 1;
      Piece[] ret = new Piece[PieceConstants.amtOnTeam * 2];
      int index = 0;
      for (int i = 0; i < 2; i++) {
         boolean white = i == 0;
//         int index = white ? 0 : PieceConstants.amtOnTeam;
         for ( ; index % PieceConstants.amtOnTeam < PieceConstants.amtPawn; index++) {
            ret[index] = new Pawn(id, white, b.get(PieceConstants.getCol(id), PieceConstants.getRow(id)));
            System.out.println(ret[index]);
            id++;
         }
         ret[index] = new Rook(id, white, b.get(PieceConstants.getCol(id), PieceConstants.getRow(id))); index++; id++;
         ret[index] = new Knight(id, white, b.get(PieceConstants.getCol(id), PieceConstants.getRow(id))); index++; id++;
         ret[index] = new Bishop(id, white, b.get(PieceConstants.getCol(id), PieceConstants.getRow(id))); index++; id++;
         ret[index] = new King(id, white, b.get(PieceConstants.getCol(id), PieceConstants.getRow(id))); index++; id++;
         ret[index] = new Queen(id, white, b.get(PieceConstants.getCol(id), PieceConstants.getRow(id))); index++; id++;
         ret[index] = new Bishop(id, white, b.get(PieceConstants.getCol(id), PieceConstants.getRow(id))); index++; id++;
         ret[index] = new Knight(id, white, b.get(PieceConstants.getCol(id), PieceConstants.getRow(id))); index++; id++;
         ret[index] = new Rook(id, white, b.get(PieceConstants.getCol(id), PieceConstants.getRow(id))); index++; id++;
      }
      return ret;
   }

   public boolean isWhite() {
      return white;
   }

   public void setWhite(boolean white) {
      this.white = white;
   }

   public boolean isAlive() {
      return alive;
   }

   public void setAlive(boolean alive) {
      this.alive = alive;
   }

   public Space getLocation() {
      return location;
   }

   public void setLocation(Space location) {
      this.location = location;
   }

   public byte getId() {
      return id;
   }
   
   public String toString() {
      return getClass().getName() + "[id= " + id + ", location=" + location + "]";
   }
}
