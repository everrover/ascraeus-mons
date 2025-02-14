package dsa.leetcode.VallesMarineris;

// https://leetcode.com/problems/maximize-the-minimum-game-score/description/
// 
// Use binary search to maximize the minimum value in gameScore.
// For a possible score, distribute points by attempting to balance moves by iterating
// back and forth using binary search principles to spread values across the gameScore array.
// 
// TC: O(n log(max(result))) SC: O(1)
// #array #binary-search #greedy #hard #binary-search-over-solution-space

public class MaximizeTheMinimumGameScore {
  // for a given `target` we greedily check if it is possible to achieve it
  // by greedily satisfying the target for each point in the array starting from left to right
  public boolean poss(final long target, int[] points, final int M) {
    // singlesteps is the number of points that can be traversed through in a single step
    long moves = 0, extramoved = 0, singlesteps = 0;
    for (int p : points) {
      // For a given incorrect A, we move back and forth between A and B
      // to satisfy the target for A, this takes 2*n-1 moves
      // where n = stepsreqd
      long stepsreqd = (target + p - 1L) / p;
      if (extramoved >= stepsreqd) { // req satisfied because of prev iteration
        extramoved = 0; 
        singlesteps++;
      } else {
        long stepsreqdwithoutextra = stepsreqd - extramoved;
        // singlesteps is only to be added for intermediate operations
        moves += 2 * stepsreqdwithoutextra - 1 + singlesteps;
        // for a given incorrect A, A-B skip's B part
        extramoved = stepsreqdwithoutextra - 1;
      }
      if (moves > M) return false;
    }
    return moves <= M;
  }
    
  public long maxScore(int[] pts, int M) {
    if (M < pts.length) return 0;
    long lo = 1, hi = (long)1e18, ans = 0;
    while (lo <= hi) {
      long mid = lo + (hi - lo) / 2;
      if (poss(mid, pts, M)) { ans = mid; lo = mid + 1; }
      else hi = mid - 1;
    }
    return ans;
  }
}