/**
 * Created by Damindu on 11/24/2016.
 */
public class Heapsort {
    int[][] array;
    int INDEX;

    public Heapsort(){}

    public void runSort(int[][] array, int index){
        this.array = array;
        this.INDEX = index;
        sort();
    }

    public synchronized void maxHeapify(int[][] array, int currentIndex, int heapSize){
        int left = 2*currentIndex+1;
        int right = 2*currentIndex+2;
        int largest = currentIndex;

        if( left < heapSize && array[left][INDEX] > array[currentIndex][INDEX] ){
            largest = left;
        }
        if( right < heapSize && array[right][INDEX] > array[largest][INDEX] ){
            largest = right;
        }
        if( largest != currentIndex ){
            swap(array, currentIndex, largest);
            maxHeapify(array, largest, heapSize);
        }
    }

    public synchronized void buildMaxHeap(int[][] array, int heapSize){
        int lastElementIndex = array.length - 1;
        int parentIndex = (lastElementIndex - 1)/2;
        for(int i = parentIndex; i >= 0; i--){
            maxHeapify(array, i, heapSize);
        }
    }

    private synchronized void sort(){
        int[][] array = this.array;
        if(array == null || array.length < 2)
            return;

        buildMaxHeap(array, array.length);
        int heapSize = array.length;
        for(int i = array.length - 1; i > 0; i--){
            swap(array, 0, i);
            heapSize = heapSize - 1;
            maxHeapify(array, 0, heapSize);
        }
    }

    /**
     * Swaps i & j array items. (counted from 0)
     * @param array
     * @param i
     * @param j
     */
    public synchronized void swap(int[][] array, int i, int j){
        int[] temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }

}
