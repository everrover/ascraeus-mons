package dsa.leetcode.kuiperBelt;

public class MinimizeManhattanDistances {

  /**
   * https://leetcode.com/problems/minimize-manhattan-distances/
   * 
   * intuition: The maximum Manhattan distance between any of the two points, if eliminated, 
   * will minimize the maximum Manhattan distance amonbgs the remaining points.
   * 
   * This solution involves finding the Manhattan points first and then calculating the minimum
   * distance by excluding one point and comparing.
   * 
   * We've to find max(|x1-x2|, |y1-y2|) across points.
   * = max({
   *     x1-x2+y1-y2,
   *     x1-x2-y1+y2,
   *    -x1+x2+y1-y2,
   *    -x1+x2-y1+y2
   * })
   * = max({
   *     (x1+y1) - (x2+y2),
   *     (x1-y1) - (x2-y2),
   *    -(x1+y1) + (x2+y2),
   *    -(x1-y1) + (x2-y2)
   * })
   * = max({
   *     S1 - S2,
   *     D1 - D2,
   *    -S1 + S2,
   *    -D1 + D2
   * })
   * = max(|S1-S2|, |D1-D2|)
   * 
   * This churns out two possible Manhattan points. We then calculate the minimum distance by excluding
   * one of these points and comparing the distances. Then return the minimum of the two distances, since 
   * we've to minimize the Manhattan distance by excluding one point.
   * 
   * TC: O(n) SC: O(1)
   * #array #math #hard
   */
  public int minimumDistance(int[][] points) {
    int []r = manhattanPoints(points, -1);
    return Math.min(manhattanPoints(points, r[1])[0], manhattanPoints(points, r[2])[0]);
  }
  
  private int[] manhattanPoints(int [][]pts, int skip){
    int maxsum = Integer.MIN_VALUE, minsum = Integer.MAX_VALUE;
    int maxsumi = -1, minsumi = -1;
    int maxdiff = Integer.MIN_VALUE, mindiff = Integer.MAX_VALUE;
    int maxdiffi = -1, mindiffi = -1;
    
    for(int i=0; i<pts.length; i++){
      if(i == skip) continue;
      if(maxsum < (pts[i][0]+pts[i][1])){
        maxsum = pts[i][0]+pts[i][1];
        maxsumi = i;
      }
      if(minsum > (pts[i][0]+pts[i][1])){
        minsum = pts[i][0]+pts[i][1];
        minsumi = i;
      }
      if(maxdiff < (pts[i][0]-pts[i][1])){
        maxdiff = pts[i][0]-pts[i][1];
        maxdiffi = i;
      }
      if(mindiff > (pts[i][0]-pts[i][1])){
        mindiff = pts[i][0]-pts[i][1];
        mindiffi = i;
      }
    }
    int diffd = Math.abs(maxdiff-mindiff), sumd = Math.abs(maxsum-minsum);
    if(diffd>sumd) return new int[]{diffd, mindiffi, maxdiffi};
    return new int[]{sumd, minsumi, maxsumi};
  }
}