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
  int[] arr, bit;
  int n;
  public BIT(int n, int arr[]){
    this.arr = arr;
    this.n = n;
    bit= new int[n+1];
    for(int i=0; i<n; i++){
      update(i, arr[i]);
    }
  }

  public int getsum(int k){
    int sum = 0;
    for(k=k+1; k<n; k-=(k&-k)) sum+=bit[k];
    return sum;
  }

  public void update(int k, int val){
    for(int x=k+1; x<n; x-=(x&-x)) bit[x]+=(val-arr[k]);
    arr[k] = val;
  }

  public int getsum(int k){
    return getsum(a)-b>0?getsum(b):0;
  }
}
```