import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;

public class TestCases
{
  public static long startTime;
  public static long endTime;
  public static int [] array;
  public static int n;
  // Get # of threads depending on our processors
  public static final int MAX_THREADS = Runtime.getRuntime().availableProcessors();

  public static int [] createArray(int n)
  {
    array = new int[n];
    for(int i = 0; i < n; i++)
        array[i] = (int)(Math.random() * 100) + 1;
    return array;
  }

  public static void computation(int [] array, int start, int end)
  {
    // Concurrent
    startTime = System.currentTimeMillis();
    ThreadedQuick quick = new ThreadedQuick(array.length / MAX_THREADS, array, 0, array.length - 1);
    quick.run();
    endTime = System.currentTimeMillis();
    System.out.println("\nArray size " + array.length + " Parallel: Total computation time = " + (endTime - startTime) + "ms");
    
    // Sequential 
    startTime = System.currentTimeMillis();
    QuickSort.quickSort(array, start, end);
    endTime = System.currentTimeMillis();
    System.out.println("Array size " + array.length + " Not Parallel: Total computation time = " + (endTime - startTime) + "ms");
  }

  public static void testSize10()
  {
    int n = 10;
    array = createArray(n);
    computation(array, 0, n-1);
  }

  public static void testSize100()
  {
    int n = 100;
    array = createArray(n);
    computation(array, 0, n-1);
  }

  public static void testSize1000()
  {
    int n = 1000;
    array = createArray(n);
    computation(array, 0, n-1);
  }

  public static void testSize10000()
  {
    int n = 10000;
    array = createArray(n);
    computation(array, 0, n-1);
  }

  public static void testSize100000()
  {
    int n = 100000;
    array = createArray(n);
    computation(array, 0, n-1);
  }

  public static void testSize1000000()
  {
    int n = 1000000;
    array = createArray(n);
    computation(array, 0, n-1);
  }

  public static void testSize10000000()
  {
    int n = 10000000;
    array = createArray(n);
    computation(array, 0, n-1);
  }

  public static void testSize100000000()
  {
    int n = 100000000;
    array = createArray(n);
    computation(array, 0, n-1);
  }

  public static void main(String [] args)
  {
    testSize10();
    testSize100();
    testSize1000();
    testSize10000();
    testSize100000();
    //testSize1000000();
    //testSize10000000();
    //testSize100000000();
    System.out.println();
  }
}
