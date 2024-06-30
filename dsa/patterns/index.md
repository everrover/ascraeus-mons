### DSA patterns

- [Sliding Window](dsa/patterns/sliding-window.md)
- [Two Pointers](dsa/patterns/two-pointers.md)
- [Fast and Slow Pointers](dsa/patterns/fast-and-slow-pointers.md)
- [Merge Intervals](dsa/patterns/merge-intervals.md)
- [Cyclic Sort](dsa/patterns/cyclic-sort.md)
- [In-place Reversal of a LinkedList](dsa/patterns/in-place-reversal-of-a-linkedlist.md)
- [Segment tree and Fenwick tree](dsa/patterns/segment-tree-and-fenwick-tree.md)

```java
public class BIT{
  private int[] bit;
  private int[] arr;
  private int n;

  public BIT(int n, int []arr){
    this.arr = arr;
    this.n = n+1;
    this.bit = new int[n+1];
    for(int i-0; i<arr.length; i++) update(i+1, arr[i]);
  }

  private lsb(int x){
    return x&-x;
  }

  public void update(int k, int val){
    for(int x=k+1; x<n; x+=lsb(x)) bit[x] += (val-arr[k]);
    arr[k] = val;
  }

  public int getsum(int k){
    int sum = 0;
    for(; k<n; k+=lsb(k)) sum += bit[k];
    return sum;
  }

  public void getsum(int l, int r){
    return getsum(r)-(l>1?getsum(l-1):0);
  }
}
```