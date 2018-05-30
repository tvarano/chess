//Thomas Varano
//May 13, 2018

package com.varano.chess.game;


import com.varano.chess.game.pieces.Piece;
import com.varano.chess.information.logging.Logger;

public class Player {
   private boolean white;
   private Game parentGame;
   private Piece selectedPiece;
   private Space endSpace;
   private byte startRow;
   
   private static final Logger log = Logger.getLogger(Player.class.getName());
   
   public Player(boolean white, Game parentGame) {
      this.parentGame = parentGame; this.white = white;
      startRow = (byte) (white ? 1 : 8);
   }
   
   public boolean isForwards(Space start, Space end) {
      
      return Math.abs(start.getRow() - startRow) < Math.abs(end.getRow() - startRow);
   }
   
   public void submitMove() {
      parentGame.submitMove(new Move(selectedPiece.getId(), endSpace));
      selectedPiece = null;
      endSpace = null;
   }

   public boolean isWhite() {
      return white;
   }

   public void setWhite(boolean white) {
      this.white = white;
   }

   public Piece getSelectedPiece() {
      return selectedPiece;
   }

   public void setSelectedPiece(Piece selectedPiece) {
      this.selectedPiece = selectedPiece;
   }

   public Space getEndSpace() {
      return endSpace;
   }

   public void setEndSpace(Space endSpace) {
      this.endSpace = endSpace;
   }
   
   public byte getStartRow() {
      return startRow;
   }

   public String toString() {
      return getClass().getName() + "[white = "+white + "]";
   }
}
