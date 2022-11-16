package rasterops;

import rasterdata.RasterImage;

import java.util.Optional;
import java.util.function.Predicate;

public class SeedFill8<P> implements SeedFill<P>{
    @Override
    public void fill(RasterImage<P> img, int c, int r, P pixelValue) {
        Optional<P> currentPixel = img.getPixel(c,r);
        if(currentPixel==pixelValue) {
            return;
        }

        img.setPixel(c,r,pixelValue);

        fill(img,c+1,r,pixelValue);
        fill(img,c,r+1,pixelValue);
        fill(img,c-1,r,pixelValue);
        fill(img,c+1,r-1,pixelValue);

        fill(img,c+1,r+1,pixelValue);
        fill(img,c-1,r+1,pixelValue);
        fill(img,c+1,r-1,pixelValue);
        fill(img,c-1,r-1,pixelValue);

    }
}
