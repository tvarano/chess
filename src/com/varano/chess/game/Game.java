//Thomas Varano
//May 13, 2018

package com.varano.chess.game;

import com.varano.chess.game.pieces.Piece;

public class Game {
   /**
    * all pieces in the game. ids are ordinals in the array + 1
    */
   private Piece[] pieces;
   private Board board;
   
   public Game() {
      init();
   }
   
   private void init() {
      board = Board.build();
      pieces = createPieces();
   }
   
   private Piece[] createPieces() {
      return new Piece[0];
   }

   public Piece[] getPieces() {
      return pieces;
   }

   public void setPieces(Piece[] pieces) {
      this.pieces = pieces;
   }

   public Board getBoard() {
      return board;
   }

   public void setBoard(Board board) {
      this.board = board;
   }
}
