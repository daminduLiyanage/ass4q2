import java.util.ArrayList;
import java.util.Arrays;
import java.util.Stack;
import java.util.LinkedList;

/**
 * Created by Damindu on 11/26/2016.
 */
public class RR {
    final static int PID = 0;
    final static int BT = 1;
    final static int PRIORITY = 2;
    final int QUOTA = 2;

    InputArray input;
    RRWaitQ rrWaitQ;
    RRGantt rrGantt;
    int[][] inputArr;
    //ArrayList<Integer> newArrivalsList;
    LinkedList<Integer> newArrivalsList;

    public RR(RRWaitQ rrq, RRGantt rrGantt, InputArray input){
        this.rrWaitQ = rrq;
        this.rrGantt = rrGantt;
        this.input = input;
        this.inputArr = input.getArray();
        //this.newArrivalsList = new ArrayList<Integer>(0);
        this.main();
    }

    public void main(){
        this.input.displayArray();

        int start = 0; int end = start + 2;
        do{
            if(checkArrival(start, end)){
                enqueueWait();
            } //the list gets updated only on true
            if(!this.rrWaitQ.isEmpty()) {
                int pid = dequeueWait();
                deductBurst(pid);
                enqueueGantt(pid);
                if(!isBurstEmpty(pid)){
                    enqueueWait(pid);
                }

            }
            start += 2; end += 2;
        }while(this.rrWaitQ.isEmpty() != true);
        displayGantt();
    }

    private boolean isBurstEmpty(int pid) {
        for(int i=0; i<inputArr.length; i++){
            if(inputArr[i][PID] == pid){
                if(inputArr[i][BT] > 0)
                    return false;
            }
        }
        return true;
    }

    private void deductBurst(int pid) {
        for(int i=0; i<inputArr.length; i++){
            if(inputArr[i][PID] == pid){
                inputArr[i][BT] -= this.QUOTA;
            }
        }
    }


    /**
     * Step 1 RoundRobin
     * Checks t-2 to t arrivals. But does not include t-2 slot arrivals. Include t.
     */
    public boolean checkArrival(int startTime, int endTime){
        //this.newArrivalsList.clear();
        if(endTime > this.inputArr.length){
            endTime = this.inputArr.length;
        }
        if((endTime == startTime) ||
                (startTime>endTime)
                ){
            return false;
        }
        //ArrayList<Integer> newArrivalsList = new ArrayList<Integer>();
        LinkedList<Integer> newArrivalList = new LinkedList<>();
        for(int i = startTime; i<endTime; i++){
            if(this.inputArr[i][PID] != -1 ||
                    this.inputArr[i][BT] > 0) {
                //newArrivalsList.add(this.inputArr[i][PID]);
                newArrivalList.push(this.inputArr[i][PID]);
            }
        }
        //this.newArrivalsList = newArrivalsList;
        this.newArrivalsList = newArrivalList;
        return true;
    }

    /**
     * Step 2 RoundRobin
     */
    public void enqueueWait(){
        this.rrWaitQ.enqueue(this.newArrivalsList);
    }

    public void enqueueWait(int pid){
        this.rrWaitQ.enqueue(pid);
    }

    /**
     * Step 3 RoundRobin
     * @return
     */
    public int dequeueWait(){
        return this.rrWaitQ.dequeue();
    }

    /**
     * Step 4 RoundRobin
     * @param PID
     */
    public void enqueueGantt(int PID){
        this.rrGantt.enqueue(PID);
    }

    private void displayGantt() {
        this.rrGantt.display();
    }
}
