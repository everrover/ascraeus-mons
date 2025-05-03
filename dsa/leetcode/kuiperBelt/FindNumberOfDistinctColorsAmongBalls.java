package dsa.leetcode.KuiperBelt;

import java.util.HashMap;
import java.util.Map;

/**
 * https://leetcode.com/problems/find-the-number-of-distinct-colors-among-the-balls/
 * This code keeps track of the distinct colors among the balls through queries.
 * A hashmap `map` is used to track each color's count, and a reverse map `revmap`
 * tracks which ball is of what color. On coloring a ball, it increases distinct colors count if new,
 * or decreases it if a color is being overridden and it was unique.
 * 
 * TC: O(n) SC: O(n)
 * #hashing #array #medium #straightforward
 */
public class FindNumberOfDistinctColorsAmongBalls {
  public int[] queryResults(int limit, int[][] queries) {
    int []res = new int[queries.length];
    Map<Integer, Integer> map = new HashMap<>(); // color -> ball count
    Map<Integer, Integer> revmap = new HashMap<>(); // ball -> color
    int distinct = 0;
    for(int i=0; i<queries.length; i++){
      int bidx = queries[i][0];
      int bcolor = queries[i][1];
      if(revmap.containsKey(bidx)){ // assigned, hence uncolor first
        int oldcolor = revmap.get(bidx);
        int oldcolorcount = map.get(oldcolor);
        if(oldcolorcount == 1){
          map.remove(oldcolor);
          distinct--;
        } else{
          map.put(oldcolor, oldcolorcount-1);
        }
      }
      if(map.containsKey(bcolor)){ // color the ball
        map.put(bcolor, map.get(bcolor)+1);
      } else{
        map.put(bcolor, 1);
        ++distinct;
      }
      res[i] = distinct;
      revmap.put(bidx,bcolor);
    }
    return res;
  }
}
