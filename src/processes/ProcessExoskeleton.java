/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package processes;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Random;
import twinmoons.Varea;
import twinmoons.TwinMoons;

/**
 *
 * @author James
 */
public class ProcessExoskeleton extends Thread {

          // processes the altitude of pixels to get an endoskeleton       
          private int size;

          public ProcessExoskeleton() {
                    super();
                    this.size = Varea.size;
          }

          @Override
          public void run() {

                    if (TwinMoons.processLogger) {
                              System.out.println("Exoskeleton process initialized");
                    }

                    // *************************************
                    Varea.firstImpressionMutex.lock();
                    Varea.altitudeMutex.lock();
                    Varea.exoskeletonMutex.lock();

                    int[] angleBias = {0, 1, 2, 2, 2, 2, 2, 1, 0, -1, -2, -2, -2, -2, -2, -1};
                    int yy = 0;                    
                    int xx = 4;
                    
                    /*
                    for (int i = 0; i < size; i++) {
                              for (int j = 0; j < size; j++) {
                                        Varea.exoskeletonImage[i][j] = false;
                              }
                              
                    }*/// not sure about this code, i think we need to get all the pixels

                    for (int i = 0; i < size; i++) {
                              for (int j = 0; j < size; j++) {

                                        if (Varea.altitudeThresholdImage[i][j]) {
                                                  for (int angle = 0; angle < 16; angle++) {
                                                            int biasx = (angle + xx) % 16;
                                                            int biasy = (angle + yy) % 16;

                                                            int x = angleBias[biasx];
                                                            int y = angleBias[biasy];

                                                            int step = 1; // so that the line is a ray that radiates outwards                                                         

                                                            int posx = x * step + i;
                                                            int posy = y * step + j;

                                                            while (true) {
                                                                      if (posx > 0 && posx < Varea.size && posy > 0 && posy < Varea.size) {
                                                                                posx = x * step + i;
                                                                                posy = y * step + j;                                                                                
                                                                                
                                                                                          
                                                                                if (isEdge(posx, posy)) { 
                                                                                          
                                                                                          if (posx > 0 && posx < Varea.size && posy > 0 && posy < Varea.size)
                                                                                          Varea.exoskeletonImage[posx][posy] = true;
                                                                                          break;
                                                                                } else {
                                                                                          step++;
                                                                                }
                                                                      } else {
                                                                                break;
                                                                      }
                                                            }
                                                  }
                                        }


                              }
                    }
                    // calculate the altitude, and put it in the altitude bin
                    Varea.firstImpressionMutex.unlock();
                    Varea.altitudeMutex.unlock();
                    Varea.exoskeletonMutex.unlock();
                    // *************************************


                    BufferedImage thought = new BufferedImage(size, size, BufferedImage.TYPE_INT_RGB);

                    int positive = Color.getHSBColor(0, 0, 100).getRGB();
                    int negative = 0;

                    Varea.exoskeletonMutex.lock(); // so that image won't change by another
                    for (int i = 0; i < size; i++) {
                              for (int j = 0; j < size; j++) {

                                        if (Varea.exoskeletonImage[i][j]) {
                                                  thought.setRGB(i, j, positive);
                                        } else {
                                                  thought.setRGB(i, j, negative);
                                        }
                              }
                    }
                    Varea.exoskeletonMutex.unlock();

                    // draw freely
                    Varea.dreamMutex.lock();
                    Varea.dreams[2].drawMe(thought);
                    Varea.dreamMutex.unlock();
          }

          private boolean isEdge(int i, int j) {
                    
                    int counter = 0;                                       

                    for (int k1 = -1; k1 <= 1; k1++) {
                              for (int k2 = -1; k2 <= 1; k2++) {
                                        if (i+k1 > 0 && i+k1 < size && j+k2 > 0 && j+k2 < size) {
                                                  if (Varea.firstImpressionImage[i + k1][j + k2]) {
                                                            counter++;
                                                  }
                                        }
                              }
                    }                    

                    if (counter <= 2) {
                              return true;
                    }

                    return false;

          }
}