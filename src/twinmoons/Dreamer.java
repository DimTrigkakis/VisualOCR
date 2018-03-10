/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package twinmoons;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.awt.image.BufferedImage;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 *
 * @author James
 */
public class Dreamer extends JPanel implements MouseMotionListener {

          Graphics myGraphics;
          BufferedImage painting;

          public Dreamer() {

                    super();
                    setBackground(Color.white);
                    this.setBounds(0, 0, 150, 150);
                    painting = new BufferedImage(this.getWidth(),this.getHeight(),BufferedImage.TYPE_INT_RGB);
                    
                    initializeListener();

          }

          private void initializeListener() {
                    this.addMouseMotionListener(this);
          }

          public void paintComponent(Graphics g) // draw graphics in the panel
          {
                    int width = getWidth();             // width of window in pixels
                    int height = getHeight();           // height of window in pixels

                    super.paintComponent(g);            // call superclass to make panel display correctly

                    g.drawImage(painting, 0, 0, this);
                    // Drawing code goes here
          }

          public void mouseMoved(MouseEvent e) {
          }

          public void mouseDragged(MouseEvent e) {

                    
          }
          
          public void drawMe(BufferedImage painting)
          {
                    this.painting = painting;
                    repaint();
          }

          public void reset()
          {
                    painting = new BufferedImage(this.getWidth(),this.getHeight(),BufferedImage.TYPE_INT_RGB);
                    repaint();
          }
}
