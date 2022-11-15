package objectdata;

import transforms.Point3D;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Cube implements Solid{

   final List<Point3D> vertices;
   final List<Integer> indices;



   public Cube(){
       vertices= new ArrayList<>();

       vertices.add(new Point3D(0,0,0));
       vertices.add(new Point3D(1,0,0));
       vertices.add(new Point3D(1,1,0));
       vertices.add(new Point3D(0,1,0));
       vertices.add(new Point3D(0,0,1));
       vertices.add(new Point3D(1,0,1));
       vertices.add(new Point3D(1,1,1));
       vertices.add(new Point3D(0,1,1));

       Integer[] auxIndices = {0,1,1,2,2,3,3,0,3,7,2,6,0,4,1,5,7,4,5,6,6,7,4,5};
       indices = Arrays.asList(auxIndices);
   }
    @Override
    public List<Point3D> vertices() {
        return vertices;
    }

    @Override
    public List<Integer> indices() {
        return indices;
    }
}
