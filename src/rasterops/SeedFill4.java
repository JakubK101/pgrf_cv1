package rasterops;

import rasterdata.RasterImage;

import javax.swing.*;
import java.util.Optional;
import java.util.function.Predicate;

public class SeedFill4<P> implements SeedFill<P> {
    @Override
    public void fill(RasterImage<P> img, int c, int r, P pixelValue) {


        Optional<P> currenPixel = img.getPixel(c,r);
        if(currenPixel==pixelValue){
            return;
        }
        img.setPixel(c,r,pixelValue);
        fill(img,c+1,r,pixelValue);
        fill(img,c,r+1,pixelValue);
        fill(img,c-1,r,pixelValue);
        fill(img,c+1,r-1,pixelValue);

    }
}
