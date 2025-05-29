package dsa.leetcode.EuropasOceanWorld;

import java.util.*;

public class LongestConsecutiveSequence {
    /**
     * https://leetcode.com/problems/longest-consecutive-sequence/
     * 
     * Sort the array and then iterate while counting consecutive sequences considering duplicates.
     * This is a simple yet effective way, despite the algorithm's constraint.
     *
     * Alternatively, we can use a hash set to store the elements and then iterate through the array.
     *
     * Can use PQ/BST for above approach for finding the next relevant element (after a split is found).
     *
     * IMP: We can use union-find algorithm to solve this problem in O(n)[amortized] time. By clubbing
     * consecutive elements together, we can find the longest sequence in a single pass. Got this in an interview...
     * Can use hashset to find the elements around the current element and marking them as visited by removing them from the set, to mimic the union-find behavior.
     * 
     * TC: O(n log n) due to sorting SC: O(1)
     * #array #hash-table #union-find #medium
     */
    public int longestConsecutiveUF(int[] nums) {
        Set<Integer> s = new HashSet<>();
        for(int num: nums) s.add(num);
        int res = 0;
        for(int num: nums){
            if(s.contains(num)){
                Queue<Integer> q = new LinkedList<>();
                int cres = 0;
                q.offer(num);
                while(!q.isEmpty()){
                    int curr = q.poll();
                    if(!s.contains(curr)) continue;
                    s.remove(curr);
                    cres++;
                    if(s.contains(curr-1)) q.offer(curr-1);
                    if(s.contains(curr+1)) q.offer(curr+1);
                }
                res = Math.max(cres, res);
            }
        }
        return res;
    }
    
    public int longestConsecutive(int[] nums) {
        if (nums.length == 0) return 0;
        Arrays.sort(nums);
        int start = 0, end = 0, ans = 1, ansTmp = 1;
        for (int i = 1; i < nums.length; i++) {
            if (nums[end] + 1 == nums[i]) {
                end = i;
                ansTmp++;
            } else if (nums[end] == nums[i]) {
                end = i;
            } else {
                ansTmp = 1;
                start = end = i;
            }
            ans = Integer.max(ansTmp, ans);
        }
        return ans;
    }
}