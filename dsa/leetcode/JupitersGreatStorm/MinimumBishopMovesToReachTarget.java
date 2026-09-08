package dsa.leetcode.JupitersGreatStorm;

import java.util.*;

/**
 * https://leetcode.com/problems/minimum-bishop-moves-to-reach-target/
 *
 * A bishop's reachable set from any square is exactly the 8x8 board's diagonals of one color, and since it can slide any distance in a single move, every square lying on the same diagonal as the current square is at distance +1 from it, regardless of how far along that diagonal it is — so BFS layers correspond exactly to move count.
 *
 * If `source == target` is excluded by the constraints, the answer is at most 2: reachable in 1 move iff same color and on a shared diagonal (`r-c` or `r+c` matches), otherwise reachable in 2 moves (through any intermediate square sharing a diagonal with both) unless colors differ, in which case it's unreachable (-1).
 *
 * Optimizations applied to the captured BFS (kept as a general BFS rather than special-casing the closed-form 0/1/2/-1 answer above, since the algorithm itself wasn't meant to change):
 * - Mark a square visited the moment it's enqueued (`mark[x][y] = true` right before `offer`), not when it's dequeued. The original marked on dequeue, so the same square could be pushed onto the queue once per ray that reaches it before any of those duplicates got popped — wasted queue growth that's easy to avoid since a bishop's rays only add squares, never remove them.
 * - Check target while walking each ray and return immediately (`mv.score + 1`) instead of waiting for that node to reach the front of the queue — since every square found this way is already known to be at the correct BFS depth, there's no need to requeue-then-immediately-dequeue-and-check.
 * - Collapsed the four ray-walking loops into one loop over a `dirs` array to remove duplication; behavior is unchanged.
 *
 * TC: O(1) SC: O(1)
 * #matrix #bfs #chessboard #medium
 */

class MinimumBishopMovesToReachTarget {
    public static class P{
        public int x, y, score;
        P(int x, int y, int score){
            this.x = x;
            this.y = y;
            this.score = score;
        }
    }
    public int minBishopMoves(int[] source, int[] target) {
        boolean [][]mark = new boolean[9][9];
        Queue<P> moveTo = new LinkedList<>();
        moveTo.offer(new P(source[0], source[1], 0));
        mark[source[0]][source[1]] = true;
        int[][] dirs = {{1,1},{-1,1},{-1,-1},{1,-1}};
        while(!moveTo.isEmpty()){
            P mv = moveTo.poll();
            if(mv.x == target[0] && mv.y == target[1]) return mv.score;
            for(int[] d : dirs){
                for(int x=mv.x+d[0], y=mv.y+d[1]; x>=1&&x<=8&&y>=1&&y<=8; x+=d[0], y+=d[1]){
                    if(mark[x][y]) continue;
                    mark[x][y] = true;
                    if(x == target[0] && y == target[1]) return mv.score+1;
                    moveTo.offer(new P(x,y,mv.score+1));
                }
            }
        }
        return -1;
    }
}
