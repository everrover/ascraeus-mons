package dsa.leetcode.KuiperBelt;

import java.util.*;

public class VerticalOrderTraversalOfBinaryTree {
    /**
     * https://leetcode.com/problems/vertical-order-traversal-of-a-binary-tree/
     *
     * Given the root of a binary tree, calculate the vertical order traversal of the binary tree.
     * For each node at position (row, col), its left and right children will be at positions (row + 1, col - 1) and (row + 1, col + 1) respectively.
     * The vertical order traversal of a binary tree is a list of top-to-bottom orderings for each column index starting from the leftmost column and ending on the rightmost column.
     * Nodes on the same row and column are sorted by their values.
     *
     * Although PQ approach is faster, the TreeMap approach is easier to understand and implement.
     * 
     * TC: O(n) SC: O(n)
     * #hash-table #tree #dfs #bfs #sorting #binary-tree #hard #priority-queue
     */

    class Q{
        int val,x,y;
        public Q(int val, int x, int y) {
            this.val = val;
            this.x = x;
            this.y = y;
        }
    }
    public List<List<Integer>> verticalTraversalWithPQ(TreeNode root) {
        List<List<Integer>> res = new ArrayList<>();
        PriorityQueue<Q> pq = new PriorityQueue<>((Q a, Q b) -> {
            if(a.x!=b.x) return a.x - b.x;
            else if(a.y!=b.y) return a.y - b.y;
            else return a.val - b.val;
        });
        dfs(root,0,0, pq);
        while(!pq.isEmpty()) {
            List<Integer> list = new ArrayList<>();
            Q p = pq.poll();
            list.add(p.val);
            while(!pq.isEmpty() && pq.peek().x==p.x) list.add(pq.poll().val);
            res.add(list);
        }
        return res;
    }
    private void dfs(TreeNode root, int x, int y, PriorityQueue<Q> pq) {
        if(root==null) return;
        pq.add(new Q(root.val,x,y));
        dfs(root.left,x-1,y+1,pq);
        dfs(root.right,x+1,y+1,pq);
    }

    public List<List<Integer>> verticalTraversal(TreeNode root) {
        List<Integer>[] al = new List[2001]; // can use TreeMap(BST) to keep only required number of LL
        for (int i = 0; i <= 2000; i++) al[i] = new LinkedList<>();
        dfs(root, al);
        List<List<Integer>> res = new LinkedList<>();
        for (List<Integer> ll : al) if (!ll.isEmpty()) res.add(ll);
        return res;
    }

    private static class TreeNode {
        int val;
        TreeNode left, right;
        TreeNode(int val) { this.val = val; }
    }

    private static class T{
        public int i;
        public TreeNode n;
        public T(int idx, TreeNode node){
            i = idx;
            n = node;
        }
    }

    private void dfs(TreeNode root, List<Integer>[] al) {
        if (root == null) return;
        Queue<T> q = new LinkedList<>();
        q.offer(new T(0, root));
        Map<Integer, List<Integer>> tm = new HashMap<>();
        while (!q.isEmpty()) {
            int n = q.size();
            tm.clear();
            while (n-- > 0) {
                T t = q.poll();
                tm.putIfAbsent(t.i, new ArrayList<>());
                tm.get(t.i).add(t.n.val);
                if (t.n.left != null) q.offer(new T(t.i - 1, t.n.left));
                if (t.n.right != null) q.offer(new T(t.i + 1, t.n.right));
            }
            for (Map.Entry<Integer, List<Integer>> me : tm.entrySet()) {
                Collections.sort(me.getValue());
                al[me.getKey() + 1000].addAll(me.getValue());
            }
        }
    }

/*
public List<List<Integer>> verticalTraversal(TreeNode root) {
    Map<Integer, List<Integer>> m = new TreeMap<>();
    dfs(root, m);
    List<List<Integer>> al = new ArrayList<>(1000); // res
    for(Map.Entry<Integer, List<Integer>> me: m.entrySet()) al.add(me.getValue());
    return al;
  }

  private static class T{
    public int i;
    public TreeNode n;
    public T(int idx, TreeNode node){
      i = idx;
      n = node;
    }
  }

  private void dfs(TreeNode root, Map<Integer, List<Integer>> m){
    if(root == null) return;
    Queue<T> q=new LinkedList<>();
    q.offer(new T(0, root));
    Map<Integer, List<Integer>> tm = new HashMap<>();
    while(!q.isEmpty()){
      int n = q.size();
      tm.clear();
      while(n-->0){
        T t = q.poll();
        if(t.n == null) continue;
        tm.putIfAbsent(t.i, new ArrayList<>());
        tm.get(t.i).add(t.n.val);
        q.offer(new T(t.i-1, t.n.left));
        q.offer(new T(t.i+1, t.n.right));
      }
      for(Map.Entry<Integer, List<Integer>> me: tm.entrySet()) {
        m.putIfAbsent(me.getKey(), new ArrayList<>());
        Collections.sort(me.getValue());
        m.get(me.getKey()).addAll(me.getValue());
      }
    }
  }
     */
}