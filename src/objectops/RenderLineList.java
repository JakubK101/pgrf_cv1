package objectops;

import objectdata.Scene;
import objectdata.Solid;
import rasterdata.RasterImage;
import rasterdata.RasterImageBI;
import rasterops.Liner;
import rasterops.TrivialLiner;
import transforms.Camera;
import transforms.Mat4;

public class RenderLineList<P> implements Renderer<P>{
    private Liner<P> liner;
    private P pixelValue;
    private RasterImage<P> img;

    private Camera camera;
    private Mat4 modelMat;


    public RenderLineList(P pixelVaule, Liner<P> liner, RasterImage<P> img, Camera camera, Mat4 modelMat){
        this.liner= new TrivialLiner<>();
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
        //transformation of vertices
        //cut according to w
        //dehomog()
        //transformation to viewport
        //draw lines
    }
}
