//Thomas Varano
//May 13, 2018

package com.varano.chess.ui;

import java.awt.Color;

public class UIHandler {
   public static Color lightTeam, darkTeam, highlight;

   public static void setColors() {
      lightTeam = Color.WHITE;
      darkTeam = Color.DARK_GRAY;
      highlight = Color.YELLOW.brighter();
   }
   
   public static void setUI() {
      setColors();
   }
}
