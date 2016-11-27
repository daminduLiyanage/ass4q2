import java.util.ArrayList;
import java.util.Arrays;

/**
 * Created by Damindu on 11/24/2016.
 */
public class InputArray {
    final static int PID = 0;
    final static int BT = 1;
    final static int PRIORITY = 2;

    static int[][] array;

    public InputArray(){
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
        System.out.print(Arrays.toString(pidArr)+"\n");
        System.out.println();

        int[][] timeArr = new int[array.length][array[0].length-1];
        for(int i=0; i<timeArr.length; i++){
            timeArr[i][0] = array[i][BT];
            timeArr[i][1] = array[i][PRIORITY];
        }
        for(int i=0; i<timeArr.length; i++){
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
        int[][] array= {
                {1,2,2},
                {2,1,1},
                {3,8,4},
                {4,4,2},
                {5,5,3}
        };
        this.array = array;
    }
}
