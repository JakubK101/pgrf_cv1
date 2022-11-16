package rasterops;

import rasterdata.RasterImage;

import java.util.Optional;
import java.util.function.Predicate;

public class SeedFill4<P> {
 private Optional<P> backGroundColor;
 Optional<P> bckR,bckG,bckB;

 public SeedFill4(){

 }
 /*public SeedFill4(Optional<P> backGroundColor){
     this.backGroundColor=backGroundColor;

    bckR=(backGroundColor & 0x00ff0000) >>16;
    bckG=(backGroundColor & 0x0000ff00) >>8;
    bckB=(backGroundColor & 0x000000ff);

 }*/

    public void fill( RasterImage<P> img, int c, int r, P pixelValue,  Predicate<P> isInArea) {


        Optional<P> currentPixel = img.getPixel(c, r);
        if (currentPixel.isEmpty() || !isInArea.test(currentPixel.get())) {
            return;
        }

        img.setPixel(c, r, pixelValue);
        fill(img, c + 1, r, pixelValue, isInArea);
        fill(img, c, r + 1, pixelValue, isInArea);
        fill(img, c - 1, r, pixelValue, isInArea);
        fill(img, c, r - 1, pixelValue, isInArea);
    }

}
