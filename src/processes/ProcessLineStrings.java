/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package processes;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Random;
import structures.Line;
import structures.Mesh;
import structures.Point;
import twinmoons.TwinMoons;
import twinmoons.Varea;

/**
 *
 * @author James
 */
public class ProcessLineStrings extends Thread{
           
          private int size;
          boolean[][] lineSegments;

          public ProcessLineStrings() {
                    super();
                    this.size = Varea.size;
          }

          
          @Override
          public void run() {

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
                    
                    
                    lineSegments = new boolean[size][size];
                    
                    Varea.meshsesMutex.lock();
                    ArrayList<Mesh> meshes = createMeshes(endolines);     
                    for (Mesh m : meshes)
                    {
                              System.out.println("Mesh "+meshes.indexOf(m) );
                    m.print();
                    }
                    
                    Varea.improvedLineListMutex.unlock();
                    
                    BufferedImage thought = new BufferedImage(size, size, BufferedImage.TYPE_INT_RGB);  
                    
                    float maxColour = meshes.size();
                    for (Mesh m : meshes)
                    {
                    float colour = meshes.indexOf(m);
                    for (Line l : m.getLines()) {                    
                              l.drawLine(lineSegments);
                    }
                     
                    for (int i = 0; i < size; i++) {
                              for (int j = 0; j < size; j++) {
                                        if (lineSegments[i][j])
                                                            thought.setRGB(i,size-1-j, Color.getHSBColor(colour/maxColour, 0.5f, 1).getRGB()); 
                                        lineSegments[i][j] = false;
                              }
                    } 
                    }
                    
                    
                    for (Line l : endolines) {                    
                              Point p1 = l.getX();                  
                              Point p2 = l.getY();
                              thought.setRGB(p1.getX(),size-1-p1.getY(), Color.getHSBColor(1f, 1f, 1).getRGB()); 
                              thought.setRGB(p2.getX(),size-1-p2.getY(), Color.getHSBColor(1f, 1f, 1).getRGB()); 
                    }
                    Varea.meshsesMutex.unlock();
                                        
                    // draw freely
                    Varea.dreamMutex.lock();
                    Varea.dreams[12].drawMe(thought);
                    Varea.dreamMutex.unlock();
          }
          
          public ArrayList<Mesh> createMeshes(ArrayList<Line> lines)
          {
                    ArrayList<Line> lineQueue = (ArrayList<Line>) lines.clone();
                    ArrayList<Mesh> meshes = new ArrayList<>();
                    
                    for (int k = 0;k<lineQueue.size();k=0)
                    {
                              Mesh m = new Mesh();                               
                              System.out.println("linesOFPoin"+linesOfPoint(lineQueue.get(k),lineQueue).size());
                              recursiveMesh(m,linesOfPoint(lineQueue.get(k),lineQueue),lineQueue); // this removes a line from lineQueue which iterates, hopefully not creatin issues                                     
                              meshes.add(m);
                              
                              System.out.println("once");
                    }
                    
                    return meshes;
          }
          
          public void recursiveMesh(Mesh m, ArrayList<Line> linesToAdd,ArrayList<Line> lineQueue)
          {                    
                    for (int k=0;k<linesToAdd.size();k++)
                    {
                              Line l = linesToAdd.get(k);
                              lineQueue.remove(l);
                              m.addLine(l);
                              
                              System.out.println("lines op"+linesOfPoint(l,lineQueue).size());
                              if (lineQueue.isEmpty())
                                        break;
                              recursiveMesh(m,linesOfPoint(l,lineQueue),lineQueue);
                    }                  
          }
          
          public ArrayList<Line> linesOfPoint(Line k,ArrayList<Line> lines)
          {
                    ArrayList<Line> linesOfPoint = new ArrayList<>();
                              
                    Point x = k.getX();                             
                    Point y = k.getY();
                    
                    for (Line l  : lines)
                    {
                              if (l.getX().equals(x) || l.getY().equals(x) || l.getX().equals(y) || l.getY().equals(y))
                              linesOfPoint.add(l);
                    }
                    
                    return linesOfPoint;
          }
}
