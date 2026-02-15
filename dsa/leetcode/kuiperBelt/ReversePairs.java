package dsa.leetcode.KuiperBelt;

/*
 * https://leetcode.com/problems/reverse-pairs/
 * This solution uses a modified merge sort to count reverse pairs efficiently.
 * It splits the array, sorts each half, and then counts the reverse pairs during the merge step.
 * Each number from the left part is compared with elements from the right part to find reverse pairs.
 *
 * TC: O(n log n) SC: O(n)
 * #divide-and-conquer #merge-sort #binary-search #hard
 */

class ReversePairs {
  public int reversePairs(int[] nums) {
    int []res = new int[nums.length];
    int cnt = mergeSort(0, nums.length-1, nums, res);
    return cnt;
  }
  private int mergeSort(int l, int r, int []nums, int []res){
    if(l >= r) return 0;
    int mid = (l+r)/2;
    int cnt = mergeSort(l, mid, nums, res) + mergeSort(mid+1, r, nums, res);
    for(int i=l, j=mid+1; i<=mid; i++){
      while(j<=r && nums[i]/2.0 > nums[j]) j++;
      cnt += (j-mid-1);
    }
    merge(l, mid, r, nums, res);
    return cnt;
  }
  private void merge(int l, int mid, int r, int []nums, int []res){
    int p1 = l, p2 = mid+1, p = 0;
    while(p1 <= mid && p2 <= r){
      if(nums[p1] < nums[p2]) res[p++] = nums[p1++];
      else res[p++] = nums[p2++];
    }
    while(p1 <= mid) res[p++] = nums[p1++];
    while(p2 <= r) res[p++] = nums[p2++];
    for(int i=0; i<p; i++) nums[l+i] = res[i];
  }
}