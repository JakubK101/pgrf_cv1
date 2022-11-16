package rasterops;

import rasterdata.RasterImage;

import javax.swing.*;
import java.util.Optional;
import java.util.function.Predicate;

public class SeedFill4<P> implements SeedFill<P> {
    @Override
    public void fill(RasterImage<P> img, int c, int r, P pixelValue,Predicate<P> isInArea) {


        Optional<P> currentPixel = img.getPixel(c, r);
        if (currentPixel.isEmpty() || !isInArea.test(currentPixel.get())) {
            return;
        }

        img.setPixel(c,r,pixelValue);
        fill(img,c+1,r,pixelValue,isInArea);
        fill(img,c,r+1,pixelValue,isInArea);
        fill(img,c-1,r,pixelValue,isInArea);
        fill(img,c+1,r-1,pixelValue,isInArea);

    }
}
