/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package twinmoons;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;
import structures.Line;
import structures.Mesh;
import sun.awt.Mutex;

/**
 *
 * @author James
 */
public class Varea {

          public static boolean reset = false;
          
          public static boolean[][] startingImage; // first common image          
          public static Mutex startingImageMutex = new Mutex();
          
          public static boolean[][] firstImpressionImage; // second common image
          public static Mutex firstImpressionMutex = new Mutex();
                    
          public static Mutex altitudeMutex = new Mutex();
          public static boolean[][] altitudeImage; // second common image         
          public static Mutex altitudeThresholdMutex = new Mutex();
          public static boolean[][] altitudeThresholdImage; //     
          public static int altitudeThreshold = 3; // when is a pixel certainly bound in filled object (thickness of objects)
          public static ArrayList<Integer> altitudeImageList = new ArrayList<>(); // contains randomly distributed pixel locations
          public static int altitudeDensity = 100; // 1/x , chance a pixel will show up in the exoskeleton processing
          
          public static Mutex exoskeletonMutex = new Mutex();
          public static boolean[][] exoskeletonImage; 
          
          //public static Mutex lineDetectionMutex = new Mutex();
          //public static boolean[][] lineDetectionImage;                 
          
          public static Mutex lineListMutex = new Mutex();
          public static ArrayList<Line> endolineList = new ArrayList<>();
          public static ArrayList<Line> exolineList = new ArrayList<>();
          
          public static Mutex improvedLineListMutex = new Mutex();
          public static ArrayList<Line> improvedEndolineList = new ArrayList<>();
          public static ArrayList<Line> improvedExolineList = new ArrayList<>();
          
          public static ArrayList<Mesh> meshes = new ArrayList<>();
          public static Mutex meshsesMutex = new Mutex();
          
          public static Dreamer[] dreams;
          public static Mutex dreamMutex = new Mutex();
          public static int size;

          public static void initialize(int kind, Dreamer[] dreams) {
                                        
                    Varea.dreams = dreams;
                    Varea.size = startingImage[0].length;
                    
                    firstImpressionImage= new boolean[size][size];    
                    altitudeImage= new boolean[size][size];        
                    altitudeThresholdImage= new boolean[size][size];      
                    exoskeletonImage= new boolean[size][size];        
                    //lineDetectionImage= new boolean[size][size];      
                    
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
