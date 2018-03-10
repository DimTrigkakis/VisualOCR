package processes;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
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


public class ProcessClosestLineConnector extends Thread {

          // processes the altitude of pixels to get an endoskeleton       
          private int size;
          boolean[][] lineSegments;
          
          public ProcessClosestLineConnector() {
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
                                        
                    ArrayList<Line> endolines = new ArrayList<>();
                    ArrayList<Line> exolines = new ArrayList<>();
                    
                    for (Line l : Varea.endolineList)
                    {
                              endolines.add(l);
                    } 
                    
                    for (Line l : Varea.exolineList)
                    {
                              exolines.add(l);
                    }                    
                    
                    lineSegments = new boolean[size][size];
                    
                    for (Line l : endolines)
                    {
                              l.reset();
                    }
                                                            
                    for (int distOff = 3;distOff <= 9;distOff+=3)
                    {
                            connectLines(endolines,distOff,2,false);
                            removeTriangularLines(endolines);
                            removeSingularLines(endolines);  
                            removeLines(endolines);
                    }
                    
                    //simplify(endolines);
                                      
                    for (int distOff = 1;distOff <=3;distOff++)
                    {
                              connectLines(exolines,distOff,2,false); 
                              for (Line l : exolines)
                              {
                                        l.reset();
                              }
                              
                              removeLines(exolines);
                              removeTriangularLines(exolines);
                              removeSingularLines(exolines);  
                    }
                    
                    for (Line l : exolines)
                    {
                              l.reset();
                    }
                    
                    for (int distOff = 1;distOff <=3;distOff++)
                    {
                              connectLines(exolines,distOff,2,false);
                              removeLines(exolines);
                    }
                    
                    //simplify(exolines);
                    Varea.improvedLineListMutex.lock();
                    Varea.improvedEndolineList = (ArrayList<Line>)endolines.clone();
                    Varea.improvedExolineList = (ArrayList<Line>)exolines.clone();                    
                    Varea.improvedLineListMutex.unlock();
                    
                    Varea.lineListMutex.unlock();
                    
                    Varea.improvedLineListMutex.lock();
                    Varea.improvedEndolineList = endolines;
                    Varea.improvedExolineList = exolines;
                    Varea.improvedLineListMutex.unlock();
                    
                    // *************************************

                    BufferedImage thought = new BufferedImage(size, size, BufferedImage.TYPE_INT_RGB);      
                    BufferedImage thought2 = new BufferedImage(size, size, BufferedImage.TYPE_INT_RGB);                     

                    for (Line l : endolines) {                    
                              l.drawLine(lineSegments);
                    }
                     
                    for (int i = 0; i < size; i++) {
                              for (int j = 0; j < size; j++) {
                                        if (lineSegments[i][j])
                                                            thought.setRGB(i,size-1-j, Color.getHSBColor(0.2f, 0.5f, 1).getRGB()); 
                                        
                                        lineSegments[i][j] = false;
                              }
                    } 
                    
                    for (Line l : endolines) {                    
                              Point p1 = l.getX();                  
                              Point p2 = l.getY();
                              thought.setRGB(p1.getX(),size-1-p1.getY(), Color.getHSBColor(1f, 1f, 1).getRGB()); 
                              thought.setRGB(p2.getX(),size-1-p2.getY(), Color.getHSBColor(1f, 1f, 1).getRGB()); 
                    }
                    
                    for (Line l : exolines) {                    
                              l.drawLine(lineSegments);
                    }
                     
                    for (int i = 0; i < size; i++) {
                              for (int j = 0; j < size; j++) {
                                        if (lineSegments[i][j])
                                                            thought2.setRGB(i,size-1-j, Color.getHSBColor(0.2f, 0.5f, 1).getRGB()); 
                              }
                    } 
                    
                    for (Line l : exolines) {                    
                              Point p1 = l.getX();                  
                              Point p2 = l.getY();
                              thought2.setRGB(p1.getX(),size-1-p1.getY(), Color.getHSBColor(1f, 1f, 1).getRGB()); 
                              thought2.setRGB(p2.getX(),size-1-p2.getY(), Color.getHSBColor(1f, 1f, 1).getRGB()); 
                    }
                                        
                    // draw freely
                    Varea.dreamMutex.lock();
                    Varea.dreams[9].drawMe(thought);
                    Varea.dreams[10].drawMe(thought2);
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
                                   if (k1 != k2 && lines.get(k1).isTheSameAs(lines.get(k2)))
                                   {
                                                       lines.remove(k2);
                                                       k2--;         
                                                       if (k1>k2)
                                                                 k1--;
                                   }
                         }
          }
          
          private void simplify(ArrayList<Line> lines)
          {
                    
                         for (int k1 = 0;k1<lines.size();k1++)
                         for (int k2 = 0;k2<lines.size();k2++)
                         {
                                   
                                   Line l1=  lines.get(k1);
                                   Line l2=  lines.get(k2);
                                   if (k1 != k2 && lines.get(k1).compareSlope(lines.get(k2)))
                                   {
                                             if (l1.getY().equals(l2.getX()))
                                             {
                                                       Line l = new Line(l1.getX(),l2.getY());
                                                       lines.add(l);
                                                       
                                                       lines.remove(l2);
                                                       k2--;
                                                       if (k1>k2)
                                                            k1--;
                                                       lines.remove(l1);
                                                       if (k2>k1)
                                                                 k2--;
                                                       
                                             }
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
                                                       if (k1 > k3)
                                                                 k1--;     
                                                         if (k2>k3)
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