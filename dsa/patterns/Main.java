package dsa.patterns;

import java.util.*;
import java.io.BufferedReader;
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
			this.st = new int[maxSize][0];
			construct(0, n-1, 0, arr);
      // System.out.println("ST: "+Arrays.deepToString(st));
		}
		private int[] merge(int []one, int []two){
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
		
		private int[] construct(int ss, int se, int si, int []arr){
			if(ss == se){
				return st[si] = new int[]{arr[ss]};
			}else{
				int mid = ss+(se-ss)/2;
				return st[si] = merge(construct(ss, mid, 2*si+1, arr), construct(mid+1, se, 2*si+2, arr));
			}
		}
		
		private int binsearch(int []arr, int x){
			int idx=-1, l=0, r=arr.length-1, mid;
			while(l<=r){
				mid = (l+r)/2;
				if(arr[mid]>x){
					idx = mid;
					r = mid-1;
				}else l=mid+1;
			}
      if(idx==-1) return 0;
			return arr.length-idx;
		}
		
		public int query(int ss, int se, int si, int qs, int qe, int x){
			if (qs>se || qe<ss) {
				return 0;
			}else if(qs<=ss && se<=qe){
				return binsearch(st[si], x);
			}else {
        int mid = ss+(se-ss)/2;
        int left = query(ss, mid, 2*si+1, qs, qe, x);
        int right = query(mid + 1, se, 2*si+2, qs, qe, x);
        return left+right;
      }
		}
	}
	public static void main (String[] args) throws Exception {
    try{
      BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
      StringTokenizer st = new StringTokenizer(br.readLine());
      
      int n = Integer.parseInt(st.nextToken());
      
      int[] array = new int[n];
      st = new StringTokenizer(br.readLine());
      for(int i=0;i<n;i++){
        array[i]= Integer.parseInt(st.nextToken());
      }
      
      st = new StringTokenizer(br.readLine());
      int qcnt = Integer.parseInt(st.nextToken());

      int queries[][] = new int[qcnt][3];
      for(int i=0; i<qcnt; i++){
        st = new StringTokenizer(br.readLine());
        queries[i][0] = Integer.parseInt(st.nextToken())-1;
        queries[i][1] = Integer.parseInt(st.nextToken())-1;
        queries[i][2] = Integer.parseInt(st.nextToken());
      }

      ST segt = new ST(array);
      for(int i=0; i<qcnt; i++){
        System.out.println(segt.query(0, n-1, 0, queries[i][0], queries[i][1], queries[i][2]));
      }
    }catch(Exception e){
      System.out.println(e);
    }
	}
}