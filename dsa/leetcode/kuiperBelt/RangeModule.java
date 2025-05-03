package dsa.leetcode.KuiperBelt;

import java.util.Map;
import java.util.TreeMap;

/**
 * https://leetcode.com/problems/range-module/
 *
 * A Range Module is a module that tracks ranges of numbers. Uses a TreeMap(BST) to keep the intervals sorted and allows
 * for querying but poor update performance.
 *
 * For add, we find ranges that overlap with the new range and merge them. by sub-tree creation and deletion.
 * For remove, we find ranges that overlap with the new range and split them. by sub-tree creation and deletion.
 * For query, we find the range that overlaps with the query range and return true if it exists.
 * 
 * TC: for addRange and removeRange: O(log n) :: subtree is deleted via deleting the submap(root) ref and all underneath it
 *     Guava implements it better
 *     for queryRange: O(log n)
 * SC: O(n)
 * #bin-search-tree #segment-tree #design #hard
 */
public class RangeModule {

  TreeMap<Integer, Integer> m = new TreeMap<>();

  public RangeModule() {}

  public void addRange(int left, int right) {
    Map.Entry<Integer, Integer> l = m.floorEntry(left);
    Map.Entry<Integer, Integer> r = m.floorEntry(right);
    int llim=left, rlim=right;
    if(l!=null && l.getValue()>=left)llim=l.getKey();
    if(r!=null && r.getValue()>right)rlim=r.getValue();
    m.subMap(llim, rlim).clear();
    m.put(llim, rlim);
  }

  public boolean queryRange(int left, int right) {
    Map.Entry<Integer, Integer> l = m.floorEntry(left);
    return l!=null && l.getValue()>=right;
  }

  public void removeRange(int left, int right) {
    Map.Entry<Integer, Integer> l = m.floorEntry(left);
    Map.Entry<Integer, Integer> r = m.floorEntry(right);
    int llim=-1, rlim=-1;
    if(l!=null && l.getValue()>left) m.put(l.getKey(), left);
    if(r!=null && r.getValue()>right) m.put(right, r.getValue());
    m.subMap(left, right).clear();
  }
}

/**
 public class RM {
 private static class SegmentNode{
 public int l, r;
 public boolean state;
 public SegmentNode left, right;
 public SegmentNode(int l,int r,boolean state){
 this.l = l; // ss
 this.r = r; // se
 this.state = state;
 }

 public boolean query(SegmentNode node, int qs, int qe){
 if(qs>=node.r || qe<=node.l) return true; // no overlap
 else if((qs<=node.l && qe>=node.r) || node.left == null) return node.state; // full overlap
 int mid = node.l+(node.r-node.l)/2;

 if(qe<=mid) return query(node.left, qs, qe); // left-segment
 else if(qs>=mid) return query(node.right, qs, qe); // right-segment
 else return query(node.left, qs, qe) && query(node.right, qs, qe); // overlapping
 }

 public boolean update(SegmentNode node, int qs, int qe, boolean state){
 if(node.l>=qs && node.r <= qe){
 node.left = null; node.right = null;
 return node.state = state;
 } else if(qs>=node.r || qe<=node.l) return node.state;
 int mid = node.l+(node.r-node.l)/2;
 if(node.left == null){
 node.left = new SegmentNode(node.l, mid, node.state);
 node.right = new SegmentNode(mid, node.r, node.state);
 }
 boolean left = update(node.left, qs, qe, state);
 boolean right = update(node.right, qs, qe, state);
 return node.state = left && right;
 }
 }

 private SegmentNode root;

 public RM() {
 root = new SegmentNode(0, (int)(1e3+1), false);
 }

 public void addRange(int left, int right) {
 root.update(root, left, right, true);
 }

 public boolean queryRange(int left, int right) {
 return root.query(root, left, right);
 }

 public void removeRange(int left, int right) {
 root.update(root, left, right, false);
 }

 }
 */