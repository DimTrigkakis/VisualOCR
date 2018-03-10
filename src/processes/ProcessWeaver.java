/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package processes;

import cortical.Node;
import java.awt.Color;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Random;
import structures.Line;
import structures.Point;
import twinmoons.TwinMoons;
import twinmoons.Varea;

/**
 *
 * @author James
 */
public class ProcessWeaver extends Thread{          
          
          private int size;

          public ProcessWeaver() {
                    super();
                    this.size = Varea.size;
          }

          
          @Override
          public void run() {
                    
                    ArrayList<Node> nodes = new ArrayList<>();

                    //lineList.clear();
                    Random r = new Random();

                    if (TwinMoons.processLogger) {
                              System.out.println("Line String process initialized");
                    }
                    
                    Varea.improvedLineListMutex.lock();                    
                    
                    ArrayList<Line> endolines = new ArrayList<>();
                    ArrayList<Line> exolines = new ArrayList<>();
                    
                    for (Line l : Varea.improvedEndolineList)
                              endolines.add(l);
                    
                    for (Line l : Varea.improvedExolineList)
                              exolines.add(l);
                    
                    //System.out.println("New nodes");
                    
                    for (Line l : endolines)
                    {
                              //nodes.add(new Node("line"));
                              //System.out.println("Node line");
                    }         
                    
                    Varea.improvedLineListMutex.unlock();      
          }
          
}
