package objectdata;

public class Point2D {

    private  int x;
    private  int y;

    public Point2D(int x, int y) {
        this.x = x;
        this.y = y;
    }
    public Point2D(double x, double y) {
        this.x = (int)Math.round(x);
        this.y = (int)Math.round(y);
    }

    public int getX() {
        return x;
    }


    public int getY() {
        return y;
    }

}
