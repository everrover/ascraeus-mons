package dsa.leetcode.VallesMarineris;

import java.util.*;

public class DesignSnakeGame {

  // URL: https://leetcode.com/problems/design-snake-game/description/?envType=weekly-question&envId=2025-05-01
  /*
   * I used a q to keep track of the snake's body's tail and a set to keep track of the occupied cells to check
   * if the snake has hit itself. The snake's head is represented by a pair of coordinates, and the snake's body
   * is represented by a queue of coordinates. The snake moves in the direction specified by the input string,
   * and the snake's head is updated accordingly. If the snake eats food, the score is incremented and the food
   * index is updated. If the snake hits the wall or itself, the game ends and -1 is returned.
   * 
   * TC: O(1) per move SC: O(N) where N is the number of cells in grid
   * #array #hash-table #design #queue #simulation #medium
   */

   class SnakeGame {

    private int width;
    private int height;
    private int[][] food;
    private int foodIndex;
    private int score;
    private int[] currentHead;
    private Queue<int[]> snake;
    private Set<String> occupied;
  
    public SnakeGame(int width, int height, int[][] food) {
      this.width = width;
      this.height = height;
      this.food = food;
      this.foodIndex = 0;
      this.score = 0;
      this.snake = new LinkedList<>();
      this.occupied = new HashSet<>();
      currentHead = new int[]{0, 0};
      snake.offer(new int[]{0, 0});
      occupied.add("0,0");
    }
  
    public int move(String direction) {
      int[] movement = {0, 0};
  
      switch (direction) {
        case "U": movement = new int[]{-1, 0}; break;
        case "D": movement = new int[]{1, 0}; break;
        case "L": movement = new int[]{0, -1}; break;
        case "R": movement = new int[]{0, 1}; break;
      }
  
      currentHead = new int[]{currentHead[0] + movement[0], currentHead[1] + movement[1]};
  
      // Check if snake hits the boundary
      if (currentHead[0] < 0 || currentHead[1] < 0 || currentHead[0] >= height || currentHead[1] >= width) {
          return -1;
      }
  
      // Check if snake hits itself
      String currentHeadPos = currentHead[0] + "," + currentHead[1];
      if (occupied.contains(currentHeadPos) && !currentHeadPos.equals(snake.peek()[0] + "," + snake.peek()[1])) {
        return -1;
      }
  
      // Check if snake eats the food
      if (foodIndex < food.length && currentHead[0] == food[foodIndex][0] && currentHead[1] == food[foodIndex][1]) {
          score++;
          foodIndex++;
      } else {
          int[] tail = snake.poll();
          occupied.remove(tail[0] + "," + tail[1]);
      }
  
      snake.offer(new int[]{currentHead[0], currentHead[1]});
      occupied.add(currentHeadPos);
  
      return score;
    }
  }
  
  /**
   * Your SnakeGame object will be instantiated and called as such:
   * SnakeGame obj = new SnakeGame(width, height, food);
   * int param_1 = obj.move(direction);
   */
}