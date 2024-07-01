## Fenwick tree

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

  public void update(int k, int val) {
    for(int idx=k+1; idx<ft.length; idx=next(idx)) ft[idx]+=val;
    arr[k] += val;
  }
}
```

### Niche cases 

**Range update and pt query**

```java
public class FenwickTree {
  
  // same as above ... without `arr` and `n` as they are not needed

  public int pointquery(int b) { // sum[b...b]
    int sum = 0;
    for (b=b+1; b>0; b=parent(b)) sum+=ft[b];
    return sum;
  }

  public void update(int k, int newval) {
    for(int idx=k+1; idx<ft.length; idx=next(idx)) ft[idx]+=(newval-arr[k]);
  }

  public void rangeupdate(int l, int r, int val) {
    /** if update triggered on [l,r] then with `val` then for query on any index `b`
     * i<l => no impact on sum
     * r>=i>=l => sum = orig_sum+val
     * r<i => sum = orig_sum+val-val = orig_sum
     */
    update(l, val);
    update(r + 1, -val);
  }
}
```

**Range update and range query**

```java
public class RRFenwickTree {
  
  private int[] ft1, ft2;
  private int n;

  public RRFenwickTree(int n) {
    ft1 = new int[n + 1];
    ft2 = new int[n + 1];
    this.n = n;
  }

  public RRFenwickTree(int n, int []arr) {
    RRFT(n);
    for(int i = 0; i < n; i++) rangeupdate(i, i, arr[i]);
  }

  public void rangeupdate(int l, int r, int val) {
    /** if update triggered on [l,r] then with `val` then after re-arranging
     * the formulae we get will result in reqd query results. There are six cases in total and after re-aranging we get the apt results. Ver less chance of such tricky scenarios being asked in interviews.
     * 
     * Took me 2 hrs to evaluate all 6 cases and then re-arrange the formulae to get the apt results.
     */
    ft1.update(l, val);
    ft1.update(r + 1, -val);
    ft2.update(l, val * (l - 1));
    ft2.update(r + 1, -val * r);
  }

  public int prefixquery(int b) {
    return ft1.rangequery(b) * b - ft2.rangequery(b); // b * ()
  }

  public int rangesumquery(int a, int b) {
    return prefixquery(b) - (a == 1 ? 0 : prefixquery(a - 1));
  }
}
```