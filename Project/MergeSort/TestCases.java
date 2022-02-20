public class TestCases
{
  public static long startTime;
  public static long endTime;
  public static int [] array;

  public static int [] createArray(int n)
  {
    array = new int[n];

    for(int i = 0; i < n; i++)
      array[i] = (int)(Math.random() * 100) + 1;

    return array;
  }

  public static void computation(int [] array)
  {
    startTime = System.currentTimeMillis();
    ParallelMergeSort.parallelMergeSort(array);
    endTime = System.currentTimeMillis();

    System.out.println("\nArray size " + array.length + " Parallel: Total computation time = " + (endTime - startTime) + "ms");

    startTime = System.currentTimeMillis();
    MergeSort.mergeSort(array);
    endTime = System.currentTimeMillis();

    System.out.println("Array size " + array.length + " Not Parallel: Total computation time = " + (endTime - startTime) + "ms");
  }

  public static void testSize10()
  {
    array = createArray(10);
    computation(array);
  }

  public static void testSize100()
  {
    array = createArray(100);
    computation(array);
  }

  public static void testSize1000()
  {
    array = createArray(1000);
    computation(array);
  }

  public static void testSize10000()
  {
    array = createArray(10000);
    computation(array);
  }

  public static void testSize100000()
  {
    array = createArray(100000);
    computation(array);
  }

  public static void testSize1000000()
  {
    array = createArray(1000000);
    computation(array);
  }

  public static void testSize10000000()
  {
    array = createArray(10000000);
    computation(array);
  }

  public static void testSize100000000()
  {
    array = createArray(100000000);
    computation(array);
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
