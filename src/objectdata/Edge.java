package objectdata;

import org.jetbrains.annotations.NotNull;

public class Edge {

    final @NotNull Point2D start;
    final @NotNull Point2D end;

    public Edge(@NotNull Point2D start, @NotNull Point2D end) {
        this.start = start;
        this.end = end;
    }

    public Edge oriented(){
        if(start.getY() > end.getY()){
            return new Edge(end,start);

        }return this;
    }

    public boolean hasInsersection(final int y){ //prusečík s Y
        return(start.getY()<=y && y<=end.getY());



    }

    /**
     * Returns this pixel shortened by one pixel, assumes start.y=!end.y
     * @return
     */
    public @NotNull Edge shortened(){
        float k = (end.getY()-start.getY())/((float)(end.getX()-start.getX()));
        float q = start.getY() - k * start.getX();
        Point2D newY= new Point2D(end.getX(), end.getY()-1);

        if(start.getX()==end.getX()){
            return new Edge(start,newY);
        }
        return new Edge(start,new Point2D(Math.round((newY.getY()-q)/k),newY.getY()));
    }

    public int instersect(final int y){ //prúsečík
        float k = (end.getY()-start.getY())/((float)(end.getX()-start.getX()));
        float q = start.getY() - k * start.getX();
        return Math.round((y-q)/k);
    }
}
