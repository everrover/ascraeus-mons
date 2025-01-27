package dsa.leetcode.mithrim_montez;

import java.util.*;

public class CountMentionsPerUser {

  /**
   * https://leetcode.com/problems/count-mentions-per-user/description/
   *
   * Sort events by timestamp then process each event.
   * Maintain a set of offline users and uses it to update mentions
   * 
   * Simple event simulation/B.Force
   *
   * TC: O(n log n) SC: O(n)
   * #array #set #event-simulation #medium
   */

   public int[] countMentions(int numberOfUsers, List<List<String>> events) {
    int []res = new int[numberOfUsers];
    Collections.sort(events, (a,b)->{
      int B = Integer.valueOf(b.get(1));
      int A = Integer.valueOf(a.get(1));
      if(A==B) {
        return b.get(0).compareTo(a.get(0));
      }
      return A-B;
    });
    int []offline = new int[numberOfUsers];
    Arrays.fill(offline, -1);
    for(List<String> eve: events){
      // update online
      int ts = Integer.valueOf(eve.get(1));
      for(int i=0; i<offline.length; i++){
        if(offline[i] != -1 && ts - offline[i] >= 60) offline[i] = -1;
      }
      if(eve.get(0).equals("MESSAGE")){
        if(eve.get(2).equals("ALL")){
          for(int i=0; i<numberOfUsers; i++) res[i]++;
        }else if(eve.get(2).equals("HERE")) {
          for(int i=0; i<numberOfUsers; i++) if(offline[i] == -1) res[i]++;
        }else for(String id: eve.get(2).split(" ")){
          res[Integer.valueOf(id.substring(2))]++;
        }
      }else{
        offline[Integer.valueOf(eve.get(2))] = ts;
      }
    }
    return res;
  }
}