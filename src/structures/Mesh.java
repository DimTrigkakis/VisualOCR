/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package structures;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

/**
 *
 * @author James
 */
public class Mesh {
          
          ArrayList<Line> mesh;
          
          public Mesh()
          {
                    mesh = new ArrayList<>();
          }
          
          public void addLine(Line l)
          {
                    mesh.add(l);
          }
          
          public ArrayList<Line> getLines()
          {
                    return mesh;
          }
          
          public void print()
          {
                    for (Line l : mesh)
                    {
                              l.print();
                    }
          }
}
