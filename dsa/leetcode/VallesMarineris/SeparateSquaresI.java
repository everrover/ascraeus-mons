package dsa.leetcode.VallesMarineris;

public class SeparateSquaresI {

  /**
   * https://leetcode.com/problems/separate-squares-i/description/
   *
   * The task is to find a horizontal line such that the total area of squares above the line equals the total area of squares below the line.
   * Binary search can be applied over the y-coordinates to find the minimum y-value satisfying this condition.
   *
   * TC: O(n log(maxY)) SC: O(1)
   * #binary-search #geometry #medium
   */

  private double findAreaBelow(double ydx, int [][]squares){
    double aa = 0.0, ab = 0.0;
    for(int []sq: squares){
      if(sq[1] >= ydx) {
        aa += (double)sq[2]*(double)sq[2];
      } else if(sq[1]+sq[2] <= ydx) {
        ab += (double)sq[2]*(double)sq[2];
      } else {
        aa += (double)sq[2]*((double)sq[1]+(double)sq[2]-ydx);
        ab += (double)sq[2]*(ydx-(double)sq[1]);
      }
    }
    return (low+high)/2.0;
  }

}