/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package structures;

/**
 *
 * @author James
 */
public class Vector {
          
          double a;
          double b;
          
          public Vector(double a, double b)
          {
                    this.a=a;
                    this.b=b;
          }
                    
          public Vector(Point a, Point b)
          {
                    this.a = b.getX()-a.getX();
                    this.b = b.getY()-a.getY();
          }
          
          public Point getPoint()
          {
                    Point p = new Point((int)this.a,(int)this.b);
                    return p;
          }
          
          public Vector add(Point p)
          {
                    return new Vector(this.a + p.getX(),this.b + p.getY());                                        
          }
          
          public double dotProduct(Vector v)
          {
                    return (v.a*a + v.b*b);
          }
          
          public Vector scalarProduct(double value)
          {
                    return (new Vector(a*value,b*value));
          }
          
          public Vector add(Vector v)
          {
                    return (new Vector(v.a+a,v.b+b));
          }
}
