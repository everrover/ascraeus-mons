package dsa.leetcode.Ipestus;

import java.util.*;

public class ValidateBinaryTreeNodes {

    /**
     * https://leetcode.com/problems/validate-binary-tree-nodes/
     *
     * To determine if the nodes form a valid binary tree, ensure:
     * 1. Only one root exists
     * 2. During DFS no cycle exists to a visited node(parents/ancestors)
     * 3. All nodes are accessed in the traversal (connected)
     * Using arrays to identify parent nodes and check conditions efficiently.
     * 
     * Other way is to use union-find to ensure no cycles and all nodes connected with one representative only.
     *
     * TC: O(n) SC: O(n)
     * #tree #depth-first-search #breadth-first-search #union-find #graph #binary-tree #medium
     */

    public boolean validateBinaryTreeNodes(int n, int[] lc, int[] rc) {
        int []parent = new int[n];
        Arrays.fill(parent, -1);
        for(int i=0; i<lc.length; i++) if(lc[i] != -1) if(parent[lc[i]] != -1) return false; else parent[lc[i]] = i;
        for(int i=0; i<rc.length; i++) if(rc[i] != -1) if(parent[rc[i]] != -1) return false; else parent[rc[i]] = i;

        int pc = 0, p = -1;
        for(int i=0; i<parent.length; i++) if(parent[i] == -1) {pc++; p = i;}
        if(pc != 1) return false;

        boolean []v = new boolean[n];
        Stack<Integer> s = new Stack<>(); s.push(p);
        while(!s.isEmpty()){
            int c = s.pop();
            if(v[c] == true) return false;
            v[c] = true;

            if(lc[c] != -1) {s.push(lc[c]);}
            if(rc[c] != -1) {s.push(rc[c]);}
        }
        for(boolean st: v) if(!st) return false;
        return true;
    }
}