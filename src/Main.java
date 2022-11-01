import java.util.Random;

public class Main {
    static StopWatch sw = new StopWatch();
    public static final int sizeOfArrays = 15;

    public static void main(String[] args) {
        int[] arrayForBubble = createArray("bubble");
        sw.start();
        bubbleSort(arrayForBubble);
        sw.stop();
        System.out.println(" Sorted bubble: " + displayArray(arrayForBubble));
        System.out.println("  Time taken for bubble (ns): " + sw.getElapsedTime());


        int[] arrayForSelection = createArray("selection");
        sw.start();
        selectionSort(arrayForSelection);
        sw.stop();
        System.out.println(" Sorted selection: " + displayArray(arrayForBubble));
        System.out.println("  Time taken for selection (ns): " + sw.getElapsedTime());


        int[] arrayForQuick = createArray("quick");
        sw.start();
        quickSort(arrayForQuick, 0, sizeOfArrays-1);
        sw.stop();
        System.out.println(" Sorted quick: " + displayArray(arrayForQuick));
        System.out.println("  Time taken for quick (ns): " + sw.getElapsedTime());

        int[] arrayForCocktail = createArray("cocktail");
        sw.start();
        cocktailSort(arrayForCocktail); // Not cocktail shaker!!
        sw.stop();
        System.out.println(" Sorted cocktail: " + displayArray(arrayForCocktail));
        System.out.println("  Time taken for cocktail (ns): " + sw.getElapsedTime());

        int[] arrayForHeap = createArray("heap");
        sw.start();
        heapSort(arrayForHeap);
        sw.stop();
        System.out.println(" Sorted heap: " + displayArray(arrayForHeap));
        System.out.println("  Time taken for heap (ns): " + sw.getElapsedTime());

        int[] arrayForMerge = createArray("merge");
        sw.start();
        mergeSort(arrayForMerge, 0, sizeOfArrays-1);
        sw.stop();
        System.out.println(" Sorted merge: " + displayArray(arrayForMerge));
        System.out.println("  Time taken for merge (ns): " + sw.getElapsedTime());
    }

    static void mergeSort(int arr[], int l, int r) {
        if (l < r) {
            // Find the middle point
            int m = l + (r - l) / 2;

            // Sort first and second halves
            mergeSort(arr, l, m);
            mergeSort(arr, m + 1, r);

            // Merge the sorted halves
            merge(arr, l, m, r);
        }
    }

    static void merge(int[] arr, int l, int m, int r) {
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
            } else {
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

    static String displayArray(int[] myArray) {
        StringBuilder build = new StringBuilder();
        build.append('[');
        for (int number: myArray){
            build.append(number + ", ");
        }
        build.delete(build.lastIndexOf(","), build.length());
        build.append(']');

        return build.toString();
    }

    static int[] createArray(String sort) {
        Random rd = new Random(); // creating Random object
        int[] arr = new int[sizeOfArrays];
        for (int i = 0; i < sizeOfArrays; i++) {
            arr[i] = rd.nextInt(100 - (-100) +1) +(-100);
            // random object.(maxval - minval)+1) + minval for the bounds of the random
        }
        System.out.println("Array before " + sort + ": " + displayArray(arr));
    
        return arr;
    }

    static void heapSort(int[] array) {
        // TODO: I don't even know what this is
    }

    static void cocktailSort(int[] array) {
        int start = 0;
        int end = array.length;
        boolean swap = false;

        do {
            for (int i = start; i < end - 1; ++i) {
                if (array[i] > array[i + 1]) {
                    // Store and swap
                    int temp = array[i];
                    array[i] = array[i + 1]; array[i + 1] = temp;
                    swap = true;
                }
            }

            if (!swap) { break; } else { swap = !swap; } // Already sorted

            end--;
            for (int i = end - 1; i >= start; i--) {
                if (array[i] > array[i + 1]) {
                    int temp = array[i];
                    array[i] = array[i + 1]; array[i + 1] = temp;
                    swap = true;
                }
            }

            start++;
        } while (swap);
    }

    static void quickSort(int[] array, int low, int high) {
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

    static int partition(int[] arr, int low, int high) {
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

    static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }


    static void selectionSort(int[] array) {
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

    static void bubbleSort(int[] array) {
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

