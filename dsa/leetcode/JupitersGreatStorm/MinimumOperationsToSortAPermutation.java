package dsa.leetcode.JupitersGreatStorm;

import java.util.*;

/**
 * https://leetcode.com/problems/minimum-operations-to-sort-a-permutation/description/
 *
 * Key insight: the two operations (reverse + rotate-left) can only produce rotations of nums or rotations of reverse(nums). So sorting is possible iff nums is a cyclic rotation of [0..n-1] or of [n-1..0]. Check case 1 (already cyclic-increasing): find where 0 sits at index lidx; rotating left lidx times costs lidx, or reversing then rotating costs sz-lidx, then reversing again (+1 each) — take the min. Check case 2 (cyclic-decreasing after one reverse): reverse nums in-place and repeat the same cost analysis with a +1 offset for the initial reverse. Return -1 if neither case holds.
 *
 * TC: O(n) SC: O(1)
 * #array #math #case-analysis #medium
 */

class MinimumOperationsToSortAPermutation {
    private int isinc(int []nums){
        int sz = nums.length;
        int lidx = -1;
        for(int i=0; i<sz; i++){
            if(nums[i] == 0) lidx = i;
        }
        for(int j=(lidx+1)%sz, num = 1; j!=lidx; j=(j+1)%sz, num++){
            if(nums[j] != num) return -1;
        }
        return lidx;
    }
    public int minOperations(int[] nums) {
        int sz = nums.length;
        int li1 = isinc(nums);
        int res1 = li1==-1?Integer.MAX_VALUE:Math.min(li1+1, sz - li1 + 1);

        for(int i=0; i<nums.length/2; i++){
            int t = nums[i];
            nums[i] = nums[nums.length - i - 1];
            nums[nums.length - i - 1] = t;
        }
        int li2 = isinc(nums);
        int res2 = li2==-1?Integer.MAX_VALUE:Math.min(1+li2, 1+sz-li2);
        int res = Math.min(res1, res2);
        return res==Integer.MAX_VALUE?-1:res;
    }

    // [9 7 6 6 5 3 0] = [0 3 5 6 6 7 9]
    // [7 6 6 5 3 0 9] = [9 0 3 5 6 6 7]
    // [6 6 5 3 0 9 7] = [7 9 0 3 5 6 6]
    // [0 9 7 6 6 5 3] = [3 5 6 6 7 9 0]

}
