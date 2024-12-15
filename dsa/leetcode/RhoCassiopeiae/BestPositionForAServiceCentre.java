package dsa.leetcode.RhoCassiopeiae;

public class BestPositionForAServiceCentre {

    /**
    * https://leetcode.com/problems/best-position-for-a-service-centre/
    *
    * The problem is to find the geometric median of a set of points on a 2D plane,
    * which minimizes the sum of Euclidean distances from a point to a set of given points.
    * A heuristic approach is used by iterating over possible positions with decreasing step size
    * until the improvement is smaller than a certain precision threshold.
    *
    * TC: O(n * precision) SC: O(1)
    * #array #math #geometry #randomized #hard
    */

    public double getMinDistSum(int[][] positions) {
        double res = Double.MAX_VALUE;
        double x = 50, y = 50, delta = 50;
        double resx = 50, resy = 50;
        while (delta >= 1e-6) { // Loop until the step size is smaller than the precision threshold
            boolean found = false;
            for (int i = -1; i <= 1; i++) {
                for (int j = -1; j <= 1; j++) {
                    double nx = resx + delta * i;
                    double ny = resy + delta * j;
                    double tmp = 0;
                    for (int[] pos : positions) { // Compute the total distance from this point
                        tmp += Math.sqrt((pos[0] - nx) * (pos[0] - nx) + (pos[1] - ny) * (pos[1] - ny));
                    }
                    if (tmp < res) { // Found a better position
                        res = tmp;
                        x = nx;
                        y = ny;
                        found = true;
                    }
                }
            }
            if (!found) { // Reduce step size if no better position is found
                delta /= 2;
            } else {
                resx = x;
                resy = y;
            }
        }
        return res;
    }
}