## Segment tree & [Fenwick tree](./binindexedtree.md)

> Segment tree is a data structure that allows answering range queries over an array effectively, while still being flexible enough to allow modifying the array. This includes finding the sum of consecutive array elements a[l…r], or finding the minimum element in a such a range in O(logn) time.

**TC::O(nlogn) for construction, O(logn) for query, O(logn) for update**
**SC::O(2n=n) for construction and upkeep**

### When can it be used

- Associative property should hold f(a, f(b,c)) = f(f(a,b), c)
- Examples: Addition, Multiplication, Min./Max., etc.

**A version with expl**

```java
public class SegmentTree {
  private int[] st; // The array that stores segment tree nodes
  private int n; // The number of elements in the input array

  public SegmentTree(int[] arr) {
    n = arr.length;
    int x = (int) (Math.ceil(Math.log(n) / Math.log(2))); // log2(n)
    int max_size = 2 * (int) Math.pow(2, x) - 1;
    st = new int[max_size]; // Memory allocation
    constructST(arr, 0, n - 1, 0);
  }

  // A utility function to get the middle index from corner indexes
  private int getMid(int s, int e) {
    return s + (e - s) / 2;
  }

  // CREATE
  // A recursive function that constructs Segment Tree for array[ss..se].
  // si is the index of the current node in the segment tree st
  private int constructST(int[] arr, int ss, int se, int si) {
    if (ss == se) {
      st[si] = arr[ss];
      return arr[ss];
    }
    int mid = getMid(ss, se);
    st[si] = constructST(arr, ss, mid, si * 2 + 1) + constructST(arr, mid + 1, se, si * 2 + 2);
    return st[si];
  }

  // QUERY
  // A recursive function to get the sum of values in the given range of the array.
  // si is the index of the current node in the segment tree st
  // ss & se are the starting and ending indexes of the segment represented by the current node
  // qs & qe are the starting and ending indexes of the query range
  private int getSum(int ss, int se, int qs, int qe, int si) {
    if (qs <= ss && qe >= se) { // complete overlap
      return st[si];
    }else if (se < qs || ss > qe) { // no overlap
      return 0;
    }
    int mid = getMid(ss, se);
    return getSum(ss, mid, qs, qe, 2 * si + 1) + getSum(mid + 1, se, qs, qe, 2 * si + 2);
  }

  // UPDATE
  // The function to update a value in input array and segment tree.
  // si is the index of the current node in the segment tree st
  // ss & se are the starting and ending indexes of the segment represented by the current node
  // i is the index of the element to be updated. This index is in the input array.
  // newVal is the value to be added to the input array
  // ss = segment-start, se = segment-end, si = curr-node-index, newVal = new-value with which to update, 
  private void updateValue(int[] arr, int ss, int se, int idx, int newVal, int si) {
    if (idx < ss || idx > se) {
      return;
    } else if(ss == se) {
      arr[idx] = st[si] = newVal; return;
    } else if (se != ss) {
      int mid = getMid(ss, se);
      if(idx>=ss && idx<=se) updateValue(arr, ss, mid, i, newVal, 2 * si + 1); 
      else updateValue(arr, mid + 1, se, i, newVal, 2 * si + 2);
      st[si] = st[2 * si + 1] + st[2 * si + 2];
    }
  }
}
```

**Short version**
**Practice** :: Target 7mins
  
```java
// ...
private int constructST(int[] arr, int ss, int se, int si) {
  if (ss == se) {
    st[si] = arr[ss];
    return arr[ss];
  }
  int mid = getMid(ss, se);
  return st[si] = Math.max(constructST(arr, ss, mid, si * 2 + 1), constructST(arr, mid + 1, se, si * 2 + 2));
}

private int getMax(int ss, int se, int qs, int qe, int si) {
  if (qs <= ss && qe >= se) return st[si]; // total overlap
  if (se < qs || ss > qe) return -1; // no overlap
  int mid = getMid(ss, se); // partial overlap
  return Math.max(getMax(ss, mid, qs, qe, 2 * si + 1), getMax(mid + 1, se, qs, qe, 2 * si + 2));
}

private int update(int[] arr, int ss, int se, int i, int newVal, int si) {
  if (i < ss || i > se) Integer.MIN_VALUE;
  if (ss == se) {
    arr[i] = newVal;
    return st[si] = newVal;
  }
  int mid = getMid(ss, se);
  return st[si] = Math.max(update(arr, ss, mid, i, newVal, 2 * si + 1), update(arr, mid + 1, se, i, newVal, 2 * si + 2));
}
// ...
```

