package rasterops;

import objectdata.Edge;
import objectdata.Point2D;
import objectdata.Polygon2D;
import org.jetbrains.annotations.NotNull;
import rasterdata.RasterImage;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ScanLineImpl<P> implements ScanLine<P>{
    @Override
    public void fill(@NotNull RasterImage<P> img, @NotNull Polygon2D polygon, @NotNull P areaPixel, @NotNull Polygoner<P> polygoner, @NotNull Liner<P> liner, @NotNull P polygonPixel) {
        List<Point2D> points = polygon.getPoints();
        List<Edge> edges= new ArrayList<>();

        for(int i =0;i<points.size();i++){
            Point2D start = points.get(i);
            Point2D end = points.get((i+1) % points.size());

            Edge edge =new Edge(start,end);
            if(start.getY()==end.getY()){
                continue;
            }

            edges.add(edge
                    .oriented()
                    .shortened());

        }

        int yMin= points.get(0).getY();
        int yMax= points.get(0).getY();

        for(Point2D point : points){
            if (point.getY()<yMin){
                yMin= point.getY();
            }
            if (point.getY()>yMax) {
                yMax=point.getY();
            }
        }
            for(int y=yMin;y<yMax;y++){
            List<Integer> intersections = new ArrayList<>();

                for(Edge edge:edges){
                    if(edge.hasInsersection(y)){
                        intersections.add(edge.instersect(y));
                    }
                }
                Collections.sort(intersections);

                for (int i = 0;i<intersections.size();i+=2){

                        liner.drawLine(img,intersections.get(i),y,intersections.get(i+1),y,areaPixel);


                }
        }
            polygoner.drawPolygon(polygon,img,polygonPixel,liner);


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

