//Thomas Varano
//May 11, 2018

package com.varano.chess.game.pieces;

import com.varano.chess.game.Game;
import com.varano.chess.game.Move;
import com.varano.chess.game.Space;
import com.varano.chess.resources.ResourceManager;

public class Rook extends Piece {
   public Rook(byte id, boolean white, Space location, Game parent) {
      super(id, white, location, parent);
      setSkin(ResourceManager.getSkins()[(white) ? ResourceManager.W_ROOK : ResourceManager.B_ROOK]);
   }
   
   public boolean moveLegal(Move m) {
      System.out.println("ROOK");
      if (!super.moveLegal(m)) return false;
      System.out.println("um");
      if (m.getEnd().isDiagonal(location)) return false;
      return true;
   }
}
