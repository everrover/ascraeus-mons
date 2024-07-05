### Segment tree - point update, range query

```java
// ----------- basic implementation -----------
public class ST{

  private int[] st; // The array that stores segment tree nodes
  private int n; // The number of elements in the input array

  public NonLazyST(int[] arr) {
    n = arr.length;
    // int x = (int) (Math.ceil(Math.log(n) / Math.log(2))); // log2(n)
    // int max_size = 2 * (int) Math.pow(2, x) - 1;
    int max_size = 4 * n; // 4 * n is enough -- for worst case
    st = new int[max_size]; // Memory allocation
    // constructST(arr, 0, n - 1, 0);
    for (int i = 0; i < n; i++) {
      update(0, n - 1, i, i, 0, arr[i]);
    }
  }

  private void constructST(int []arr, int ss, int se, int si) {
    if (ss == se) {
      st[si] = arr[ss];
    } else {
      int mid = getMid(ss, se);
      build(arr, ss, mid, left(si));
      build(arr, mid + 1, se, right(si));
      st[si] = st[left(si)] + st[right(si)];
    }
  }

  private void update(int ss, int se, int si, int idx, int diff) { // point update nums[idx] += diff
    if(ss>idx || se<idx) return;
    else if (ss == se) {
      st[si] += diff; return;
    }else{
      int mid = getMid(ss, se);
      if(ss<=idx && idx<=mid) update(ss, mid, idx, left(si), diff);
      else update(mid + 1, se, idx, right(si), diff);
      st[si] = st[left(si)] + st[right(si)];
    }
  }

  private int query(int ss, int se, int qs, int qe, int si) { // range query nums[qs ... qe]
    if(qs>qe) return 0;
    else if (ss == qs && se == qe) return st[si];
    else{
      int mid = getMid(ss, se);
      return query(ss, mid, qs, Math.min(qe, mid), left(si)) + query(mid + 1, se, Math.max(qs, mid + 1), qe, right(si));
    }
  }

  private int getMid(int s, int e) {
    return s + (e - s) / 2; // or (s + e) / 2
  }

  private int left(int i) {
    return 2 * i + 1;
  }

  private int right(int i) {
    return 2 * i + 2;
  }

}
```

### Segment tree - range update, range query with lazy propagation

```java
public class LazyST{
  private int[] st; // The array that stores segment tree nodes
  private int[] lazy; // The array that stores pending updates
  private int n; // The number of elements in the input array

  public LazyST(int[] arr) {
    n = arr.length;
    int x = (int) (Math.ceil(Math.log(n) / Math.log(2))); // log2(n)
    int max_size = 2 * (int) Math.pow(2, x) - 1;
    st = new int[max_size]; // Memory allocation
    lazy = new int[max_size];
    constructST(arr, 0, n - 1, 0);
  }

  private void constructST(int []arr, int ss, int se, int si) {
    if (ss == se) {
      st[si] = arr[ss];
    } else {
      int mid = getMid(ss, se);
      build(arr, ss, mid, left(si));
      build(arr, mid + 1, se, right(si));
      st[si] = st[left(si)] + st[right(si)];
    }
  }

  private void update(int ss, int se, int qs, int qe, int si, int diff) { // range update nums[qs ... qe] += diff
    if(ss == qs && qe == se){
      lazy[idx] += valDiff;
    } else if(qs <= qe) {
      int left = idx*2+1;
      int right = idx*2+2;

      lazy[left] += lazy[idx];
      lazy[right] += lazy[idx];
      lazy[idx] = 0;
      
      int mid = ss+(se-ss)/2;
      update(ss, mid, qs, Math.min(mid, qe), left, valDiff);
      update(mid+1, se, Math.max(mid+1, qs), qe, right, valDiff);
      st[idx] = (query(ss, mid, ss, mid, left)+query(mid+1, se, mid+1, se, right))%MOD;
    }
  }

  private int query(int ss, int se, int qs, int qe, int si) { // range query nums[qs ... qe]
    if(qs>qe) return 0;
    else if(ss == qs && se == qe) {
      return (st[idx]+(qe-qs+1)*lazy[idx])%MOD;
    } else {
      int mid = ss+(se-ss)/2;
      return (query(ss, mid, qs, Math.min(mid, qe), 2*idx+1) + query(mid+1, se, Math.max(qs, mid+1), qe, 2*idx+2)+(qe-qs+1)*lazy[idx])%MOD;
    }
  }
}

```

### Segment tree - range update, range query

```java
// ----------- basic implementation ----------- no lazy propagation
public class NonLazyST{

  // ... basic initialization

  private void constructST(int []arr, int ss, int se, int si) {
    if (ss == se) {
      st[si] = arr[ss];
    } else {
      int mid = getMid(ss, se);
      build(arr, ss, mid, left(si));
      build(arr, mid + 1, se, right(si));
      st[si] = st[left(si)] + st[right(si)];
    }
  }
  
  private void update(int ss, int se, int qs, int qe, int si, int diff) { // range update nums[qs ... qe] += diff
    if(qs>qe) return;
    else if (ss == qs && se == qe) {
      st[si] += diff; return;
    }else{
      int mid = getMid(ss, se);
      update(ss, mid, qs, Math.min(qe, mid), left(si), diff); // not restricted now
      update(mid + 1, se, Math.max(mid+1, qs), qe, right(si), diff); // not restricted now
      st[si] = st[left(si)] + st[right(si)];
    }
  }

  private int query(int ss, int se, int qs, int qe, int si) { // range query nums[qs ... qe]
    if(qs>qe) return 0;
    else if (ss == qs && se == qe) return st[si];
    else{
      int mid = getMid(ss, se);
      return query(ss, mid, qs, Math.min(qe, mid), left(si)) + query(mid + 1, se, Math.max(qs, mid + 1), qe, right(si));
    }
  }

  public int query(int qs, int qe) {
    return query(0, n - 1, qs, qe, 0);
  }

  public void update(int qs, int qe, int diff) {
    update(0, n - 1, qs, qe, 0, diff);
  }

  // ... other methods

}

```