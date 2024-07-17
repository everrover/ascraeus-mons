package dsa.leetcode.kuiperBelt;

/**
 * https://leetcode.com/problems/dot-product-of-two-sparse-vectors/
 *
 * This problem is to be thought of in respect teo an actual interview.
 *
 * Simple appoach: for i=0...n-1, res += v1[i]*v2[i]
 * But array is sparse, if we assume 80/20 rule, 80% of the elements are 0, the 80% of mult ops are worthless
 * So we can store either in hashmap or in a 2D array where each row is a pair of index and value.
 *
 * Hashmap can have a lot of collisions for numbers that are close to each other. Since it's resolved via chaining, it can become relatively slow.
 *
 * So we can use a 2D array where each row is a pair of index and value. The array is sorted based on the index already so bin search can also be applied for faster seeks
 * Otherwise we can do a two pointer approach to calculate the dot product.
 *
 * TC: O(n) for initialization, O(k) for dot product where k is the count of non-zero elements.
 * SC: O(k)
 * #array #hash-table #two-pointers #design #medium #interview #binary-search
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

//  public int dotProduct(SparseVector vec) { // binary search approach
//    int res = 0,r=0;
//    while(r<size){
//      int vidx = bins(nums[r][0],vec);
//      if(vidx!=-1) res += vec.nums[vidx][1]*nums[r][1];
//      r++;
//    }
//    return res;
//  }

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

// Your SparseVector object will be instantiated and called as such:
// SparseVector v1 = new SparseVector(nums1);
// SparseVector v2 = new SparseVector(nums2);
// int ans = v1.dotProduct(v2);