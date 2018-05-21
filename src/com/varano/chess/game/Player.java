//Thomas Varano
//May 13, 2018

package com.varano.chess.game;

import com.varano.chess.game.pieces.Piece;

public class Player {
   private boolean white;
   private Game parentGame;
   private Piece selectedPiece;
   private Space endSpace;
   
   public Player(Game parentGame, boolean white) {
      this.parentGame = parentGame; this.white = white;
   }
   
   public void submitMove() {
      parentGame.submitMove(new Move(selectedPiece.getId(), endSpace));
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
}
