//Thomas Varano
//May 11, 2018

package com.varano.chess.game.pieces;

import com.varano.chess.game.Game;
import com.varano.chess.game.Move;
import com.varano.chess.game.Space;
import com.varano.chess.resources.ResourceManager;

public class Knight extends Piece {

   public Knight(byte id, boolean white, Space location, Game parent) {
      super(id, white, location, parent);
      setSkin(ResourceManager.getSkins()[(white) ? ResourceManager.W_KNIGHT : ResourceManager.B_KNIGHT]);
   }
   
   public boolean moveLegal(Move m) {
      if (m.getEnd().isOccupied() && parent.pieceAt(m.getEnd()).isWhite() == white) return false;
      return (m.getEnd().distanceTo(location) == Math.sqrt(5));
   }
}
