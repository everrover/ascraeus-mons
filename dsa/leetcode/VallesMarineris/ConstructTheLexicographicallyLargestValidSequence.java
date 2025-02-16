package dsa.leetcode.VallesMarineris;

public class ConstructTheLexicographicallyLargestValidSequence {

  /**
   * https://leetcode.com/problems/construct-the-lexicographically-largest-valid-sequence/description/?envType=daily-question&envId=2025-02-16
   *
   * This solution uses backtracking to construct the sequence by placing integers from n to 1 
   * at possible positions ensuring that the distance between two occurrences of an integer i
   * is exactly i. The sequence is constructed in such a way to ensure it is lexicographically largest.
   * 
   * Earlier used a version that used counts and checked for i's presence at `idx-1` if `cnt[i] == 1 
   * // i.e it was used before once`.
   * Didn't work.
   *
   * TC: O(n!) SC: O(n)
   * #array #backtracking #medium
   */

  // private static int count = 0;
  private boolean findPerm(final int n, int idx, boolean []cnt, int []res){
    while(idx < res.length && res[idx] != 0) idx++;
    if(idx == res.length) return true;
    // count++;
    for(int i=n; i >= 1; i--){
      if(cnt[i]) continue;
      if(i!=1){
        if(idx + i >= res.length || res[idx+i] != 0) continue;
        cnt[i]=true;
        res[idx] = res[idx+i] = i;
        if(findPerm(n, idx+1, cnt, res)) return true;
        res[idx] = res[idx+i] = 0;
        cnt[i]=false;
      }else{
        res[idx] = 1;
        cnt[i] = true;
        if(findPerm(n, idx+1, cnt, res)) return true;
        cnt[i] = false;
        res[idx] = 0;
      }
    }
    return false;
  }
  public int[] constructDistancedSequence(final int n) {
    int []res = new int[2*n-1];
    boolean []cnt = new boolean[n+1];
    findPerm(n, 0, cnt, res);
    // System.out.println(count);
    return res;
  }

  // check at prev version
  // private boolean findPerm(final int n, int idx, int []cnt, int []res){
  //   if(idx == res.length) return true;
  //   for(int i=n; i >= 1; i--){
  //     if(cnt[i] == 0) continue;
  //     if(i!=1){
  //       if(cnt[i] == 1 && (idx < i || res[idx-i] != i)) continue;
  //     }
  //     cnt[i]--;
  //     res[idx] = i;
  //     if(findPerm(n, idx+1, cnt, res)) return true;
  //     res[idx] = 0;
  //     cnt[i]++;
  //   }
  //   return false;
  // }
  // public int[] constructDistancedSequence(final int n) {
  //   int []res = new int[2*n-1];
  //   int []cnt = new int[n+1];
  //   Arrays.fill(cnt, 2); cnt[1] = 1;
  //   findPerm(n, 0, cnt, res);
  //   return res;
  // }
}