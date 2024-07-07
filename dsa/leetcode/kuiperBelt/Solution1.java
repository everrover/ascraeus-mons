package dsa.leetcode.kuiperBelt;

import java.util.Arrays;

public class Solution1 {
  public static void main(String[] args) {
    String []ops = new String[]{"BookMyShow","scatter","scatter","scatter","gather","gather","gather","gather","gather","scatter","scatter","gather","gather","gather","scatter","scatter","scatter","gather","scatter","scatter","gather","scatter","gather","scatter","scatter","scatter","gather","gather","gather","gather","scatter","gather","scatter","gather","gather","BookMyShow","gather","gather","scatter","scatter"};
    int [][]vals = new int[][]{{33,724},{475,32},{510,17},{522,9},{414,3},{900,24},{900,30},{233,18},{64,29},{330,1},{748,17},{615,28},{638,33},{701,36},{650,28},{435,5},{759,24},{220,12},{320,10},{842,3},{579,17},{214,15},{292,9},{205,32},{439,24},{643,24},{257,33},{271,25},{174,26},{980,18},{662,8},{413,30},{971,4},{110,16},{818,25},{2,5},{4,0},{2,0},{5,1},{5,1}};
    Solution1 obj = null;
    for(int i=0; i<ops.length; i++){
      if(ops[i].equals("BookMyShow")){
        System.out.println();
        obj = new Solution1(vals[i][0], vals[i][1]);
        System.out.print("null");
      }else if(ops[i].equals("gather")){
//        System.out.println("gather:"+vals[i][0]+":"+vals[i][1]);
        int []res = obj.gather(vals[i][0], vals[i][1]);
        if(res.length==2) System.out.print("["+res[0]+","+res[1]+"]");
        else System.out.print("[]");
      }else if(ops[i].equals("scatter")){
//        System.out.println("scatter:"+vals[i][0]+":"+vals[i][1]);
        System.out.print(obj.scatter(vals[i][0], vals[i][1]));
      }
      System.out.print(",");
    }
    System.out.println();
  }
  private int rows, size;
  private ST st;
  private int []counts;
  private int cidx;

  public Solution1(int n, int m) {
    this.rows = n;
    this.size = m;
    st = new ST(n, this.size);
    counts= new int[n];
    cidx=0;
    Arrays.fill(counts, size);
  }

  public int[] gather(int k, int maxRow) {
    if(k == 0) return new int[]{0,-1};
    int l=0, r=maxRow+1, mid;
    int mrfqRes = -1;
    // System.out.println(st);
    while(l<r){
      mid = (r+l)/2;
      int mrfq = st.querymax(0, mid);
      if(mrfq>=k){
        mrfqRes = mrfq;
        r = mid;
      }else l = mid+1;
    }

    if(mrfqRes != -1){
      int []res = new int[]{r, size-mrfqRes};
      counts[r] -= k;
      st.update(r, k);
      //System.out.println(st+"+++"+mrfqRes[1]+"++++"+counts[mrfqRes[1]]);
      return res;
    }else{
      return new int[0];
    }
  }

  public boolean scatter(int k, int maxRow) {
    long mrf = st.query(0, maxRow);
//    System.out.println(mrf+":"+k+":"+cidx);
    if(mrf >= k){
      while(cidx<rows&&k>0){
//        System.out.println(st);
        if(counts[cidx]<=0) cidx++;
        int reduce = Math.min(k, counts[cidx]);
        k -= reduce;

//        System.out.println(cidx+"---"+counts[cidx]+"---"+reduce);

        st.update(cidx, reduce);
        counts[cidx]-=reduce;
        if(counts[cidx] <= 0) cidx++;
      }
      return true;
    }else return false;
  }

  private static class ST {
    private long []st;
    private int []stmax;
    private int n;
    public ST(int n, int size){
      this.n = n;
      this.st = new long[4*n];
      this.stmax = new int[4*n];
      construct(size, 0, n-1, 0);
    }

    private void construct(int size, int ss, int se, int si){
      if(ss == se){
        st[si] = size; stmax[si] = size;
      }else{
        int mid = ss+(se-ss)/2;
        int left = 2*si+1, right = 2*si+2;
        construct(size, ss, mid, left);
        construct(size, mid+1, se, right);

        st[si] = st[left]+st[right];
        stmax[si] = size;
      }
    }

    private void update(int ss, int se, int si, int idx, int diff){
      if(ss>idx || se<idx) return;
      else if (ss == se) {
        st[si] -= diff;
        stmax[si] = (int)st[si];
      }else{
        int mid = ss+(se-ss)/2;
        int left = 2*si+1, right = 2*si+2;
        if(ss<=idx && idx<=mid) update(ss, mid, left, idx, diff);
        else update(mid + 1, se, right, idx, diff);

        st[si] = st[left] + st[right];
        stmax[si] = Math.max(stmax[left], stmax[right]);
      }
    }

    private long query(int ss, int se, int si, int qs, int qe){
      if(qs>qe) return 0;
      else if (ss == qs && se == qe) return st[si];
      else{
        int mid = ss+(se-ss)/2;
        return query(ss, mid, 2*si+1, qs, Math.min(qe, mid)) + query(mid + 1, se, 2*si+2, Math.max(qs, mid + 1), qe);
      }
    }

    private int querymax(int ss, int se, int si, int qs, int qe){
      if(qs>qe) return -1;
      else if (ss == qs && se == qe) return stmax[si];
      else{
        int mid = ss+(se-ss)/2;
        int left = querymax(ss, mid, 2*si+1, qs, Math.min(qe, mid));
        int right = querymax(mid + 1, se, 2*si+2, Math.max(qs, mid + 1), qe);
        int res = Math.max(left, right);
        return res;
      }
    }

    public String toString(){
      String res = "";
      for(int i=0; i<st.length; i++){
        res += st[i]+",";
      }
      res += " :: ";
      for(int i=0; i<st.length; i++){
        res += stmax[i]+",";
      }
      return res;
    }

    public void update(int idx, int diff){
      update(0, n-1, 0, idx, diff);
    }

    public long query(int qs, int qe){
      return query(0, n-1, 0, qs, qe);
    }

    public int querymax(int qs, int qe){
      return querymax(0, n-1, 0, qs, qe);
    }
  }
}

/*
["BookMyShow","gather","gather","scatter","scatter"]
[[2,5},{4,0},{2,0},{5,1},{5,1]]
*/

/**
 * Your BookMyShow object will be instantiated and called as such:
 * BookMyShow obj = new BookMyShow(n, m);
 * int[] param_1 = obj.gather(k,maxRow);
 * boolean param_2 = obj.scatter(k,maxRow);
 */
