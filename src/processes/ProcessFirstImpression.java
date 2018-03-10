/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package processes;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;
import twinmoons.TwinMoons;
import twinmoons.Varea;

/**
 *
 * @author James
 */
public class ProcessFirstImpression extends Thread {
          // needed for process Random Pixels
          private int size;
          
          public ProcessFirstImpression()
          {
                    super();
                    this.size = Varea.size;
          }
          @Override
          public void run() {
                    
                    if (TwinMoons.processLogger)
                    System.out.println("Random Pixels process initialized");
                    Random r = new Random();                    

                    // *************************************       
                    Varea.firstImpressionMutex.lock();                    
                    Varea.firstImpressionImage = Varea.startingImage.clone();                    
                    Varea.firstImpressionMutex.unlock();
                    // *************************************
                    
                    BufferedImage thought = new BufferedImage(size, size, BufferedImage.TYPE_INT_RGB);                    
                    
                    int positive = Color.getHSBColor(0, 0, 100).getRGB();                    
                    int negative = 0;                    
                    
                    Varea.firstImpressionMutex.lock();
                    
                    for (int i = 0; i < size; i++) {
                              for (int j = 0; j < size; j++) { 
                                                  if (Varea.firstImpressionImage[i][j]) {
                                                            thought.setRGB(i, j, positive);
                                                  } else {
                                                            thought.setRGB(i, j, negative);
                                                  }
                                        
                              }
                    }
                    
                    Varea.firstImpressionMutex.unlock();
                    
                    // draw freely
                    Varea.dreamMutex.lock();
                    Varea.dreams[0].drawMe(thought);
                    Varea.dreamMutex.unlock();

          }
}
