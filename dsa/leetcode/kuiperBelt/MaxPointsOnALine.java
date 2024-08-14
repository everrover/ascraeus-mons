package dsa.leetcode.kuiperBelt;

import java.util.HashMap;
import java.util.Map;

public class MaxPointsOnALine {

    /**
     * https://leetcode.com/problems/max-points-on-a-line/
     * 
     * Used 3-nested iteration to calculate slope and intercept for each pair of points.
     * Then checked the count of points which satisfy slope and intercept for the count.
     * It's TC is O(n^3) and it fails for large inputs. Especially with double values.
     * 
     * y = mx + c
     * Then thought of two point line formulae to calculate slope m = (y2-y1)/(x2-x1) and c = y1 - m*x1
     * 
     * Iterate over each point and calculate slope for each other point. This considers slope and constant automatically
     * while computing the slope. Also, intercept need not be calculated or hashed.
     * 
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