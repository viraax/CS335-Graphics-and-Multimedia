import java.util.*;
public class Path {
    public LinkedList<Path> pathToGo;//adds non visited neighbors
    public boolean found;
    public Double dist;
    public Path path;
    public int index;


    public Path(){
        found = false;
        pathToGo = new LinkedList();
        dist = Double.POSITIVE_INFINITY;
    }

    public void makeAdj(Path pathWay){
        pathToGo.add(pathWay);
    }


}