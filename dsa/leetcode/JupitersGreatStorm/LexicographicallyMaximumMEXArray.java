package dsa.leetcode.JupitersGreatStorm;

import java.util.*;

/**
 * https://leetcode.com/problems/lexicographically-maximum-mex-array/description/
 *
 * Two-pass greedy. First pass (right to left): precompute suffix[i] = MEX of nums[i..n-1] by maintaining a running frequency map and advancing mex monotonically — this gives the best possible value we can ever emit starting at position i. Second pass (left to right): at position l, the optimal next emit is suffix[l]. If it is 0, no 0 exists anywhere ahead, so every remaining element contributes a 0. Otherwise, expand r until the window [l, r) contains all distinct values 0..suffix[l]-1 (tracking count of newly-seen required values), emit suffix[l], then clear the frequency window. The suffix MEX precomputation guarantees we always pick the lexicographically largest achievable first element at each step.
 *
 * TC: O(n) SC: O(n)
 * #array #hash-table #greedy #hard
 */

class LexicographicallyMaximumMEXArray {
    final static int L = (int)1e5;
    public int[] maximumMEX(int[] nums) {
        List<Integer> res = new LinkedList<>();
        final int Z = nums.length;
        int []freq = new int[L+1];
        int l=0, r=Z-1, mex = 0;
        int []suffix = new int[Z];
        while(r >= 0){
            freq[nums[r]]++;
            while(freq[mex] > 0) mex++;
            suffix[r] = mex;
            r--;
        }
        l = r = 0;
        Arrays.fill(freq, 0);
        while(l < Z && r < Z){
            if(suffix[r] == 0){
                while(r < Z){
                    res.add(0);
                    r++;
                }
            }else{
                int cnt = 0;
                while(cnt < suffix[l]){
                    if(nums[r] < suffix[l] && freq[nums[r]] == 0) cnt++;
                    freq[nums[r]]++;
                    r++;
                }
                res.add(suffix[l]);
                while(l<r){ // fill will take O(n)
                    freq[nums[l]]--;
                    l++;
                }
            }
        }
        int []fres = new int[res.size()];
        l = 0;
        for(int num: res){
            fres[l++] = num;
        }
        return fres;
    }
}
