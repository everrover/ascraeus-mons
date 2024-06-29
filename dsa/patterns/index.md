### DSA patterns

- [Sliding Window](dsa/patterns/sliding-window.md)
- [Two Pointers](dsa/patterns/two-pointers.md)
- [Fast and Slow Pointers](dsa/patterns/fast-and-slow-pointers.md)
- [Merge Intervals](dsa/patterns/merge-intervals.md)
- [Cyclic Sort](dsa/patterns/cyclic-sort.md)
- [In-place Reversal of a LinkedList](dsa/patterns/in-place-reversal-of-a-linkedlist.md)
- [Segment tree and Fenwick tree](dsa/patterns/segment-tree-and-fenwick-tree.md)

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
    else if(qs>ss || qe<se) return Integer.MAX_VALUE;
    else{
      int mid = getMid(ss, se);
      return Math.min(getMin(ss, mid, qs, qe, si*2+1), getMin(mid+1, se, qs, qe, si*2+2));
    }
  }
  private update(int ss, int se, int si, int idx, int newVal){
    if(idx<ss || idx >se) return Integer.MAX_VALUE;
    else if(ss == se) {
      return st[si] = arr[idx] = newVal;
    }else{
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