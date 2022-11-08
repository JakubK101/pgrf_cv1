package objectdata;

import org.jetbrains.annotations.NotNull;

public class Edge {

    final @NotNull Point start;
    final @NotNull Point end;

    public Edge(@NotNull Point start, @NotNull Point end) {
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
       //TODO
        return  new Edge(end,start);
    }

    public int instersect(final int y){ //prúsečík
        if(y==start.getY())
            return start.getX();
        if(y== end.getY())
            return end.getX();

        if (start.getX()==end.getX()){
            return start.getX();
        }
        float k = (end.getY()-start.getY())/((float)(end.getX()-start.getX()));
        float q = start.getY() - k * start.getX();

        return Math.round((y-q)/k);
    }
}
