//Thomas Varano
//May 13, 2018

package com.varano.chess.ui;

/**
 * main class, simply starts game and holds program wide variables.
 * @author Thomas Varano
 *
 */
public class Chess {
   public static final String APP_NAME = "Chess";
   public static final String BUILD = "0.2";
   
   public static void main(String[] args) {
      java.awt.EventQueue.invokeLater(new Thread() {
         public void run() {
            GameUI.createAndShowGUI();
         }
      });
   }
}
