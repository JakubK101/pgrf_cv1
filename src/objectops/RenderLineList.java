package objectops;

import objectdata.Scene;
import objectdata.Solid;
import rasterdata.RasterImage;
import rasterops.Liner;
import transforms.Camera;
import transforms.Mat4;
import transforms.Point3D;

import java.util.List;
import java.util.stream.IntStream;

public class RenderLineList<P> implements Renderer<P>{
    private Liner<P> liner;
    private P pixelValue;
    private RasterImage<P> img;

    private Camera camera;
    private Mat4 modelMat;
    private Mat4 projectionMatrix;


    public RenderLineList(P pixelVaule, Liner<P> liner, RasterImage<P> img, Camera camera, Mat4 modelMat){
        this.liner= liner;
        this.pixelValue=pixelVaule;
        this.img=img;
        this.camera= camera;
        this.modelMat=modelMat;



    }

    @Override
    public void renderScene(Scene scene) {
        for(int i =0; i <scene.getSolids().size()-1;i++){
            renderSolid(scene.getSolids().get(i),scene.getModelMats().get(i));

        }

    }

    @Override
    public void renderSolid(Solid solid, Mat4 modelMat) {
        //compute final transformation matrix
        final Mat4 transMatrix = modelMat.mul(camera.getViewMatrix().mul(projectionMatrix));
        //transformation of vertices
        final List<Point3D> vertices = solid.vertices().stream()
                .map(p -> p.mul(transMatrix))
                .toList();
        //cut according to w
        IntStream.iterate(0,i->i+2).limit(solid.vertices().size()/2).parallel()
                .forEach(i ->{
                    final Point3D start = solid.vertices().get(solid.indices().get(i));
                    final Point3D end = solid.vertices().get(solid.indices().get(i+1));
                }
                //for x and y lies within

        );
        //dehomog()

        //transformation to viewport

        //draw lines
    }
}
