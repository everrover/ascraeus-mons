package dsa.leetcode.Ipestus;

import java.util.*;

public class MinimumNumberOfPeopleToTeach {
    /**
     * https://leetcode.com/problems/minimum-number-of-people-to-teach/
     * 
     * Find the number of people who cannot communicate with each other -> cantconnect set
     * For each friend who cannot communicate, find the languages they do know -> langcount
     * The max-known language will be the one we remove from the cantconnect set
     * => cantconnect.size() - max-known
     * 
     * That's the greedy choice that works
     * 
     * TC: O(n * m + f * l), SC: O(n + m + f)
     * #hash-table #set #map #greedy #medium
     */
    public int minimumTeachings(int n, int[][] languages, int[][] friendships) {
        Set<Integer> cantconnect = new HashSet<>();
        for(int []fs: friendships){
            Set<Integer> m = new HashSet<>();
            boolean canconn = false;
            for(int l: languages[fs[0]-1]){
                m.add(l);
            }
            for(int l: languages[fs[1]-1]){
                if(m.contains(l)) {canconn=true; break;}
            }
            if(!canconn) {
                cantconnect.add(fs[0]-1);
                cantconnect.add(fs[1]-1);
            }
        }
        int maxcnt = 0;
        int []langcount = new int[n+1];
        for(int f: cantconnect){
            for(int l: languages[f]){
                langcount[l]++;
                maxcnt = Math.max(langcount[l], maxcnt);
            }
        }
        return cantconnect.size()-maxcnt;
    }
}