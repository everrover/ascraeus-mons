package dsa.leetcode.kuiperBelt;

import java.util.*;

/**
 * https://leetcode.com/problems/the-skyline-problem/
 *
 * The skyline problem solved using a line sweep algorithm.
 * We converted the building endpoints into 'Line' objects marking their start and end.
 * A custom comparator for sorting these lines is used to manage the processing order.
 * TreeMap is utilized to keep track of current building heights in sorted order.
 * 
 * TC: O(n log n) SC: O(n)
 * #heap #priority-queue #line-sweep #hard
 */
public class TheSkylineProblem {
    class Line implements Comparable<Line>{
        public int x, y;
        public boolean isStart;

        public Line(int x, int y, boolean isStart){
            this.x = x;
            this.y = y;
            this.isStart = isStart;
        }

        public String toString(){
            return "{"+x+":"+y+":"+isStart+"}";
        }

        @Override
        public int compareTo(Line o) {
            // Sorting first by x then by y
            return this.x != o.x? this.x - o.x : this.y - o.y;
        }
    }

    public List<List<Integer>> getSkyline(int[][] buildings) {
        final int m = buildings.length*2;
        if(m == 0) return new LinkedList<>();
        List<List<Integer>> ans = new ArrayList<>();
        Line []lines = new Line[m];
        TreeMap<Integer, Integer> pq = new TreeMap<>();

        for(int i=0; i<buildings.length; i++){
            lines[2*i] = new Line(buildings[i][0], buildings[i][2], true);
            lines[2*i+1] = new Line(buildings[i][1], buildings[i][2], false);
        }

        Arrays.sort(lines);

        pq.put(0, 1);
        int prev = 0;

        for(Line line: lines){
            if(line.isStart) {
                // For start of a building, add its height
                pq.compute(line.y, (k, v) -> v == null ? 1 : v + 1);
            } else {
                // For end of a building, remove its height
                pq.compute(line.y, (k, v) -> v == 1 ? null : v - 1);
            }
            Integer curr = pq.lastKey();
            if(prev != curr) {
                ans.add(Arrays.asList(line.x, curr));
                prev = curr;
            }
        }

        return ans;
    }
}