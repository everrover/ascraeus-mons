package dsa.leetcode.EuropasOceanWorld;

import java.util.Arrays;

public class MinimumWindowSubstring {

    /**
     * https://leetcode.com/problems/minimum-window-substring/description/
     *
     * Utilizes a sliding window approach to find the minimum substring window
     * in s that contains all characters from t. Expand the right pointer until all
     * characters from t are found, then contract the left pointer while maintaining
     * the condition, updating the minimum window size as necessary.
     * 
     * TC: O(m + n), SC: O(1)
     * #sliding-window #hash-table #string #hard
     */

    public String minWindow(String s, String t) {
        int left = 0, right = 0, ansSize = Integer.MAX_VALUE, size=0;
        char []str = s.toCharArray();
        char []ttr = t.toCharArray();
        final int m=s.length(), n=t.length();
        String ans = "";

        if(m == 0 || n == 0 || m<n){ // empty strings
            return ans;
        }

        int[] map = new int[128];
        int[] extras = new int[128];
        Arrays.fill(map, -1);
        Arrays.fill(extras, -1);
        for(int i=0; i<n; i++){
            if(map[ttr[i]] == -1){
                map[ttr[i]] = 0;
                extras[ttr[i]] = 0;
            }
            map[ttr[i]]++;
        }

        while(right<m) {
            if (map[str[right]] != -1 && map[str[right]] > 0) {
                size++;
                map[str[right]]--;
                while(size == n) {
                    while (map[str[left]] == -1) left++;
                    if (ansSize > (right - left + 1)) {
                        ans = s.substring(left, right + 1);
                        ansSize = (right - left + 1);
                    }

                    if (extras[str[left]]!=-1 && extras[str[left]]>0) {
                        extras[str[left]]--;

                    } else {
                        map[str[left]]++;
                        size--;
                    }
                    left++;
                }
            } else if (map[str[right]] != -1 && map[str[right]] == 0) {
                extras[str[right]]++;
            }
            right++;
        }
        return ans;
    }
}