package dsa.leetcode.kuiperBelt;

import java.util.*;

/**
 * https://leetcode.com/problems/top-k-frequent-elements/submissions/
 *
 * Maintains a frequency count for each element using a HashMap. Uses a priority queue to identify and retrieve
 * the top k frequent elements. The queue orders the elements by their frequency, allowing us to efficiently
 * obtain the k most frequent elements.
 *
 * TC: O(n log k) SC: O(n)
 * #hashmap #priorityqueue #heaps #medium
 */

public class TopKFrequentElements {

    public int[] topKFrequent(int[] nums, int k) {
        // Frequency map to count occurrences of each element
        Map<Integer, Integer> map = new HashMap<>();
        // Priority queue to keep track of top k elements by frequency
        Queue<Integer> pq = new PriorityQueue<>((a, b) -> map.getOrDefault(a, 0) - map.getOrDefault(b, 0));

        for (int num : nums) { // Count frequency of each element
            map.put(num, map.getOrDefault(num, 0) + 1);
        }

        for (Map.Entry<Integer, Integer> me : map.entrySet()) { // Push all elements into the priority queue
            pq.offer(me.getKey());
            if (pq.size() > k) pq.poll(); // Ensure the queue has no more than k elements
        }

        int[] res = new int[pq.size()];
        // Retrieve the top k frequent elements
        for (int i = 0; i < res.length; i++) res[i] = pq.poll();

        return res;
    }
}