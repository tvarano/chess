//Thomas Varano
//May 14, 2018

package com.varano.chess.ui.wrappers;

import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JPanel;

import com.varano.chess.game.Board;
import com.varano.chess.game.Game;

public class BoardUIWrapper extends JPanel {
   private Board b;
   private SpaceUIWrapper[][] spaces;
   
   public BoardUIWrapper(Board b, Game master) {
      super(new GridLayout(b.rows(), b.cols()));
      this.b = b;
      spaces = new SpaceUIWrapper[b.rows()][b.cols()];
      for (int i = 0; i < spaces.length; i++)
         for (int j = 0; j < spaces[i].length; j++) {
            spaces[i][j] = new SpaceUIWrapper(b.get(i, j), master);
         }
      addComponents();
      requestFocus();
   }
   
   private void addComponents() {
      for (JButton[] bs : spaces)
         for(JButton b : bs)
            add(b);
   }
}
