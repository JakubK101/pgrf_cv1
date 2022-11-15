package objectdata;

import transforms.Mat4;
import transforms.Mat4Identity;

import java.util.ArrayList;
import java.util.List;

public class Scene {

    private final List<Solid> solids;
    private final List<Mat4> modelMats;

    public Scene() {
        solids = new ArrayList<>();
        modelMats = new ArrayList<>();
    }

    public void addSolid(Solid solid, Mat4 modelMat){
        solids.add(solid);
        modelMats.add(modelMat);

    }

    public void addSolid(Solid solid){
        solids.add(solid);
        modelMats.add(new Mat4Identity());

    }

    public List<Solid> getSolids() {
        return solids;
    }

    public List<Mat4> getModelMats() {
        return modelMats;
    }
}
