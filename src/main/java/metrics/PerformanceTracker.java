package metrics;

public class PerformanceTracker {
    private long comparisons = 0;
    private long swaps = 0;
    private long arrayReads = 0;
    private long arrayWrites = 0;

    public void incComparison() { comparisons++; }
    public void incSwap() { swaps++; }
    public void recordArrayRead() { arrayReads++; }
    public void recordArrayWrite() { arrayWrites++; }

    public long getComparisons() { return comparisons; }
    public long getSwaps() { return swaps; }
    public long getArrayReads() { return arrayReads; }
    public long getArrayWrites() { return arrayWrites; }
    public long getArrayAccesses() { return arrayReads + arrayWrites; }

    public void reset() {
        comparisons = 0;
        swaps = 0;
        arrayReads = 0;
        arrayWrites = 0;
    }

    public String toCsvHeader() {
        return "comparisons,swaps,arrayReads,arrayWrites";
    }

    public String toCsv() {
        return comparisons + "," + swaps + "," + arrayReads + "," + arrayWrites;
    }

    @Override
    public String toString() {
        return String.format("comparisons=%d swaps=%d arrayReads=%d arrayWrites=%d",
                comparisons, swaps, arrayReads, arrayWrites);
    }
}
