/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package structures;

import java.util.ArrayList;
import twinmoons.Varea;


/**
 *
 * @author James
 */
public class Line {
          
          private Point x;
          private Point y;
          boolean safe = false;          
                              
          public float length()
          {
                    return (float) x.distance(y);
          }
          
          public boolean lineCoincides(Line l)
          {
                    if (compareSlope(l))
                              if (comparePosition(l))
                                        if (Math.abs(length() - l.length()) <= 20)
                                        return true;
                    
                    return false;
          }
          
          public boolean isTheSameAs(Line l)
          {
                    if (x.distance(l.getX()) == 0 && y.distance(l.getY()) == 0)
                              return true;
                    if (y.distance(l.getX()) == 0 && x.distance(l.getY()) == 0)
                              return true;
                    
                    return false;
          }
          
          public void reset()
          {
                    x.reset();// reset the travelled distances                    
                    y.reset(); 
          }
          
          public boolean connect(Line l,int distOff,int contigency,boolean linearBonus) // true if there was a change
          {
                    Line shorter,longer;
                                                            
                    if (l.length() < length())
                    {
                              shorter = l;
                              longer = this;
                    }
                    else
                    {
                              shorter = l;
                              longer = this;
                    }
                    
                    boolean proximity = false;
                    
                    if (linearBonus && shorter.compareSlope(longer))
                    {
                              distOff *= 2;
                    }
                                                           
                    int distance = (int) longer.getX().distance(shorter.getX());
                    if (distance <= distOff*contigency)
                    {
                              if (shorter.getX().canTravel(distance,distOff*contigency))
                              {
                                        shorter.setX(longer.getX().clone());
                                        shorter.getX().setTravel(distance);
                                        proximity =  true;
                              }
                    }
                    
                    distance = (int) longer.getX().distance(shorter.getY());
                    if (distance <=  distOff*contigency)
                    {
                              if (shorter.getY().canTravel(distance,distOff*contigency))
                              {
                                        shorter.setY(longer.getX().clone());
                                        shorter.getY().setTravel(distance);
                                        proximity =  true;
                              }
                    }
                    
                    distance = (int) longer.getY().distance(shorter.getX());
                    if (distance <=  distOff*contigency)
                    {
                              if (shorter.getX().canTravel(distance,distOff*contigency))
                              {
                                        shorter.setX(longer.getY().clone());
                                        shorter.getX().setTravel(distance);
                                        proximity =  true;
                              }
                    }
                    
                    distance = (int) longer.getY().distance(shorter.getY());
                    if (distance <=  distOff*contigency)
                    {                              
                              if (shorter.getY().canTravel(distance,distOff*contigency))
                              {
                                        shorter.setY(longer.getY().clone());
                                        shorter.getY().setTravel(distance);
                                        proximity =  true;
                              }
                    }
                    
                    
                    return proximity;
          }
          
          public boolean comparePosition(Line l)
          {
                    Point a = new Point((x.getX()+y.getX())/2,(x.getY()+y.getY())/2);       
                    Point b = new Point((l.getX().getX()+l.getY().getX())/2,(l.getX().getY()+l.getY().getY())/2);                
                    
                    if (a.distance(b) <=10)
                    {
                              return true;
                    }
                    
                    return false;
          }
          
          public boolean lineInside(Line l) 
          {
                    Point a = l.getX();
                    Point b = l.getY();
                    
                    if (isInside(a) && isInside(b))
                    {                              
                              return true;
                    }
                    
                    return false;                    
          }
          
          public boolean pointInside(Line l) 
          {
                    Point a = l.getX();
                    Point b = l.getY();                    
                    boolean isa = isInside(a);
                    boolean isb = isInside(b);        
                    
                    if ((isa || isb) && !(isa && isb))
                              return true;
                    
                    return false;                    
          }
          
          private boolean isInside(Point a)
          {
                    int minDistance = 3;
                     Vector v = new Vector(x,y);
                     Vector w = new Vector(x,a);

                    double c1 = v.dotProduct(w);
                    if ( c1 <= 0 )
                    return (x.distance(a) <= minDistance);

                    double c2 = v.dotProduct(v);
                    if ( c2 <= c1 )
                    return (y.distance(a) <= minDistance);

                    double b = c1 / c2;
                    Point Pb;
                    x.addVector(v.scalarProduct(b));
                    Pb = x;
                    return  (Pb.distance(a) <= minDistance);
                    
          }
                    
          public boolean compareSlope(Line l)
          {
                    if (Math.abs(x.getX() - y.getX()) < 0.05)
                    {
                              if (Math.abs(l.getX().getX() - l.getY().getX()) < 0.05)
                                        return true;
                              else
                                        return false;                              
                    }
                    else
                    {
                              if (Math.abs(l.getX().getX() - l.getY().getX()) < 0.05)
                                        return false;      
                    }
                    
                    
                    
                    float slope1 = (x.getY() - y.getY())/(x.getX() - y.getX());
                    float slope2 = (l.getX().getY() -l.getY().getY())/(l.getX().getX() - l.getY().getX());
                    
                    Math.toDegrees(slope2);
                    if (Math.abs(Math.toDegrees(Math.atan(slope1)) - Math.toDegrees(Math.atan(slope2))) <= 15)
                              return true;
                    
                    return false;                  
                    
          }
          
          public Line(Point x, Point y)
          {
                    if (x.getX() <= y.getX())
                    {
                              this.x = x;
                              this.y = y;
                    }
                    else
                    {                              
                              this.x = y;
                              this.y = x;
                    }
          }
          
          public Line getLine()
          {
                    return this;
          }
          
          public Point getX()
          {
                    return x;
          }
          
          public Point getY()
          {
                    return y;
          }
          
          public void setPoint(Point x, Point y)
          {
                    this.x = x;
                    this.y = y;
          } 
          
          public void print()
          {
                    System.out.print(" line -> ");
                    x.print();
                    y.print();
                    System.out.println();
          }
                             
          public void drawLine(boolean[][] map) {                   
                    
                    int x0 = getX().getX();
                    int y0 = getX().getY();
                    int x1 = getY().getX();
                    int y1 = getY().getY();

                    int dxx = Math.abs(x1 - x0);
                    int dyy = Math.abs(y1 - y0);
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
                    int err = dxx - dyy;

                    while (true) {
                              if (Varea.valid(x0,y0))
                                        map[x0][y0] = true;
                              else
                                        break;

                              if (x0 == x1 && y0 == y1) {
                                        break;
                              }

                              int e2 = 2 * err;

                              if (e2 > -dyy) {
                                        err -=  dyy;
                                        x0 += sx;
                              }
                              if (x0 == x1 && y0 == y1) {
                                        
                              if (Varea.valid(x0,y0))
                                        map[x0][y0] = true;
                              
                                        break;
                              }
                              
                              if (e2 < dxx) {
                                        err += dxx;
                                        y0 += sy;
                              }
                    }
          }

          private void setX(Point p) {
                    this.x = p;
                    
          }
          
          private void setY(Point p) {
                    this.y = p;
                    
          }
}
