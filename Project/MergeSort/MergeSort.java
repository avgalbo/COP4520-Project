public class MergeSort
{
  public static void mergeSort(int [] array)
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
      mergeSort(left);
      mergeSort(right);

      // Merge the first half with the second half into our array
      merge(array, left, right);
  }

  public static void merge(int [] array, int [] left, int [] right)
  {
    int firstIndex = 0;
    int secondIndex = 0;
    int i = 0;

    // Loop through as long as the first index is less than the left part of the
    // arrays length and the second index is less than the second half of the
    // arrays length.
    while (firstIndex < left.length && secondIndex < right.length)
    {
      // Case where the left part of our array at our first index is smaller
      // than the right part of our array at our second index. Then we put
      // the left part into our main array and increment both indexes. Otherwise
      // we put the right part into our main array and increment both indexes.
      if (left[firstIndex] < right[secondIndex])
        array[i++] = left[firstIndex++];

      else
        array[i++] = right[secondIndex++];
    }

    // Loop through and fill the remanider of elements for our left part and
    // right part of our array.
    while (firstIndex < left.length)
      array[i++] = left[firstIndex++];

    while (secondIndex < right.length)
      array[i++] = right[secondIndex++];

  }
}
