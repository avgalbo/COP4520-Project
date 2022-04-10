import java.util.concurrent.ForkJoinPool;
import java.util.Random;

public class TestCases
{
  public static long startTime;
  public static long endTime;
  public static int [] array;
  public static int n;

  public static int [] createArray(int n)
  {
    Random random = new Random();
    array = new int[n];

    for (int i = 0; i < n; i++)
        array[i] = random.nextInt();

    return array;
  }

  public static void sequentialComputation(int start, int end, int [] array)
  {
    startTime = System.currentTimeMillis();
    QuickSort.quickSort(start, end, array);
    endTime = System.currentTimeMillis();

    System.out.println("Array size " + array.length + " Not Parallel: Total computation time = " + (endTime - startTime) + "ms");
  }

  public static void parallelComputation(int start, int end, int[] array)
  {
    startTime = System.currentTimeMillis();
    ForkJoinPool pool = new ForkJoinPool();
    pool.invoke(new QuickSortMultiThreading(start, end, array));
    endTime = System.currentTimeMillis();

    System.out.println("Array size " + array.length + " Parallel: Total computation time = " + (endTime - startTime) + "ms");
  }

  public static void testSize10()
  {
    int n = 10;
    array = createArray(n);
    sequentialComputation(0, n-1, array);
    array = createArray(n);
    parallelComputation(0, n-1, array);
    System.out.println("\n");

  }

  public static void testSize100()
  {
    int n = 100;
    array = createArray(n);
    sequentialComputation(0, n-1, array);
    array = createArray(n);
    parallelComputation(0, n-1, array);
    System.out.println("\n");

  }

  public static void testSize1000()
  {
    int n = 1000;
    array = createArray(n);
    sequentialComputation(0, n-1, array);
    array = createArray(n);
    parallelComputation(0, n-1, array);
    System.out.println("\n");

  }

  public static void testSize10000()
  {
    int n = 10000;
    array = createArray(n);
    sequentialComputation(0, n-1, array);
    array = createArray(n);
    parallelComputation(0, n-1, array);
    System.out.println("\n");

  }

  public static void testSize100000()
  {
    int n = 100000;
    array = createArray(n);
    sequentialComputation(0, n-1, array);
    array = createArray(n);
    parallelComputation(0, n-1, array);
    System.out.println("\n");

  }

  public static void testSize1000000()
  {
    int n = 1000000;
    array = createArray(n);
    sequentialComputation(0, n-1, array);
    array = createArray(n);
    parallelComputation(0, n-1, array);
    System.out.println("\n");

  }

  public static void testSize10000000()
  {
    int n = 10000000;
    array = createArray(n);
    sequentialComputation(0, n-1, array);
    array = createArray(n);
    parallelComputation(0, n-1, array);
    System.out.println("\n");

  }

  public static void testSize100000000()
  {
    int n = 100000000;
    array = createArray(n);
    sequentialComputation(0, n-1, array);
    array = createArray(n);
    parallelComputation(0, n-1, array);
    System.out.println("\n");

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