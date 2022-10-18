package rasterops;

import objectdata.Point2D;
import objectdata.Polygon2D;
import org.jetbrains.annotations.NotNull;
import rasterdata.RasterImage;

import java.util.List;

public class ScanLineImpl<P> implements ScanLine<P>{
    @Override
    public void fill(@NotNull RasterImage<P> img, @NotNull Polygon2D polygon, @NotNull P areaPixel, @NotNull Polygoner<P> polygoner, @NotNull Liner<P> liner, @NotNull P polygonPixel) {
        List<Point2D> points = polygon.getPoints();

    }
}
