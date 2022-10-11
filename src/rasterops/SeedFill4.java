package rasterops;

import rasterdata.RasterImage;

import java.util.Optional;
import java.util.function.Predicate;

public class SeedFill4<P> implements SeedFill<P> {
    @Override
    public void fill(RasterImage<P> img, int c, int r, P pixelValue, Predicate<P> isInArea) {

        Optional<P> currenPixel = img.getPixel(c,r);
        if(currenPixel.isEmpty() || isInArea.test(currenPixel.get())){
            return;
        }
        img.setPixel(c,r,pixelValue);
        fill(img,c+1,r,pixelValue,isInArea);
        fill(img,c,r+1,pixelValue,isInArea);
        fill(img,c-1,r,pixelValue,isInArea);
        fill(img,c+1,r-1,pixelValue,isInArea);

    }
}
