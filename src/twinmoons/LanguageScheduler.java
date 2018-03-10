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
public class LanguageScheduler extends Thread {

          boolean[][] startingImage;
          Dreamer[] dreams;
          
          LanguageScheduler(Dreamer[] dreams) {
                    
                    this.dreams = dreams;
                    
                    Larea.initialize(1,dreams);
          }

          @Override
          public void run() {   
                              
                    while(true)
                    {
                              if (Larea.reset)
                                        break;
                              
                              this.startingImage = Larea.startingImage;
                              
                              try {
                                        Thread.sleep(100);
                              } catch (InterruptedException ex) {
                                        Logger.getLogger(LanguageScheduler.class.getName()).log(Level.SEVERE, null, ex);
                              }
                              
                    }
                    
          }
          
          
          
}
