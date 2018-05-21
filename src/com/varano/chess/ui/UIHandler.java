//Thomas Varano
//May 13, 2018

package com.varano.chess.ui;

import java.awt.Color;

public class UIHandler {
   public static Color lightTeam, darkTeam;

   public static void setColors() {
      lightTeam = Color.WHITE;
//      darkTeam = new Color(210, 140, 40);
      darkTeam = Color.DARK_GRAY;
   }
   
   public static void setUI() {
      setColors();
   }
}
