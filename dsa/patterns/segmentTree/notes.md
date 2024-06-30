# Segment tree & Fenwick tree

## Segment tree

> Segment tree is a data structure that allows answering range queries over an array effectively, while still being flexible enough to allow modifying the array. This includes finding the sum of consecutive array elements a[l…r], or finding the minimum element in a such a range in O(logn) time.

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
  
```java
public class MaxQuerySegmentTree {
  private int[] st;
  private int n;
  private int[] arr;

  public MaxQuerySegmentTree(int[] arr) {
    n = arr.length;
    int x = (int) (Math.ceil(Math.log(n) / Math.log(2))); // log2(n)
    int max_size = 2 * (int) Math.pow(2, x) - 1;
    st = new int[max_size];
    constructST(arr, 0, n - 1, 0);
  }

  private int getMid(int s, int e) {
    return s + (e - s) / 2;
  }

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

  public void update(int i, int newVal) {
    update(arr, 0, n - 1, i, newVal, 0);
  }

  public int getMax(int qs, int qe) {
    return getMax(0, n - 1, qs, qe, 0);
  }

  public static void main(String[] args) {
    int[] arr = {1, 3, 5, 7, 9, 11};
    MaxQuerySegmentTree tree = new MaxQuerySegmentTree(arr);
    System.out.println(tree.getMax(0, 5, 1, 3, 0)); // 7
    tree.update(arr, 0, 5, 1, 10, 0);
    System.out.println(tree.getMax(0, 5, 1, 3, 0)); // 10
  }
}
```

**Practice** :: Target 7mins

```java
public class MinQSegmentTree {
  private int[] arr;  
  private int[] st;
  private int n;

  public MinQSegmentTree(int []arr){
    int n = arr.length;
    int x = (int) (Math.ceil(Math.log(n)/Math.log(2)));
    int maxSize = (int)Math.pow(2, x)*2-1;
    st = new int[maxSize];
    this.arr = arr;
    construct();
  }

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

  private int getMin(int qs, int qe){
    getMin(0, n-1, qs, qe, 0);
  }

  private void update(int idx, int newVal){
    update(0, n-1, 0, idx, newVal);
  }

}
```

### Fenwick tree

> A Fenwick tree or binary indexed tree is a data structure that can efficiently update elements and calculate prefix sums in a table of numbers.

### When can it be used

- Associative property should hold f(a, f(b,c)) = f(f(a,b), c)
- Mathematical inverse should be possible for functions
- Examples: Addition, Multiplication, etc.

**Tree exist w.r.t. bits set**

```txt
// parent-child relationship
0 (0000)
|
// only 1 bit set more than lsb(0)=0 [from right]
1 (*0001*)    2 (001**0**)  4 (0**1**00)              8 (**1**000)
              |             |                         |
              3 (*0011*)    5 (0101) 6 (011**0**)     9 (1001)  10 (10**1**0) 12 (1**1**00)
                                     |                          |             | 
                                     7 (*0111*)                 11 (*1011*)   13 (110**1**) 14 (11**1**0)
                                                                                            |
                                                                                            15 (**1111**)

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

**A version with expl**

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
    for(int i = 0; i < n; i++) update(i+1, arr[i]);
  }

  private int parent(int i) { // parent of node i
    return i - lsb(i);
  }

  private int lsb(int i) { // least significant bit
    return i & -i;
  }

  public int rangesumquery(int b) {
    int sum = 0;
    for (; b>0; b=parent(b)) sum+=ft[b];
    return sum;
  }

  public int rangesumquery(int a, int b) {
    return rangesumquery(b) - (a == 1 ? 0 : rangesumquery(a - 1));
  }

  public void update(int k, int newval) {
    for(int idx=k+1; idx<ft.length; idx+=lsb(idx)) ft[idx]+=(newval-arr[k]);
    arr[k] = newval;
  }
}
```

### Advanced variants

