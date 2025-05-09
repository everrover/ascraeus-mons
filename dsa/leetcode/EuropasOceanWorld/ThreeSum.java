package dsa.leetcode.EuropasOceanWorld;

import java.util.*;

public class ThreeSum {

    /**
     * https://leetcode.com/problems/3sum/description/
     *
     * Given an integer array nums, return all the triplets [nums[i], nums[j], nums[k]] such that i != j, i != k,
     * and j != k, and nums[i] + nums[j] + nums[k] == 0.
     * The solution set must not contain duplicate triplets.
     *
     * To solve this, sort the array and use a two-pointers technique to find pairs that sum up with a fixed element
     * to zero, skipping over duplicates as necessary.
     *
     * TC: O(n^2) SC: O(1) - excluding input and output space
     * #array #two-pointers #sorting #medium
     */

    public List<List<Integer>> threeSum(int[] nums) {
        List<List<Integer>> ans = new ArrayList<>();
        List<Integer> tmp;
        Arrays.sort(nums);
        for(int i=0; i<(nums.length-2); i++){

            if(i>0 && nums[i]==nums[i-1]){
                continue;  // remove duplicates from beg
            }
            int l=i+1, r=nums.length-1;
            int reqdSum = -nums[i];

            while(l<r){
                int sum = nums[l] + nums[r];
                if(sum < reqdSum){
                    l++;
                }else if(sum > reqdSum){
                    r--;
                }else{
                    tmp = new ArrayList<Integer>();
                    tmp.add(nums[i]);
                    tmp.add(nums[l]);
                    tmp.add(nums[r]);
                    ans.add(tmp);
                    r--;
                    while(r > l && nums[r] == nums[r+1]) r--; // remove duplicates from end
                }
            }
        }

        return ans;
    }
}