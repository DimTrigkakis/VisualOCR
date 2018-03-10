/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package processes;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Random;
import structures.Line;
import structures.Point;
import structures.Rectangle;
import twinmoons.Varea;
import twinmoons.TwinMoons;

/**
 *
 * @author James
 */
public class ProcessLineDetection extends Thread {

          // processes the altitude of pixels to get an endoskeleton       
          private int size;
          boolean[][] lineSegments;
          int[][] pixelDirection;
          double[][] gradients;
          boolean[][] trueGradients;

          public ProcessLineDetection() {
                    super();
                    this.size = Varea.size;
          }

          @Override
          public void run() {

                    //lineList.clear();
                    Random r = new Random();

                    if (TwinMoons.processLogger) {
                              System.out.println("Line Detection process initialized");
                    }
                    
                    BufferedImage thought = new BufferedImage(size, size, BufferedImage.TYPE_INT_RGB);
                    BufferedImage thought2 = new BufferedImage(size, size, BufferedImage.TYPE_INT_RGB);
                    BufferedImage thought3 = new BufferedImage(size, size, BufferedImage.TYPE_INT_RGB);
                    BufferedImage thought4 = new BufferedImage(size, size, BufferedImage.TYPE_INT_RGB);
                    BufferedImage thought5 = new BufferedImage(size, size, BufferedImage.TYPE_INT_RGB);
                                        
                    Varea.altitudeMutex.lock();
                    Varea.exoskeletonMutex.lock();
                    Varea.lineListMutex.lock();
                    
                    Varea.endolineList = new ArrayList<>();
                    Varea.exolineList = new ArrayList<>();
                    lineSegments = new boolean[size][size];
                    gradients = new double[size][size];
                    trueGradients = new boolean[size][size];    
                    
                    blur(Varea.altitudeImage);
                    gradient();
                    gradientToPoints();
                    pointsGrow();
                    calculateRectangles();       
                    calculateLines(0);
                    
                    if (regions.size() > 0)
                    {
                              for (int k=0;k<regions.size();k++)
                                        for (Point a :regions.get(k))
                                                  {
                                                            thought.setRGB(a.getX(),a.getY(), Color.getHSBColor((k/10f), 1, 1).getRGB());
                                                  }
                    }                    
                    
                    blur(Varea.firstImpressionImage);
                    gradient();
                    gradientToPoints();
                    pointsGrow();
                    calculateRectangles();       
                    calculateLines(1);       
                    
                    Varea.exoskeletonMutex.unlock();
                    Varea.altitudeMutex.unlock();
                    
                    for (Line l : Varea.endolineList) {  
                              l.drawLine(lineSegments);
                    }

                    for (int i = 0; i < size; i++) 
                              for (int j = 0; j < size; j++) {
                                        if (lineSegments[i][j])
                                        {
                                                            thought4.setRGB(i,size-1-j, Color.getHSBColor(0.2f, 0.5f, 1).getRGB()); 
                                                            lineSegments[i][j] = false;
                                        }
                    } 
                    
                    for (Line l : Varea.exolineList) {                    
                              l.drawLine(lineSegments);
                    }  
                     
                    for (int i = 0; i < size; i++) {
                              for (int j = 0; j < size; j++) {
                                        if (lineSegments[i][j])
                                                            thought5.setRGB(i,size-1-j, Color.getHSBColor(0.2f, 0.5f, 1).getRGB()); 
                              }
                    } 
                    
                    for (Line l : Varea.endolineList) {                    
                              Point p1 = l.getX();                  
                              Point p2 = l.getY();
                              thought4.setRGB(p1.getX(),size-1-p1.getY(), Color.getHSBColor(1f, 1f, 1).getRGB()); 
                              thought4.setRGB(p2.getX(),size-1-p2.getY(), Color.getHSBColor(1f, 1f, 1).getRGB()); 
                    }
                    
                    for (Line l : Varea.exolineList) {                    
                              Point p1 = l.getX();                  
                              Point p2 = l.getY();
                              thought5.setRGB(p1.getX(),size-1-p1.getY(), Color.getHSBColor(1f, 1f, 1).getRGB()); 
                              thought5.setRGB(p2.getX(),size-1-p2.getY(), Color.getHSBColor(1f, 1f, 1).getRGB()); 
                    }
                    
                              
                    
                    
                                
                    
                   if (regions.size() > 0)
                    {
                              for (int k=0;k<regions.size();k++)
                                        for (Point a :regions.get(k))
                                                  {
                                                            thought2.setRGB(a.getX(),a.getY(), Color.getHSBColor((k/10f), 1, 1).getRGB());
                                                  }
                    }
                    
                    if (rectangles.size() > 0)
                    {
                                        for (Rectangle rect :rectangles)
                                        {
                                                            int a = rect.getPosition().getX();
                                                            int b = rect.getPosition().getY();
                                                            thought3.setRGB(a,b, Color.getHSBColor((float)(rect.getAngle()/Math.PI), 1, 1).getRGB());
                                        }
                    }
                    
                    Varea.lineListMutex.unlock();        


                    // draw freely
                    Varea.dreamMutex.lock();
                    Varea.dreams[3].drawMe(thought);
                    Varea.dreams[4].drawMe(thought2);
                    Varea.dreams[5].drawMe(thought3);
                    Varea.dreams[6].drawMe(thought4);
                    Varea.dreams[7].drawMe(thought5);
                    Varea.dreamMutex.unlock();
          }   
          
