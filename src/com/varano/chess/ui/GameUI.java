//Thomas Varano
//May 13, 2018

package com.varano.chess.ui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.logging.Level;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import com.varano.chess.game.Game;
import com.varano.chess.information.logging.LogManager;
import com.varano.chess.ui.wrappers.BoardUIWrapper;

public class GameUI extends JPanel {
   private Game game;
   private JLabel player;
   private JPanel north;
   
   public JLabel playerLabel() {
      return player;
   }
   
   private static final String prefix = "Now Playing: ";
   
   public void updatePlayer() {
      player.setText(prefix + game.getCurrentPlayer().getName());
   }
   
   public GameUI() {
      super(new BorderLayout());
      game = new Game(this);
      add(new BoardUIWrapper(game.getBoard(), game), BorderLayout.CENTER);
      player = new JLabel("Now Playing: White");
      player.setFont(player.getFont().deriveFont(30f));
      north = new JPanel(new FlowLayout(FlowLayout.LEFT));
      north.add(player);
      JButton restart = new JButton("Restart");
      restart.addActionListener(new ActionListener() {
         public void actionPerformed(ActionEvent e) {
            game.restart();
         }
      });
      north.add(restart);
      add(north, BorderLayout.NORTH);
   }
   
   public void showEnd(boolean winnerWhite) {
      String text = winnerWhite ? "WINNER : WHITE" : "WINNER : BLACK";
      player.setText(text);
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
