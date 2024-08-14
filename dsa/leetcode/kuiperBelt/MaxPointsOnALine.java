package dsa.leetcode.kuiperBelt;

import java.util.HashMap;
import java.util.Map;

public class MaxPointsOnALine {

    /**
     * https://leetcode.com/problems/max-points-on-a-line/
     * 
     * 
     * Iterate over each point and calculate slope for each other point. Use a hashmap to store slopes.
     * The key of hashmap will be slope and value will be the frequency of each slope.
     * Finally, return the maximum value from the hashmap.
     * 
     * TC: O(n^2) SC: O(n)
     * #array #hash-table #math #geometry #hard
     */

    public int maxPoints(int[][] points) {
        if (points.length == 1) return 1;
        int res = 0;
        for (int i = 0; i < points.length; i++) {
            Map<Double, Integer> m = new HashMap<>();
            for (int j = i + 1; j < points.length; j++) {
                double mx = (0.0 + points[j][0] - points[i][0]);
                double my = (0.0 + points[j][1] - points[i][1]);
                double e = my == 0 ? 0.0 : (mx == 0 ? Double.MAX_VALUE : (my / mx));
                m.put(e, m.getOrDefault(e, 1) + 1);
                res = Math.max(res, m.get(e));
            }
        }
        return res;
    }
}