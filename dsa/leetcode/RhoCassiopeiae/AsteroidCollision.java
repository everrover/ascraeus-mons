package dsa.leetcode.RhoCassiopeiae;

import java.util.*;

public class AsteroidCollision {

  /**
   * https://leetcode.com/problems/asteroid-collision/
   *
   * The solution uses a stack to keep track of the asteroids as they move.
   * Essentially all positive asteroids will move to the right and all negative asteroids will move to the left.
   * Any out-of-order ones are to be eliminated if they collide.
   *
   * TC: O(n) SC: O(n)
   * #array #stack #simulation #medium
   */

  public int[] asteroidCollision(int[] asteroids) {
    Stack<Integer> st = new Stack<>();
    for (int asteroid : asteroids) {
      if (asteroid > 0) {
        // Push positive asteroid to stack
        st.push(asteroid);
      } else {
        boolean mk = false;
        while (!st.isEmpty() && st.peek() > 0) {
          if (Math.abs(asteroid) > st.peek()) {
            st.pop(); // Negative asteroid is larger, pop the stack
          } else if (Math.abs(asteroid) < st.peek()) {
            mk = true; // Stack asteroid is larger, mark collision
            break;
          } else {
            mk = true;
            st.pop(); // Both asteroids are equal, pop stack and mark collision
            break;
          }
        }
        if (!mk && (st.isEmpty() || st.peek() < 0)) st.push(asteroid); // No collision or only negative asteroids left
      }
    }
    int sz = st.size();
    int[] res = new int[sz];
    while (--sz >= 0) res[sz] = st.pop();
    return res;
  }
}