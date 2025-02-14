package dsa.leetcode.VallesMarineris;

import java.util.*;

public class MaximumEmployeesToBeInvitedToAMeeting {

    /**
     * https://leetcode.com/problems/maximum-employees-to-be-invited-to-a-meeting/description/
     *
     * Construct a graph from favorite list, treat each favorite relationship as a directed edge.
     * Detect the cycles in this directed graph or deal with longest path if cycles are of length 2.
     * Count maximum employees that can be seated by either selecting cycles directly or combining chains.
     * 
     * TC: O(n) SC: O(n)
     * #graph #dfs #cycle #hard
     */

     private int dfs(int idx, List<Set<Integer>> favRev, boolean []isVisited){
        if(isVisited[idx]){
            return 0;
        }
        int res = 0;
        isVisited[idx] = true;
        for(int i: favRev.get(idx)){
            res = Math.max(res, 1+dfs(i, favRev, isVisited));
        }
        return res;
    }
    public int maximumInvitations(int[] favs) {
        int res = 0;
        boolean []isVisited = new boolean[favs.length];

//        Arrays.fill(isVisited, -1);
        // build reverse adj list
        List<Set<Integer>> al = new ArrayList<>(favs.length);
        for(int i=0; i<favs.length; i++){
            al.add(new HashSet<>());
        }
        for(int i=0; i<favs.length; i++){
            if(favs[favs[i]] != i)
                al.get(favs[i]).add(i);
        }
        // calculate with mutual favs
        for(int i=0; i<favs.length; i++){
            if(favs[favs[i]] == i){
                res += dfs(i, al, isVisited);
                res += 1;
            }
        }
        // calculate with longest cycle
        for(int i=0; i<favs.length; i++){
            int j=i, count=0, k;
            while(!isVisited[j]){ // we find loop entry point
                isVisited[j] = true;
                count++;
                j = favs[j];
            }
            k = i;
            while(k != j){ // subtract distance from `i` to `j`
                count--;
                k = favs[k];
            }
            res = Math.min(res, count);
        }
        return res;
    }

    public static void main(String[] args) {
        MaximumEmployeesToBeInvitedToAMeeting meeting = new MaximumEmployeesToBeInvitedToAMeeting();
//        System.out.println(meeting.maximumInvitations(new int[]{2,2,1,2}));
//        System.out.println(meeting.maximumInvitations(new int[]{3,0,1,4,1}));
        System.out.println(meeting.maximumInvitations(new int[]{1,2,1,0,3,3,2,6}));
    }
}