package dsa.leetcode.VallesMarineris;

// https://leetcode.com/problems/maximize-the-minimum-game-score/description/
// 
// Use binary search to maximize the minimum value in gameScore.
// For a possible score, distribute points by attempting to balance moves by iterating
// back and forth using binary search principles to spread values across the gameScore array.
// TC: O(n log(max(points))) SC: O(1)
// #array #binary-search #greedy #hard

public class MaximizeTheMinimumGameScore {
  public boolean poss(final long target, int[] points, final int M) {
    long moves = 0, extramoved = 0, singlesteps = 0;
    for (int p : points) {
      // Calculate the required steps without any extra moves
      long stepsreqd = (target + p - 1L) / p;
      if (extramoved >= stepsreqd) { // Requirement satisfied due to previous moves
        extramoved = stepsreqd - 1; // Reduce extra move
      } else {
        // Extra move adjustments to satisfy current point
        extramoved = stepsreqd - 1;
        moves += 2 * stepsreqd - 1 + singlesteps;
        singlesteps++;
      }
      long stepsreqdwithoutextra = stepsreqd - extramoved;
      extramoved = stepsreqdwithoutextra - 1; // A-B skip's B part
    }
    return moves <= M;
  }
}