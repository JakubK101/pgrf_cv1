package rasterops;

import org.jetbrains.annotations.NotNull;
import rasterdata.RasterImage;

public class TrivialLiner<P> implements Liner<P>{
    @Override
    public void drawLine(final @NotNull RasterImage<P> img, final int c1, final int r1, final int c2,final int r2, @NotNull P pixelValue) {
        double k =(r2-r1)/(c2-c1);
        double q = r1-k*c1;

        for(int c=c1;c<=c2;c++){
            int r = (int) Math.round(k*c+q);
            img.setPixel(c,r,pixelValue);

    }

    }
}
