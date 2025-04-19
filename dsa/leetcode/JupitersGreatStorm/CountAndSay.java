package dsa.leetcode.JupitersGreatStorm;

import java.util.LinkedList;
import java.util.List;


/**
 * https://leetcode.com/problems/count-and-say/?envType=daily-question&envId=2025-04-18
 * 
 * The problem involves generating the nth term in a sequence where each term is derived from the
 * previous one using run-length encoding, which counts consecutive characters and outputs them
 * as character followed by count.
 * 
 * TC: O(2^(n-1)) or exponential given the doubling length with each step
 * SC: O(2^(n-1)) as space doubles with the sequence length
 * #string #simulation #medium
 */

class CountAndSay {

    public String countAndSay(int n) {
        int []nums = null;
        String prev = "1";
        n--;
        while(n-->0) {
          nums = cs(prev);
          prev = csutil(nums);
        }
        return prev;
      }
    
      private int []cs(String str){
        List<Integer> l = new LinkedList<>();
        int idx = 0;
        while(idx < str.length()){
          int cnt = 1;
          while(idx+cnt < str.length() && str.charAt(idx) == str.charAt(idx+cnt)){
            cnt++;
          }
          l.add(cnt);
          l.add((int)(str.charAt(idx)-'0'));
          idx+=cnt;
        }
        idx=0;
        int []res = new int[l.size()];
        for(int ll: l) res[idx++]=ll;
        return res;
      }
    
      private String csutil(int []nums){
        StringBuilder sb = new StringBuilder();
        for(int num: nums) sb.append(num);
        return sb.toString();
      }
}