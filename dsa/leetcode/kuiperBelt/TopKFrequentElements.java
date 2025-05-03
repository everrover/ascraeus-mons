package dsa.leetcode.KuiperBelt;

import java.util.*;

/**
 * https://leetcode.com/problems/top-k-frequent-elements/submissions/
 *
 * Maintains a frequency count for each element using a HashMap. Uses a priority queue to identify and retrieve
 * the top k frequent elements. The queue orders the elements by their frequency, allowing us to efficiently
 * obtain the k most frequent elements.
 *
 * How about using no extra space? We can use quick select. We can also use bucket sort.
 *
 * TC: O(n log k) SC: O(n)
 *
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
//        while(pq.size()>k) pq.poll();

        int[] res = new int[pq.size()];
        // Retrieve the top k frequent elements
        for (int i = 0; i < res.length; i++) res[i] = pq.poll();

        return res;
    }
    /*
    class Solution {
    int[] unique;
    Map<Integer, Integer> count;

    public void swap(int a, int b) {
        int tmp = unique[a];
        unique[a] = unique[b];
        unique[b] = tmp;
    }

    public int partition(int left, int right, int pivot_index) {
        int pivot_frequency = count.get(unique[pivot_index]);
        // 1. Move pivot to end
        swap(pivot_index, right);
        int store_index = left;

        // 2. Move all less frequent elements to the left
        for (int i = left; i <= right; i++) {
            if (count.get(unique[i]) < pivot_frequency) {
                swap(store_index, i);
                store_index++;
            }
        }

        // 3. Move the pivot to its final place
        swap(store_index, right);

        return store_index;
    }

    public void quickselect(int left, int right, int k_smallest) {

        //Sort a list within left..right till kth less frequent element
        //takes its place.

    // base case: the list contains only one element
        if (left == right) return;

    //Select a random pivot_index
    Random random_num = new Random();
    int pivot_index = left + random_num.nextInt(right - left);

    // Find the pivot position in a sorted list
    pivot_index = partition(left, right, pivot_index);

    // If the pivot is in its final sorted position
        if (k_smallest == pivot_index) {
        return;
    } else if (k_smallest < pivot_index) {
        // go left
        quickselect(left, pivot_index - 1, k_smallest);
    } else {
        // go right
        quickselect(pivot_index + 1, right, k_smallest);
    }
}

    public int[] topKFrequent(int[] nums, int k) {
        // Build hash map: character and how often it appears
        count = new HashMap();
        for (int num: nums) {
            count.put(num, count.getOrDefault(num, 0) + 1);
        }

        // Array of unique elements
        int n = count.size();
        unique = new int[n];
        int i = 0;
        for (int num: count.keySet()) {
            unique[i] = num;
            i++;
        }

        // kth top frequent element is (n - k)th less frequent.
        // Do a partial sort: from less frequent to the most frequent, till
        // (n - k)th less frequent element takes its place (n - k) in a sorted array.
        // All elements on the left are less frequent.
        // All the elements on the right are more frequent.
        quickselect(0, n - 1, n - k);
        // Return top k frequent elements
        return Arrays.copyOfRange(unique, n - k, n);
    }
}
     */
}