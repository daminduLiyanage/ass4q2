import com.sun.org.apache.xalan.internal.xsltc.util.IntegerArray;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Stack;

/**
 * Created by Damindu on 11/27/2016.
 */
public class RRWaitQ {
    final int PID = 0;
    final int STATUS = 1;
    //int[][] q;
    int count = 0;
    LinkedList<Integer> q;


    public RRWaitQ(){
        //this.q = new int[100][2];
        // QInit();
        q = new LinkedList<>();
    }
//
//    private void QInit() {
//        for(int i=0; i<this.q.length; i++){
//            q[i][STATUS] = 0;
//        }
//    }

    public void enqueue(LinkedList<Integer> newArrivalsList){
        while(newArrivalsList.size() != 0) {
            this.q.add(newArrivalsList.getLast());
            newArrivalsList.removeLast();
        }
    }

    public void enqueue(int pid){
        this.q.add(pid);
    }

    public int dequeue(){
        if(isEmpty())
            return -1;
        else
            return q.removeFirst();
    }

    public boolean isEmpty() {
        if(q.isEmpty())
            return true;
        else
            return false;
    }
}