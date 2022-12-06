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
    private Mat4 projectionMatrix;


    public RenderLineList(Camera camera,Mat4 projectionMatrix,P pixelVaule, Liner<P> liner, RasterImage<P> img){
        this.liner= liner;
        this.pixelValue=pixelVaule;
        this.img=img;
        this.camera= camera;
        this.projectionMatrix=projectionMatrix;



    }

    @Override
    public void renderScene(Scene scene) {
        for(int i =0; i <scene.getSolids().size();i++){
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
        IntStream.iterate(0,i->i+2).limit(solid.indices().size()/2).parallel()
                .forEach(i ->{
                    final Point3D start = vertices.get(solid.indices().get(i));
                    final Point3D end = vertices.get(solid.indices().get(i+1));

                    if(start.getX()>= -start.getW() && start.getX()<= start.getW()
                            && start.getY() >= -start.getW() && start.getY()<= start.getW()
                            && start.getZ() >=0 && start.getZ() <=start.getW() &&
                            end.getX()>= -end.getW() && end.getX()<= end.getW()
                            && end.getY() >= -end.getW() && end.getY()<= end.getW()
                            && end.getZ() >=0 && end.getZ() <=end.getW()) {
                        start.dehomog().ifPresent(from -> end.dehomog().ifPresent(to->{
                            final int c1 = (int) Math.round((from.getX()+1)/2 * img.getWidth());
                            final int c2 = (int) Math.round((to.getX()+1)/2 * img.getWidth());
                            final int r1 = (int) Math.round((1-(from.getY()+1)/2)*img.getHeight());
                            final int r2 = (int) Math.round((1-(to.getY()+1)/2)*img.getHeight());

                            liner.drawLine(img,c1,r1,c2,r2,pixelValue);


                        }));

                    }
                }
                //for x and y lies withing <-w, w>
                        //for z lies withing <0, w>

        );
        //dehomog()
        //transformation to viewport
        //draw lines
    }
    @Override
    public void setCamera(Camera camera) {
        this.camera=camera;
    }

    @Override
    public Camera getCamera() {
        return camera;
    }
}
