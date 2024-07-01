### DSA patterns

- [Sliding Window](dsa/patterns/sliding-window.md)
- [Two Pointers](dsa/patterns/two-pointers.md)
- [Fast and Slow Pointers](dsa/patterns/fast-and-slow-pointers.md)
- [Merge Intervals](dsa/patterns/merge-intervals.md)
- [Cyclic Sort](dsa/patterns/cyclic-sort.md)
- [In-place Reversal of a LinkedList](dsa/patterns/in-place-reversal-of-a-linkedlist.md)
- [Segment tree and Fenwick tree](dsa/patterns/segment-tree-and-fenwick-tree.md)

```java
import java.util.*;
import java.lang.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Main{
	private static class ST{
		private int[][] st;
		private int n;
		public ST(int arr[]){
			this.n = arr.length;
			int x = (int)Math.ceil(Math.log(n)/Math.log(2));
			int maxSize = 2*(int)Math.pow(2,x)-1;
			this.st = new int[maxSize];
			construct(0, n-1, 0, arr);
		}
		private []int merge(int []one, int []two){
			int []thr = new int[one.length + two.length];
			int i=0,j=0,k=0;
			while(j<one.length && k<two.length){
				if(one[j]<two[k]){
					thr[i++] = one[j++];
				}else{
					thr[i++] = two[k++];
				}
			}
			while(k<two.length){
				thr[i++] = two[k++];
			}
			while(j<one.length){
				thr[i++] = one[j++];
			}
			return thr;
		}
		
		private int[] constuct(int ss, int se, int si, int []arr){
			if(ss == se){
				return st[si] = new int[]{arr[ss]};
			}else{
				int mid = ss+(se-ss)/2;
				return st[si] = merge(construct(ss, mid, 2*si+1, arr), constuct(mid+1, se, 2*si+2, arr));
			}
		}
		
		private int binsearch(int []arr, int x){
			int idx=-1, l=0, r=0, mid;
			while(l<r){
				mid = (l+r)/2;
				if(arr[mid]>x){
					idx = mid;
					r = mid-1;
				}else l=mid+1;
			}
			return arr.length-idx+1;
		}
		
		public int query(int ss, int se, int si, int qs, int qe, int x){
			if(qs<=ss && qe>=se){
				return binsearch(st[si], x);
			}else  if (se < qs || ss > qe) {
				return 0;
			}
			int mid = getMid(ss, se);
			int left = query(ss, mid, 2*si+1, qs, qe, x);
			int right = query(mid + 1, se, 2*si+2, qs, qe, x);
			return left+right;
		}
	}
	public static void main (String[] args) throws java.lang.Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	    StringTokenizer st = new StringTokenizer(br.readLine());
	    
	    int n = Integer.parseInt(st.nextToken());
	    
	    int[] array = new int[n];
	    for(int i=0;i<n;i++){
	      array[i]= Integer.parseInt(st.nextToken());
	    }
	    
	    int qcnt = Integer.parseInt(st.nextToken());
	    
	    int queries[][] = new int[qcnt][3];
	    for(int i=0; i<n; i++){
	    	queries[i][0] = Integer.parseInt(st.nextToken());
	    	queries[i][1] = Integer.parseInt(st.nextToken());
	    	queries[i][2] = Integer.parseInt(st.nextToken());
	    }
	}
}
```