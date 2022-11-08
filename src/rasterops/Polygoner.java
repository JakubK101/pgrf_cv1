package rasterops;

import objectdata.Point;
import objectdata.Polygon2D;
import org.jetbrains.annotations.NotNull;
import rasterdata.RasterImage;

import java.util.Iterator;
import java.util.Optional;

public class Polygoner<P> {

    public void drawPolygon(final @NotNull Polygon2D polygon, final @NotNull RasterImage<P> img, final @NotNull P pixelValue, final @NotNull Liner<P> liner){

        Iterator<Point> point2DIterator = polygon.getPoints().iterator();
        Optional<Point> lastEnd = Optional.empty();
        boolean iterate = point2DIterator.hasNext();

        while (iterate) {
            Point a = lastEnd.isEmpty() ? point2DIterator.next() : lastEnd.get();
            Point b;

            if (point2DIterator.hasNext()) {
                b = point2DIterator.next();
            } else {
                b = polygon.getPoints().get(0);
                iterate = false;
            }

            lastEnd = Optional.of(b);
            liner.drawLine(img, a.getX(), a.getY(), b.getX(), b.getY(), pixelValue);
        }




    }
}
