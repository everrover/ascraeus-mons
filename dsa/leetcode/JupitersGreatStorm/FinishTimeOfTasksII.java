package dsa.leetcode.JupitersGreatStorm;

import java.util.*;

/**
 * https://leetcode.com/problems/finish-time-of-tasks-ii/description/
 *
 * Rerooting DP in two passes. The finish-time formula simplifies to 2*max - min + baseTime[u] (since latest + (latest - earliest) + base = 2*latest - earliest + base). Pass 1 (post-order from root 0): compute down[v], the finish time of node v when its subtree is treated as a standalone rooted tree. Pass 2 (pre-order): propagate up[v], the finish-time value that the "parent side" contributes when v's subtree is flipped upward. At each node, maintain top-2 max and top-2 min across all neighbor values so that each child's up value can be computed in O(1) by excluding that child's down value. The answer for each node as root is 2*max1 - min1 + baseTime[u] over all neighbor values.
 *
 * TC: O(n) SC: O(n)
 * #tree #dp #rerooting #hard
 */

class FinishTimeOfTasksII {
    public long finishTime(int n, int[][] edges, int[] baseTime) {
        if (n == 1) return baseTime[0];
 
        // CSR adjacency
        int[] head = new int[n], nxt = new int[2 * (n - 1)], to = new int[2 * (n - 1)];
        Arrays.fill(head, -1);
        int idx = 0;
        for (int[] e : edges) {
            to[idx] = e[1]; nxt[idx] = head[e[0]]; head[e[0]] = idx++;
            to[idx] = e[0]; nxt[idx] = head[e[1]]; head[e[1]] = idx++;
        }
 
        // DFS order (parents before children), rooted at 0
        int[] parent = new int[n], order = new int[n], stack = new int[n];
        int sp = 0, cnt = 0;
        parent[0] = -1;
        stack[sp++] = 0;
        while (sp > 0) {
            int u = stack[--sp];
            order[cnt++] = u;
            for (int e = head[u]; e != -1; e = nxt[e]) {
                int v = to[e];
                if (v != parent[u]) { parent[v] = u; stack[sp++] = v; }
            }
        }
 
        // Pass 1 (post-order): down[v] = f(parent -> v)
        long[] down = new long[n];
        for (int i = n - 1; i >= 0; i--) {
            int u = order[i];
            long mx = Long.MIN_VALUE, mn = Long.MAX_VALUE;
            boolean hasChild = false;
            for (int e = head[u]; e != -1; e = nxt[e]) {
                int v = to[e];
                if (v == parent[u]) continue;
                hasChild = true;
                mx = Math.max(mx, down[v]);
                mn = Math.min(mn, down[v]);
            }
            down[u] = hasChild ? 2 * mx - mn + baseTime[u] : baseTime[u];
        }
 
        // Pass 2 (pre-order): up[v] = f(v -> parent), and answer per root
        long[] up = new long[n];
        long ans = Long.MAX_VALUE;
        for (int i = 0; i < n; i++) {
            int u = order[i];
            // Collect all edge values at u: down[c] for children, up[u] for the parent side.
            // Track top-2 max and top-2 min (duplicate-safe: a tie pushes the old top into 2nd).
            long max1 = Long.MIN_VALUE, max2 = Long.MIN_VALUE;
            long min1 = Long.MAX_VALUE, min2 = Long.MAX_VALUE;
            int deg = 0;
            for (int e = head[u]; e != -1; e = nxt[e]) {
                int v = to[e];
                long val = (v == parent[u]) ? up[u] : down[v];
                deg++;
                if (val >= max1) { max2 = max1; max1 = val; } else if (val > max2) max2 = val;
                if (val <= min1) { min2 = min1; min1 = val; } else if (val < min2) min2 = val;
            }
 
            // u as root: uses all neighbor values
            ans = Math.min(ans, 2 * max1 - min1 + baseTime[u]);
 
            // up for each child = combine at u excluding that child's down value
            for (int e = head[u]; e != -1; e = nxt[e]) {
                int v = to[e];
                if (v == parent[u]) continue;
                if (deg == 1) {                      // u has no other neighbor -> leaf w.r.t. v
                    up[v] = baseTime[u];
                } else {
                    long ex = down[v];
                    long mx = (ex == max1) ? max2 : max1;
                    long mn = (ex == min1) ? min2 : min1;
                    up[v] = 2 * mx - mn + baseTime[u];
                }
            }
        }
        return ans;
    }
}
