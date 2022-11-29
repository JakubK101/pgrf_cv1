package objectdata;

import org.jetbrains.annotations.NotNull;


import java.util.ArrayList;
import java.util.List;


public class Polygon2D {

    public Polygon2D(List<Point2D> points){
        this.points=points;
    }
    public Polygon2D(){

    }

    private  List<Point2D> points = new ArrayList<>();

    public List<Point2D> getPoints() {
        return points;
    }

    public List<Edge> getEdges(){
        List<Edge> edges = new ArrayList<>();
        Point2D p1 =points.get(points.size()-1);
            for(final Point2D p2 : points){
                edges.add(new Edge(p1,p2));
                p1=p2;
            }
            return edges;
    }

    public void addPoint2D(final @NotNull Point2D point){
        points.add(point);
    }

   /* public Polygon2D transform(Mat3 mat){
        //List<Point2D> transformPoints = points.stream()


        return new Polygon2D();
    }*/

}
