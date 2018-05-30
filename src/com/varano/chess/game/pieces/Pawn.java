//Thomas Varano
//May 11, 2018

package com.varano.chess.game.pieces;

import com.varano.chess.game.Game;
import com.varano.chess.game.Move;
import com.varano.chess.game.Space;
import com.varano.chess.resources.ResourceManager;

public class Pawn extends Piece {

   private Space start;
   
   public Pawn(byte id, boolean white, Space location, Game parent) {
      super(id, white, location, parent);
      start = location;
      setSkin(ResourceManager.getSkins()[(white) ? ResourceManager.W_PAWN : ResourceManager.B_PAWN]);
   }
   
   public boolean moveLegal(Move m) {
      if (!super.moveLegal(m)) return false;
      if (m.getEnd().isHorizontal(location)) return false;
      System.out.println("CHECK ONE");
      if (parent.getCurrentPlayer().isForwards(location, m.getEnd())) {
         int distanceAvailable = (location.equals(start)) ? 2 : 1;
         if (m.getEnd().isVertical(location) && 
               m.getEnd().distanceTo(location) <= distanceAvailable && !m.getEnd().isOccupied())
            return true;
         System.out.println("CHECK TWO");
      if (m.getEnd().isDiagonal(location) && m.getEnd().isOccupied()
            && parent.pieceAt(m.getEnd()).isWhite() != isWhite() && m.getEnd().distanceTo(location) == 1)
         return true;
      }
      System.out.println("CHECK THREE");
      return false;
   }
}
