package cli;

import algorithms.HeapSort;
import metrics.PerformanceTracker;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.Random;

public class BenchmarkRunner {

    public static void main(String[] args) throws Exception {
        int n = 10000;
        int runs = 5;
        String dist = "random";

        if (args.length >= 1) n = Integer.parseInt(args[0]);
        if (args.length >= 2) runs = Integer.parseInt(args[1]);
        if (args.length >= 3) dist = args[2];

        System.out.printf("Benchmarking HeapSort: n=%d runs=%d dist=%s%n", n, runs, dist);

        long totalTime = 0;
        long totalComparisons = 0;
        long totalSwaps = 0;
        long totalArrayAccesses = 0;

        new File("performance-data").mkdirs();

        for (int r = 0; r < runs; r++) {
            int[] arr = generate(n, dist, r);
            int[] working = Arrays.copyOf(arr, arr.length);
            PerformanceTracker t = new PerformanceTracker();
            long start = System.nanoTime();
            HeapSort.sort(working, t);
            long end = System.nanoTime();
            long elapsed = end - start;
            totalTime += elapsed;
            totalComparisons += t.getComparisons();
            totalSwaps += t.getSwaps();
            totalArrayAccesses += t.getArrayAccesses();

            System.out.printf("run %d: time=%.3f ms, %s%n", r+1, elapsed / 1e6, t.toString());
        }

        double avgTimeMs = (totalTime / (double) runs) / 1e6;
        double avgComparisons = totalComparisons / (double) runs;
        double avgSwaps = totalSwaps / (double) runs;
        double avgArrayAccesses = totalArrayAccesses / (double) runs;

        System.out.printf("AVG: time=%.3f ms, comparisons=%.1f, swaps=%.1f, arrayAccesses=%.1f%n",
                avgTimeMs, avgComparisons, avgSwaps, avgArrayAccesses);

        String csv = "n,runs,dist,avgTimeMs,avgComparisons,avgSwaps,avgArrayAccesses\n" +
                n + "," + runs + "," + dist + "," + avgTimeMs + "," + avgComparisons + "," + avgSwaps + "," + avgArrayAccesses + "\n";

        try (FileWriter fw = new FileWriter("performance-data/heap_benchmark.csv", true)) {
            fw.write(csv);
        } catch (IOException e) {
            System.err.println("Failed to write CSV: " + e.getMessage());
        }
    }

    private static int[] generate(int n, String dist, int seed) {
        Random r = new Random(seed);
        int[] a = new int[n];
        switch (dist) {
            case "sorted":
                for (int i = 0; i < n; i++) a[i] = i;
                break;
            case "reversed":
                for (int i = 0; i < n; i++) a[i] = n - i;
                break;
            case "nearly":
                for (int i = 0; i < n; i++) a[i] = i;
                for (int k = 0; k < Math.max(1, n / 100); k++) {
                    int i = r.nextInt(n);
                    int j = r.nextInt(n);
                    int tmp = a[i]; a[i] = a[j]; a[j] = tmp;
                }
                break;
            case "duplicates":
                int v = r.nextInt(10);
                for (int i = 0; i < n; i++) a[i] = v;
                break;
            default:
                for (int i = 0; i < n; i++) a[i] = r.nextInt();
        }
        return a;
    }
}
