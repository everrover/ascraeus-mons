package dsa.leetcode.JupitersGreatStorm;

import java.util.*;

/**
 * https://leetcode.com/problems/create-grid-with-exactly-one-path/
 *
 * Constructs a canonical L-shaped path: fill row 0 entirely with '.' (go right across the top), then for every subsequent row fill with '#' and place a single '.' at column n-1 (go down the last column). This guarantees exactly one path — (0,0)→(0,n-1)→(m-1,n-1) — because any deviation from the top row or the rightmost column hits an obstacle.
 *
 * TC: O(m*n) SC: O(m*n)
 * #array #matrix #construction #easy
 */

class CreateGridWithExactlyOnePath {
    public String[] createGrid(int m, int n) {
        String []res = new String[m];
        char []chs;
        for(int i=0; i<m; i++){
            if(i == 0) {
                chs = new char[n];
                Arrays.fill(chs, '.');
                res[i] = new String(chs);
                continue;
            }
            chs = new char[n];
            Arrays.fill(chs, '#');
            chs[n-1] = '.';
            res[i] = new String(chs);
        }
        return res;
    }
}
