package rasterops;

import rasterdata.RasterImage;

import java.util.function.Predicate;

public interface SeedFill<P> {
    public void fill(RasterImage<P> img, int c, int r, P pixelValue);
}
