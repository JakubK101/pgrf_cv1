package rasterdata;

import org.jetbrains.annotations.NotNull;

public interface Presentable<D> {

     @NotNull D present(final @NotNull D device);
}
