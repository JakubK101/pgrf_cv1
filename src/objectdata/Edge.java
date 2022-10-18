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
       //TODO
        return  new Edge(end,start);
    }

    public int instersect(final int y){
        //TODO
        return 0;
    }
}
