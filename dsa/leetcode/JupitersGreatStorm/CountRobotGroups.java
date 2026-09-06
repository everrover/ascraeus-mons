package dsa.leetcode.JupitersGreatStorm;

import java.util.*;

/**
 * https://leetcode.com/problems/count-robot-groups/
 *
 * At t = 0 every gap already at most `distance` collapses instantly, so first coalesce consecutive robots into blocks wherever `position[i+1] - position[i] <= distance`; each block is represented by its rightmost (fastest-position-wise) robot, since a merged group always inherits the rightmost member's position and speed.
 *
 * Scan these block representatives from right to left, keeping `curr` = the speed of the nearest finalized group to the right. For the block just to the left:
 * - if its initial gap to `curr`'s group is already <= distance, it has already merged with it (handled by the block-forming step above via `continue`);
 * - otherwise, since positions only diverge or converge linearly, the two eventually meet iff the left block's speed exceeds `curr` (it must catch up because it is behind); if `speed[idx] > curr` it will inevitably merge into the same group, so also `continue`;
 * - if neither holds, no amount of time ever makes them collide (their separation is monotonically non-decreasing), so it survives as its own final group: increment `res` and set `curr = speed[idx]`.
 *
 * This right-to-left single pass is correct because a group's future merge behavior toward the left only depends on the speed of the nearest already-finalized group to its right, not on anything further away (once a merge happens, the slower groups' speed is irrelevant to what's already merged).
 *
 * TC: O(n) SC: O(1)
 * #array #greedy #medium
 */

class CountRobotGroups {
    public int countGroups(int[] position, int[] speed, int distance) {
        int res = 1;
        int curr = speed[speed.length-1];
        for(int idx=speed.length-2; idx>=0; idx--){
            // merge or due to higher speed of the robo before it'll catch up
            if(position[idx+1] - position[idx] <= distance || speed[idx] > curr) continue;
            res++;
            curr = speed[idx];
        }

        return res;
    }
}
