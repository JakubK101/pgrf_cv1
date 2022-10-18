package rasterops;

import objectdata.Polygon2D;
import org.jetbrains.annotations.NotNull;
import rasterdata.RasterImage;

public interface ScanLine<P> {

    public void fill(final @NotNull RasterImage<P>img, final @NotNull Polygon2D polygon, final@NotNull P areaPixel,
                     final @NotNull Polygoner<P> polygoner, final  @NotNull Liner<P> liner, final @NotNull P polygonPixel );
}
