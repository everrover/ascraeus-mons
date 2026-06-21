package dsa.leetcode.JupitersGreatStorm;

import java.util.*;

/**
 * https://leetcode.com/problems/minimum-lights-to-illuminate-a-road/
 *
 * Two-pass greedy. First, use a difference array to mark all positions already illuminated by existing bulbs (each bulb at i with value v covers [max(0,i-v), min(n-1,i+v)]). Then scan left to right: on finding the first dark position i, greedily place a new bulb at i+1 (which covers i, i+1, i+2 — the maximum rightward reach), increment the count, and jump ahead 3. This greedy choice is optimal because placing any further right would leave i uncovered, and placing further left wastes coverage.
 *
 * TC: O(n) SC: O(n)
 * #array #greedy #difference-array #medium
 */

class MinimumLightsToIlluminateARoad {
    // 
    public int minLights(int[] lights) {
        int n = lights.length;
        int[] ravelunico = lights.clone();
        int[] diff = new int[n + 1];
        for (int i = 0; i < n; i++) {
            int v = ravelunico[i];
            if (v > 0) {
                int left = Math.max(0, i - v);
                int right = Math.min(n - 1, i + v);
                diff[left]++;
                diff[right + 1]--;
            }
        }
        boolean[] illuminated = new boolean[n];
        int running = 0;
        for (int i = 0; i < n; i++) {
            running += diff[i];
            if (running > 0) {
                illuminated[i] = true;
            }
        }
        int res = 0;
        int i = 0;
        while (i < n) {
            if (!illuminated[i]) {
                // Place bulb at i+1, covers i, i+1, i+2
                res++;
                i += 3;
            } else {
                i++;
            }
        }
        
        return res;
    }
}
