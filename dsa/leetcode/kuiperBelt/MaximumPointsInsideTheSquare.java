package dsa.leetcode.KuiperBelt;

/**
 * https://leetcode.com/problems/maximum-points-inside-the-square/
 * This approach involves binary search over the possible sizes of the square,
 * and checking each size for the maximum number of points it can contain.
 * 
 * This solution optimizes the search by using binary search on the edge length
 * of the square, minimizing the need to check all possible square sizes.
 * 
 * I tried BST approach as well to check square membership, but would need to refine it further...
 *
 * TC: O(n log n) due to sorting and binary search 
 * SC: O(n) for storing points.
 * #binary-search #sorting #geometry #medium #binsearch-over-solution-space
 * [Next](./MaximumPointsInsideTheSquare1.java) 
 */
public class MaximumPointsInsideTheSquare {
  public int maxPointsInsideSquare(int[][] points, String s) {
    int res = 0;
    int l = 0, r = 0, mid;
    // TreeSet<Integer>[] bstx = new TreeSet[26];
    // TreeSet<Integer>[] bsty = new TreeSet[26];
    // for(int i=0; i<26; i++){
    //   bstx[i] = new TreeSet<>();
    //   bsty[i] = new TreeSet<>();
    // }
    for(int i=0; i<points.length; i++){
      int []pt = points[i];
      r = Math.max(r, Math.max(Math.abs(pt[0]), Math.abs(pt[1])));
      // char ch = s.charAt(i);
      // bstx[ch-'a'].add(pt[0]);
      // bsty[ch-'a'].add(pt[1]);
    }
    while(l<=r){
      mid = (l+r)/2;
      int checked = check(mid, points, s);
      if(checked!=-1){
        l = mid+1;
        res = Math.max(checked, res);
      }else{
        r = mid-1;
      }
    }
    return res;
  }
  // private int check(int size, TreeSet<Integer> []bstx, TreeSet<Integer> []bsty){
  //   int res = 0;
  //   for(int i=0; i<26; i++){
  //     Integer xg = bstx[i].ceiling(-size), xs = bstx[i].floor(size);
  //     if(xg == null || xs == null || xg > size || xs < -size) continue;
  //     Integer yg = bsty[i].ceiling(-size), ys = bsty[i].floor(size);
  //     if(yg == null || ys == null || yg > size || ys < -size) continue;
  //     if(xg != xs){
  //       return -1;
  //     }else if(yg != ys){
  //       return -1;
  //     }else if(xg == xs && yg == ys){
  //       res++;
  //     }
  //   }
  //   return res;
  // }
  private int check(int size, int [][]pts, String s){
    int res = 0;
    boolean mark[] = new boolean[26];
    for(int i=0; i<pts.length; i++){
      int[] pt = pts[i];
      int c = s.charAt(i)-'a';
      if(pt[0] > size || pt[0] < -size || pt[1] > size || pt[1] < -size) continue;
      if(mark[c]) return -1;
      mark[c] = true;
      res++;
    }
    return res;
  }
}
