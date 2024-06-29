### Segment tree & Fenwick tree

> Segment tree is a data structure that allows answering range queries over an array effectively, while still being flexible enough to allow modifying the array. This includes finding the sum of consecutive array elements a[l…r], or finding the minimum element in a such a range in O(logn) time.

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
    if (qs <= ss && qe >= se) {
      return st[si];
    }
    if (se < qs || ss > qe) {
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
  private void updateValue(int[] arr, int ss, int se, int i, int newVal, int si) {
    if (i < ss || i > se) {
      return;
    } else if(ss == se) {
      arr[i] = newVal; st[si] = newVal; return;
    } else if (se != ss) {
      int mid = getMid(ss, se);
      updateValue(arr, ss, mid, i, newVal, 2 * si + 1); updateValue(arr, mid + 1, se, i, newVal, 2 * si + 2);
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