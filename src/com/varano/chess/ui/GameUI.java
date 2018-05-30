//Thomas Varano
//May 13, 2018

package com.varano.chess.ui;

import java.awt.Dimension;
import java.util.logging.Level;

import javax.swing.JFrame;
import javax.swing.JPanel;

import com.varano.chess.game.Game;
import com.varano.chess.information.logging.LogManager;
import com.varano.chess.ui.wrappers.BoardUIWrapper;

public class GameUI extends JPanel {
   private Game game;
   
   public GameUI() {
      game = new Game();
      add(new BoardUIWrapper(game.getBoard(), game));
   }
   
   public Dimension getPreferredSize() {
      return new Dimension(ChessUI.PREF_W, ChessUI.PREF_H);
   }
   public static void createAndShowGUI() {
      UIHandler.setUI();
      JFrame f = new JFrame(Chess.APP_NAME + " " + Chess.BUILD + "GAMEUI TEST BUILD");
      LogManager.getGlobal().setThreshold(Level.SEVERE);
      f.getContentPane().add(new GameUI());
      f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      f.pack();
      f.setLocationRelativeTo(null);
      f.setVisible(true);
   }
}
