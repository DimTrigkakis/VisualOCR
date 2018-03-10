/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package processes;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Random;
import twinmoons.TwinMoons;
import twinmoons.Varea;

/**
 *
 * @author James
 */
public class ProcessAltitude extends Thread {
          
          // processes the altitude of pixels to get an endoskeleton       
          private int size;
          
          public ProcessAltitude()
          {
                    super();
                    this.size = Varea.size;
          }
          
          @Override
          public void run() {
                    
                    Random r = new Random();
                    if (TwinMoons.processLogger)
                    System.out.println("Altitude process initialized");            

                    // *************************************
                    Varea.firstImpressionMutex.lock();
                    Varea.altitudeThresholdMutex.lock();
                    
                    
                    int[][] earlyAltitudeImage = new int[Varea.size][Varea.size];
                             
                    for (int i = 0; i < size; i++) {
                              for (int j = 0; j < size; j++) {
                              
                                        if (Varea.firstImpressionImage[i][j])
                                                  earlyAltitudeImage[i][j] = closestFree(i,j);
                              
                                        if (earlyAltitudeImage[i][j] > Varea.altitudeThreshold)
                                        {
                                                  if (r.nextInt(Varea.altitudeDensity) ==0)
                                                            Varea.altitudeThresholdImage[i][j] = true;
                                                  else
                                                            Varea.altitudeThresholdImage[i][j] = false;
                                        }
                              }
                              
                    
                    }
                    // calculate the altitude, and put it in the altitude bin
                    Varea.firstImpressionMutex.unlock();
                    Varea.altitudeThresholdMutex.unlock();
                    // *************************************
                   

                    BufferedImage thought = new BufferedImage(size, size, BufferedImage.TYPE_INT_RGB);
                    
                    int positive = Color.getHSBColor(0, 0, 100).getRGB();                    
                    int negative = 0;
                    
                    Varea.altitudeMutex.lock();
                    for (int i = 0; i < size; i++) {
                              for (int j = 0; j < size; j++) {
                                        
                                                  boolean belongs = true;
                                                  int greater = 0;
                                                  
                                                  for (int k1=-1;k1<=1;k1++)
                                                  {
                                                  for (int k2=-1;k2<=1;k2++)
                                                  {
                                                            if (k1+i > 0 && k1+i < size && k2+j >0 && k2+j<size)
                                                            if (earlyAltitudeImage[i+k1][j+k2] > earlyAltitudeImage[i][j])
                                                                      greater++;
                                                            
                                                            if (greater >0)
                                                            {
                                                                      belongs = false;
                                                                      break;
                                                            }
                                                  }
                                                            if (greater >0)
                                                                      break;
                                                  }
                                                  
                                        
                                                  if (belongs && earlyAltitudeImage[i][j] > 0) {
                                                            Varea.altitudeImage[i][j] = true;
                                                            thought.setRGB(i, j, positive);
                                                  } else {
                                                            Varea.altitudeImage[i][j] = false;
                                                            thought.setRGB(i, j, negative);
                                                  }
                                        
                                        
                              }
                    }                
                    Varea.altitudeMutex.unlock();    
                    
                    // draw freely
                    Varea.dreamMutex.lock();                                
                    Varea.dreams[1].drawMe(thought);    
                    Varea.dreamMutex.unlock();                
          }
          
          private int closestFree(int i,int j)
          {
                    int radius = 1;
                    
                    
                    while(true)
                    {
                              boolean needToBreak = false;
                              for (int k1 = i-radius;k1<=i+radius;k1++)
                              for (int k2 = j-radius;k2<=j+radius;k2++)
                              {
                                        if (k1 > 0 && k1 < size && k2>0 && k2 < size)
                                        {                    
                                                  if (!Varea.firstImpressionImage[k1][k2])
                                                  needToBreak = true;
                                        }
                              }
                              
                              
                              
                              if (radius > 150 || needToBreak)
                                        break;
                              else
                                        radius++;
                    }
                    
                    return radius;
          }
}
