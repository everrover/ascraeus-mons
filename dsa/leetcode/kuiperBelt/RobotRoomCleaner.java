package dsa.leetcode.kuiperBelt;

/**
 * https://leetcode.com/problems/robot-room-cleaner/
 * 
 * It utilizes depth-first search (DFS) algorithm to explore all reachable areas in the grid, marking visited cells.
 * The robot can move forward, turn left, and turn right, attempting to clean every accessible spot.
 * 
 * Messed up going only backwards part, just moved forward, left and right along with backtracking
 * TC: O(m*n) SC: O(m*n)
 * #depth-first-search #simulation #hard
 */

// This is the robot's control interface.
// You should not implement it, or speculate about its implementation
interface Robot {
    // Returns true if the cell in front is open and robot moves into the cell.
    // Returns false if the cell in front is blocked and robot stays in the current cell.
    public boolean move();

    // Robot will stay in the same cell after calling turnLeft/turnRight.
    // Each turn will be 90 degrees.
    public void turnLeft();
    public void turnRight();

    // Clean the current cell.
    public void clean();
}

class Solution {
    private static Robot r; private static int dir=0;
    private static int [][]moves = new int[][]{{-1,0},{0,1},{1,0},{0,-1}};
    private void recurse(int face, int i, int j, int [][]grid){
        grid[i][j] = 1; r.clean();

        for(int nface = face; nface<face+4; nface++){
            int nx = i+moves[nface%4][0], ny = j+moves[nface%4][1];
            if(grid[nx][ny] == 0){
                if(r.move()) recurse(nface, nx, ny, grid);
                else grid[nx][ny] = -1;
            }
            r.turnRight();
        }

        // backtrack
        r.turnLeft();
        r.turnLeft();
        r.move();
        r.turnLeft();
        r.turnLeft();
    }
    public void cleanRoom(Robot robot) {
        r = robot;
        int [][]grid = new int[202][402];//  1=visited,-1=walled,0=notvisited
        recurse(0, 100, 200, grid);
        System.out.println("Robot cleaned all rooms.");
    }
}
