package dsa.leetcode.kuiperBelt;

import java.util.Deque;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

/**
 * https://leetcode.com/problems/find-the-first-player-to-win-k-games-in-a-row/
 * Simulation of a competition where players are queued based on their skills. The logic involves
 * tracking wins and managing player positions until a player wins k consecutive games.
 * 
 * TC: O(n) SC: O(n)
 * #array #simulation #medium
 */
public class FindTheFirstPlayerToWinKGamesInARow {

  private void swap(int []idxs, int []wins, int []skills, int i, int j){
    int tmp = idxs[i]; idxs[i] = idxs[j]; idxs[j] = tmp;
    tmp = wins[i]; wins[i] = wins[j]; wins[j] = tmp;
    tmp = skills[i]; skills[i] = skills[j]; skills[j] = tmp;
  }

  public int findWinningPlayer(int[] skills, int k) {
    Map<Integer, Integer> idxs = new HashMap<>();
    Deque<Integer> q = new LinkedList<>();
    int[] wins = new int[skills.length];
    int max = 0;
    for(int i=0; i<skills.length; i++){
      idxs.put(skills[i], i);
      wins[i] = 0;
      max = Math.max(max, skills[i]);
      q.addLast(skills[i]);
    }
    while(q.getFirst() != max){
      int one = q.removeFirst(), two = q.removeFirst();
      if(one>two){
        q.addFirst(one);
        q.addLast(two);
      }else{
        q.addFirst(two);
        q.addLast(one);
      }
      int idx = idxs.get(q.getFirst());
      wins[idx]++;
      if(wins[idx]>=k){
        return idx;
      }
    }
    return idxs.get(q.getFirst());
  }
}