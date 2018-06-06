//Thomas Varano
//May 11, 2018

package com.varano.chess.game.pieces;

import com.varano.chess.game.Game;
import com.varano.chess.game.Move;
import com.varano.chess.game.Space;
import com.varano.chess.resources.ResourceManager;

public class King extends Piece {

   public King(byte id, boolean white, Space location, Game parent) {
      super(id, white, location, parent);
      setSkin(ResourceManager.getSkins()[(white) ? ResourceManager.W_KING : ResourceManager.B_KING]);
   }
   
   public boolean moveLegal(Move m) {
      if (!super.moveLegal(m)) return false;
      return m.getEnd().distanceTo(location) <= Math.sqrt(2);
   }
   
   public void die() {
      super.die();
      parent.notifyEnd(!white);
   }
}
