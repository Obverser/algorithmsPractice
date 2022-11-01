import java.util.Random;

public class Main {
    StopWatch sw = new StopWatch();
    public static final int sizeOfArrays = 15;

    public static void main (String[] args){
        Main main = new Main();
        main.run();
    }

    private void run() {
        int[] arrayForBubble = createArray("Bubble sort");
        sw.start();
        BubbleSort(arrayForBubble);
        sw.stop();
        System.out.println("Sorted bubble: " + displayArray(arrayForBubble));
        System.out.println("Time taken for bubble: " + sw.getElapsedTime());


        int[] arrayForSelection = createArray("Selection Sort");
        sw.start();
        SelectionSort(arrayForSelection);
        sw.stop();
        System.out.println("Sorted selection: " + displayArray(arrayForBubble));
        System.out.println("Time taken for selection: " + sw.getElapsedTime());


        int[] arrayForQuicksort = createArray("QuickSort");
        sw.start();
        quickSort(arrayForQuicksort, 0, sizeOfArrays-1);
        sw.stop();
        System.out.println("Sorted QuickSort: " + displayArray(arrayForQuicksort));
        System.out.println("Time taken for quick: " + sw.getElapsedTime());

        /**
        int[] arrayForCocktail = createArray("CockTail Sort");
        CocktailSort(arrayForCocktail);

        int[] arrayForHeapsort = createArray("HeapSort");
        HeapSort(arrayForHeapsort);
        */

        int[] arrayForMergesort = createArray("MergeSort");
        sw.start();
        MergeSort(arrayForMergesort, 0, sizeOfArrays-1);
        sw.stop();
        System.out.println("Sorted MergeSort: " + displayArray(arrayForMergesort));
        System.out.println("Time taken for merge: " + sw.getElapsedTime());

        }
        private void MergeSort(int arr[], int l, int r) {
        if (l < r) {
            // Find the middle point
            int m = l + (r - l) / 2;

            // Sort first and second halves
            MergeSort(arr, l, m);
            MergeSort(arr, m + 1, r);

            // Merge the sorted halves
            Merge(arr, l, m, r);
            }
        }

        private void Merge(int[] arr, int l, int m, int r) {
            // Find sizes of two subarrays to be merged
            int n1 = m - l + 1;
            int n2 = r - m;

            /* Create temp arrays */
            int L[] = new int[n1];
            int R[] = new int[n2];

            /*Copy data to temp arrays*/
            for (int i = 0; i < n1; ++i)
                L[i] = arr[l + i];
            for (int j = 0; j < n2; ++j)
                R[j] = arr[m + 1 + j];

            /* Merge the temp arrays */

            // Initial indexes of first and second subarrays
            int i = 0, j = 0;

            // Initial index of merged subarray array
            int k = l;
            while (i < n1 && j < n2) {
                if (L[i] <= R[j]) {
                    arr[k] = L[i];
                    i++;
                }
                else {
                    arr[k] = R[j];
                    j++;
                }
                k++;
            }

            /* Copy remaining elements of L[] if any */
            while (i < n1) {
                arr[k] = L[i];
                i++;
                k++;
            }

            /* Copy remaining elements of R[] if any */
            while (j < n2) {
                arr[k] = R[j];
                j++;
                k++;
            }
        }

    public static String displayArray(int[] myArray) {
        StringBuilder build = new StringBuilder();
        build.append('[');
        for (int number: myArray){
            build.append(number + ", ");
        }
        build.delete(build.lastIndexOf(","), build.length());
        build.append(']');
        return build.toString();
    }

    private int[] createArray(String sort) {
        Random rd = new Random(); // creating Random object
        int[] arr = new int[sizeOfArrays];
        for (int i = 0; i < sizeOfArrays; i++) {
            arr[i] = rd.nextInt(100 - (-100) +1) +(-100);
            // random object.(maxval - minval)+1) + minval for the bounds of the random
        }
        System.out.println("Array before " + sort + ": " + displayArray(arr));
        return arr;
    }

    public void HeapSort(int[] array) {
    }

    public void CocktailSort(int[] array) {
    }

    public void quickSort(int[] array, int low, int high) {
        if (low < high) {
            // pi is partitioning index, arr[p]
            // is now at right place
            int pi = partition(array, low, high);
            // Separately sort elements before
            // partition and after partition
            quickSort(array, low, pi - 1);
            quickSort(array, pi + 1, high);
        }
    }
    private int partition(int[] arr, int low, int high) {
        // pivot
        int pivot = arr[high];

        // Index of smaller element and
        // indicates the right position
        // of pivot found so far
        int i = (low - 1);

        for (int j = low; j <= high - 1; j++) {

            // If current element is smaller
            // than the pivot
            if (arr[j] < pivot) {

                // Increment index of
                // smaller element
                i++;
                swap(arr, i, j);
            }
        }
        swap(arr, i + 1, high);
        return (i + 1);
    }

    private void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }


    public void SelectionSort(int[] array) {
        for (int i = 0; i < Main.sizeOfArrays - 1; i++) {
            // Find the minimum element in unsorted array
            int min_idx = i;
            for (int j = i + 1; j < Main.sizeOfArrays; j++)
                if (array[j] < array[min_idx])
                    min_idx = j;

            // Swap the found minimum element with the first
            // element
            int temp = array[min_idx];
            array[min_idx] = array[i];
            array[i] = temp;
        }
    }

    public void BubbleSort(int[] array) {
        // loop to access each array element
        for (int i = 0; i < sizeOfArrays - 1; i++)

            // loop to compare array elements
            for (int j = 0; j < sizeOfArrays - i - 1; j++)

                // compare two adjacent elements
                // change > to < to sort in descending order
                if (array[j] > array[j + 1]) {

                    // swapping occurs if elements
                    // are not in the intended order
                    int temp = array[j];
                    array[j] = array[j + 1];
                    array[j + 1] = temp;
                }
    }
}
class StopWatch {
    private long startTime;
    private long endTime;

    StopWatch() {
        startTime = System.nanoTime();
    }

    void start() {
        startTime = System.nanoTime();
    }

    void stop() {
        endTime = System.nanoTime();
    }

    long getElapsedTime() {
        return endTime - startTime;
    }
}

