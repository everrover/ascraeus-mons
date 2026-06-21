package dsa.leetcode.JupitersGreatStorm;

import java.util.*;

/**
 * https://leetcode.com/problems/number-of-pairs-after-increment/description/
 *
 * Uses a segment tree with lazy propagation to handle range-add updates on nums2 in O(log n) time. For type-1 queries, the lazy tag defers additions to subtrees until needed. For type-2 queries, since nums1.length <= 5, the tree is traversed leaf-by-leaf via findElementWithValue to count indices in nums2 whose current value equals (tot - nums1[j]), accumulating counts across all nums1 values. The leaf-scan is O(n2) per distinct nums1 value, making type-2 queries O(nums1.length * nums2.length).
 *
 * TC: O(Q * n1 * n2) SC: O(n2)
 * #array #segment-tree #lazy-propagation #hard
 */

class NumberOfPairsAfterIncrement {
    class BlocksImpl {
        private long[] tree, lazy;
        private int n;
        
        public BlocksImpl(int[] arr) {
            int arrsize = arr.length;
            int block = (int) Math.sqrt(arr.length);
            int size = block;
            if((block*block) < arrsize) size += 2;

            Map<Integer, Integer> []freq = new HashMap[size];
            for(Map<Integer, Integer> fq[] = freq[0]: )

            tree = new long[4 * n];
            lazy = new long[4 * n];
            if (n > 0) {
                build(arr, 0, 0, n - 1);
            }
        }
        
        private void build(int[] arr, int node, int start, int end) {
            if (start == end) {
                tree[node] = arr[start];
            } else {
                int mid = (start + end) / 2;
                build(arr, 2 * node + 1, start, mid);
                build(arr, 2 * node + 2, mid + 1, end);
                tree[node] = tree[2 * node + 1] + tree[2 * node + 2];
            }
        }
        
        public void rangeUpdate(int l, int r, int val) {
            rangeUpdateHelper(0, 0, n - 1, l, r, val);
        }
        
        private void rangeUpdateHelper(int node, int start, int end, int l, int r, int val) {
            if (lazy[node] != 0) {
                tree[node] += lazy[node] * (end - start + 1);
                if (start != end) {
                    lazy[2 * node + 1] += lazy[node];
                    lazy[2 * node + 2] += lazy[node];
                }
                lazy[node] = 0;
            }
            
            if (start > end || start > r || end < l) return;
            
            if (l <= start && end <= r) {
                tree[node] += (long) val * (end - start + 1);
                if (start != end) {
                    lazy[2 * node + 1] += val;
                    lazy[2 * node + 2] += val;
                }
                return;
            }
            
            int mid = (start + end) / 2;
            rangeUpdateHelper(2 * node + 1, start, mid, l, r, val);
            rangeUpdateHelper(2 * node + 2, mid + 1, end, l, r, val);
            tree[node] = tree[2 * node + 1] + tree[2 * node + 2];
        }
        
        public long pointQuery(int idx) {
            return pointQueryHelper(0, 0, n - 1, idx);
        }
        
        private long pointQueryHelper(int node, int start, int end, int idx) {
            if (lazy[node] != 0) {
                tree[node] += lazy[node] * (end - start + 1);
                if (start != end) {
                    lazy[2 * node + 1] += lazy[node];
                    lazy[2 * node + 2] += lazy[node];
                }
                lazy[node] = 0;
            }
            
            if (start == end) return tree[node];
            
            int mid = (start + end) / 2;
            if (idx <= mid)
                return pointQueryHelper(2 * node + 1, start, mid, idx);
            else
                return pointQueryHelper(2 * node + 2, mid + 1, end, idx);
        }
        
        public List<Integer> findElementWithValue(long targetValue) {
            List<Integer> indices = new ArrayList<>();
            findHelper(0, 0, n - 1, targetValue, indices);
            return indices;
        }
        
        private void findHelper(int node, int start, int end, long targetValue, List<Integer> indices) {
            if (lazy[node] != 0) {
                tree[node] += lazy[node] * (end - start + 1);
                if (start != end) {
                    lazy[2 * node + 1] += lazy[node];
                    lazy[2 * node + 2] += lazy[node];
                }
                lazy[node] = 0;
            }
            
            if (start == end) {
                if (tree[node] == targetValue) {
                    indices.add(start);
                }
                return;
            }
            
            int mid = (start + end) / 2;
            findHelper(2 * node + 1, start, mid, targetValue, indices);
            findHelper(2 * node + 2, mid + 1, end, targetValue, indices);
        }
    }
    public int[] numberOfPairs(int[] nums1, int[] nums2, int[][] queries) {
        List<Integer> reslist = new LinkedList<>();
        BlocksImpl st = new BlocksImpl(nums2);
        int res = 0;
        for(int []query: queries){
            if(query[0] == 1){
                st.rangeUpdate(query[1], query[2], query[3]);
            }else{
                for(int num: nums1){
                    res += st.findElementWithValue(query[1] - num);
                }
                reslist.add(res);
                res = 0;
            }
        }
        return reslist.stream().mapToInt(Integer::intValue).toArray();
    }
}
