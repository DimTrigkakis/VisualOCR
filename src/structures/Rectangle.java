/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package structures;

import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import twinmoons.Varea;

/**
 *
 * @author James
 */
public class Rectangle {
          
          Point position;
          float angle;
          int width;
          int length;

          public Rectangle(Point position, float angle, int width,int length) {
                    this.position = position;
                    this.angle = angle;
                    this.width = width;
                    this.length = length;
          }          
          
          public Rectangle(ArrayList<Point> region,float angle)
          {
                    Vector v = new Vector(0,0);
                    for (Point p : region)
                    {
                              v = v.add(p);
                    }
                    
                    v = v.scalarProduct(1f/region.size());
                    position = v.getPoint();   
                    
                    this.angle = angle;
          }       
          
          public int getWidth()
          {
                    return width;
          }
          
          public void setSize(ArrayList<Point> region)
          {
                    width = 1000;
                    length = 1;
                    boolean broken;
                    
                     for (this.length = 1; this.length < 300;this.length++)
                    {
                              broken = false;
                              for (Point p : region)
                              {
                                        if (!isInside(p))
                                        {
                                                  broken = true;
                                        }
                                        if (broken)
                                                  break;
                              }
                                        if (!broken)
                                                  break;
                    }
                     
                    for (this.width = 1; this.width < 300;this.width++)
                    {
                              broken = false;
                              for (Point p : region)
                              {
                                        if (!isInside(p))
                                        {
                                                  broken = true;
                                        }
                                        if (broken)
                                                  break;
                              }
                                        if (!broken)
                                                  break;
                    }
                    
                    if (width > length)                              
                    {
                              int temp = length;
                              length = width;
                              width = temp;
                    }
                   
          }
          
          public boolean isInside(Point p)
          {
                    Point rotated = p.rotatePoint(angle,position);
                    if (rotated.getX() < position.getX() - length/2 || rotated.getX() > position.getX() + length/2)
                              return false;
                                      
                    if (rotated.getY() < position.getY() - width/2 || rotated.getY() > position.getY() + width/2)
                              return false;
                    
                    return true;
          }
          
          
          public float getAngle()
          {
                    return angle;
          }
          
          public Point getPosition()
          {
                    Point p = new Point(position.getX(),position.getY());
                    return p;
          }
          
          public int getLength()
          {
                    return length;
          }
          
          public void print()
          {
                    System.out.print(" Rectangle - > at ");
                    position.print();
                    System.out.println(" , at angle "+angle+" with size "+width + " "+length);
          }
}
