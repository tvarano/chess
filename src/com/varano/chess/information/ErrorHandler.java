//Thomas Varano
//May 14, 2018

package com.varano.chess.information;

import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintStream;
import java.util.logging.Level;


public class ErrorHandler {
   private static class ErrorLevel extends Level {
      private static final long serialVersionUID = -8164200315564230090L;

      public static final Level SEVERE = new ErrorLevel("SEVERE ERROR", 900);
      
      public static final Level MODERATE = new ErrorLevel("MODERATE ERROR", 850);
      
      public static final Level TRIVIAL = new ErrorLevel("TRIVIAL ERROR", 600);
      
      private ErrorLevel(String name, int severity) {
         super(name, severity);
      }
   }
   
   public static final Level SEVERE = ErrorLevel.SEVERE;
   
   public static final Level MODERATE = ErrorLevel.MODERATE;
   
   public static final Level TRIVIAL = ErrorLevel.TRIVIAL;
   
   public static String errorMessage(Throwable e) {
      String ret = e.getClass().getName() + ": " + e.getMessage();
      if (e.getCause() != null)
         ret += "\tCaused by: "+errorMessage(e.getCause());
      
      return ret;
   }
   
   public static String getStackTrace(Throwable e) {
      StringBuilder b = new StringBuilder();
      OutputStream o = new OutputStream() {
         @Override
         public void write(int arg0) throws IOException {
            b.append((char)arg0);
         }
      };
      e.printStackTrace(new PrintStream(o));
      return b.toString();
   }
   
   public static void main(String[] args) {
      try {
         System.out.println(5/0);
      } catch (Exception e) {
         System.out.println(getStackTrace(e));
      }
   }
}