          private void calculateLines(int index)
          {
                    for (Rectangle r : rectangles)
                    {
                              Point a1 = r.getPosition();
                              Point a2 = r.getPosition();
                                                                            
                              a1.renormalizeY(size);         
                              a2.renormalizeY(size);          
                              int length = Math.max(r.getWidth(),r.getLength());
                              a1.addPoint(new Point((int)(Math.cos(r.getAngle())*length/2f),(int)(Math.sin(r.getAngle())*length/2f)));                              
                              a2.addPoint(new Point((int)(-Math.cos(r.getAngle())*length/2f),(int)(-Math.sin(r.getAngle())*length/2f)));                              
                              a1.bind();
                              a2.bind();
                              Line l = new Line(a1,a2);
                              if (index == 0)
                                        Varea.endolineList.add(l);
                              else
                                        Varea.exolineList.add(l);
                              
                    }
          }
          
          ArrayList<Rectangle> rectangles;
          private void calculateRectangles() 
          {                    
                    rectangles = new ArrayList<>();
                    for (ArrayList<Point> region : regions)
                    {
                              float angle = 0;
                              
                              for (Point p : region)
                              {
                                        angle += gradients[p.getX()][p.getY()];
                              }
                              
                              angle /= region.size();     
                              
                              while (angle > Math.PI)
                              {
                                        angle-= Math.PI;
                              }
                                     
                              while (angle < 0)
                              {
                                        angle+= Math.PI;
                              }
                              
                              Rectangle r = new Rectangle(region,angle);
                              r.setSize(region);
                              
                              rectangles.add(r);                              
                    }
          }
          
          float[][] blur = null;
          private void blur(boolean[][] map)
          {                   
                  blur = new float[size][size];
                  
                  for (int i = 0; i < size; i++) 
                  for (int j = 0; j < size; j++)
                  {
                            
                            if (i == 0 || i == size-1 || j == 0 || j == size-1)
                            {
                                      if (map[i][j])
                                                blur[i][j] = 0.5f;
                                      else
                                                blur[i][j] = 0;    
                                      
                                      if (map[i][j])
                                                blur[i][j] = 0.5f;
                                      else
                                                blur[i][j] = 0;
                            }
                            else
                            {
                            
                            int blurCount = 0;
                            if (map[i][j])
                                      blurCount++;
                            if (map[i-1][j])
                                      blurCount++;
                            if (map[i+1][j])
                                      blurCount++;
                            if (map[i][j-1])
                                      blurCount++;
                            if (map[i][j+1])
                                      blurCount++;                           
                            
                              blur[i][j] = blurCount/5f;
                            }
                            
                           
                    
                  }
                      
          }
          
