package rasterops;

import org.jetbrains.annotations.NotNull;
import rasterdata.RasterImage;

public class TrivialLiner<P> implements Liner<P> {


    @Override
    public void drawLine(final @NotNull RasterImage<P> img, final int c1, final int r1, final int c2, final int r2, @NotNull P pixelValue) {
        int counter = 0;
        boolean drawing = true;

        double k = (r2 - r1) / (double) (c2 - c1);
        double q = r1 - k * c1;

        if (c1 < c2) {
            for (int c = c1; c <= c2; c++) {
                int r = (int) Math.round(k * c + q);

                if (counter < 10){
                    counter++;
                }
                else{
                    drawing = !drawing;
                    counter = 0;
                }


                if (drawing){
                    img.setPixel(c, r, pixelValue);
                }

            }

        } else {
            for (int c = c2; c <= c1; c++) {
                int r = (int) Math.round(k * c + q);

                if (counter < 10){
                    counter++;
                }
                else{
                    drawing = !drawing;
                    counter = 0;
                }


                if (drawing){
                    img.setPixel(c, r, pixelValue);
                }

            }

        }

    }
}
