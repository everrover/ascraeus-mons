package dsa.leetcode.mithrim_montez;

public class MaximumManhattanDistanceAfterKChanges {

    /**
     * https://leetcode.com/problems/maximum-manhattan-distance-after-k-changes/description/
     *
     * You can apply up to k changes to maximize the Manhattan distance in any chosen direction.
     * This algorithm keeps track of how many steps are taken in each direction and performs character changes to optimize the result.
     *
     * TC: O(n) SC: O(1)
     * #greedy #string #manhattan-distance #medium
     */

    public int maxManhattanDistance(String s, int k) {
        int n = 0, e = 0, s = 0, w = 0, res = 0;
        int kt = k, tod = 0;
        for(char ch: s.toCharArray()){
            if(ch == 'N') n++;
            else if(ch == 'S') s++;
            else if(ch == 'E') e++;
            else if(ch == 'W') w++;
        }
        if(e > w) {
            tod += Math.min(s, kt);
            kt -= Math.min(s, kt);
        } else {
            kt -= Math.min(n, kt);
            tod += Math.min(n, kt);
        }
        return Math.abs(n - s) + Math.abs(e - w) + 2 * tod;
    }
}