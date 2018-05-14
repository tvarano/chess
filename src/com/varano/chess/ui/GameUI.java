//Thomas Varano
//May 13, 2018

package com.varano.chess.ui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JFrame;
import javax.swing.JPanel;

import com.varano.chess.game.Game;
import com.varano.chess.game.Space;
import com.varano.chess.game.pieces.Piece;

public class GameUI extends JPanel {
   private Game game;
   private static final int BUFFER = 20;
   
   public GameUI() {
      game = new Game();
   }
   
   private int computeSpaceSize() {
      return 40;
   }
   
   protected void paintGame(Graphics2D g2) {
      int spaceS = computeSpaceSize();
      //draw spaces
      for (int r = 0; r < game.getBoard().rows(); r++)
         for (int c = 0; c < game.getBoard().cols(); c++) {
            g2.setColor(game.getBoard().get(r, c).isWhite() ? Color.WHITE : Color.BLACK);
            g2.fillRect(BUFFER + spaceS * c, BUFFER + spaceS * r, spaceS, spaceS);
            g2.setColor(game.getBoard().get(r, c).isWhite() ? Color.BLACK : Color.WHITE);
            System.out.println(
                  "drawing square "+game.getBoard().get(r, c) + " at " + (BUFFER + spaceS * c) + ", "+ (BUFFER + spaceS * r));
            g2.drawString(game.getBoard().get(r, c).toString(), BUFFER + spaceS * c, BUFFER + spaceS * (r+1));
         }
      //draw pieces
      for (Piece p : game.getPieces()) {
         Space s = p.getLocation();
//         System.out.println("drawing "+p);
         g2.setColor(Color.RED);
//         g2.setColor((s.isWhite()) ? Color.BLACK : Color.WHITE);
         g2.drawString(p.getId() + "", BUFFER + spaceS * (s.getColumn() - 1) + spaceS/2, BUFFER + spaceS * (s.getRow()));
      }
   }
   
   protected void paintComponent(Graphics g) {
      Graphics2D g2 = (Graphics2D) g;
      paintGame(g2);
   }
   
   public Dimension getPreferredSize() {
      return new Dimension(ChessUI.PREF_W, ChessUI.PREF_H);
   }
   public static void main(String[] args) {
      JFrame f = new JFrame(Chess.APP_NAME + " " + Chess.BUILD + "GAMEUI TEST BUILD");
      f.getContentPane().add(new GameUI());
      f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      f.pack();
      f.setLocationRelativeTo(null);
      f.setVisible(true);
   }
}
