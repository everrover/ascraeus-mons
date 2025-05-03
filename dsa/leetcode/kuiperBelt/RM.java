package dsa.leetcode.KuiperBelt;

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
