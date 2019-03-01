import java.time.Clock;
import java.util.Arrays;
import java.util.Random;

/**
 * @author Lyle Matthew Po Lalunio
 * @author Brandon Shin
 * @version 1.0
 *
 * n^n possibilities
 */


public class BigGuessSort {


    private static final int numSims = 1000;
    private static int[] array;
    private static Random rand;
    private static int min;
    private static int max;

    public static void main(String[] args) {
        int length;
        int seed;
        if (args.length != 0) {
            length = Integer.parseInt(args[0]);
            seed = Integer.parseInt(args[1]);
        } else {
            //default
            length = 5;
            seed = 8675309;
        }


        long startTime = System.currentTimeMillis();

        min = 10000000;
        max = 0;

        rand = new Random(seed);
        int n = length;
        array = generateRandomSortedArray(n);
        System.out.println("Target Array: " + java.util.Arrays.toString(array));
        System.out.println("Array Length: " + array.length);

        double expectedAverage = Math.pow(n,n);
        double simulatedAverage = simulate(numSims, array);

        System.out.println("Number of simulations: " + numSims);
        System.out.println("Expected Average: " + expectedAverage);
        System.out.println("Simulated Average: " + simulatedAverage);

        System.out.println("Maximum: " + max);
        System.out.println("Minimum: " + min);

        long endTime = System.currentTimeMillis();
        System.out.println("Execution time: " + (endTime - startTime)/1000.0);

    }

    private static double simulate(int n, int[] arr) {
        int[] vals = new int[n];
        for (int i = 0; i < n; i++) {
            vals[i] = sort(arr);
            if (vals[i] > max) {
                max = vals[i];
                System.out.println("New max! " + max);
            }
            if (vals[i] < min) {
                min = vals[i];
                System.out.println("New min! " + min);
            }
            System.out.println("Number of iterations for simulation #" + i + ": " + vals[i]);
        }
        return calculateAverage(vals);
    }

    private static int sort(int[] arr) {
        int tally = 0;
        boolean flag = true;

        while (flag) {
            int[] testArray = new int[arr.length];
            int[] indices = generateIndices(arr.length);

            for (int i = 0; i < indices.length; i++) {
                testArray[i] = arr[indices[i]];
            }
            if (Arrays.equals(arr, testArray)) {
                flag = false;
            }
            tally += 1;
        }
        return tally;
    }

    private static int[] generateRandomSortedArray(int n) {
        int[] array = new int[n];
        for (int i = 0; i < n; i++) {
            array[i] = rand.nextInt();
        }
        Arrays.sort(array);
        return array;
    }

    private static int[] generateIndices(int n) {
        int[] indices = new int[n];
        for (int i = 0; i < n; i++) {
            indices[i] = rand.nextInt(n);
        }
        return indices;
    }

    private static double calculateAverage(int[] vals) {
        double sum = 0;
        for (int val : vals) {
            sum += val;
        }
        return (sum / vals.length);
    }
}
