import java.util.Arrays;

class Testat1 {
    public static void main(String[] args) {

        int[] tenNumbers = new int[10];
        int[] oneKNumbers = new int[1_000];
        int[] oneHundredKNumbers = new int[100_000];
        int[] oneMNumbers = new int[1_000_000];
        int[] counting_help = new int[oneMNumbers.length];
        int[] passingarray = new int[10];
        
        Heap_Sort(reverseSorted(oneKNumbers));
        RandomizedQuickSort(allSortedButOne(tenNumbers), 0, tenNumbers.length - 1);
        mergeSort(singleKeyArray(oneHundredKNumbers), 0, oneHundredKNumbers.length - 1);
        COUNTING_SORT(percentageSameKey(oneMNumbers, 50, 10), counting_help, 1001);
        //System.out.println(Arrays.toString(tenNumbers));
        //System.out.println(Arrays.toString(oneKNumbers));
        //System.out.println(Arrays.toString(oneHundredKNumbers));
        //System.out.println(Arrays.toString(oneMNumbers));
        
        
        double resultAverageTime = 0l;

        resultAverageTime = 0l;

        for (int i = 0; i < 4; i++) {
            if (i == 0) {

                System.out.println("\nHeap");
            } else if (i == 1) {

                System.out.println("\nRandom");
            } else if (i == 2) {

                System.out.println("\nCounting");
            } else if (i == 3) {

                System.out.println("\nMerge");
            }

            for (int n = 0; n < 4; n++) {
                
            
            if (n == 0) {
                passingarray = tenNumbers;

                System.out.println("10 Array");
            } else if (n == 1) {
                passingarray = oneKNumbers;

                System.out.println("1_000 Array");
            } else if (n == 2) {
                passingarray = oneHundredKNumbers;

                System.out.println("100_000 Array");
            } else if (n == 3) {
                passingarray = oneMNumbers;

                System.out.println("1_000_000 Array");
            }
            resultAverageTime = 0l;
            for (int j = 0; j < 100; j++) {
                SortedArray(passingarray);
                resultAverageTime += runAlgorithm(passingarray, i, passingarray.length);
            }

            resultAverageTime = resultAverageTime / 100 / 1_000_000;
            System.out.println("Sortiert: " + resultAverageTime);

            resultAverageTime = 0l;
            for (int j = 0; j < 100; j++) {
                reverseSorted(passingarray);
                resultAverageTime += runAlgorithm(passingarray, i, passingarray.length);
            }

            resultAverageTime = resultAverageTime / 100 / 1_000_000;
            System.out.println("Reverse Sorted: " + resultAverageTime);

            resultAverageTime = 0l;
            for (int j = 0; j < 100; j++) {
                allSortedButOne(passingarray);
                resultAverageTime += runAlgorithm(passingarray, i, passingarray.length);
            }
            System.out.println("AllSortedButOne: " + resultAverageTime);

            resultAverageTime = 0l;
            for (int j = 0; j < 100; j++) {
                singleKeyArray(passingarray);
                resultAverageTime += runAlgorithm(passingarray, i, 2);
            }

            resultAverageTime = resultAverageTime / 100 / 1_000_000;
            System.out.println("100% same key: " + resultAverageTime);

            resultAverageTime = 0l;
            for (int j = 0; j < 100; j++) {
                percentageSameKey(passingarray, 50, 10);
                resultAverageTime += runAlgorithm(passingarray, i,1000);
            }

            resultAverageTime = resultAverageTime / 100 / 1_000_000;
            System.out.println("50% same key: " + resultAverageTime);

            resultAverageTime = 0l;
            for (int j = 0; j < 100; j++) {
                randomArray(passingarray, 1_000_000);
                resultAverageTime += runAlgorithm(passingarray, i, 1_000_000);
            }

            resultAverageTime = resultAverageTime / 100 / 1_000_000;
            System.out.println("Random Array 1-1000: " + resultAverageTime);

        }
    }
    }

    private static double runAlgorithm(int[] array, int number, int values) {
        double result = 0d;
        if (number == 0) {
            result = runHeapSort(array);
        }
        if (number == 1) {
            if(array.length < 50000){
            result = runRandomizedQuicksort(array);
            } else {
            	result = -1.0;
            }
        }
        if (number == 2) {
            result = runCountingSort(array, values+1);
        }
        if (number == 3) {
            result = runMergeSort(array);
        }
        
        return result;
    }


    private static double runHeapSort(int[] array) {
        double startTime = 0l;
        double endTime = 0l;
        double result = 0l;

        startTime = System.nanoTime();
        Heap_Sort(array);
        endTime = System.nanoTime();
        result = endTime - startTime;
        return result;
    }

    private static double runRandomizedQuicksort(int[] array) {
        double startTime = 0l;
        double endTime = 0l;
        double result = 0l;

        startTime = System.nanoTime();
        RandomizedQuickSort(array, 0, array.length - 1);
        endTime = System.nanoTime();
        result = endTime - startTime;
        return result;
    }

    private static double runCountingSort(int[] array, int values) {
        double startTime = 0l;
        double endTime = 0l;
        double result = 0l;

        int[] countingSortHelpArray = new int[array.length];
        startTime = System.nanoTime();
        COUNTING_SORT(array, countingSortHelpArray, values + 1);
        endTime = System.nanoTime();
        result = endTime - startTime;
        return result;
    }

