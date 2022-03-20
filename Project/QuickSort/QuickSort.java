import java.io.*;

// Regular algorithm for quicksort
public class QuickSort {
    private static int partition(int[] arr, int left, int right) {
       int partitionPoint = arr[left];
       int start = left - 1;
       int end = right + 1;
       while (true) {
           while (++start < right && arr[start] < partitionPoint);
           while (--end > left && arr[end] > partitionPoint);
           if (start < end) swap(arr,start,end);
           else return end;
       }
    }
    public static void swap(int [] array, int start, int end) {
        int temp = array[start];
        array[start] = array[end];
        array[end] = temp;
    }
    public static void quickSort(int[] a, int p, int r) {
       if (p < r) {
           int q = partition(a, p, r);
           quickSort(a, p, q);
           quickSort(a, q + 1, r);
       }
   }
}
