package dsa.leetcode.KuiperBelt;

/**
 * https://leetcode.com/problems/robot-return-to-origin/
 * The code iterates through the string of moves and adjusts the x and y coordinates
 * based on the move. 'U' and 'D' affect the y-coordinate, while 'L' and 'R' affect the x-coordinate.
 * It returns true if and only if the robot returns to the origin (0,0).
 * 
 * TC: O(n) SC: O(1)
 * #simulation #string #easy
 */
public class RobotReturnToOrigin {
  public boolean judgeCircle(String moves) {
    int x = 0, y = 0;
    for(int i=0; i<moves.length(); i++){
      if(moves.charAt(i) == 'U') y++;
      else if(moves.charAt(i) == 'D') y--;
      else if(moves.charAt(i) == 'L') x--;
      else x++;
    }
    return x == 0 && y == 0;
  }
}