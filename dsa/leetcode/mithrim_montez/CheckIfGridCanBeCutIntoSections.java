package dsa.leetcode.mithrim_montez;

import java.util.*;

class CheckIfGridCanBeCutIntoSections {
  /**
   * https://leetcode.com/problems/check-if-grid-can-be-cut-into-sections/description/
   *
   * To determine if a grid of size n x n can be split into sections with two cuts, consider sorting
   * the rectangles by x and y coordinates separately. Attempt to find positions where vertical and
   * horizontal cuts can leave at least one rectangle in each of the three sections formed by those cuts.
   *
   * TC: O(m log m) SC: O(m)
   * #array #sorting #geometry #medium
   */
  public boolean checkValidCuts(int n, int[][] rectangles) {
    int [][]y = new int[rectangles.length*2][3];
    int [][]x = new int[rectangles.length*2][3];
    for(int i=0; i<rectangles.length; i++){
      y[i*2][0]=rectangles[i][1];
      y[i*2+1][0]=rectangles[i][3];
      x[i*2][0]=rectangles[i][0];
      x[i*2+1][0]=rectangles[i][2];
      y[i*2][1]=y[i*2+1][1]=x[i*2][1]=x[i*2+1][1]=i;
      y[i*2][2]=x[i*2][2]=1; y[i*2+1][2]=x[i*2+1][2]=0;
    }
    Arrays.sort(x, (a,b)->(a[0]==b[0])?(a[2]-b[2]):(a[0]-b[0]));
    Arrays.sort(y, (a,b)->(a[0]==b[0])?(a[2]-b[2]):(a[0]-b[0]));
    int xc = 0, yc = 0;
    Set<Integer> xs = new HashSet<>();
    Set<Integer> ys = new HashSet<>();
    for(int i=0; i<x.length; i++){
      if(x[i][2] == 1) xs.add(x[i][1]);
      else xs.remove(x[i][1]);
      if(y[i][2] == 1) ys.add(y[i][1]);
      else ys.remove(y[i][1]);

      if(xs.isEmpty()) xc++;
      if(ys.isEmpty()) yc++;

    }
    return xc > 2 || yc > 2;
  }
}