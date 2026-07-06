package dsa.leetcode.JupitersGreatStorm;

import java.util.*;

/**
 * https://leetcode.com/problems/network-recovery-pathways/description/
 *
 * Binary search on the answer (minimum edge cost threshold). For a given threshold `lt`, only edges with cost >= lt are usable; the check asks whether Dijkstra can reach node n-1 with total cost <= k using only those edges. The key insight: maximizing the minimum edge cost is monotone — if threshold lt works, any smaller threshold also works — so binary search over [minEdge, maxEdge] finds the largest feasible lt. Offline nodes are pruned during adjacency list construction. The check runs Dijkstra for minimum total cost; if the settled cost at n-1 exceeds k the path is invalid.
 *
 * TC: O(m log m log C) SC: O(n + m)
 * #graph #binary-search #dijkstra #shortest-path #hard
 */

class NetworkRecoveryPathways {
    public int findMaxPathScore(int[][] edges, boolean[] online, long k) {
        int n = online.length;
        List<int[]> []adj = new List[n];
        for(int i=0; i<n; i++) adj[i] = new LinkedList<>();
        int left=Integer.MAX_VALUE, right=0, mid, res=-1;
        for(int []edge: edges){
            if(!online[edge[0]] || !online[edge[1]]) continue;
            adj[edge[0]].add(new int[]{edge[1], edge[2]});
            left = Math.min(left, edge[2]);
            right = Math.max(right, edge[2]);
        }
        if(!check(adj, left, k, n)) return -1;
        while(left<=right){
            mid = (left+right)/2;
            if(check(adj, mid, k, n)){
                left = mid+1;
            }else{
                right = mid-1;
            }
        }
        return right;
    }

    public boolean check(List<int[]> []adj, int lt, long k, int n){
        long[] dist = new long[n];
        Arrays.fill(dist, Long.MAX_VALUE);
        dist[0] = 0;

        PriorityQueue<long[]> pq = new PriorityQueue<>((a, b) -> Long.compare(a[1], b[1]));
        pq.offer(new long[]{0, 0});

        while (!pq.isEmpty()) {
            long[] cur = pq.poll();
            int u = (int)cur[0]; long d = cur[1];
            if (d > k) return false;
            if (u == n-1) return true;
            if (d > dist[u]) continue;
            for (int[] edge : adj[u]) {
                int v = edge[0], w = edge[1];
                if (w < lt) continue;
                if (dist[u] + w < dist[v]) {
                    dist[v] = dist[u] + w;
                    pq.offer(new long[]{v, dist[v]});
                }
            }
        }
        return false;
    }
}
