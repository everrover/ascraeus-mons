package dsa.leetcode.VallesMarineris;

public class PushDominoes {

  /**
   * https://leetcode.com/problems/push-dominoes/?envType=daily-question&envId=2025-05-02
   *
   * The approach considers each domino and the force pushing it to balance the series.
   * Two pointers are used to simulate the push effects from both the right and left directions,
   * resolving conflicts by splitting when the effects are equal.
   *
   * TC: O(n) SC: O(1)
   * #two-pointers #string #dynamic-programming #medium
   */

   public String pushDominoes(String dominoes) {
    char []dominos = dominoes.toCharArray();
    boolean []mark = new boolean[dominos.length];
    int l=-1, r=-1;
    for(int i=0; i<dominos.length; i++){
        if(dominos[i] == 'R'){
            r = i;
        }else if(dominos[i] == 'L'){
            if(r!=-1 && r > l){
                // System.out.println(l+"-"+r);
                for(int j=r+1; j<i; j++){
                    if(j<=(r+i)/2){
                        dominos[j]='R';
                    }else if(j>(r+i)/2){
                        dominos[j]='L';
                    }
                    mark[j] = true;
                }
                if((r+i)%2==0){
                    dominos[(r+i)/2]='.';
                }
                mark[r] = mark[i] = true;
            }
            l = i;
        }
        // System.out.println(l+"::"+r);
    }
    for(int i=0; i<dominos.length; i++){
        if(dominos[i]=='R'){
            int j=i+1;
            while(j<dominos.length && !mark[j] && dominos[j]=='.') dominos[j++] = 'R';
        }
    }
    for(int i=dominos.length-1; i>=0; i--){
        if(dominos[i]=='L'){
            int j=i-1;
            while(j>=0 && !mark[j] && dominos[j]=='.') dominos[j--] = 'L';
        }
    }
    return new String(dominos);
}
}