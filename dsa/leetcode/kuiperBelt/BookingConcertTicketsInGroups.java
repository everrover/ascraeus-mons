package dsa.leetcode.KuiperBelt;

import java.util.Arrays;

public class BookingConcertTicketsInGroups {
  
  /**
   * https://leetcode.com/problems/booking-concert-tickets-in-groups/
   *
   * The system uses a segment tree (ST) to efficiently manage seat allocations, allowing for both contiguous (gather) and
   * non-contiguous (scatter) seat bookings within given maximum row constraints. Utilizes binary search for gather and
   * iterative allocation for scatter to ensure optimal seat placement. Complexity is managed by segment tree operations.
   *
   * Binary search in gather tries to find the row with the minimum number of available seats for a given group size. And allocates
   * to it only.
   * Scatter finds the sum of all available seats and tries to allocate the group size to the rows sequentially.
   * 
   * TC: O(logn*logn) for gather due to binary search, O(nlogn) Theta(logn) for scatter due to sequential checking.
   * SC: O(n) for segment tree construction where n is the number of rows.
   * #binary-search #design #binary-indexed-tree #segment-tree #hard
   */

  private int rows, size;
  private ST st;
  private int []counts;
  private int cidx;

  public BookingConcertTicketsInGroups(int n, int m) {
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
    while(l<r){
      mid = (r+l)/2;
      int mrfq = st.querymax(0, mid);
      if(mrfq>=k){
        mrfqRes = mrfq;
        r = mid;
      } else l = mid+1;
    }
    if(mrfqRes != -1){
      int []res = new int[]{r, size-mrfqRes};
      counts[r] -= k;
      st.update(r, k);
      return res;
    }else{
      return new int[0];
    }
  }

  public boolean scatter(int k, int maxRow) {
    long mrf = st.query(0, maxRow);
    if(mrf >= k){
      while(cidx<rows && k>0){
        if(counts[cidx]<=0) cidx++;
        int reduce = Math.min(k, counts[cidx]);
        k -= reduce;
        st.update(cidx, reduce);
        if(k>0) cidx++;
      }
      return true;
    }
    return false;
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