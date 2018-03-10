package processes;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;
import structures.Line;
import structures.Point;
import twinmoons.TwinMoons;
import twinmoons.Varea;

/*/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
/*
package processes;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Random;
import structures.Line;
import structures.Point;
import twinmoons.Varea;
import twinmoons.TwinMoons;

/**
 *
 * @author James
 */


public class ProcessJunction extends Thread {

          // processes the altitude of pixels to get an endoskeleton       
          private int size;
          boolean[][] lineSegments;
          
          public ProcessJunction() {
                    super();
                    this.size = Varea.size;
          }

          @Override
          public void run() {
                    
                    //lineList.clear();
                    Random r = new Random();

                    if (TwinMoons.processLogger) {
                              System.out.println("Line Connector process initialized");
                    }

                    // *************************************
                    Varea.lineListMutex.lock();             
                    Varea.improvedLineListMutex.lock();
                    
                    ArrayList<Point> junctionsList = new ArrayList<>();
                    Set<Point> junctions =  new HashSet<>();
                    
                    
                    for (Line k : Varea.improvedEndolineList)
                    {
                              for (Line l : Varea.improvedEndolineList)
                              {    
                                        if (!k.isTheSameAs(l))
                                        {
                                        if (k.getX().equals(l.getX()))
                                        junctions.add(k.getX());    
                                        else if (k.getX().equals(l.getY()))
                                        junctions.add(k.getX());         
                                        else if (k.getY().equals(l.getX()))
                                        junctions.add(k.getY());    
                                        else if (k.getY().equals(l.getY()))
                                        junctions.add(k.getY());       
                                        }         
                              }
                    }                      
                                                  
                    Varea.lineListMutex.unlock();
                    Varea.improvedLineListMutex.unlock();
                    // *************************************

                    BufferedImage thought = new BufferedImage(size, size, BufferedImage.TYPE_INT_RGB);                 

                    
                     
                    for (Point junction : junctions) {    
                              thought.setRGB(junction.getX(),size-1-junction.getY(), Color.getHSBColor(1f, 1f, 1).getRGB()); 
                    }
                    
                                   
                    // draw freely
                    Varea.dreamMutex.lock();
                    Varea.dreams[11].drawMe(thought);
                    Varea.dreamMutex.unlock();
          }
          
          private void connectLines(ArrayList<Line> lines,int distOff,int contigency,boolean linearBonus) 
          {
                         for (Line l1 : lines)
                         for (Line l2 : lines)
                              {
                                         l1.connect(l2,distOff,contigency,linearBonus);
                              }    
          }
          
          private void removeLines(ArrayList<Line> lines)
          {
                         for (int k1 = 0;k1<lines.size();k1++)
                         for (int k2 = 0;k2<lines.size();k2++)
                         {
                                   if (k1 != k2)
                                   if (lines.get(k1).isTheSameAs(lines.get(k2)))
                                   {
                                             lines.remove(k2);
                                             if (k1 >= k2)
                                                       k1--;
                                             k2--;
                                   }
                         }
                         
          }          
          
          private void removeSingularLines(ArrayList<Line> lines)
          {                        
                    for (int k = 0;k<lines.size();k++)
                    {
                              if (lines.get(k).length() < 3)
                              {
                                        lines.remove(k);
                                        k--;
                              }
                              
                    }
          }
          
          private void removeTriangularLines(ArrayList<Line> lines)
          {
                         for (int k1 = 0;k1<lines.size();k1++)
                         for (int k2 = 0;k2<lines.size();k2++)
                         {
                                   if (lines.get(k2).getY().equals(lines.get(k1).getX()))
                                             
                                   for (int k3 = 0;k3<lines.size();k3++)
                                   if (k1 != k2 && k2!= k3 && k1 != k3)
                                   {
                                              Point first = lines.get(k2).getX();
                                              Point last = lines.get(k1).getY();
                                              if (lines.get(k3).isTheSameAs(new Line(first,last)))
                                              {
                                                        lines.remove(k3);                                                        
                                                        if (k1 >= k3)
                                                                  k1--;     
                                                        if (k2>=k3)
                                                                  k2--;
                                                        k3--;                                                        
                                                                                                               
                                                        
                                                        
                                              }                                                        
                                   }
                                   
                                   
                                  
                                   
                         }
          }  
          /*
          private boolean isVertex(int i,int j)
          {
                    for (int k=0;k<Varea.lineList.size();k++)
                    {
                              Line l = Varea.lineList.get(k);
                              Point p1 = l.getX();
                              Point p2 = l.getY();
                              
                              if (p1.equals(i,j))
                                        return true;
                              if (p2.equals(i,j))
                                        return true;
                              
                    }
                    
                    return false;
          }
          

          private Line closestSame(int i,int j)
          {
                    int radius = 2;      
                    Random r = new Random();
                    int posx,posy;
                    posx = 0;
                    posy = 0;
                    
                    while(true)
                    {
                              boolean needToBreak = false;
                              
                              int k1 = r.nextInt(radius)+2;
                              int k2 = r.nextInt(radius)+2;
                              
                             if (k1+i > 0 && k1+i < size && k2+j>0 && k2+j < size)
                                        {                    
                                                  if (Varea.lineDetectionImage[k1+i][k2+j])
                                                  {
                                                            posx = k1+i;
                                                            posy = k2+j;
                                                            needToBreak = true;
                                                  }
                                        }
                              
                              
                              if (radius > 150 || needToBreak)
                                        break;
                              else
                                        radius++;
                    }
                    
                    Point pfinish= new Point(posx,posy);
                    Point pstart = new Point(i,j);
                    Line l = new Line(pstart,pfinish);
                                        
                    return l;
          }*/
}