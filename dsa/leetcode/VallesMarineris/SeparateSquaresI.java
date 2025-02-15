package dsa.leetcode.VallesMarineris;

public class SeparateSquaresI {

  /**
   * https://leetcode.com/problems/separate-squares-i/description/
   *
   * What i got stuck on was type-casting the result of the area calculation to double.
   * 
   * Otherwise it's a binary-search problem over solution space. Where the line we sweep is the y-axis
   * and is our solution space.
   *
   * TC: O(n log(maxY)) SC: O(1)
   * #binary-search #geometry #medium
   */

   final double eps = 10e-7;
   public double separateSquares(int[][] squares) {
     double high = 0.0, low = 0.0;
     for(int []sq: squares) {
       high = Math.max(high, sq[1]+sq[2]);
       low = Math.min(low, sq[1]);
     }
     while(Math.abs(high-low) > eps){
       double mid = (low+high)/2;
       double areadiff = findAreaBelow(mid, squares);
       if(areadiff > 0) low  = mid;
       else high = mid;
     }
     return (low+high)/2.0;
   }
 
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
     return aa-ab;
   }

}