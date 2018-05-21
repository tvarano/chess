//Thomas Varano
//May 11, 2018

package com.varano.chess.game.pieces;

import com.varano.chess.game.Game;
import com.varano.chess.game.Move;
import com.varano.chess.game.Space;
import com.varano.chess.resources.ResourceManager;

public class Queen extends Piece {
   
   public Queen(byte id, boolean white, Space location, Game parent) {
      super(id, white, location, parent);
      setSkin(ResourceManager.getSkins()[(white) ? ResourceManager.W_QUEEN : ResourceManager.B_QUEEN]);
   }
   
}
