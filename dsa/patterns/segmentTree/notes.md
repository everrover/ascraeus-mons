# Segment tree & Fenwick tree

## Segment tree

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

- 


### Fenwick tree

> A Fenwick tree or binary indexed tree is a data structure that can efficiently update elements and calculate prefix sums in a table of numbers.

**TC::O(nlogn) for construction, O(logn) for query, O(logn) for update**
**SC::O(n) for construction and upkeep**

### When can it be used

- Associative property should hold f(a, f(b,c)) = f(f(a,b), c)
- Mathematical inverse should be possible for functions
- Examples: Addition, Multiplication, etc.

**Tree exist w.r.t. bits set**

```markdown
// parent-child relationship
0 (0000)
|
// only 1 bit set more than lsb(0)=0 [from right]
1 (*0001*)    2 (001**0**)  4 (0**1**00)              8 (**1**000)
              |             |                         |
              3 (*0011*)    5(*0101*) 6 (011**0**)    9(*1001*)  10 (10**1**0) 12 (1**1**00)
                                      |                          |             | 
                                      7 (*0111*)                 11 (*1011*)   13 (110**1**) 14 (11**1**0)
                                                                                             |
                                                                                             15 (*1111*)

lsb(i) = i & -i // least significant bit - & op with 2s complement
parent(i) = i - lsb(i) // used for query
child(i) = i + lsb(i) // used for update

// ranges covered
4=(0)(100) (layer1 so picked 0000 in bin[no-set-bits] as init and 100 elems afterwards) -> 0,3
5=(01)(01) -> (layer2 so picked 0100 in bin as init[1-set-bits] and 0001 elems afterwards) -> 4,4
7=(011)(1) -> (layer3 so picked 0110 in bin as init[2-set-bits] and 0001 elems afterwards) -> 6,6
11=(101)(1) -> (layer3 so picked 1010 in bin as init[2-set-bits] and 0001 elems afterwards) -> 10,10
13=(11)(01) -> (layer3 so picked 1100 in bin as init[2-set-bits] and 0001 elems afterwards) -> 12,12
14=(11)(10) -> (layer3 so picked 1100 in bin as init[2-set-bits] and 0010 elems afterwards) -> 12,13
15=(111)(1) -> (layer4 so picked 1110 in bin as init[3-set-bits] and 0001 elems afterwards) -> 14,14

layer means number of set bits picked as prefix
and remaining bits are the count of elements for which we store range info in that layer
0 (0000)
|
1 (0 - 1)     2 (0,1)    4 (0,3)             8 (0,7)
              |             |                |
              3 (2,2)    5 (4,4)  6 (4,5)    9 (8,8)  10 (8,9)    12 (8,11)
                                  |                   |           | 
                                  7 (6,6)             11 (10,10)  13 (12,12) 14 (12,13)
                                                                  |
                                                                  15 (14,14)
```

**A version with expl** :: Target 4mins

```java
public class FenwickTree {
  private int[] ft;
  private int n;
  private int []arr;

  public FenwickTree(int n) {
    ft = new int[n + 1];
    this.n = n;
    this.arr = new int[n];
  }

  public FenwickTree(int n, int []arr) {
    ft = new int[n + 1];
    this.n = n;
    this.arr = arr;
    for(int i = 0; i < n; i++) update(i, arr[i]);
  }

  private int parent(int i) { // parent of node i - use i-(i&-i) for parent node and not method
    return i - lsb(i);
  }

  private int next(int i) { // next of node i - use i+(i&-i) for next node and not method
    return i + lsb(i);
  }

  private int lsb(int i) { // least significant bit
    return i & -i;
  }

  public int rangesumquery(int b) {
    int sum = 0;
    for (b=b+1; b>0; b=parent(b)) sum+=ft[b];
    return sum;
  }

  public int rangesumquery(int a, int b) {
    return rangesumquery(b) - (a == 1 ? 0 : rangesumquery(a - 1));
  }

  public void update(int k, int newval) {
    for(int idx=k+1; idx<ft.length; idx=next(idx)) ft[idx]+=(newval-arr[k]);
    arr[k] = newval;
  }
}
```
