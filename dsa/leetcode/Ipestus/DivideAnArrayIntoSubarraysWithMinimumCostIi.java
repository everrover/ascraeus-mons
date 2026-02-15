package dsa.leetcode.Ipestus;

import java.util.*;

public class DivideAnArrayIntoSubarraysWithMinimumCostIi {

    /**
     * https://leetcode.com/problems/divide-an-array-into-subarrays-with-minimum-cost-ii/description/?envType=daily-question&envId=2026-02-07
     *
     * Use two heaps to manage the selection of k - 2 smallest elements within the given distance.
     * A max heap manages the top smallest k - 2 elements, while a min heap serves as a fallback storage.
     * By maintaining sorted structures, we can efficiently find the valid subarrays and their minimum cost.
     *
     * TC: O(n log k) SC: O(k)
     * #array #sliding-window #heap #tree-set #hard
     */

    private void removeE(TreeMap<Integer, Integer> st, int x){
        st.put(x, st.getOrDefault(x, 0)-1);
        if(st.get(x) == 0) st.remove(x);
    }
    
    private void addE(TreeMap<Integer, Integer> st, int x){
        st.put(x, st.getOrDefault(x, 0)+1);
    }

    public class T {
        private int K;
        public long sum = 0;
        private int st1sz = 0, st2sz = 0;
        TreeMap<Integer, Integer> st2 = new TreeMap<>();
        TreeMap<Integer, Integer> st1 = new TreeMap<>();

        public T(int K){
            this.K = K;
        }
    }
}