import java.util.ArrayList;
import java.util.Arrays;

/**
 * Created by Damindu on 11/24/2016.
 */
public class InputArray {
    final static int PID = 0;
    final static int BT = 1;
    final static int PRIORITY = 2;
    final static int OPTPID = 0;
    final static int OPTPRIORITY = 1;
    final static int OPTBT = 2;
    final static int OPTAT = 3;

    static int[][] array;
    static int[][] optimize;

    public InputArray(int[][] array){
        this.optimize = array;
        readFile();
    }

    public int[][] getArray(){
        return this.array;
    }

    public void displayArray(){
        int[][] array = this.array;
        int[] pidArr = new int[array.length];
        for(int i=0; i<pidArr.length; i++){
            pidArr[i] = array[i][PID];
        }
        System.out.println();
        System.out.println("Array is:");

        for(int i=0; i<pidArr.length; i++){
            if(pidArr[i] != -1){
                System.out.print("Index: "+i+" "+pidArr[i]+"\t");
            }
        }
        System.out.println();

        int[][] timeArr = new int[array.length][array[0].length-1];
        for(int i=0; i<timeArr.length; i++){
            timeArr[i][0] = array[i][BT];
            timeArr[i][1] = array[i][PRIORITY];
        }
        for(int i=0; i<timeArr.length; i++){
            if(timeArr[i][0] != -1)
                System.out.println(Arrays.toString(timeArr[i]));
        }
    }

    public void readFile(){
        this.array = new int[5][3];
        for(int i=0; i<this.array.length; i++){
            for(int j=0; j<this.array[i].length; j++){
                this.array[i][j] = -1;
            }
        }


//        int[][] optimize= {
//                {1,40,20,0},
//                {2,30,25,25},
//                {3,30,25,30},
//                {4,35,15,60},
//                {5,5,10,100},
//                {6,10,10,105}
//        };//input get
        int[][] optimize = this.optimize;


        int[][] array = new int[200][3];
        for(int i=0; i<array.length; i++){
            array[i][BT] = -1;
            array[i][PID] = -1;
        }
        for(int i=0; i<optimize.length; i++){
            array[optimize[i][OPTAT]][PID] = optimize[i][PID];
            array[optimize[i][OPTAT]][BT] = optimize[i][BT];
            array[optimize[i][OPTAT]][PRIORITY] = optimize[i][PRIORITY];

        }

        this.array = array;
    }
}
