package dsa.leetcode.kuiperBelt;

import java.util.Arrays;
import java.util.TreeSet;

public class Solution1 {
  class Solution {
    private final long MOD = (long)(1e9+7);
    private final long B = (long)(1e3);

    private static class T implements Comparable<T>{
      public int b; // idx in main
      public int left, right; // x1, x2
      public long d; // idx in range
      public T(int left, int right, int b){
        this.left = left; this.right = right;
        this.b = b;
        this.d = left *1000L+b*10L;
      }

      @Override
      public String toString(){
        return "{"+left+"-"+right+"-"+b+"-"+d+"}";
      }
      @Override
      public int compareTo(T o) {
        return Long.compare(this.d, o.d);
      }
    }
    public int rectangleArea(int[][] rectangles) {
      int n = rectangles.length;
      Arrays.sort(rectangles, (a,b)->a[0]==b[0]?a[1]-b[1]:a[0]-b[0]);
      int [][]yl = new int[n*2][3];
      int size = 0;
      for(int i=0; i<n; i++){
        if(rectangles[i][1] == rectangles[i][3] || rectangles[i][0] == rectangles[i][2])
          continue;
        yl[size*2][0] = rectangles[i][1];
        yl[size*2][1] = 1;
        yl[size*2][2] = i;
        yl[size*2+1][0] = rectangles[i][3];
        yl[size*2+1][1] = 0;
        yl[size*2+1][2] = i;
        size++;
      }
      Arrays.sort(yl, (a,b)->a[0]-b[0]);
      long res = 0;
      int cnty = 0, prevy = -1, lx = -1, rx = -1;
      TreeSet<T> bst = new TreeSet<>((a, b)->Long.compare(a.d, b.d));
      for(int i=0; i<size*2; i++){

        int []yline = yl[i];

        if(cnty > 0) {
          T next = bst.higher(new T(-1, -1, 0));
          lx = -1;
          while (next != null) {
            if (lx == -1) { // first
              lx = next.left; rx = next.right;
            } else if (next.left <= rx) rx = Math.max(rx, next.right); // overlap
            else { // next.left > rx.. no overlap
              res += (long)(rx-lx)*(long)(yline[0]-prevy);
              lx = next.left; rx = next.right;
            }
            next = bst.higher(next);
            if(next == null) res += (long)(rx-lx)*(long)(yline[0]-prevy);
          }
        }

        int ri = yline[2];
        if(yline[1] == 1){
          cnty++;
          bst.add(new T(rectangles[ri][0], rectangles[ri][2], ri));
        }else{
          cnty--;
          bst.remove(new T(rectangles[ri][0], rectangles[ri][2], ri));
        }
        prevy = yline[0];
      }
      return (int)res;
    }
  }
  /*
  0,0,2,2
  1,0,2,3
  */
  public static void main(String[] args) {
    Solution1 s = new Solution1();
    Solution sol = s.new Solution();
    String str = "471,0,947,999],[780,0,823,320],[868,0,948,538],[907,0,911,673],[929,0,952,596],[458,0,889,669],[156,0,364,754],[900,0,973,236],[406,0,620,454],[773,0,946,538],[407,0,834,23],[759,0,858,526],[431,0,776,599],[969,0,979,30],[642,0,737,339],[239,0,448,183],[260,0,517,903],[14,0,674,976],[251,0,850,112],[57,0,794,395],[595,0,728,149],[970,0,989,36],[496,0,954,791],[447,0,832,805],[829,0,939,100],[169,0,568,501],[704,0,969,411],[607,0,609,221],[935,0,953,437],[47,0,670,130],[794,0,799,230],[943,0,959,90],[332,0,337,732],[123,0,228,344],[281,0,487,598],[381,0,732,443],[235,0,391,548],[646,0,930,20],[219,0,675,95],[8,0,212,227],[138,0,704,658],[368,0,782,707],[810,0,826,957],[543,0,697,654],[887,0,986,180],[837,0,900,228],[280,0,391,331],[180,0,229,42],[201,0,489,687],[648,0,680,732],[228,0,630,922],[886,0,960,56],[946,0,955,522],[903,0,992,464],[557,0,860,38],[89,0,268,642],[669,0,774,185],[1,0,724,374],[395,0,923,782],[82,0,230,550],[166,0,166,808],[441,0,644,435],[497,0,823,224],[372,0,973,556],[188,0,846,127],[226,0,396,535],[869,0,945,575],[406,0,526,795],[781,0,795,569],[563,0,831,991],[466,0,486,641],[274,0,855,529],[61,0,819,364],[285,0,421,101],[193,0,950,748],[320,0,655,836],[207,0,627,945],[782,0,899,56],[578,0,970,913],[499,0,684,205],[490,0,877,16],[483,0,668,915],[364,0,741,16";
    String[] strArr = str.split("\\],\\[");
    int[][] rectangles = new int[strArr.length][4];
    for(int i=0; i<strArr.length; i++){
      String[] sArr = strArr[i].split(",");
      for(int j=0; j<4; j++){
        rectangles[i][j] = Integer.parseInt(sArr[j]);
      }
    }
    System.out.println(sol.rectangleArea(rectangles));
  }
}