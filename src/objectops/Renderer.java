package objectops;


import objectdata.Scene;
import objectdata.Solid;
import transforms.Camera;
import transforms.Mat4;

public interface Renderer<P> {

    void renderScene(final Scene scene);

    void renderSolid(final Solid solid, final Mat4 modelMat);

    void setCamera(Camera camera);
    public Camera getCamera();
}
