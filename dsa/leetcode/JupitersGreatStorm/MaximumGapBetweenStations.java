package dsa.leetcode.JupitersGreatStorm;

import java.util.*;

/**
 * https://leetcode.com/problems/maximum-gap-between-stations/
 *
 * For each adjacent worker pair (i-1, i), the maximum realizable gap uses two independent assignments simultaneously: everyone before i pushed to their earliest possible station, everyone from i onward pushed to their latest possible station. These can't be combined into one shared assignment (see 4026's wrong-answer variant, which tried exactly that), but the two arrays needed are each computable in a single greedy pass:
 * - `e[i]` (earliest): scanning skill/station left to right, matching each skill char to the first unused station char that could follow the previous match — this is exactly the standard subsequence-matching two-pointer.
 * - `l[i]` (latest): the mirror image, scanning both strings right to left.
 *
 * Once both arrays exist, the answer is `max over i>=1 of l[i] - e[i-1]`, since that's the largest gap any adjacent pair can realize while both halves of the assignment remain individually valid.
 *
 * Optimization (kept in comments below, alongside the captured/accepted version above): the third loop only depends on `e[]` and `l[i]`, and by the time the latest-pass loop computes `l[i]`, `e[i-1]` was already fully computed by the earlier earliest-pass loop — so `res` can be updated directly inside the latest-pass loop, removing the separate O(n) scan (a constant-factor speedup, complexity class unchanged).
 *
 * TC: O(n+m) SC: O(n)
 * #string #two-pointers #greedy #medium
 */

class MaximumGapBetweenStations {
    public int maximumGap(String skill, String station) {
        if(skill.length() == 1) return 0;
        int lk, rk, ls, rs;
        lk = ls = 0;
        rk = skill.length()-1; rs = station.length()-1;
        int e[] = new int[skill.length()];
        int l[] = new int[skill.length()];
        int res = 0;
        while(lk<skill.length()){
            while(ls <= station.length()){
                if(skill.charAt(lk) == station.charAt(ls)){
                    e[lk] = ls;
                    break;
                }
                ls++;
            }
            lk++; ls++;
        }
        while(rk>=0){
            while(rs >=0){
                if(skill.charAt(rk) == station.charAt(rs)){
                    l[rk] = rs;
                    break;
                }
                rs--;
            }
            rk--; rs--;
        }
        for(int i=1; i<l.length; i++){
            res = Math.max(res, l[i]-e[i-1]);
        }
        return res;
    }

    /*
     * Optimization: the third loop only reads e[] and l[], both of which are
     * fully known by the time the earliest-pass and latest-pass loops finish.
     * l[i] is produced in decreasing i order in the latest-pass loop, and by
     * then e[i-1] was already computed in the earliest-pass loop above it, so
     * res = max(l[i] - e[i-1]) can be folded straight into that same loop,
     * removing the separate scan entirely. Renamed for clarity: e -> earliest,
     * l -> latest, lk/rk -> leftWorker/rightWorker, ls/rs -> leftStation/rightStation.
     *
     * public int maximumGap(String skill, String station) {
     *     int n = skill.length();
     *     if (n == 1) return 0;
     *     int m = station.length();
     *     int[] earliest = new int[n];
     *
     *     int leftWorker = 0, leftStation = 0;
     *     while (leftWorker < n) {
     *         while (leftStation <= m) {
     *             if (skill.charAt(leftWorker) == station.charAt(leftStation)) {
     *                 earliest[leftWorker] = leftStation;
     *                 break;
     *             }
     *             leftStation++;
     *         }
     *         leftWorker++; leftStation++;
     *     }
     *
     *     int res = 0;
     *     int rightWorker = n - 1, rightStation = m - 1;
     *     while (rightWorker >= 0) {
     *         while (rightStation >= 0) {
     *             if (skill.charAt(rightWorker) == station.charAt(rightStation)) {
     *                 if (rightWorker >= 1) {
     *                     res = Math.max(res, rightStation - earliest[rightWorker - 1]);
     *                 }
     *                 break;
     *             }
     *             rightStation--;
     *         }
     *         rightWorker--; rightStation--;
     *     }
     *     return res;
     * }
     */
}
