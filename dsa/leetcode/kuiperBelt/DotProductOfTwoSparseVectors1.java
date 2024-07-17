package dsa.leetcode.kuiperBelt;

/**
 * https://leetcode.com/problems/dot-product-of-two-sparse-vectors/
 * This class represents a sparse vector and allows calculating the dot product of two sparse vectors efficiently.
 * 
 * TC: O(n) for initialization, O(k) for dot product where k is the count of non-zero elements.
 * SC: O(k)
 * #array #hash-table #two-pointers #design #medium
 */

class SparseVector {
  private int [][]nums; 
  private int size;
  SparseVector(int[] nums) {
    this.size = 0;
    for(int i=0; i<nums.length; i++) if(nums[i]!=0) size++;
    this.nums = new int[size][2];
    for(int i=0,j=0; i<nums.length; i++) {
      if(nums[i] == 0) continue;
      this.nums[j][0]=i; this.nums[j++][1]=nums[i];
    }
  }

  // Return the dotProduct of two sparse vectors
  public int dotProduct(SparseVector vec) {
    int res = 0,l=0,r=0;
    while(l<vec.size && r<size){
      if(vec.nums[l][0]>nums[r][0]) r++;
      else if(vec.nums[l][0]<nums[r][0]) l++;
      else{
        res += nums[r][1]*vec.nums[l][1];
        r++; l++;
      }
    }
    return res;
  }
}

// Your SparseVector object will be instantiated and called as such:
// SparseVector v1 = new SparseVector(nums1);
// SparseVector v2 = new SparseVector(nums2);
// int ans = v1.dotProduct(v2);