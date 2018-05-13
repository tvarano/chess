//Thomas Varano
//May 11, 2018

package com.varano.chess.game.pieces;

import com.varano.chess.game.Space;

public abstract class Piece {
   private byte id;
   private boolean isWhite;
   private Space location;
   
   public Piece(byte id, boolean isWhite, Space location) {
      
   }
   
   public Piece(byte id, boolean isWhite) {
      this (id, isWhite, null);
   }
   
   public Piece() {
      this((byte) 0, false);
   }
   
   public Piece[] createStart(boolean white) {
      byte id = (byte) ((white) ? 1 : 33);
      int index = 0;
      Piece[] ret = new Piece[PieceConstants.amtOnTeam];
      for ( ; index < PieceConstants.amtPawn; index++) {
         ret[index] = new Pawn(id, white);
         id++;
      }
      ret[index] = new Rook(id, white); index++; id++;
      ret[index] = new Knight(id, white); index++; id++;
      ret[index] = new Bishop(id, white); index++; id++;
      ret[index] = new King(id, white); index++; id++;
      ret[index] = new Queen(id, white); index++; id++;
      ret[index] = new Bishop(id, white); index++; id++;
      ret[index] = new Knight(id, white); index++; id++;
      ret[index] = new Rook(id, white); index++; id++;
      return ret;
   }
}
