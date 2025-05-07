package dsa.leetcode.VallesMarineris;

import java.util.*;

public class DesignSnakeGame {

  // URL: https://leetcode.com/problems/design-snake-game/description/?envType=weekly-question&envId=2025-05-01
  /*
   * A snake game on a grid where the snake can move up, down, left, or right.
   * Eating food increases the length and score. The game ends if the snake
   * goes out of bounds or collides with itself.
   * 
   * TC: O(1) per move SC: O(N) where N is the number of cells in grid
   * #array #hash-table #design #queue #simulation #medium
   */

  private Set<String> occupied;
  private Queue<int[]> snake;
  private int[][] food;
  private int width, height, score, foodIndex;

  public DesignSnakeGame(int width, int height, int[][] food) {
    this.width = width;
    this.height = height;
    this.food = food;
    this.snake = new LinkedList<>();
    this.snake.offer(new int[]{0, 0});
    this.occupied = new HashSet<>();
    this.occupied.add("0,0");
    this.score = 0;
    this.foodIndex = 0;
  }

  public int move(String direction) {
    int[] currentHead = snake.peek();
    int[] newHead = Arrays.copyOf(currentHead, 2);
    switch (direction) {
      case "U": newHead[0]--; break;
      case "D": newHead[0]++; break;
      case "L": newHead[1]--; break;
      case "R": newHead[1]++; break;
      default: return -1;
    }

    String currentHeadPos = newHead[0] + "," + newHead[1];

    // Check if it hits boundaries or itself
    if (newHead[0] < 0 || newHead[0] >= height || newHead[1] < 0 || newHead[1] >= width ||
        (occupied.contains(currentHeadPos) && !currentHeadPos.equals(snake.peekLast()[0] + "," + snake.peekLast()[1]))) {
      return -1;
    }

    // Eating food
    if (foodIndex < food.length && newHead[0] == food[foodIndex][0] && newHead[1] == food[foodIndex][1]) {
      foodIndex++;
    } else {
      int[] tail = snake.poll();
      occupied.remove(tail[0] + "," + tail[1]);
    }

    snake.offer(newHead);
    occupied.add(currentHeadPos);
    score++;

    return score;
  }
}