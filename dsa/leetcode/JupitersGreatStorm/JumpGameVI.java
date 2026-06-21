package dsa.leetcode.JupitersGreatStorm;

import java.util.*;

/**
 * https://leetcode.com/problems/jump-game-vi/
 *
 * DP with a monotonic deque for O(1) sliding window maximum. memo[i] = max score to reach index i = nums[i] + max(memo[i-k..i-1]). The deque stores indices in decreasing order of their memo values; the front always holds the index with the maximum memo in the valid window [i-k, i-1]. Before computing memo[i], evict out-of-window indices from the front, then pop from the back any index whose memo value is <= memo[i] (they can never be the best choice for future positions). Each index is pushed and popped at most once, giving O(n) overall.
 *
 * TC: O(n) SC: O(n)
 * #array #dp #monotonic-deque #medium
 */

class JumpGameVI {
    public int maxResult(int[] nums, int k) {
        Integer[] memo = new Integer[nums.length];
        memo[0] = nums[0];
        Deque<Integer> dq = new LinkedList<>();
        dq.offerLast(0);
        for(int i=1; i<nums.length; i++){
            while(!dq.isEmpty() && dq.peekFirst() < i-k){
                dq.pollFirst();
            }
            memo[i] = memo[dq.peekFirst()] + nums[i];
            while(!dq.isEmpty() && memo[i] >= memo[dq.peekLast()]){
                dq.pollLast();
            }
            dq.offerLast(i);
        }
        return memo[nums.length-1];
    }
}
