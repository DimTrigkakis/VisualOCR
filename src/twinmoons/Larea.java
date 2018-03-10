/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package twinmoons;

import sun.awt.Mutex;

/**
 *
 * @author James
 */
public class Larea {
          
          public static boolean reset = false;
          public static boolean[][] startingImage;    
          
          public static Dreamer[] dreams;
          public static Mutex dreamMutex = new Mutex();
          public static int size;          
          
          public static void initialize(int kind, Dreamer[] dreams) {
                                        
                    Varea.dreams = dreams;
                    Varea.size = startingImage[0].length;
                    
                    //firstImpressionImage= new boolean[size][size];      
                    
                    reset = false;                    
          }
          
          public static void resetPressed()
          {
                    reset = true;
          }
          
          public static boolean valid(int i, int j)
          {
                    if (i < size && j < size && i > 0 && j > 0)
                              return true;
                    
                    return false;
          }
          
}
