package objectdata;

import org.jetbrains.annotations.NotNull;


import java.util.ArrayList;
import java.util.List;

public class Polygon2D {

    private final @NotNull List<Point2D> points = new ArrayList<>();

    public List<Point2D> getPoints() {
        return points;
    }

    public void addPoint2D(final @NotNull Point2D point){
        points.add(point);
    }

   /* public Polygon2D transform(Mat3 mat){
        //List<Point2D> transformPoints = points.stream()


        return new Polygon2D();
    }*/

}
