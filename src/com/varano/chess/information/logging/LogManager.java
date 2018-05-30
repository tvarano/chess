//Thomas Varano
//May 22, 2018

package com.varano.chess.information.logging;

import java.util.HashMap;
import java.util.logging.Filter;
import java.util.logging.Level;
import java.util.logging.LogRecord;

public class LogManager {
   public static final String GLOBAL_NAME = "global";
   
   static final LogManager manager;
   public static final Level DEFAULT_LEVEL = Level.ALL;
   
   public static final Filter ALL_FILTER = new Filter() {
      @Override
      public boolean isLoggable(LogRecord arg0) {
         return arg0.getLevel().intValue() >= DEFAULT_LEVEL.intValue();
      }
   };
   
   
   private final String name;
   private final LogManager parent;
   private final HashMap<String, Logger> children;

   private Logger master;
   
   static {
      manager = new LogManager();
   }
   
   public static Logger getGlobal() {
      return manager.master;
   }
   
   public LogManager(String name, LogManager parent) {
      this.name = name;
      children = new HashMap<String, Logger>();
      this.parent = parent;
      initMaster();
   }
   
   /**
    * only for initializing the global manager
    */
   private LogManager() {
      name = GLOBAL_NAME;
      children = new HashMap<String, Logger>();
      parent = null;
      master = new Logger(name, null);
      addLogger(master);
      master.setFilter(defaultFilter());
      master.setThreshold(DEFAULT_LEVEL);
      master.setOut(System.out);
   }
   
   private Filter defaultFilter() {
      return new Filter() {
         @Override
         public boolean isLoggable(LogRecord arg0) {
            return arg0.getLevel().intValue() >= master.getThreshold().intValue();
         }
      };
   }
   
   private void initMaster() {
      master = getLogger(name);
      master.setFilter(defaultFilter());
      master.setThreshold(DEFAULT_LEVEL);
      master.setOut(System.out);      
   }
   
   public LogManager(String name) {
      this(name, null);
   }
   
   private Logger putLogger(Logger l) {
      addLogger(l);
      if (parent != null) parent.putLogger(l);
      if (name != GLOBAL_NAME) manager.putLogger(l);
      return l;
   }
   
   public void addLogger(Logger l) {
      children.put(l.getName(), l);      
   }

   public Logger getAnonymousLogger() {
      return new Logger();
   }
   
   
   Logger getLogger(String name) {
      if (children.containsKey(name)) return children.get(name);
      if(manager.children.containsKey(name)) putLogger(manager.children.get(name));
      Logger created =  putLogger(new Logger(name, master));
      return created;
   }
   
   public Filter getFilter() {
      return master.getFilter();
   }

   public void setFilter(Filter filter) {
      master.setFilter(filter);
   }

   public Level getThreshold() {
      return master.getThreshold();
   }

   public void setThreshold(Level threshold) {
      master.setThreshold(threshold);
   }

   public String getName() {
      return name;
   }

   public LogManager getParent() {
      return parent;
   }

   public HashMap<String, Logger> getChildren() {
      return children;
   }

   public Logger getMaster() {
      return master;
   }
}