          char[][] usablePoints; 
          private void gradientToPoints()
          {
                  usablePoints = new char[size][size];
                    
                  for (int i = 0; i < size; i++) 
                  for (int j = 0; j < size; j++) {   
                            
                            if (trueGradients[i][j])
                                      usablePoints[i][j] = 2;                      
                            else
                                      usablePoints[i][j] = 0;
                  }
          }
          
          ArrayList<ArrayList<Point>> regions = new ArrayList<>();
          char[][] usablePointsBackup; 
          private void pointsGrow()
          {
                  usablePointsBackup= new char[size][size];
                  regions.clear();
                    
                  for (int i = 0; i < size; i++) 
                  for (int j = 0; j < size; j++)
                  {
                            if (usablePoints[i][j] == 2)
                            {                                                                    
                                        ArrayList<Point> region = new ArrayList<>();
                                        regions.add(region);                                                     
                                        
                                        usablePointsBackup= usablePoints.clone();
                                      
                                        recursiveGrowth(region,i,j,gradients[i][j]);
                                        if (region.size() <= 3)
                                        {
                                                  usablePoints = usablePointsBackup.clone();
                                                  regions.remove(region);
                                        }
                            }
                  }
                                    
          }
          
          private void recursiveGrowth(ArrayList<Point> region,int i, int j,double angle)
          {                                                
                    double diff = gradients[i][j] - angle;
                    
                    //System.out.println(gradients[i][j]+" "+angle);
                    
                    if (usablePoints[i][j] == 2 && Math.abs(diff) < 0.4f)         
                    {
                              usablePoints[i][j] = 1;                                        
                              region.add(new Point(i,j));    
                              
                              for (int k1 = -1;k1<=1;k1++)
                              for (int k2 =-1;k2<=1;k2++)
                              {
                                        if (!(k1==0 &&  k2 == 0) && Varea.valid(i+k1,j+k2))
                                                  recursiveGrowth(region,i+k1,j+k2,angle);
                                        }                              
                    }                                      
          }
          
          float[][] dx;
          float[][] dy;
          
          private void gradient()
          {
                   dx= new float[size][size];
                   dy= new float[size][size];
                   trueGradients = new boolean[size][size];
                   gradients = new double[size][size];
                     
                  for (int i = 1; i < size-1; i++) 
                  for (int j = 1; j < size-1; j++) {                            
                            
                            dx[i][j] = blur[i+1][j] - blur[i-1][j];
                            dy[i][j] = blur[i][j+1] - blur[i][j-1];
                            
                            
                                        
                            if (dx[i][j] == 0 && dy[i][j] == 0)
                                      trueGradients[i][j] = false;
                            else
                                      trueGradients[i][j] = true;
                            gradients[i][j] = Math.atan2(dx[i][j], dy[i][j]);
                                                                          
                  }
                    
          }
          
