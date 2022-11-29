package rasterops;

import objectdata.Edge;
import objectdata.Point2D;
import objectdata.Polygon2D;
import rasterdata.RasterImage;

import java.util.ArrayList;
import java.util.List;

public class Clipper<P> {

    public Polygon2D clip(RasterImage<P> img, Liner liner, Polygoner polygoner, Polygon2D polygon, Polygon2D polygonClipper){
        List<Point2D> in =polygon.getPoints();

            for(Edge edge: polygonClipper.getEdges()){
                ArrayList<Point2D> out = new ArrayList<>();

                if(in.isEmpty())
                    continue;
                Point2D v1 =in.get(in.size()-1);

                for(Point2D v2 : in){

                    if(isInside(v2,edge)){
                        if(!isInside(v1,edge)){
                            Point2D intersection = findIntersection(v1,v2,edge);
                            out.add(intersection);
                        }
                        out.add(v2);
                    } else if (isInside(v1,edge)) {
                        Point2D intersection=findIntersection(v1,v2,edge);
                        out.add(intersection);
                    }
                    v1=v2;

                }
                in=out;
            }
            return new Polygon2D(in);



    }
public boolean isInside(Point2D point, Edge edge){
    Point2D t = new Point2D(edge.getEnd().getX() - edge.getStart().getX(), edge.getEnd().getY()-edge.getStart().getY() );
    Point2D n = new Point2D(t.getY(),-t.getX());
    Point2D v = new Point2D(point.getX()-edge.getStart().getX(), point.getY()-edge.getStart().getY());

    return(v.getX()*n.getX()+v.getY()*n.getY()>0);
}
public static Point2D findIntersection(Point2D v1, Point2D v2, Edge edge){
        double x1 = v1.getX();
        double y1 = v1.getY();

        double x2 = v2.getX();
        double y2 = v2.getY();

        double x3 = edge.getStart().getX();
        double y3 = edge.getStart().getY();

        double x4 = edge.getEnd().getX();
        double y4 = edge.getEnd().getY();

        double x = (x1*y2 - x2*y1) * (x3-x4) - (x3*y4 - x4*y3) * (x1-x2) / ((x1-x2)*(y3-y4) - (y1-y2) *(x3-x4));
        double y = (x1*y2 - x2*y1) * (y3-y4) - (x3*y4 - x4*y3) * (y1-y2) / ((x1-x2)*(y3-y4) - (y1-y2) *(x3-x4));

        return new Point2D(x,y);
}

}
