//Thomas Varano
//May 13, 2018

package com.varano.chess.game;

import com.varano.chess.game.pieces.Piece;
import com.varano.chess.information.logging.Logger;
import com.varano.chess.ui.GameUI;
import com.varano.chess.ui.wrappers.BoardUIWrapper;

public class Game {
   /**
    * all pieces in the game. ids are ordinals in the array + 1
    */
   private Piece[] pieces;
   private Player[] players;
   private boolean whitePlaying;
   private Board board;
   private BoardUIWrapper boardUI;
   private GameUI gameUI;
   private boolean gameOver;
   private static final Logger log = Logger.getLogger("standard");
   
   public Game(GameUI parent) {
      gameUI = parent;
      init();
   }
   
   public void restart() {
      init();
      if (boardUI != null) boardUI.updateGameUI();
   }
   
   private void init() {
      board = new Board(this);
      pieces = createPieces();
      players = new Player[2];
      players[0] = new Player(true, this);
      players[1] = new Player(false, this);
      whitePlaying = true;
   }
   
   public void submitMove(Move m) {
      if (!moveLegal(m)) return;
      putMove(m);
   }
   
   public boolean isGameOver() {
      return gameOver;
   }
   
   public void notifyEnd(boolean winnerWhite) {
      gameOver = true;
      gameUI.showEnd(winnerWhite);
   }
   
   public void putMove(Move m) {
      log.info("move submitted... "+m);
      board.put(pieces[m.getPieceID() - 1], m.getEnd());
      if (boardUI != null) boardUI.updateGameUI(); 
      if (gameOver) return;
      whitePlaying = !whitePlaying;
      gameUI.updatePlayer();
   }
   
   public boolean moveLegal(Move m) {
      if (m.getPieceID() >= pieces.length || m.getPieceID() < 0) return false;
      log.fine("a");
      if (!pieces[m.getPieceID() - 1].isAlive()) return false;
      log.fine("b");
      if (m.getEnd().isOccupied())
         for (Piece p : pieces)
            if (p.getLocation().equals(m.getEnd()) && p.isWhite() == pieces[m.getPieceID() - 1].isWhite()) 
               return false;
      return pieces[m.getPieceID() - 1].moveLegal(m);
   }
   
   public Piece pieceAt(Space s) {
      if (!s.isOccupied()) return null;
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
   
   public void setBoardUI(BoardUIWrapper ui) {
      this.boardUI = ui;
   }
   
   public Player getCurrentPlayer() {
      if (players[0].isWhite() == whitePlaying)
         return players[0];
      return players[1];
   }
}