          /*
          private void absorbLines()
          {                   
                    for (int k1 = 0; k1 < Varea.lineList.size(); k1++) 
                              for (int k2 = 0; k2 < Varea.lineList.size(); k2++) {                                        
                                        
                                        Line l1 = Varea.lineList.get(k1);
                                        Line l2 = Varea.lineList.get(k2);     
                                        
                                         if (l1.compareSlope(l2) && (k1 != k2)) 
                                         {
                                        if (l1.lineInside(l2))                                                   
                                        {
                                                            if (k1 > k2)
                                                                      k1--;
                                                            Varea.lineList.remove(k2--);   
                                        }                                        
                                        else if (l1.pointInside(l2))
                                        {
                                                  if (l1.length() < l2.length())
                                                  {
                                                            if (k2 > k1)
                                                                      k2--;
                                                            Varea.lineList.remove(k1--);
                                                  }
                                                  else
                                                  {                                                            
                                                            if (k1 > k2)
                                                                      k1--;
                                                            Varea.lineList.remove(k2--); 
                                                  }
                                        }    
                                         
                                        if (k1 < 0 )
                                        k1 = 0;   
                                        if (k2 < 0 )
                                        k2 = 0;    
                              }       
                              }
                    
          }

          private void connectSimilarLines() {

                    for (int k1 = 0; k1 < Varea.lineList.size(); k1++) {
                              for (int k2 = 0; k2 < Varea.lineList.size(); k2++) {
                                        Line l1 = Varea.lineList.get(k1);
                                        Line l2 = Varea.lineList.get(k2);

                                        // we have two lines, and need to decide whether they belong together
                                        if (k1 != k2) {
                                                  if (l1.compareSlope(l2)) {
                                                            Point p1 = l1.getX();
                                                            Point p2 = l1.getY();

                                                            if (p1.distance(l2.getY()) < 5) {
                                                                      Point aa = l2.getX();
                                                                      Point bb = l1.getY();

                                                                      Line l = new Line(aa, bb);
                                                                      
                                                                      Point values = Merge(l, k1, k2);
                                                                      k1 = values.getX();
                                                                      k2 = values.getY();
                                                                      
                                                            } 

                                                  }
                                        }


                              }
                    }

          }

          private Point Merge(Line l, int k1, int k2) {
                    
                    if (k1 < k2) {
                              Varea.lineList.add(l);
                              Varea.lineList.remove(k1);                              
                                        k1--;
                                        k2--;
                              if (k2 < 0 )
                                        k2 = 0;
                              Varea.lineList.remove(k2);
                                        k2--;
                              
                              if (k1 < 0 )
                                        k1 = 0;
                              if (k2 < 0)
                                        k2 = 0;
                    }

                    if (k1 > k2) {
                              Varea.lineList.add(l);
                              Varea.lineList.remove(k2);
                                        k2--;
                                        k1--;
                              if (k1 < 0 )
                                        k1 = 0;
                              Varea.lineList.remove(k1);
                                        k1--;
                                        
                              if (k1 < 0 )
                                        k1 = 0;
                              if (k2 < 0)
                                        k2 = 0;
                    }
                    
                    return new Point(k1,k2);
          }
          
          
          float strengthParameter = 0.5f;

          private void updateLineStrength() {
                    Random r = new Random();

                    for (int k = 0; k < Varea.lineList.size(); k++) {
                              Line l = Varea.lineList.get(k);

                              Point survival = pixelsInLine(l);
                              double survivalProbability = 1f * survival.getX() / survival.getY();

                              r.nextGaussian();
                              if (survivalProbability < r.nextFloat() * strengthParameter + 1 - strengthParameter) {
                                        Varea.lineList.remove(k--);
                              }
                    }
          }

* */
          

          /*
          private Point pixelsInLine(Line l) {

                    int strength = 0;
                    int maxStrength = 0;

                    int x0 = l.getX().getX();
                    int y0 = l.getX().getY();
                    int x1 = l.getY().getX();
                    int y1 = l.getY().getY();

                    int dx = Math.abs(x1 - x0);
                    int dy = Math.abs(y1 - y0);
                    int sx, sy;
                    if (x0 < x1) {
                              sx = 1;
                    } else {
                              sx = -1;
                    }

                    if (y0 < y1) {
                              sy = 1;
                    } else {
                              sy = -1;
                    }
                    int err = dx - dy;

                    while (true) {
                              maxStrength++;
                              if (true){ //Varea.lineDetectionImage[x0][y0] == true) {
                                        strength++;
                              }

                              if (x0 == x1 && y0 == y1) {
                                        break;
                              }

                              int e2 = 2 * err;

                              if (e2 > -dy) {
                                        err = err - dy;
                                        x0 = x0 + sx;
                              }
                              if (x0 == x1 && y0 == y1) {
                                        maxStrength++;
                                        if (true) {
                                                  strength++;
                                        }

                                        break;
                              }
                              if (e2 < dx) {
                                        err = err + dx;
                                        y0 = y0 + sy;
                              }
                    }

                    Point p = new Point(strength, maxStrength);
                    return p;
          }
          * */
}