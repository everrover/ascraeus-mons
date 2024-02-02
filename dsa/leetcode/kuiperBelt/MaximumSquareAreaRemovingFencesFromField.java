package dsa.leetcode.kuiperBelt;

import java.util.HashSet;
import java.util.Set;

public class MaximumSquareAreaRemovingFencesFromField {
  /**
   * https://leetcode.com/problems/maximum-square-area-by-removing-fences-from-a-field/
   * 
   * Enumerate all the possible areas of the squares that can be formed by removing a pair of fences. For one set.
   * Check the same side for the other set. If found, calculate the area and return the maximum.
   * 
   * TC O(n^2) SC O(n)
   * #two-pointers #set #enumeration #area-calculation
   */
  private static final long MOD = (long)(1e9+7);
  private long enumerateAreas(int []fences, int x, Set<Long> enumeratedAreas, boolean hEnumerated){
    long res = -1;
    int []fencesWZ = new int[fences.length+2];
    for(int i=0; i<fences.length; i++) fencesWZ[i+1] = fences[i];
    fencesWZ[0] = 1;
    fencesWZ[fences.length+1] = x;
    for(int i=0; i<fencesWZ.length; i++){
      for(int j=i+1; j<fencesWZ.length; j++){
        long side = Math.abs(fencesWZ[i]-fencesWZ[j]);
        if(hEnumerated){
          if(enumeratedAreas.contains(side)){
            res = Math.max(res, (side*side));
          }          
        }else{
          enumeratedAreas.add(side);
        }
      }
    }
    return res;
  }
  public int maximizeSquareArea(int m, int n, int[] hFences, int[] vFences) {
    Set<Long> sides = new HashSet<>();
    enumerateAreas(hFences, m, sides, false);
    return (int)(enumerateAreas(vFences, n, sides, true)%MOD);
  }
}
