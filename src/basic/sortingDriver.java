package basic;
///**
// * Group Number - Group 5
// * Program - sortingDriver.java
// * Description - This sorting driver will conduct data collection on the following 4 sorting algorithms:
// *               - Shell Sort
// *               - Comb Sort
// *               - Bucket Sort
// *               - Bubble Sort
// *               This will also take the time in nanoseconds and output this to an excel file using the writeXLSX class
// * NOTICE -      THIS DRIVER PROGRAM WILL NOT RUN WITHOUT APACHE POI ADDED TO THE LIST OF DEPENDENCIES
// */
//
//import java.io.IOException;
//import java.util.Collections;
//import java.util.Random;
//import java.util.Vector;
//
//public class sortingDriver {
//    private static Random rd = new Random();
//
//    public static void main(String[] args) throws IOException {
//        long start, end;
//        int[][] arrayOfArrays = new int[1000][];
//        writeXLSX writeToExcel = new writeXLSX("./test11.xlsx");
//
//        for (int i = 0; i < arrayOfArrays.length; i++) {
//            if (i != 0)
//                arrayOfArrays[i] = new int[i + 1];
//            else
//                arrayOfArrays[i] = new int[i + 2];
//
//            int[] temp = new int[arrayOfArrays[i].length];
//            fillRandomArray(arrayOfArrays[i]);
//            arrayCopy(arrayOfArrays[i], temp);
//
//            if(i != 0)
//                writeToExcel.writeSize(i, 0, temp.length+1);
//            else
//                writeToExcel.writeSize(i, 0, temp.length);
//
//            start = System.nanoTime();
//            shellSort(temp);
//            end = System.nanoTime();
//            writeToExcel.write(i, 1, end - start);
//            arrayCopy(arrayOfArrays[i], temp);
//
//            start = System.nanoTime();
//            combSort(temp);
//            end = System.nanoTime();
//            writeToExcel.write(i, 2, end - start);
//            arrayCopy(arrayOfArrays[i], temp);
//
//            float[] x = convertToFloat(temp);
//            start = System.nanoTime();
//            bucketSort(x, temp.length);
//            end = System.nanoTime();
//            writeToExcel.write(i, 3, end - start);
//            arrayCopy(arrayOfArrays[i], temp);
//
//            start = System.nanoTime();
//            bubbleSort(temp);
//            end = System.nanoTime();
//            writeToExcel.write(i, 4, end - start);
//            arrayCopy(arrayOfArrays[i], temp);
//        }
//
//        writeToExcel.close();
//        System.out.println("Finished");
//    }
//
//
//    static void bubbleSort(int arr[]){
//        int sizeOfArr = arr.length;
//
//        for (int index = 0; index < (sizeOfArr - 1); index++)
//        {
//            for (int j = 0; j < (sizeOfArr - (index + 1)); j++)
//            {
//
//                if (arr[j] > arr[j+1])
//                {
//                    // swap values if out of order
//                    int dummy = arr[j];
//                    arr[j] = arr[j+1];
//                    arr[j+1] = dummy;
//                }
//            }
//        }
//    }
//
//    public static void bucketSort(float[] array, int n)
//    {
//        if (n <= 0)
//            return;
//        // 1) Create n empty buckets
//        @SuppressWarnings("unchecked")
//        Vector<Float>[] buckets = new Vector[n];
//        for (int i = 0; i < n; i++)
//        {
//            buckets[i] = new Vector<Float>();
//        }
//        // 2) Put array elements in different buckets
//        for (int i = 0; i < n; i++)
//        {
//            float index = array[i] * n;
//            buckets[(int)index].add(array[i]);
//        }
//        // 3) Sort individual buckets
//        for (int i = 0; i < n; i++)
//        {
//            Collections.sort(buckets[i]);
//        }
//        // 4) Concatenate all buckets into arr[]
//        int index = 0;
//        for (int i = 0; i < n; i++)
//        {
//            for (int j = 0; j < buckets[i].size(); j++)
//            {
//                array[index++] = buckets[i].get(j);
//            }
//        }
//    }
//
//    private static int getNextGap(int gap) {
//        // Shrink gap by Shrink factor
//        gap = (gap * 10) / 13;
//        if (gap < 1)
//            return 1;
//        return gap;
//    }
//    // Function to sort arr[] using Comb Sort
//    private static void combSort(int arr[]) {
//        int n = arr.length;
//
//        // initialize gap
//        int gap = n;
//
//        // Initialize swapped as true to make sure that
//        // loop runs
//        boolean swapped = true;
//
//        // Keep running while gap is more than 1 and last
//        // iteration caused a swap
//        while (gap != 1 || swapped == true) {
//            // Find next gap
//            gap = getNextGap(gap);
//
//            // Initialize swapped as false so that we can
//            // check if swap happened or not
//            swapped = false;
//
//            // Compare all elements with current gap
//            for (int i = 0; i < n - gap; i++) {
//                if (arr[i] > arr[i + gap]) {
//                    // Swap arr[i] and arr[i+gap]
//                    int temp = arr[i];
//                    arr[i] = arr[i + gap];
//                    arr[i + gap] = temp;
//
//                    // Set swapped
//                    swapped = true;
//                }
//            }
//        }
//    }
//
//    private static void shellSort(int[] randomArray) {
//        int numElements = randomArray.length;
//        int swapValue = 0;
//        int compareIndex = 0;
//
//        //Outer for loop takes care of dividing up array into segments
//        for (int gap = numElements / 2; gap > 0; gap /= 2) { // n/2
//
//            //First inner for loop to go through all gaps
//            for (int outer = gap; outer < numElements; outer += 1) {
//                swapValue = randomArray[outer];
//
//                //Second for loop to check if outer for loop is greater
//                //than our current value
//                for (compareIndex = outer; (compareIndex >= gap) && (randomArray[compareIndex - gap]) > swapValue; compareIndex -= gap)
//                    randomArray[compareIndex] = randomArray[compareIndex - gap];
//
//                randomArray[compareIndex] = swapValue;
//            }
//        }
//    }
//
//    private static float[] convertToFloat(int[] x){
//        float[] myFloat = new float[x.length];
//        for(int i = 0; i < x.length; i++){
//            myFloat[i] = (float)(x[i])/404; //using 404 as random number to obtain a small number
//        }
//        return myFloat;
//    }
//
//    private static void arrayCopy(int[] src, int[] dest) {
//        for (int i = 0; i < src.length && src.length == dest.length; i++) {
//            dest[i] = src[i];
//        }
//    }
//
//    private static void fillRandomArray(int[] arr) {
//        for (int i = 0; i < arr.length; i++) {
//            arr[i] = rd.nextInt(100)+1;
//        }
//    }
//}
