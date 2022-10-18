package rasterops;

import rasterdata.RasterImage;

import java.util.function.Predicate;

public class SeedFillQueue<P> implements SeedFill<P>{
    @Override
    public void fill(RasterImage<P> img, int c, int r, P pixelValue, Predicate<P> isInArea) {
        //Initialize an empty queue
        //Insert seed into queue
        //while queue not empty
            //test if pixel lies in area
            //set to the new area
            //insert all neighbours into queue
    }
}
