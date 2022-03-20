import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;

public class TestCases
{
  public static long startTime;
  public static long endTime;
  public static int [] array;
  public static int n;

  public static int [] createArray(int n)
  {
    array = new int[n];

    for(int i = 0; i < n; i++)
        array[i] = (int)(Math.random() * 100) + 1;

    return array;
  }

  public static void computation(int start, int end, int [] array)
  {
    startTime = System.currentTimeMillis();
    ForkJoinPool pool = ForkJoinPool.commonPool();
    pool.invoke(new QuickSortMutliThreading(start, end, array));
    // ParallelQuickSort.parallelQuickSort(start, end, array);
    endTime = System.currentTimeMillis();

    System.out.println("\nArray size " + array.length + " Parallel: Total computation time = " + (endTime - startTime) + "ms");

    startTime = System.currentTimeMillis();
    QuickSort.quickSort(start, end, array);
    endTime = System.currentTimeMillis();

    System.out.println("Array size " + array.length + " Not Parallel: Total computation time = " + (endTime - startTime) + "ms");
  }

  public static void testSize10()
  {
    int n = 10;
    array = createArray(n);
    computation(0, n-1, array);
  }

  public static void testSize100()
  {
    int n = 100;
    array = createArray(n);
    computation(0, n-1, array);
  }

  public static void testSize1000()
  {
    int n = 1000;
    array = createArray(n);
    computation(0, n-1, array);
  }

  public static void testSize10000()
  {
    int n = 10000;
    array = createArray(n);
    computation(0, n-1, array);
  }

  public static void testSize100000()
  {
    int n = 100000;
    array = createArray(n);
    computation(0, n-1, array);
  }

  public static void testSize1000000()
  {
    int n = 1000000;
    array = createArray(n);
    computation(0, n-1, array);
  }

  public static void testSize10000000()
  {
    int n = 10000000;
    array = createArray(n);
    computation(0, n-1, array);
  }

  public static void testSize100000000()
  {
    int n = 100000000;
    array = createArray(n);
    computation(0, n-1, array);
  }

  public static void main(String [] args)
  {
    testSize10();
    testSize100();
    testSize1000();
    testSize10000();
    testSize100000();
    testSize1000000();
    testSize10000000();
    testSize100000000();
    System.out.println();
  }
}