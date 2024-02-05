package dsa.leetcode.kuiperBelt;

public class ManimumNumberOfPushedToTypeWordI {
  public int minimumPushes(String word) {
    int res=0, i=1, wl=word.length();
    while(wl>0){
      res += i*Math.min(wl,8);
      i++; wl-=8;
    }
    return res;
  }
}
