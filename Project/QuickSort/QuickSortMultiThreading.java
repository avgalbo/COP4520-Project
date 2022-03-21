import java.util.concurrent.RecursiveAction;
import java.util.Random;

public class QuickSortMultiThreading extends RecursiveAction 
{
    int start, end;
    int [] arr;
    
    public QuickSortMultiThreading(int start, int end, int[] arr) 
    {
        this.arr = arr;
        this.start = start;
        this.end = end;
    }

    @Override
    protected void compute() 
    {
        // Base case
        if (start >= end)
            return;

        // Find partition
        int p = partition(arr, start, end);

        // Divide array
        QuickSortMultiThreading left = new QuickSortMultiThreading(start, p - 1, arr);
        QuickSortMultiThreading right = new QuickSortMultiThreading(p + 1, end, arr);

        // Create new task to compute left part
        left.fork();
        // Sort right partition using current thread
        right.compute();
        left.join();
    }

    // // Single pivot partitioning method.
    // private static int partition(int [] array, int start, int end) 
    // {
    //     int i = start, j = end;

    //     // Decide random pivot
    //     int pivot = new Random().nextInt(j - i) + i;

    //     // Swap the pivoted with the end element of array
    //     swap(pivot, j, array);

    //     // Start partitioning
    //     while (i <= j) 
    //     {
    //         if (array[i] <= array[end]) 
    //         {
    //             i++;
    //             continue;
    //         }

    //         if (array[j] >= array[end]) 
    //         {
    //             j--;
    //             continue;
    //         }

    //         swap(i, j, array);
            
    //         j--;
    //         i++;
    //     }

    //     // Swap back pivot to its correct position
    //     swap(end, j + 1, array);
    //     return j + 1;
    // }

    public static int partition(int [] array, int low, int high)
    {
        // pivot point
        int pivot = array[high];

        // Index of smaller element and
        // indicates the right position
        // of pivot found so far
        int i = (low - 1);

        for (int j = low; j <= high - 1; j++) 
        {
            // If current element is smaller
            // than the pivot
            if (array[j] < pivot) 
            {
                // Increment index of
                // smaller element
                i++;
                swap(i, j, array);
            }
        }
        swap(i + 1, high, array);
        return (i + 1);
    }
    
    // Swap values in array
    public static void swap(int i, int j, int [] array)
    {
      int temp = array[i];
      array[i] = array[j];
      array[j] = temp;
    }
}
