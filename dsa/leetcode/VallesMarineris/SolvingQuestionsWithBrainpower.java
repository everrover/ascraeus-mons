package dsa.leetcode.VallesMarineris;

import java.util.*;

public class SolvingQuestionsWithBrainpower {

  /**
   * https://leetcode.com/problems/solving-questions-with-brainpower/description/?envType=daily-question&envId=2025-04-01
   *
   * Use Dynamic Programming to decide optimal decisions for each question.
   * Store maximum points at each question starting index.
   * If skipping, use the next question's points.
   * If solving, add current points to the next possible question.
   *
   * TC: O(n) SC: O(n)
   * #array #dynamic-programming #medium
   */

  public long mostPoints(int[][] questions) {
    long[] dp = new long[questions.length];
    Arrays.fill(dp, -1);
    return dfs(0, questions, dp);
  }

  private long dfs(int idx, int[][] q, long[] dp) {
    if (idx >= q.length) return 0L;
    if (dp[idx] != -1) return dp[idx];

    // Max of solving or skipping the question
    return dp[idx] = Math.max(
      dfs(idx + 1, q, dp), // Skip current question
      q[idx][0] + dfs(idx + q[idx][1] + 1, q, dp) // Solve current question
    );
  }

  public static void main(String[] args) {
    Solution sol = new Solution();
    int[][] questions = {{3,2}, {4,3}, {4,4}, {2,5}};
    System.out.println(sol.mostPoints(questions)); // Output: 5
  }
}