import java.util.*;
import java.util.concurrent.*;

public class ThreadedQuick implements Runnable {
    public static final int MAX_THREADS = Runtime.getRuntime().availableProcessors();
    static final ExecutorService executor = Executors.newFixedThreadPool(MAX_THREADS);

    int [] arr;
    int start, end;

    // minPartitionSize = array size / number of threads 
    private static int minParitionSize;
    public ThreadedQuick(int minParitionSize, int[] array, int start, int end) {
        this.minParitionSize = minParitionSize;
        this.arr = array;
        this.start = start;
        this.end = end;
    }

    @Override
    public void run() {
        quicksort(arr, start, end);
    }

    public static void quicksort(int[] array, int start, int end) {
        int len = end - start + 1;

        if (len <= 1) return;

        int pivot_index = start + (end - start) / 2;
        int pivotValue = array[pivot_index];

        swap(array, pivot_index, end);

        int storeIndex = start;
        for (int i = start; i < end; i++) {
            if (array[i] <= pivotValue) {
                swap(array, i, storeIndex);
                storeIndex++;
            }
        }

        swap(array, storeIndex, end);
        if (len > minParitionSize) {
            ThreadedQuick quick = new ThreadedQuick(minParitionSize, array, start, storeIndex - 1);
            Future<?> future = executor.submit(quick);
            quicksort(array, storeIndex + 1, end);
            try {
                 future.get(1000, TimeUnit.SECONDS);
            } catch (Exception ex) {
                 ex.printStackTrace();
            }
        } else {
            quicksort(array, start, storeIndex - 1);
            quicksort(array, storeIndex + 1, end);
        }
    }
    public static void swap(int [] array, int start, int end)
    {
      int temp = array[start];
      array[start] = array[end];
      array[end] = temp;
    }
}
