
# Heap Sort Algorithm Analysis

## Overview
This project implements and analyzes the Heap Sort algorithm in Java using IntelliJ IDEA.  
It measures and compares key performance metrics — execution time, comparisons, swaps, and array accesses — across different input sizes.

The analysis includes both empirical and theoretical insights into Heap Sort’s behavior, along with automated benchmarking and visualization.


## Algorithm Description
Heap Sort is a comparison-based, in-place sorting algorithm based on a **binary heap** structure.  
It first builds a max heap from the unsorted array, then repeatedly extracts the maximum element to reconstruct the array in sorted order.

### Key Properties
| Metric | Value |
|--------|--------|
| **Time Complexity** | O(n log n) (best, average, worst) |
| **Space Complexity** | O(1) |
| **Stability** | Not stable |
| **In-Place** | Yes |

---

## Experimental Results
| n | avgTimeMs | avgComparisons | avgSwaps | avgArrayAccesses |
|---|------------|----------------|-----------|------------------|
| 10 | 0.15492 | 38.4 | 26.6 | 183.2 |
| 100 | 0.37138 | 1,029.6 | 582.8 | 4,390.4 |
| 1,000 | 0.99094 | 16,843.6 | 9,069.6 | 69,965.6 |
| 10,000 | 2.82568 | 235,360.2 | 124,182.0 | 967,448.4 |

---

## Visualization
The log–log performance graph shows how sorting time scales with input size `n`.

### Observations
- Execution time grows roughly in proportion to *n log n*.  
- Comparisons, swaps, and array accesses increase predictably as n grows.  
- The algorithm maintains consistent scaling behavior with low variance.

---

## Code Review & Optimization Notes
- Metrics were tracked accurately using counters for comparisons, swaps, and array accesses.  
- The code can be optimized by:
  - Reducing redundant array reads through temporary caching.
  - Minimizing swap overhead by reusing a single temporary variable.
- No major structural inefficiencies were found.

---

## Conclusion
Heap Sort demonstrates strong consistency between **theoretical** and **empirical** performance.  
It offers predictable behavior and efficient memory use, making it ideal for systems with tight space constraints.