```java
// ...
private int construct(int ss, int se, int si){
  if(ss == se){
    return st[si] = arr[ss];
  }else{
    int mid = getMid(ss, se);
    return st[si] = Math.min(construct(ss, mid, si*2+1), construct(mid+1, se, si*2+2));
  }
}

private int getMin(int ss, int se, int qs, int qe, int si){
  if(qs<=ss && qe>=se) return st[si];
  else if(qs>se || qe<ss) return Integer.MAX_VALUE;
  else{
    int mid = getMid(ss, se);
    return Math.min(getMin(ss, mid, qs, qe, si*2+1), getMin(mid+1, se, qs, qe, si*2+2));
  }
}
private update(int ss, int se, int si, int idx, int newVal){
  if(idx<ss || idx >se) return Integer.MAX_VALUE;
  else if(ss == se) return st[si] = arr[idx] = newVal;
  else{
    int mid = getMid(ss, se);
    if(idx >= ss && idx <=mid) update(ss, mid, si*2+1, idx, newVal);
    else update(mid+1, idx si*2+1, idx, newVal);
    return st[si] = Math.min(st[si*2+1], st[si*2+2]);
  }
}
// ...
```

Ref:: https://cp-algorithms.com/data_structures/segment_tree.html

### Advanced variants

- Finding GCD(and LCM) of a range
  ```txt
  gcd(i) = gcd(gcd(i*2+1), gcd(i*2+2))
  search(i,l,r) = if l<=ss && r>=se return st[i]
                  else if r<ss || l>se return 0
                  else return gcd(search(i*2+1,l,r), search(i*2+2,l,r))
  ```

- Counting the number of zeros, searching for the k-th zero
  ```txt
  // build, update and count is ~ to sum query
  count(i) = count(i*2+1) + count(i*2+2)
  search(i,k) = if k<=count(i*2+1) search(i*2+1) else search(i*2+2)
  ```
  ```java
  public int search(int si, int k, int ss, int se) {
    if (k > st[si]) return -1;
    if (ss == se) return ss;
    int mid = getMid(ss, se);
    if (k <= st[left(si)]) return search(left(si), k, ss, mid);
    return search(right(si), k - st[left(si)], mid + 1, se);
  }
  ```

- Find max and it's occurance count
  ```java
  public static class Data {
    public int max, count;
    public Data(int max, int count) {this.max = max;this.count = count;}
  }
  public Data construct(int ss, int se, int si) {
    if (ss == se) return st[si] = new Data(arr[ss], 1);
    int mid = getMid(ss, se);
    Data left = construct(ss, mid, left(si));
    Data right = construct(mid + 1, se, right(si));
    if (left.max == right.max) return st[si] = new Data(left.max, left.count + right.count);
    return st[si] = new Data(Math.max(left.max, right.max), left.max > right.max ? left.count : right.count);
  }

  public Data getMax(int ss, int se, int qs, int qe, int si) {
    if (qs <= ss && qe >= se) return st[si];
    if (se < qs || ss > qe) return new Data(Integer.MIN_VALUE, 0);
    int mid = getMid(ss, se);
    Data left = getMax(ss, mid, qs, qe, left(si));
    Data right = getMax(mid + 1, se, qs, qe, right(si));
    if (left.max == right.max) return new Data(left.max, left.count + right.count);
    return new Data(Math.max(left.max, right.max), left.max > right.max ? left.count : right.count);
  }

  public int getMaxValue(int ss, int se, int qs, int qe, int si) {
    return getMax(ss, se, qs, qe, si).value;
  }

  public int getMaxCount(int ss, int se, int qs, int qe, int si) {
    return getMax(ss, se, qs, qe, si).count;
  }

  public void update(int ss, int se, int i, int newVal, int si) {
    if (i < ss || i > se) return;
    if (ss == se) {
      arr[i] = st[si].max = newVal;
      return;
    }
    int mid = getMid(ss, se);
    update(ss, mid, i, newVal, left(si));
    update(mid + 1, se, i, newVal, right(si));
    if (st[left(si)].max == st[right(si)].max) {
      st[si] = new Data(st[left(si)].max, st[left(si)].count + st[right(si)].count);
    } else {
      st[si] = new Data(Math.max(st[left(si)].max, st[right(si)].max), st[left(si)].max > st[right(si)].max ? st[left(si)].count : st[right(si)].count);
    }
  }
  ```

