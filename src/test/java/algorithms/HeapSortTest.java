package algorithms;

import metrics.PerformanceTracker;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.util.Arrays;
import java.util.Random;

public class HeapSortTest {

    @Test
    public void testEmpty() {
        int[] a = new int[0];
        PerformanceTracker t = new PerformanceTracker();
        HeapSort.sort(a, t);
        assertArrayEquals(new int[0], a);
    }

    @Test
    public void testSingle() {
        int[] a = {42};
        PerformanceTracker t = new PerformanceTracker();
        HeapSort.sort(a, t);
        assertArrayEquals(new int[]{42}, a);
    }

    @Test
    public void testDuplicates() {
        int[] a = {5,5,5,5,5};
        PerformanceTracker t = new PerformanceTracker();
        HeapSort.sort(a, t);
        assertArrayEquals(new int[]{5,5,5,5,5}, a);
    }

    @Test
    public void testSorted() {
        int[] a = {1,2,3,4,5};
        PerformanceTracker t = new PerformanceTracker();
        HeapSort.sort(a, t);
        assertArrayEquals(new int[]{1,2,3,4,5}, a);
    }

    @Test
    public void testReverse() {
        int[] a = {5,4,3,2,1};
        PerformanceTracker t = new PerformanceTracker();
        HeapSort.sort(a, t);
        assertArrayEquals(new int[]{1,2,3,4,5}, a);
    }

    @Test
    public void testRandomLarge() {
        Random r = new Random(42);
        int[] a = r.ints(1000, -100000, 100000).toArray();
        int[] copy = Arrays.copyOf(a, a.length);
        PerformanceTracker t = new PerformanceTracker();
        HeapSort.sort(a, t);
        Arrays.sort(copy);
        assertArrayEquals(copy, a);
    }
}
