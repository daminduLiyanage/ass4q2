import java.util.Arrays;
import java.util.LinkedList;

/**
 * Created by Damindu on 11/26/2016.
 */
public class RRGantt {
    private LinkedList<Integer>  q;

    public RRGantt(){
        q = new LinkedList<>();
    }

    public void enqueue(int PID){
        q.add(PID);
    }

    public void display(){
        System.out.println();
        System.out.println("RoundRobin: ");
        System.out.println(q);
        System.out.println();
    }

    public boolean isEmpty() {
        return true;
    }
}
