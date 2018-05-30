//Thomas Varano
//May 14, 2018

package com.varano.chess.resources;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JPanel;

import com.varano.chess.information.ErrorHandler;
import com.varano.chess.information.logging.Logger;

public class ResourceManager {
   private static final Logger log = Logger.getLogger(ResourceManager.class.getName());
   private static BufferedImage pieceSheet, emptySpace;
   private static BufferedImage[] pieceSkins;
   public static final int PIECE_H = 60, PIECE_W = PIECE_H;

   
   public static final int B_QUEEN = 0, B_KING = 1, B_ROOK = 2, B_KNIGHT = 3, B_BISHOP = 4, B_PAWN = 5, 
         W_QUEEN = 6, W_KING = 7, W_ROOK = 8, W_KNIGHT = 9, W_BISHOP = 10, W_PAWN = 11, P_SIZE = 12; 
   static {
      try {
         pieceSheet = getImage(Addresses.pieceSheet);
         pieceSkins = new BufferedImage[P_SIZE];
         for (int i = 0; i < W_QUEEN; i++) {
            pieceSkins[i] = getPieces(0)[i];
            pieceSkins[i + W_QUEEN] = getPieces(PIECE_H)[i];
         }
         
      } catch (IOException e) {
         log.log(ErrorHandler.MODERATE, "cannot init sheets");
         pieceSheet = null;
      }
      try {
         emptySpace = getImage(Addresses.empty);
      } catch (IOException e) {
         log.log(ErrorHandler.MODERATE, "cannot init sheets");
         emptySpace = null;
      }
   }
   
   public static BufferedImage[] getSkins() {
      return pieceSkins;
   }
   
   public static class Addresses {
      public static final String pieceSheet = "pieces3.png";
      public static final String empty = "empty-piece.png";
   }
   
   public static BufferedImage getEmpty() {
      return emptySpace;
   }
   
   public static BufferedImage getImage(String localPath) throws IOException {
      return ImageIO.read(ResourceManager.class.getResource(localPath));
   }
   
   public static BufferedImage getPieces() throws IOException {
      return pieceSheet;
   }
   
   public static BufferedImage getCrop(int x, int y, int w, int h) throws IOException {
      return getPieces().getSubimage(x, y, w, h);
   }
   
   public static BufferedImage[] getPieces(int y) {
      BufferedImage[] ret = new BufferedImage[6];
      for (int i = 0; i < ret.length; i++)
         try {
            log.finer("getting piece at "+(PIECE_W * i));
            ret[i] = getCrop((PIECE_W) * i, y, PIECE_W, PIECE_H);
         } catch (IOException e) {
            log.log(ErrorHandler.MODERATE, ErrorHandler.errorMessage(e));
         }
      return ret;
   }
      
   public static void main(String[] args) {
      JFrame f = new JFrame("CHESS IMAGE TEST");
      JPanel p = new JPanel(new FlowLayout(FlowLayout.LEFT));
      f.getContentPane().add(p);
      p.setBackground(Color.BLACK);
      f.setSize(400,400);
      f.setLocationRelativeTo(null);
      f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      f.setVisible(true);      
   }
}
