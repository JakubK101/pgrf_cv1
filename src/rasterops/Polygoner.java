package rasterops;

import objectdata.Point2D;
import objectdata.Polygon2D;
import org.jetbrains.annotations.NotNull;
import rasterdata.RasterImage;

import java.util.Iterator;
import java.util.Optional;

public class Polygoner<P> {

    public void drawPolygon(final @NotNull Polygon2D polygon, final @NotNull RasterImage<P> img, final @NotNull P pixelValue, final @NotNull Liner<P> liner){

        Iterator<Point2D> point2DIterator = polygon.getPoints().iterator();

        while(point2DIterator.hasNext()){

        }



    }
}
