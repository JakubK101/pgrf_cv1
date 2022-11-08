package objectdata;

import org.jetbrains.annotations.NotNull;
import transforms.Mat3;
import transforms.Point2D;

import java.util.ArrayList;
import java.util.List;

public class Polygon2D {

    private final @NotNull List<Point> points = new ArrayList<>();

    public List<Point> getPoints() {
        return points;
    }

    public void addPoint2D(final @NotNull Point point){
        points.add(point);
    }

    public Polygon2D transform(Mat3 mat){
        //List<Point2D> transformPoints = points.stream()


        return new Polygon2D();
    }

}
