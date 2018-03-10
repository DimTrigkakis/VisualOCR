/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package twinmoons;

import java.awt.Color;
import java.awt.image.BufferedImage;

/**
 *
 * @author James
 */
public class Moons {
          
          private static boolean noVisionSchedule = true;
          private static boolean noLanguageSchedule = true;

          public static void processImage(BufferedImage a, BufferedImage b, Dreamer[] dreams) {

                    BufferedImage[] thoughts = new BufferedImage[dreams.length];

                    for (int i = 0; i < thoughts.length; i++) {
                              thoughts[i] = new BufferedImage(b.getWidth(), b.getHeight(), BufferedImage.TYPE_INT_RGB);
                    }

                    boolean[][] A = new boolean[a.getWidth()][a.getHeight()];

                    for (int i = 0; i < a.getWidth(); i++) {
                              for (int j = 0; j < a.getHeight(); j++) {

                                        if (a.getRGB(i, j) == Color.getHSBColor(0, 0, 100).getRGB()) {
                                                  A[i][j] = true;
                                        } else {
                                                  A[i][j] = false;
                                        }

                              }
                    }

                    boolean[][] B = new boolean[b.getWidth()][b.getHeight()];

                    for (int i = 0; i < b.getWidth(); i++) {
                              for (int j = 0; j < b.getHeight(); j++) {

                                        if (b.getRGB(i, j) == Color.getHSBColor(0, 0, 100).getRGB()) {
                                                  B[i][j] = true;
                                        } else {
                                                  B[i][j] = false;
                                        }

                              }
                    }
                                        
                    if (Moons.noVisionSchedule || Varea.reset)
                    {  
                              Varea.reset = false;
                              if (TwinMoons.selectedImage == 0)
                                        Varea.startingImage = A;    
                              else if (TwinMoons.selectedImage == 1)
                                        Varea.startingImage = B;
                              
                              Moons.noVisionSchedule = false;
                              VisionScheduler scheduler = new VisionScheduler(dreams);
                              scheduler.start();            
                    }             
          }
         
          public static void trainImage(BufferedImage a, Dreamer[] dreams)
          {
                    boolean[][] A = new boolean[a.getWidth()][a.getHeight()];

                    for (int i = 0; i < a.getWidth(); i++) {
                              for (int j = 0; j < a.getHeight(); j++) {

                                        if (a.getRGB(i, j) == Color.getHSBColor(0, 0, 100).getRGB()) {
                                                  A[i][j] = true;
                                        } else {
                                                  A[i][j] = false;
                                        }

                              }
                    }
                    
                    if (Moons.noLanguageSchedule || Larea.reset)
                    {  
                              Larea.reset = false;
                              Larea.startingImage = A;    
                              
                              Moons.noLanguageSchedule = false;
                              LanguageScheduler scheduler = new LanguageScheduler(dreams);
                              scheduler.start();            
                    }    
          }
          
}
