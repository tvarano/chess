//Thomas Varano
//May 13, 2018

package com.varano.chess.game;

/**
 * the package to be sent for a move. After being checked for legality, the move will be submitted.
 * type is to be immutable
 * @author Thomas Varano
 *
 */
public class Move {
   private Space end;
   private byte pieceID;
   
   public Move(byte pieceID, Space end) {
      this.end = end; this.pieceID = pieceID;
   }

   public Space getEnd() {
      return end;
   }

   public byte getPieceID() {
      return pieceID;
   }
}
