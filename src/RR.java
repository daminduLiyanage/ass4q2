import java.util.LinkedList;

/**
 * Created by Damindu on 11/26/2016.
 */
public class RR {
    final static int PID = 0;
    final static int BT = 1;
    final static int PRIORITY = 2;
    final int QUOTA = 10;
    final int WAIT_TIME = 1;
    int[][] waitArr;

    InputArray input;
    RRWaitQ rrWaitQ;
    RRGantt rrGantt;
    int[][] inputArr;
    LinkedList<Integer> newArrivalsList;

    public RR(RRWaitQ rrq, RRGantt rrGantt, InputArray input, Heapsort hp){
        this.rrWaitQ = rrq;
        this.rrGantt = rrGantt;
        this.input = input;
        this.inputArr = input.getArray();
        this.waitArr = new int[inputArr.length][2];

        //run
        resetArray(this.waitArr);
        this.alterPriority();
        //hp.runSort(inputArr, PRIORITY);
        this.main();
        this.printWaitTime();
        this.printTAT();

    }

    public void main(){
        int start = 0; int end = start + QUOTA;
        do{
            if(checkArrival(start, end)){
                enqueueWait();
            }
            if(!this.rrWaitQ.isEmpty()) {
                int pid = dequeueWait();
                deductBurst(pid);
                waitTime(pid);
                enqueueGantt(pid);
                if(!isBurstEmpty(pid)){
                    enqueueWait(pid);
                }
            }
            start += QUOTA; end += QUOTA;
        }while((this.rrWaitQ.isEmpty() != true)
                || (end < inputArr.length));
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
        if(endTime > this.inputArr.length){
            endTime = this.inputArr.length;
        }
        if((endTime == startTime) ||
                (startTime>endTime)
                ){
            return false;
        }
        LinkedList<Integer> newArrivalList = new LinkedList<>();
        for(int i = startTime; i<endTime; i++){
            if(this.inputArr[i][PID] != -1 ||
                    this.inputArr[i][BT] > 0) {
                newArrivalList.push(this.inputArr[i][PID]);
            }
        }
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

    private int searchByPID(int pid){
        for(int i=0; i<this.inputArr.length; i++){
            if(this.inputArr[i][PID] == pid){
                return i;
            }
        }
        return -1;
    }

    private void waitTime(int pid){
        int index = searchByPID(pid);
        waitArr[index][PID] = pid;
        for(int i=0; i<this.waitArr.length; i++){
            if(i != index && this.inputArr[i][BT] > 0)
                waitArr[i][WAIT_TIME] += 2;
        }
    }

    private void resetArray(int[][] array){
        for(int i=0; i<array.length; i++)
            for(int j=0; j<array[i].length; j++)
                array[i][j] = 0;
    }

    private void printWaitTime(){
        System.out.println("Waiting time:");
        for(int i=0; i<this.waitArr.length; i++){
            if(this.waitArr[i][PID] != 0)
                System.out.println("P" + this.waitArr[i][PID] + ": " + this.waitArr[i][1]);
        }
    }

    private void printTAT(){
        System.out.println("Turn around time:");
        for(int i=0; i<this.waitArr.length; i++)
            if(this.waitArr[i][PID] != 0)
                System.out.println("P" + this.waitArr[i][PID] + ": "
                        + (this.waitArr[i][WAIT_TIME] + remainingTime(i) - 0));

    }

    private int remainingTime(int index){
        if(this.inputArr[index][BT] == -1)
            return 1;
        else return 2;
    }

    private void alterPriority() {
        for(int i=0; i<this.inputArr.length; i++)
            this.inputArr[i][PRIORITY] = 100 - this.inputArr[i][PRIORITY];
    }
}
