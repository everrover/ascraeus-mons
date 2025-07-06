package dsa.leetcode.Ipestus;

class MinimumDeletionsToMakeStringKSpecial {

    /**
     * https://leetcode.com/problems/minimum-deletions-to-make-string-k-special/description/?envType=daily-question&envId=2025-06-21
     *
     * Trick: Try assuming one of the characters to be the smallest. Say, `x`
     *
     * Then for each other character `y`, if the freq is lesser than `x` then we attempt deleting `y` completely.
     * Else if abs(freq[y] - freq[x]) > k, then we delete the excess characters of `y` to make it equal to `x + k`.
     *
     * TC: O(n) SC: O(26)
     * #hash-table #string #greedy #medium
     */

    public int minimumDeletions(String word, int k) {
        int[] freq = new int[26];
        char[] chs = word.toCharArray();
        int res = chs.length;
        for (char ch : chs) freq[ch - 'a']++;
        for (int i = 0; i < 26; i++) {
            int del = 0;
            for(int j=0; j<26; j++){
                if(freq[i]>freq[j]){
                    del += freq[j];
                }else if(freq[j] > freq[i]+k) {
                    del += freq[j] - (freq[i]+k);
                }
            }
            res = Math.min(res, del);
        }
        return res;
    }
}