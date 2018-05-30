//Thomas Varano
//May 22, 2018

package com.varano.chess.information.logging;

import java.io.PrintStream;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.logging.Filter;
import java.util.logging.Level;
import java.util.logging.LogRecord;

public class Logger {
   private PrintStream out;
   private Filter filter;
   private Logger master;
   private Level threshold;
   private final String name;
   
   Logger(String name, Logger master) {
      this.name = name; this.master = master;
   }
   
   Logger(String name) {
      this(name, LogManager.getGlobal());
   }
   
   private static final String ANON_NAME = "anon";
   Logger() {
      this(ANON_NAME);
   }
   
   
   public static Logger getLogger(String name) {
      return LogManager.manager.getLogger(name);
   }
   
   //-------------------------------LOGGING--------------------------------
   public void log(LogRecord l) {
      if (!vetLog(l)) return;
      getOut().println(LocalDate.now() + " " + LocalTime.now() + " "+ getCallerMethod() +":");
      getOut().println(l.getLevel().getName() + ": "+l.getMessage());
   }
   
   public void log(Level l, String msg) {
      log(new LogRecord(l, msg));
   }
   
   public void severe(String msg) {
      log(Level.SEVERE, msg);
   }
   
   public void warning(String msg) {
      log(Level.WARNING, msg);
   }
   
   public void info(String msg) {
      log(Level.INFO, msg);
   }
   
   public void config(String msg) {
      log(Level.CONFIG, msg);
   }
   
   public void fine(String msg) {
      log(Level.FINE, msg);
   }
   
   public void finer(String msg) {
      log(Level.FINER, msg);
   }
   
   public void finest(String msg) {
      log(Level.FINEST, msg);
   }
   
   
   //----------------------------------------------------------------------
   
   public static String getCallerMethod() { 
      StackTraceElement[] stElements = Thread.currentThread().getStackTrace();
      for (int i=1; i<stElements.length; i++) {
          StackTraceElement ste = stElements[i];
          if (!ste.getClassName().equals(Logger.class.getName()) && ste.getClassName().indexOf("java.lang.Thread") != 0) {
              return ste.getClassName() + " " + ste.getMethodName() +  " ln " + ste.getLineNumber();
          }
      }
      return "";
   }
   
   private boolean vetLog(LogRecord l) {
      return (l.getLevel().intValue() >= getThreshold().intValue());
   }
   
   public PrintStream getOut() {
      if (out != null) return out;
      return master.getOut();
   }
   public void setOut(PrintStream out) {
      this.out = out;
   }
   public Filter getFilter() {
      if (filter != null) return filter; 
      if (master == null) return LogManager.ALL_FILTER;
      return master.getFilter();
   }
   public void setFilter(Filter filter) {
      this.filter = filter;
   }
   public Logger getMaster() {
      return master;
   }
   public void setMaster(Logger master) {
      this.master = master;
   }
   public Level getThreshold() {
      if (threshold != null) return threshold;
      if (master == null) return LogManager.DEFAULT_LEVEL;
      return master.getThreshold();
   }
   public void setThreshold(Level threshold) {
      this.threshold = threshold;
   }
   public String getName() {
      return name;
   }
   
}
