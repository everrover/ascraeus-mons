package dsa.leetcode.kuiperBelt;

/**
 * https://leetcode.com/problems/dot-product-of-two-sparse-vectors/
 * A class to represent a sparse vector and compute the dot product.
 * This implementation uses a compact form to store non-zero elements only.
 *
 * TC: O(n) for initialization, O(Čog n) for dot product
 * SC: O(n) for storing non-zero elements
 * #array #hash-table #two-pointers #design #medium
 */
public class DotProductOfTwoSparseVectors {
  private int [][]nums; private int size;
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
    int res = 0,r=0;
    while(r<size){
      int vidx = bins(nums[r][0],vec);
      if(vidx!=-1) res += vec.nums[vidx][1]*nums[r][1];
      r++;
    }
    return res;
  }

  private int bins(int idx, SparseVector vec){
    int l=0, r=vec.size-1, mid;
    while(l<=r){
      mid = l+(r-l)/2;
      if(vec.nums[mid][0]>idx) r=mid-1;
      else if(vec.nums[mid][0]<idx) l=mid+1;
      else return mid;
    }
    return -1;
  }
}