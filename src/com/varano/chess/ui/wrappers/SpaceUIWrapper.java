//Thomas Varano
//May 14, 2018

package com.varano.chess.ui.wrappers;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;

import com.varano.chess.game.Game;
import com.varano.chess.game.Space;
import com.varano.chess.information.logging.Logger;
import com.varano.chess.resources.ResourceManager;
import com.varano.chess.ui.UIHandler;

public class SpaceUIWrapper extends JButton implements ActionListener {
   private Space s;
   private Game master;
   private static final Logger log = Logger.getLogger(SpaceUIWrapper.class.getName());
   
   public SpaceUIWrapper(Space s, Game master) {
      super(s.toString());
      setSpace(s);
      this.master = master;
      setUI();
      addActionListener(this);
   }

   private void setUI() {
      setBackground((s.isWhite() ? UIHandler.lightTeam : UIHandler.darkTeam));
      setBorderPainted(false);
      setOpaque(true);
      setForeground((s.isWhite() ? UIHandler.darkTeam : UIHandler.lightTeam));
      updateImage();
   }
   
   public void updateImage() {
      if (s.isOccupied())
         setIcon(new ImageIcon(master.pieceAt(s).getSkin()));
      else
         setIcon(new ImageIcon(ResourceManager.getEmpty()));
   }
   
   public void revalidate() {
      super.revalidate();
      log.finest("reval");
      int minDim = Math.min(getWidth(), getHeight());
      setSize(minDim, minDim);
   }
   
   public Space getSpace() {
      return s;
   }

   public void setSpace(Space s) {
      this.s = s;
   }

   @Override
   public void actionPerformed(ActionEvent arg0) {
      log.finer("clicked " + s);
      if (s.isOccupied() && master.pieceAt(s).isWhite() == master.getCurrentPlayer().isWhite()) {
         master.getCurrentPlayer().setSelectedPiece(master.pieceAt(s));
      } else if (master.getCurrentPlayer().getSelectedPiece() != null) {
         master.getCurrentPlayer().setEndSpace(s);
         master.getCurrentPlayer().submitMove();
         updateImage();
      }
   }
}