//Thomas Varano
//May 13, 2018

package com.varano.chess.ui;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class ChessUI extends JPanel {
   public static final int PREF_W = 800, PREF_H = 800;
   
   public ChessUI() {
      //start with menu, then go to game
   }
   
   public static void createAndShowGUI() {
      JFrame f = new JFrame(Chess.APP_NAME + " " + Chess.BUILD);
      f.getContentPane().add(new ChessUI());
      f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      f.pack();
      f.setLocationRelativeTo(null);
      f.setVisible(true);
   }
}
