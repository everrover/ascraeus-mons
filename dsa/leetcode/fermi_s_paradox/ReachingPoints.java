package dsa.leetcode.fermi_s_paradox;

public class ReachingPoints {

    /**
     * https://leetcode.com/problems/reaching-points/description/?envType=company&envId=goldman-sachs&favoriteSlug=goldman-sachs-all
     *
     * If we go top-down, we've got a big binary tree and a ton of combinations to check
     * dfs(sx, sy, tx, ty){
     *   if(sx > tx || sy > ty) return false;
     *   if(sx == tx && sy == ty) return true;
     *   return dfs(sx + sy, sy, tx, ty) || dfs(sx, sx + sy, tx, ty);
     * }
     * Per the constraints it doesn't work.
     * 
     * I plotted a map and found going from (sx, sy) to next state
     * For any given multiple, m*sx,n*sy there's only one path to reach it
     * And going to parent state, there are two possibilities
     * x, y = x', y' if x' = x+y, y' = y => x = x' - y = x' - y'
     * x, y = x', y' if x' = x, y' = x+y => y = y' - x = y' - x'
     * 
     * going down tree levels multiples m and n are always increasing.
     * if x' > y' then x' = x + y, y' = y
     * if y' > x' then x' = x, y' = x + y
     * 
     * to accomodate for x, y -> x+y, y -> x+2y, y ... and going up,
     * we use modulo operation.
     * 
     * x, y = x', y' if x' = x+y, y' = y => x = x' % y = x' % y'
     * x, y = x', y' if x' = x, y' = x+y => y = y' % x = y' % x'
     *
     * TC: O(log(max(tx, ty))) SC: O(1)
     * #math #reachability #hard
     */
    public boolean reachingPoints(int sx, int sy, int tx, int ty) {
        while (tx > sx && ty > sy) {
            if (tx > ty) {
                tx %= ty;
            } else {
                ty %= tx;
            }
        }
        // check for last remaining step since we broke at first occurance
        // of tx <= sx, ty <= sy
        return (sx == tx && sy <= ty && (ty - sy) % sx == 0) || 
               (sy == ty && sx <= tx && (tx - sx) % sy == 0);
    }
}