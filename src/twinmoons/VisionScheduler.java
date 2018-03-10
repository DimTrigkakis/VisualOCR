/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package twinmoons;
import java.util.Collections;
import java.util.logging.Level;
import java.util.logging.Logger;
import processes.*;

/**
 *
 * @author James
 */
public class VisionScheduler extends Thread {

          boolean[][] startingImage;
          Dreamer[] dreams;
          
          VisionScheduler(Dreamer[] dreams) {
                    
                    this.dreams = dreams;
                    
                    Varea.initialize(1,dreams);
          }

          @Override
          public void run() {   
                              
                    while(true)
                    {
                              if (Varea.reset)
                                        break;
                                      
                              this.startingImage = Varea.startingImage;
                              new ProcessFirstImpression().start();
                              new ProcessAltitude().start();
                              new ProcessExoskeleton().start();
                              new ProcessLineDetection().start();
                              new ProcessClosestLineConnector().start();
                              new ProcessJunction().start();                              
                              new ProcessLineStrings().start();
                              new ProcessWeaver().start();
                              
                              try {
                                        Thread.sleep(100);
                              } catch (InterruptedException ex) {
                                        Logger.getLogger(VisionScheduler.class.getName()).log(Level.SEVERE, null, ex);
                              }
                              
                    }
                    
          }
          
          
          
}
