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
public class Point {
          
          private int x;
          private int y;
          private int distanceTravelled;
          
          public Point(int x, int y)
          {
                    this.x = x;
                    this.y = y;
          }
          
          public void reset()
          {
                    distanceTravelled = 0;
          }
          
          public boolean canTravel(int distance, int limit)
          {
                    if (distanceTravelled+distance <= limit)
                              return true;
                    
                    return false;
          }
          
          public Point closestPoint(ArrayList<Point> points)
          {
                    double minDist = 1000;
                    Point closest = null;
                    for (Point p : points)
                    {
                              double a = p.distance(this);
                              if (a < minDist && p != this) // not equals, there might be points with same values
                              {
                                        minDist = a;         
                                        closest = p;
                              }
                    }
                    
                    return closest;
          }
          
          public void setTravel(int distance)
          {
                    distanceTravelled+= distance;
          }
          
          @Override
          public Point clone()
          {
                    return new Point(x,y);
          }
          
          
          public Point getPoint()
          {
                    return this;
          }
          
          public Point rotatePoint(float theta, Point origin)
          {
                    
                    double px = Math.cos(theta) * (x-origin.getX()) - Math.sin(theta) * (y-origin.getY()) + origin.getX();
                    double py = Math.sin(theta) * (x-origin.getX()) + Math.cos(theta) * (y-origin.getY()) + origin.getY();
                    
                    return new Point((int)px,(int)py);
          }
          
          public void addVector(Vector v)
          {
                    x += v.a;
                    y += v.b;
          }
          
          public void addPoint(Point p)
          {
                    x+=p.getX();
                    y+=p.getY();
          }
          
          public int getX()
          {
                    return x;
          }
          
          public int getY()
          {
                    return y;
          }
          
          public void setPoint(int x, int y)
          {
                    this.x = x;
                    this.y = y;
          }
          
          public void bind()
          {
                    if (x < 0)
                              x = 0;
                    if (x > Varea.size-1)
                              x = Varea.size-1;
                    if (y < 0)
                              y = 0;
                    if (y > Varea.size-1)
                              y =  Varea.size-1;                    
          }
          
          public boolean equals(Point p)
          {
                    if (this.x == p.x && this.y == p.y)
                              return true;
                    
                    return false;
          }
          
          public void renormalizeY(int size)
          {
                    this.y = size-y-1;
          }
          
         public double distance(Point p)
         {
                   int x1 = p.getX();
                   int y1 = p.getY();
                   return (Math.sqrt((x1-x)*(x1-x)+(y1-y)*(y1-y)));
         }
         
         public void print()
         {
                   System.out.print(" point is "+x+" "+y+" ");
         }
          
}
