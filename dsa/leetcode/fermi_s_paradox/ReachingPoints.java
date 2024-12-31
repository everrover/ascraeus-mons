package dsa.leetcode.fermi_s_paradox;

public class ReachingPoints {

    /**
     * https://leetcode.com/problems/reaching-points/description/?envType=company&envId=goldman-sachs&favoriteSlug=goldman-sachs-all
     *
     * We backtrack from the target point (tx, ty) to the starting point (sx, sy) by reversing the allowed operations.
     * The operation can be seen as repeating steps backward which reduce either x or y component.
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
        return (sx == tx && sy <= ty && (ty - sy) % sx == 0) || 
               (sy == ty && sx <= tx && (tx - sx) % sy == 0);
    }
}