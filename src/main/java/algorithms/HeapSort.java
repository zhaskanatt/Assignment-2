package algorithms;

import metrics.PerformanceTracker;

public class HeapSort {

    public static void sort(int[] a, PerformanceTracker tracker) {
        if (a == null || a.length <= 1) return;
        int n = a.length;

        for (int i = n / 2 - 1; i >= 0; i--) {
            siftDown(a, i, n, tracker);
        }

        for (int end = n - 1; end > 0; end--) {
            swap(a, 0, end, tracker);
            siftDown(a, 0, end, tracker);
        }
    }

    private static void siftDown(int[] a, int root, int size, PerformanceTracker t) {
        while (true) {
            int left = 2 * root + 1;
            if (left >= size) break;
            int right = left + 1;
            int swapIdx = left;

            if (right < size) {
                t.incComparison();
                if (get(a, right, t) > get(a, left, t)) {
                    swapIdx = right;
                }
            }

            t.incComparison();
            if (get(a, root, t) < get(a, swapIdx, t)) {
                swap(a, root, swapIdx, t);
                root = swapIdx;
            } else {
                break;
            }
        }
    }

    private static int get(int[] a, int idx, PerformanceTracker t) {
        t.recordArrayRead();
        return a[idx];
    }

    private static void set(int[] a, int idx, int val, PerformanceTracker t) {
        t.recordArrayWrite();
        a[idx] = val;
    }

    private static void swap(int[] a, int i, int j, PerformanceTracker t) {
        int tmp = get(a, i, t);
        int vj = get(a, j, t);
        set(a, i, vj, t);
        set(a, j, tmp, t);
        t.incSwap();
    }
}
