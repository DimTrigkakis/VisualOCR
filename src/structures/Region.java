/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package structures;

import java.util.ArrayList;

/**
 *
 * @author James
 */
public class Region {
          
          ArrayList<Point> points = new ArrayList<>();
          
          public void addPoint(Point p)
          {
                    points.add(p);                    
          }
          
          public Point getPoint(int i)
          {
                   return points.get(i);
          }
}
