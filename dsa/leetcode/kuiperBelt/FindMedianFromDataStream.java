package dsa.leetcode.KuiperBelt;

import java.util.Collections;
import java.util.PriorityQueue;
import java.util.Queue;

/**
 * https://leetcode.com/problems/find-median-from-data-stream/
 *
 * The MedianFinder class maintains two heaps: a max-heap for the lower half of the data and a min-heap for the upper
 * half. When a new number is added, it is balanced between the heaps to maintain order. The median is then computed
 * from the tops of the heaps.
 *
 * Built sol using two BSTs. One for the lower half and one for the upper half. It was O(log n) for insertion and
 * O(log n) for finding median. Based on ./kuiperBelt/SlidingWindowMedian.java. But deletion isn't here so,
 * used PriorityQueues with no deletions, hence no lazy deletion and no need to track valid counts.
 *
 * For both these problems, we can also use Segment Trees, or Order Statistics Tree.
 *
 * TC: O(log n) per insertion, O(1) for finding median. SC: O(n)
 *
 * #heap #priority-queue #design #data-stream #hard #binary-search-tree
 */

public class FindMedianFromDataStream {
  private Queue<Integer> q1 = new PriorityQueue<>(Collections.reverseOrder());
  private Queue<Integer> q2 = new PriorityQueue<>();
  private boolean even = true;

  public FindMedianFromDataStream() { }

  public double findMedian() {
    if (even)
      return (q1.peek() + q2.peek()) / 2.0;
    else
      return q1.peek();
  }

  public void addNum(int num) {
    if (even) {
      q2.offer(num);
      q1.offer(q2.poll());
    } else {
      q1.offer(num);
      q2.offer(q1.poll());
    }
    even = !even;
  }

  /**
   * Your MedianFinder object will be instantiated and called as such:
   * MedianFinder obj = new MedianFinder();
   * obj.addNum(num);
   * double param_2 = obj.findMedian();
   */
}