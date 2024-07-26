package dsa.leetcode.kuiperBelt;

import java.util.List;

/**
 * https://leetcode.com/problems/nested-list-weight-sum/
 *
 * The solution follows a depth-first search (DFS) approach to traverse each element in the nested list. If the element is an integer, it multiplies it by its depth and adds it to the result. If the element is a list, it recursively calls the DFS function with increased depth to process the elements within.
 *
 * TC: O(n) SC: O(d)
 * #depth-first-search #bfs #recursion #nested-lists #medium
 */

public class NestedListWeightSum {

    public int depthSum(List<NestedInteger> nestedList) {
        int res = 0;
        for (NestedInteger ni : nestedList) {
            res += dfs(ni, 1);
        }
        return res;
    }

    private int dfs(NestedInteger ni, int depth) {
        int res = 0;
        if (ni.isInteger()) {
            res += depth * ni.getInteger();
        } else {
            for (NestedInteger n : ni.getList()) {
                res += dfs(n, depth + 1);
            }
        }
        return res;
    }
}

// This is the interface that allows for creating nested lists.
// You should not implement it, or speculate about its implementation
public interface NestedInteger {
    // Constructor initializes an empty nested list.
    public NestedInteger();

    // Constructor initializes a single integer.
    public NestedInteger(int value);

    // @return true if this NestedInteger holds a single integer, rather than a nested list.
    public boolean isInteger();

    // @return the single integer that this NestedInteger holds, if it holds a single integer;
    // Return null if this NestedInteger holds a nested list
    public Integer getInteger();

    // Set this NestedInteger to hold a single integer.
    public void setInteger(int value);

    // Set this NestedInteger to hold a nested list and adds a nested integer to it.
    public void add(NestedInteger ni);

    // @return the nested list that this NestedInteger holds, if it holds a nested list;
    // Return an empty list if this NestedInteger holds a single integer
    public List<NestedInteger> getList();
}