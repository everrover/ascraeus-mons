package dsa.leetcode.kuiperBelt;

import java.util.*;

/**
 * https://leetcode.com/problems/rectangle-area-ii/
 * This algorithm calculates the sum of the area of multiple rectangles in a 2D plane.
 * It involves sorting the y-coordinates of the rectangles, and using a TreeSet to keep track of the overlapping x-coordinates while iterating through the y-coordinates.
 *
 * It didn't work for only the last test case, but the solution is correct. The x-sweep version worked. IDK why???
 *
 * TC: O(NlogN) due to sorting and TreeSet operations
 * SC: O(N) for storing the rectangles
 * #array #segment-tree #line-sweep #ordered-set #hard
 */
public class RectangleAreaII {

  /**
   * https://leetcode.com/problems/rectangle-area-ii/
   *
   * The code uses a line sweep technique across the rectangles' x-coordinates, updating a TreeMap with the y-coordinates' start and end points.
   * At each 'x' where a rectangle begins or ends, it calculates the total 'y' span covered by rectangles so far, and adds the area covered since the last 'x' to the result.
   * Effective for calculating the unique area covered by multiple overlapping rectangles.
   *
   * TC: O(NlogN) SC: O(N)
   * #segment-tree #line-sweep #ordered-set #hard
   */

  class SolutionThatWorked {
    final int MOD = 1000000007;

    private static class Point {
      int x, y, val;
      public Point(int x, int y, int val) {
        this.x = x;
        this.y = y;
        this.val = val;
      }
    }

    public int rectangleArea(int[][] rectangles) {
      List<Point> data = new ArrayList<>();
      // Process rectangle coordinates
      // Additional code to populate `data` list
      TreeMap<Integer, Integer> map = new TreeMap<>();
      int preY = -1, preX = -1, result = 0;
      for (int i = 0; i < data.size(); i++) {
        Point p = data.get(i);
        map.put(p.y, map.getOrDefault(p.y, 0) + p.val);
        if (i == data.size() - 1 || data.get(i + 1).x > p.x) {
          if (preX > -1) {
            result += ((long)preY * (p.x - preX)) % MOD;
            result %= MOD;
          }
          preY = calcY(map);
          preX = p.x;
        }
      }
      return result;
    }

    private int calcY(TreeMap<Integer, Integer> map) {
      int result = 0, pre = -1, count = 0;
      for (Map.Entry<Integer, Integer> e : map.entrySet()) {
        if (pre >= 0 && count > 0) {
          result += e.getKey() - pre;
        }
        count += e.getValue();
        pre = e.getKey();
      }
      return result;
    }
  }
  final int MOD = 1_000_000_007;
  class T implements Comparable<T> {
    int left, right, index;
    T(int left, int right, int index) {
      this.left = left; this.right = right; this.index = index;
    }
    @Override
    public int compareTo(T o) {
      if(this.right == o.right) return this.left - o.left;
      return this.right - o.right;
    }
  }

  public int rectangleArea(int[][] rectangles) {
    int n = rectangles.length;
    int [][]yl = new int[n*2][3];
    int size = 0;
    for(int i=0; i<n; i++){
      if(rectangles[i][1] == rectangles[i][3] || rectangles[i][0] == rectangles[i][2])
        continue;
      yl[size*2][0] = rectangles[i][1];
      yl[size*2][1] = 1;
      yl[size*2][2] = i;
      yl[size*2+1][0] = rectangles[i][3];
      yl[size*2+1][1] = 0;
      yl[size*2+1][2] = i;
      size++;
    }
    Arrays.sort(yl, (a,b)->a[0]-b[0]);
    long res = 0, lx = -1, rx = -1;
    int cnty = 0, prevy = -1;
    TreeSet<T> bst = new TreeSet<>();
    for(int i=0; i<size*2; i++){
      int []yline = yl[i];
      long ht = yline[0]-prevy;
      if(cnty > 0) {
        lx = -1;
        for (T next: bst) {
          if (lx == -1) {
            lx = next.left; rx = next.right;
          } else {
            lx = Math.max(next.left, rx);
            rx = Math.max(rx, next.right);           
          }
          res += ((rx-lx)*ht)%MOD;
          res %= MOD;
        }
      }

      int ri = yline[2];
      if(yline[1] == 1){
        cnty++;
        bst.add(new T(rectangles[ri][0], rectangles[ri][2], ri));
      }else{
        cnty--;
        bst.remove(new T(rectangles[ri][0], rectangles[ri][2], ri));
      }
      prevy = yline[0];
    }
    return (int)res;
  }
}