- Searching for an array prefix with a given amount
  | Find the smallest index i such that the sum of the first `i` elements of the array is at least `x`
  ```txt
  // build, update and count is ~ to sum query
  sum(i) = sum(i*2+1) + sum(i*2+2)
  search(i,x) = if x<=sum(i*2+1) search(i*2+1,x) else search(i*2+2,x)
  ```
  ```java
  public int search(int si, int x, int ss, int se) { // init with search(0, x, 0, n-1)
    if (qs > se || qe < ss) return -1;
    else if (ss == se) return ss;
    int mid = getMid(ss, se);
    int left = search(si * 2 + 1, x, ss, mid);
    if (left == -1) return left; // on left we may or may not find the value
    return search(si * 2 + 2, x, mid + 1, se);
  }
  ```
  p.s. Binary-search can be used to find the pivot in prefix sum array. Also, BST essentially mimics the above behaviour, successor/predecessor op, with O(logn) time complexity for both search and update operations

- Searching for the first element greater than a given amount
  | For a given value `x` and a range  `a[l...r]`  find the smallest `a[i]` in the range  `a[l...r]`, such that `a[i]` is greater than `x`.
  ```txt
  max(i) = max(max(i*2+1), max(i*2+2))
  search(i,x) = if x>=max(i) return -1
                else if x<max(i*2+1) search(i*2+1,x) else search(i*2+2,x)
  ```
  ```java
  public int search(int si, int x, int ss, int se, int qs, int qe) { // init with search(0, x, 0, n-1, l, r)
    if (qs > se || qe < ss) return -1;
    else if (ss == se) return ss;
    int mid = getMid(ss, se);
    int left = search(si * 2 + 1, x, ss, mid, qs, qe);
    if (left != -1) return left;
    return search(si * 2 + 2, x, mid + 1, se, qs, qe);
  }
  ```

- Search for a subarray with the maximum sum in a given range of array `arr[l...r]`
  ```java
  public static class Data {
    public int ans, prefix, suffix, sum;
    public Data(int ans, int prefix, int suffix, int sum) { this.ans = ans; this.prefix = prefix; this.suffix  = suffix; this.sum = sum; }
    public Data(int val) { this.ans = this.prefix = this.suffix  = this.sum = Math.max(0, val); }
  }
  
  private Data combine(Data left, Data right) {
    // visualize with diagram
    int ans = Math.max(Math.max(left.ans, right.ans), left.suffix + right.prefix);
    int prefix = Math.max(left.prefix, left.sum + right.prefix);
    int suffix = Math.max(right.suffix, right.sum + left.suffix);
    int sum = left.sum + right.sum;
    return new Data(ans, prefix, suffix, sum);
  }

  public Data construct(int ss, int se, int si) {
    if (ss == se) return st[si] = new Data(arr[ss]);
    else {
      int mid = getMid(ss, se);
      return st[si] = combine(construct(ss, mid, left(si)), construct(mid + 1, se, right(si)));
    }
  }

  public Data getMax(int ss, int se, int qs, int qe, int si) {
    if (qs <= ss && qe >= se) return st[si];
    if (se < qs || ss > qe) return new Data(0);
    int mid = getMid(ss, se);
    return combine(getMax(ss, mid, qs, qe, left(si)), getMax(mid + 1, se, qs, qe, right(si)));
  }

  public void update(int ss, int se, int i, int newVal, int si) {
    if(i<ss && se>i) return;
    else if(ss == se) {
      arr[i] = newVal;
      st[si] = new Data(newVal);
    }else{
      int mid = getMid(ss, se);
      update(ss, mid, i, newVal, left(si));
      update(mid+1, se, i, newVal, right(si));
      st[si] = combine(st[left(si)], st[right(si)]);
    }
  }
  ```

- Searching for the k-th largest element in a range
  ```java
  // ???
  ```

### Storing vertices at each node - Merge Sort Tree

Storing data structures on segment tree nodes. For example, storing a sorted array(built via merged arrays on child nodes) for binary search based queries of current segment on each node. 

TC: O(2\*n\*logn=n\*logn) for construction, O(logn^2) for query, O(n*logn) for update. 
SC: O(n\*logn+2\*n) for construction and upkeep

