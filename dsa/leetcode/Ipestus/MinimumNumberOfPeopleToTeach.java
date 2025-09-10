package dsa.leetcode.Ipestus;

import java.util.*;

public class MinimumNumberOfPeopleToTeach {
    public int minimumTeachings(int n, int[][] languages, int[][] friendships) {
        Set<Integer> cantconnect = new HashSet<>();
        Map<Integer, Set<Integer>> languageMap = new HashMap<>();
        for (int i = 0; i < languages.length; i++) {
            for (int lang : languages[i]) {
                languageMap.computeIfAbsent(lang, k -> new HashSet<>()).add(i + 1);
            }
        }
        for (int[] f : friendships) {
            int u = f[0], v = f[1];
            boolean commonLang = false;
            for (int lang : languages[u - 1]) {
                if (languageMap.get(lang).contains(v)) {
                    commonLang = true;
                    break;
                }
            }
            if (!commonLang) {
                cantconnect.add(u);
                cantconnect.add(v);
            }
        }
        int maxcnt = 0;
        for (int i = 1; i <= n; i++) {
            int cnt = 0;
            for (int u : cantconnect) {
                if (!languageMap.getOrDefault(i, new HashSet<>()).contains(u)) {
                    cnt++;
                }
            }
            maxcnt = Math.max(maxcnt, cnt);
        }
        return cantconnect.size() - maxcnt;
    }
}