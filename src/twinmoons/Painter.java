/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package twinmoons;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.awt.image.BufferedImage;
import javax.swing.JPanel;
import sun.awt.image.ToolkitImage;

/**
 *
 * @author James
 */
public class Painter extends JPanel implements MouseMotionListener {

          Graphics myGraphics;
          BufferedImage painting;

          public Painter() {

                    super();
                    this.setBounds(0, 0, 150, 150);
                    painting = new BufferedImage(this.getWidth(), this.getHeight(), BufferedImage.TYPE_INT_RGB);

                    initializeListener();

          }

          private void initializeListener() {
                    this.addMouseMotionListener(this);
          }

          @Override
          public void paintComponent(Graphics g) // draw graphics in the panel
          {
                    super.paintComponent(g);            // call superclass to make panel display correctly

                    g.drawImage(painting, 0, 0, this);
                    // Drawing code goes here
          }

          @Override
          public void mouseMoved(MouseEvent e) {
          }

          @Override
          public void mouseDragged(MouseEvent e) {

                    myGraphics = this.getGraphics();

                    int x = e.getX();
                    int y = e.getY();

                    int r = TwinMoons.brushSize;
                    int positive = Color.getHSBColor(0, 0, 100).getRGB();
                    int negative = 0;
                    int colour;
                    
                    if (TwinMoons.eraser)
                              colour = negative;
                    else
                              colour = positive;

                    for (int i = -10; i < 10; i++) {
                              for (int j = -10; j < 10; j++) {
                                        if ((i) * (i) + (j) * (j) < r * r) {
                                                  if (x + i >= 0 && y + j >= 0 && x + i < 150 && y + j < 150) {
                                                            painting.setRGB(x + i, y + j, colour);
                                                  }
                                        }
                              }
                    }

                    repaint();
                    e.consume();
          }

          public BufferedImage getImage() {

                    int w = getWidth();
                    int h = getHeight();
                    BufferedImage bi = new BufferedImage(w, h, BufferedImage.TYPE_INT_RGB);
                    Graphics2D g = bi.createGraphics();
                    paint(g);

                    return painting;
                    //return bi;
          }

          public void setImage(BufferedImage painting) {

                    int positive = Color.getHSBColor(0, 0, 100).getRGB();

                    //BufferedImage after = new BufferedImage(150,150,BufferedImage.TYPE_INT_RGB);

                    boolean twoValues = true;
                    int colours = 0;

                    int[] dataBuffInt = painting.getRGB(0, 0, 150, 150, null, 0, 150);

                    Color c1, c2, c3;
                    c1 = null;
                    c2 = null;

                    for (int i = 0; i < 150; i++) {
                              for (int j = 0; j < 150; j++) {
                                        if (colours == 0) {
                                                  c1 = new Color(dataBuffInt[i + j * 150]);
                                                  colours = 1;
                                        } else if (colours == 1) {
                                                  c2 = new Color(dataBuffInt[i + j * 150]);
                                                  if (!c2.equals(c1)) {
                                                            colours = 2;
                                                  }
                                        } else if (colours == 2) {
                                                  c3 = new Color(dataBuffInt[i + j * 150]);
                                                  if (!c3.equals(c1) && !c3.equals(c2)) {
                                                            colours = 3;
                                                  }
                                        }


                              }

                    }
                    if (colours > 2) {
                              twoValues = false;
                    }

                    BufferedImage fin;

                    if (twoValues) {
                              if (TwinMoons.loadingLogger)
                              System.out.println("Loading complete, image has two values");
                              fin = painting;
                    } else {
                              
                              if (TwinMoons.loadingLogger)
                              System.out.println("Loading complete, image doesn't have two values");
                              
                              Image scaled = painting.getScaledInstance(150, 150, Image.SCALE_SMOOTH);
                              BufferedImage buffered = new BufferedImage(150, 150, BufferedImage.TYPE_INT_RGB);
                              buffered.getGraphics().drawImage(scaled, 0, 0, null);
                              BufferedImage horizontal = new BufferedImage(150, 150, BufferedImage.TYPE_INT_RGB);
                              BufferedImage vertical = new BufferedImage(150, 150, BufferedImage.TYPE_INT_RGB);
                              dataBuffInt = buffered.getRGB(0, 0, 150, 150, null, 0, 150);

                              for (int i = 1; i < 149; i++) {
                                        for (int j = 1; j < 149; j++) {
                                                  c1 = new Color(dataBuffInt[i + j * 150]);
                                                  c2 = new Color(dataBuffInt[i - 1 + j * 150]);

                                                  boolean white = false;
                                                  int red = Math.abs(c1.getRed() - c2.getRed());

                                                  if (red > 55)
                                                            white = true;

                                                  int green = Math.abs(c1.getGreen() - c2.getGreen());

                                                  if (green > 55)
                                                            white = true;

                                                  int blue = Math.abs(c1.getBlue() - c2.getBlue());

                                                  if (blue > 55)
                                                            white = true;
                                                  
                                                  if (white)
                                                  vertical.setRGB(i, j, positive);
                                        }
                              }
                              
                                                            
                              for (int i = 1; i < 149; i++) {
                                        for (int j = 1; j < 149; j++) {
                                                  c1 = new Color(dataBuffInt[i + j * 150]);
                                                  c2 = new Color(dataBuffInt[i + (j-1) * 150]);
                                                    boolean white = false;
                                                  int red = Math.abs(c1.getRed() - c2.getRed());

                                                  if (red > 55)
                                                            white = true;

                                                  int green = Math.abs(c1.getGreen() - c2.getGreen());

                                                  if (green > 55)
                                                            white = true;

                                                  int blue = Math.abs(c1.getBlue() - c2.getBlue());

                                                  if (blue > 55)
                                                            white = true;

                                                  if (white)
                                                  vertical.setRGB(i, j, positive);
                                                            
                                        }
                              }


                              BufferedImage combined = new BufferedImage(150, 150, BufferedImage.TYPE_INT_RGB);
                              
                               for (int i = 0; i < 150; i++) {
                                        for (int j =0; j < 150; j++) {
                                                  
                                                  if (!new Color(vertical.getRGB(i, j)).equals(Color.black))
                                                  {
                                                            combined.setRGB(i, j,positive);
                                                  }
                                                  else if (!new Color(horizontal.getRGB(i, j)).equals(Color.black))
                                                  {
                                                             combined.setRGB(i, j,positive);
                                                  }
                                                  else
                                                            combined.setRGB(i,j,Color.BLACK.getRGB());
                                                  
                                                  
                                        }
                               }
                              
                              
                              fin = combined;


                    }

                    /*
                     for (int i=0;i<150;i++)
                     for (int j=0;j<150;j++)
                     {
                     c1 = new Color(dataBuffInt[i+j*150]);
                              
                     System.out.print(c1.getBlue());
                     if (c1.getBlue()+c1.getRed()+c1.getGreen() > 300)
                     after.setRGB(i, j, positive);
                     }*/

                    this.painting = fin;

                    repaint();
          }

          public void reset() {
                    painting = new BufferedImage(this.getWidth(), this.getHeight(), BufferedImage.TYPE_INT_RGB);
                    repaint();
          }
}
