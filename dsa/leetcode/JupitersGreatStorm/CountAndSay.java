package dsa.leetcode.JupitersGreatStorm;

import java.util.LinkedList;
import java.util.List;


/**
 * https://leetcode.com/problems/count-and-say/?envType=daily-question&envId=2025-04-18
 * 
 * The problem involves generating the nth term in a sequence where each term is derived from the
 * previous one using run-length encoding, which counts consecutive characters and outputs them
 * as character followed by count.
 * 
 * TC: O(2^(n-1)) or exponential given the doubling length with each step
 * SC: O(2^(n-1)) as space doubles with the sequence length
 * #string #simulation #medium
 */

class Solution {

    public String countAndSay(int n) {
        List<Integer> l = new LinkedList<>();
        String prev = "1";
        int[] nums = null;
        n--;
        while(n-- > 0) {
            prev = csutil(nums);
            nums = cs(prev);
        }
        return prev;
    }
    
    private int[] cs(String str) {
        // pseudo code for counting and creating the sequence
        return null;
    }

    private String csutil(int[] nums) {
        // pseudo code for utility function
        return null;
    }
}