    private static double runMergeSort(int[] array) {
        double startTime = 0l;
        double endTime = 0l;
        double result = 0l;

        startTime = System.nanoTime();
        mergeSort(array, 0, array.length - 1);
        endTime = System.nanoTime();
        result = endTime - startTime;
        return result;
    }

    private static int[] reverseSorted(int[] array) {
        for (int i = 0; i < array.length; i++) {
            array[i] = array.length - i;
        }
        return array;
    }

    private static int[] SortedArray(int[] array) {
        for (int i = 0; i < array.length; i++) {
            array[i] = i;
        }
        return array;
    }

    private static int[] randomArray(int[] array, int value) {
        for (int i = 0; i < array.length; i++) {

            array[i] = (int) (Math.random() * (value - 1) + 1);
        }

        return array;
    }

    private static int[] singleKeyArray(int[] array) {
        for (int i = 0; i < array.length; i++) {
            array[i] = 1;
        }
        return array;
    }

    private static int[] percentageSameKey(int[] array, int percentChance, int number) {
        for (int i = 0; i < array.length; i++) {
            if ((Math.random() * (100 - 1) + 1) < percentChance) {
                array[i] = number;
            } else {
                array[i] = (int) (Math.random() * (1000 - 1) + 1);
            }

        }
        return array;
    }

    private static int[] allSortedButOne(int[] array) {
        for (int i = 0; i < array.length - 1; i++) {
            array[i] = i;
        }
        array[array.length - 1] = 1;
        return array;
    }

    private static int[] Quick_Sort(int[] array, int p, int r) {
        if (p < r) {
            int q = Partition(array, p, r);

            Quick_Sort(array, p, q - 1);
            Quick_Sort(array, q + 1, r);
        }

        return array;
    }

    private static int[] RandomizedQuickSort(int[] array, int p, int r) {
        if (p < r) {
            int q = RandomizedPartition(array, p, r);

            RandomizedQuickSort(array, p, q - 1);
            RandomizedQuickSort(array, q + 1, r);
        }

        return array;
    }

    private static int RandomizedPartition(int[] array, int p, int r) {
        int i = (int) Math.round((Math.random() * (r - p)) + p);
        int key = array[r];
        array[r] = array[i];
        array[i] = key;
        return Partition(array, p, r);
    }

    private static int Partition(int[] array, int p, int r) {
        int x = array[r];
        int i = p - 1;
        for (int j = p; j <= r - 1; j++) {
            if (array[j] <= x) {
                i++;
                int key = array[j];
                array[j] = array[i];
                array[i] = key;

            }

        }
        int key = array[r];
        array[r] = array[i + 1];
        array[i + 1] = key;
        return i + 1;
    }

    public static void COUNTING_SORT(int[] A, int[] B, int k) {
        int[] C = new int[k];
        for (int i = 0; i < C.length; i++) { 
            C[i] = 0;
        }

        for (int j = 0; j < A.length; ++j) {
            ++C[A[j]];
        }

        for (int i = 1; i < C.length; ++i) {
            C[i] += C[i - 1];
        }

        for (int j = A.length - 1; j >= 0; j--) {

            B[C[A[j]] - 1] = A[j];
            --C[A[j]];

        }
        for (int i = 0; i < A.length; i++) {
            A[i] = B[i];
        }

    }

    public static void Max_Heapify(int[] array, int n, int i) {
        int largest = i;
        int left = 2 * i + 1;
        int right = 2 * i + 2;

        if (left < n && array[left] > array[largest]) {
            largest = left;
        }
        if (right < n && array[right] > array[largest]) {
            largest = right;
        }
        if (largest != i) {
            int a = array[i];
            array[i] = array[largest];
            array[largest] = a;

            Max_Heapify(array, n, largest);
        }
    }

    public static void Build_Max_Heap(int[] array) {
        int heapsize = array.length;
        for (int i = heapsize / 2; i >= 0; i--) {
            Max_Heapify(array, heapsize, i);
        }
    }

    public static void Heap_Sort(int[] array) {
        Build_Max_Heap(array);

        for (int i = array.length - 1; i > 0; i--) {
            int a = array[0];
            array[0] = array[i];
            array[i] = a;
            Max_Heapify(array, i, 0);
        }

    }

    public static void merge(int array[], int p, int q, int r) {

        int n1 = q - p + 1;
        int n2 = r - q;

        int L[] = new int[n1];
        int R[] = new int[n2];

        for (int i = 0; i < n1; ++i)
            L[i] = array[p + i];
        for (int j = 0; j < n2; ++j)
            R[j] = array[q + 1 + j];

        int i = 0, j = 0;

        int k = p;
        while (i < n1 && j < n2) {
            if (L[i] <= R[j]) {
                array[k] = L[i];
                i++;
            } else {
                array[k] = R[j];
                j++;
            }
            k++;
        }

        while (i < n1) {
            array[k] = L[i];
            i++;
            k++;
        }

        while (j < n2) {
            array[k] = R[j];
            j++;
            k++;
        }
    }

    public static void mergeSort(int array[], int l, int r) {
        if (l < r) {
            int m = l + (r - l) / 2;

            mergeSort(array, l, m);
            mergeSort(array, m + 1, r);

            merge(array, l, m, r);
        }
    }
}