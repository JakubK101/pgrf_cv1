package objectdata;

import transforms.Point3D;

import java.util.List;

/**
 * represents object in 3D space
 */
public interface Solid {
    /**
     *
     * Returns the object´s geomertry defined as a list of 3D points
     */
    List<Point3D> vertices();

    /**
     * Returns the object´s topology defined as a list of 3D points
     */

    List<Integer> indices();


}
