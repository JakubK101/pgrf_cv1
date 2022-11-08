package rasterops;

import objectdata.Edge;
import objectdata.Point;
import objectdata.Polygon2D;
import org.jetbrains.annotations.NotNull;
import rasterdata.RasterImage;

import java.util.ArrayList;
import java.util.List;

public class ScanLineImpl<P> implements ScanLine<P>{
    @Override
    public void fill(@NotNull RasterImage<P> img, @NotNull Polygon2D polygon, @NotNull P areaPixel, @NotNull Polygoner<P> polygoner, @NotNull Liner<P> liner, @NotNull P polygonPixel) {
        List<Point> points = polygon.getPoints();
        List<Edge> edges= new ArrayList<>();

        for(int i =0;i<points.size();i++){
            Edge edge =new Edge(points.get(i),points.get(i+1 % points.size()));
            edges.add(edge);

        }
        for(int i=0;i<edges.size();i++){
            edges.get(i).oriented();
            edges.get(i).shortened();
        }
        double yMin= points.get(0).getY();
        double yMax= points.get(points.size()).getY();

        for(Point point : points){
            if (point.getY()<yMin){
                yMin= point.getY();
            }
            else if(point.getY()>yMax){
                yMax=point.getY();
            }
        }





        }

        //fill the list with edges
        //remove horizontal lines
        //orient all edges
        //shorted by 1 pixel
        //find yMin, yMax
        //for each y in range(yMin,yMax)
            //initialize list of intersections
            //for each from edges
                //if intersection exist
                    //calculate intersection and add to our list
            //sort list of intersection
            //draw lines between odd and even intersections
        //draw polygon
    }

