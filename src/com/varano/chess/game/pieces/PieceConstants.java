//Thomas Varano
//May 13, 2018

package com.varano.chess.game.pieces;

import com.varano.chess.game.ChessConstants;

public class PieceConstants {
   /*
    * piece ID
    * start at 1
    * white first, then black
    * number from top left to bottom right of each side. 
    * so white pawns are 1-8
    * white back is 9-16
    * black pawns are 16-24
    * black back is 25-32
    */
   
   public static final byte amtPawn = ChessConstants.cols, amtOnTeam = amtPawn * 2;
   
   public static byte getRow(int pieceID) {
      if (pieceID <= amtPawn) return 2;
      if (pieceID <= amtOnTeam) return 1;
      pieceID -= amtOnTeam;
      if (pieceID < amtPawn) return 7;
      return 8;
   }
   
   public static char getCol(int pieceID) {
      if (pieceID <= amtOnTeam) {
         pieceID--;
         return (char)(ChessConstants.colOne + (pieceID % ChessConstants.cols)); 
      }
      pieceID--;
      return (char)(ChessConstants.lastCol - (pieceID % ChessConstants.cols));
   }
   
   public static void main(String[] args) {
      System.out.println(getCol(25));
   }
}
