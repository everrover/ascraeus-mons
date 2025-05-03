package dsa.leetcode.KuiperBelt;

import java.util.*;
import java.util.stream.Collectors;

public class RobotCollisions {

  /**
   * https://leetcode.com/problems/robot-collisions/
   *
   * Process the robots in the order of their positions to ensure that we process the collisions correctly.
   * Use a stack to keep track of the surviving robots. Iterate through the positions and check the current robot
   * against the top of the stack to determine if a collision occurs. Resolve collisions by comparing healths
   * and update accordingly.
   *
   * TBH it wasn't that hard...
   *
   * TC: O(n log n) SC: O(n)
   * #stack #array #sorting #simulation #hard
   */

  private static class T {
    public int p, h, i;
    public boolean il;

    public T(int i, int p, int h, char s) {
      this.i = i;
      this.p = p;
      this.h = h;
      this.il = s == 'L';
    }
  }
  public List<Integer> survivedRobotsHealths(int[] positions, int[] healths, String directions) {
    
    T ts[] = new T[positions.length];
    for (int i = 0; i < positions.length; i++) {
      ts[i] = new T(i, positions[i], healths[i], directions.charAt(i));
    }
    Arrays.sort(ts, (a, b) -> (a.p - b.p));
    Stack<T> s = new Stack<>();
    List<T> res = new ArrayList<>(ts.length);
    int i = -1;
    while (++i < ts.length) {
      if (ts[i].il) {
        while (ts[i].h > 0 && !s.isEmpty()) {
          if (s.peek().h > ts[i].h) { ts[i].h = 0; s.peek().h--; }
          else if (s.peek().h == ts[i].h) { ts[i].h = 0; s.peek().h = 0; }
          else { ts[i].h--; s.peek().h = 0; }
          if (s.peek().h == 0) s.pop();
        }
        if (s.isEmpty() && ts[i].h > 0) res.add(ts[i]);
      } else {
        s.push(ts[i]);
      }
    }
    while (!s.isEmpty()) res.add(s.pop());
    Collections.sort(res, (a, b) -> (a.i - b.i));
    return res.stream().map(a -> a.h).collect(Collectors.toList());
  }
}