```java
// ...
private int[] construct(int ss, int se, int si) {
  if (ss == se) {
    st[si] = new int[1];
    st[si][0] = arr[ss];
  } else {
    int mid = getMid(ss, se);
    st[si] = new int[se - ss + 1];
    int []left = construct(ss, mid, left(si));
    int []right = construct(mid + 1, se, right(si));
    merge(st[si], left, right);
  }
  return st[si];
}

private void query(int ss, int se, int qs, int qe, int si, int x) {
  if (qs <= ss && qe >= se) {
    return binarySearch(st[si], x);
              } else if (se < qs || ss > qe) {
                return -1;
              }
              int mid = getMid(ss, se);
              return Math.max(query(ss, mid, qs, qe, left(si), x), query(mid + 1, se, qs, qe, right(si), x));
}

private void update(int ss, int se, int i, int newVal, int si) {
  if (i < ss || i > se) return;
  if (ss == se) {
    arr[i] = newVal;
    st[si] = new int[1];
    st[si][0] = newVal;
  } else {
    int mid = getMid(ss, se);
    update(ss, mid, i, newVal, left(si));
    update(mid + 1, se, i, newVal, right(si));
    merge(st[si], st[left(si)], st[right(si)]);
  }
}
```

If we were to use `TreeSet`(a red-black BST) for storing sorted array, then the time complexity would be O(nlogn) for construction, O(logn) for query, O(logn) for update. 

**Basically we can use any data structure at reqd places that supports the operations as per the req**

### Lazy propagation

TLDR; Range updates via segmen tree updates in O(logn) time.

Basically, the child nodes are updated with the pending updates from the parent node. This is done to avoid updating the child nodes multiple times(by clubbing the updates together) and in cases when it isn't needed.

TC: O(nlogn) for construction, O(logn) for query, O(logn) for update
SC: O(2n) for construction and upkeep

```java
int []lazy = new int[maxSize]; // for each node, lazy[i] stores pending updates
// arr[n] can't be updated directly in O(logn) time
// ...
private void build(int ss, int se, int si) {
  if (ss == se) {
    st[si] = arr[ss];
  } else {
    int mid = getMid(ss, se);
    build(ss, mid, left(si));
    build(mid + 1, se, right(si));
    st[si] = 0;
  }
}

private void push(int si){
  if(left(si) < n){
    st[left(si)] += lazy[si];
    lazy[left(si)] += lazy[si];
  }
  if(right(si) < n){
    st[right(si)] += lazy[si];
    lazy[right(si)] += lazy[si];
  }
  lazy[si] = 0;
}

public void update(int ss, int se, int qs, int qe, int si, int diff) {
  if (qs > se || qe < ss) return;
  else if (ss == qs && se == qe) {
    st[si] += diff;
    lazy[si] += diff;
  } else {
    push(si);
    int mid = getMid(ss, se);
    update(ss, mid, qs, Math.min(qe, mid), left(si), diff);
    update(mid + 1, se, Math.max(qs, mid + 1), qe, right(si), diff);
    st[si] = Math.max(st[left(si)], st[right(si)]);
  }
}

public int getSum(int ss, int se, int qs, int qe, int si) {
  if (qs > se || qe < ss) return 0;
  if (ss == qs && se == qe) return st[si];
  push(si);
  int mid = getMid(ss, se);
  return getSum(ss, mid, qs, Math.min(qe, mid), left(si)) + getSum(mid + 1, se, Math.max(qs, mid + 1), qe, right(si));
}

// ...

// ----------- without lazy propagation
private void update(int ss, int se, int qs, int qe, int si, int diff) {
  if (qs > se || qe < ss) return;
  if (ss == qs && se == qe) {
    st[si] += diff;
    return;
  }
  int mid = getMid(ss, se);
  update(ss, mid, qs, Math.min(qe, mid), left(si), diff);
  update(mid + 1, se, Math.max(qs, mid + 1), qe, right(si), diff);
  st[si] = Math.max(st[left(si)], st[right(si)]);
}

private int getSum(int ss, int se, int qs, int qe, int si) {
  if (qs > se || qe < ss) return 0;
  if (ss == qs && se == qe) return st[si];
  int mid = getMid(ss, se);
  return getSum(ss, mid, qs, Math.min(qe, mid), left(si)) + getSum(mid + 1, se, Math.max(qs, mid + 1), qe, right(si));
}

// ...
```