import java.io.*;
import java.lang.*;
import java.util.Random;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;
import java.util.concurrent.RecursiveAction;

public class ParallelQuickSort 
{

    /**
     * Finding random pivoted and partition
     * array on a pivot.
     * There are many different
     * partitioning algorithms.
     * 
     * @param start
     * @param end
     * @param array
     * @return
     */

    // Function to implement
    // QuickSort method
    public static void parallelQuickSort(int start, int end, int[] array)
    {
        SortTask mainTask = new SortTask(start, end, array);
        ForkJoinPool pool = new ForkJoinPool();
        pool.invoke(mainTask);
    }

    public static class SortTask extends RecursiveAction
    {
        private int[] array;
        private int start, end;

        public SortTask(int start, int end, int[] array)
        {
            this.array = array;
            this.start = start;
            this.end = end;
        }


        private int partition(int start, int end, int[] array)
        {

            int i = start, j = end;

            // Decide random pivot
            int pivot = new Random().nextInt(j - i) + i;

            // Swap the pivote with end
            // element of array;
            int t = array[j];
            array[j] = array[pivot];
            array[pivot] = t;
            j--;

            // Start partitioning
            while (i <= j) {

                if (array[i] <= array[end]) {
                    i++;
                    continue;
                }

                if (array[j] >= array[end]) {
                    j--;
                    continue;
                }

                t = array[j];
                array[j] = array[i];
                array[i] = t;
                j--;
                i++;
            }

            // Swap pivote to its
            // correct position
            t = array[j + 1];
            array[j + 1] = array[end];
            array[end] = t;
            return j + 1;
        }

        @Override
        protected void compute() {
            // Base case
            if (start >= end)
                return;

            // Find partition
            int p = partition(start, end, array);

            // Divide array
            SortTask left = new SortTask(start, p - 1, array);

            SortTask right = new SortTask(p + 1, end, array);

            // Left subproblem as separate thread
            left.fork();
            right.compute();

            // Wait untill left thread complete
            left.join();

            // We don't want anything as return
            return;
        }
    }
}