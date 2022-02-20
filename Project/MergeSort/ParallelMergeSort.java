import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveAction;

public class ParallelMergeSort
{
  public static void parallelMergeSort(int[] array)
  {
    SortTask mainTask = new SortTask(array);
    ForkJoinPool pool = new ForkJoinPool();
    pool.invoke(mainTask);
  }

    private static class SortTask extends RecursiveAction
    {
      private int [] array;

      public SortTask(int [] array)
      {
        this.array = array;
      }

      @Override
      protected void compute()
      {
        if (array.length <= 1)
          return;

        // Find sizes of elements left and right arrays
        int leftElements = array.length / 2;
        int rightElements = array.length - leftElements;

        // Create new temp arrays.
        int [] left = new int[leftElements];
        int [] right = new int[rightElements];

        // copy the data into temp arrays.
        System.arraycopy(array, 0, left, 0, leftElements);
        System.arraycopy(array, leftElements, right, 0, rightElements);

        // Recursivly sort the two halves.
        SortTask leftSortTask = new SortTask(left);
        SortTask rightSortTask = new SortTask(right);

        // Invoke declared tasks
        //invokeAll(left, right);
        invokeAll(leftSortTask, rightSortTask);

        // Merge the first half with the second half into our array
        MergeSort.merge(array, left, right);
      }
    }
}
