package rasterops;

import org.jetbrains.annotations.NotNull;
import rasterdata.RasterImage;

public class TrivialLiner<P> implements Liner<P>{
    @Override
    public void drawLine(final @NotNull RasterImage<P> img, final int c1, final int r1, final int c2,final int r2, @NotNull P pixelValue) {
    for(int c=c1;c<=c2;c++){
        img.setPixel(c,r1,pixelValue);

    }

    }
}
