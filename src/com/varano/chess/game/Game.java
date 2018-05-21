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
      board = new Board(this);
      pieces = createPieces();
   }
   
   public void submitMove(Move m) {
      if (!moveLegal(m)) return;
   }
   
   public boolean moveLegal(Move m) {
      if (m.getPieceID() >= pieces.length || m.getPieceID() < 0) return false;
      if (!pieces[m.getPieceID() - 1].isAlive()) return false;
      if (m.getEnd().isOccupied())
         for (Piece p : pieces)
            if (p.getLocation().equals(m.getEnd()) && p.isWhite() == pieces[m.getPieceID() - 1].isWhite()) 
               return false;
      return pieces[m.getPieceID() - 1].moveLegal(m);
   }
   
   public Piece pieceAt(Space s) {
      for (Piece p : pieces)
         if (p.getLocation().equals(s))
            return p;
      return null;
   }
   
   private Piece[] createPieces() {
      return Piece.createStart(this);
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
