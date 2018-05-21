//Thomas Varano
//May 14, 2018

package com.varano.chess.information;

import java.util.function.Consumer;
import java.util.logging.Filter;
import java.util.logging.Level;
import java.util.logging.LogManager;
import java.util.logging.LogRecord;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;
import java.util.logging.StreamHandler;

public class LogHandler {
   private static final String MASTER_NAME = "master";
   public static final Logger MASTER = Logger.getLogger(MASTER_NAME);
   
   //set up master logger
  static {
      MASTER.setLevel(Level.FINER);
      StreamHandler h = new StreamHandler(System.out, new SimpleFormatter());
      h.setLevel(MASTER.getLevel());
      MASTER.addHandler(h);
      MASTER.setFilter(new Filter() {
         @Override
         public boolean isLoggable(LogRecord arg0) {
            return arg0.getLevel().intValue() >= MASTER.getLevel().intValue();
         }
      });
   }
  
  public static Logger getLogger(String name) {
     Logger ret = Logger.getLogger(name);
     ret.setParent(MASTER);
     return ret;
  }
   
   public static void main(String[] args) {
      System.out.println("log");
      Logger log =  LogHandler.getLogger(LogHandler.class.getName());
//      while (LogManager.getLogManager().getLoggerNames().asIterator().hasNext())
//         System.out.println(LogManager.getLogManager().getLoggerNames().asIterator().next());
      LogManager.getLogManager().getLoggerNames().asIterator().forEachRemaining(new Consumer<String>() {
         @Override
         public void accept(String arg0) {
            System.out.println("printing: "+arg0);
         }
         
      });
      
      
      System.out.println(log.getParent().getLevel());
      System.out.println(log.getLevel());
      System.out.println("done");
      log.config("fing test");
      log.fine("fine");
   }
}
