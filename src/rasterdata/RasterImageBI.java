package rasterdata;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.Optional;

public class RasterImageBI implements RasterImage<Integer>, Presentable<Graphics>{

    private final @NotNull BufferedImage bufferedImage;

    public RasterImageBI(final int width, final int height) {
        bufferedImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
    }

    @Override
    public int getWidth() {
        return bufferedImage.getWidth();
    }

    @Override
    public int getHeight() {
        return bufferedImage.getHeight();
    }

    @Override
    public @NotNull Optional<Integer> getPixel(final int c, final int r) {
        if(getWidth() > c && getHeight() > r && c >= 0 && r >= 0){
            return Optional.of(bufferedImage.getRGB(c, r));
        }
        return Optional.empty();
    }

    @Override
    public void setPixel(final int c, final int r, final @NotNull Integer newValue) {
        if(getWidth() > c && getHeight() > r && c >= 0 && r >= 0){
            bufferedImage.setRGB(c, r, newValue);
        }

    }

    @Override
    public void clear(@NotNull Integer newValue) {
        final @Nullable Graphics g = bufferedImage.getGraphics();
        if(g!=null){
        g.setColor(new Color(newValue));
        g.fillRect(0,0,getWidth(),getHeight());
        }
    }

    @Override
    public @NotNull Graphics present(@NotNull Graphics device) {
        device.drawImage(bufferedImage, 0, 0, null);
        return device;
    }
}
