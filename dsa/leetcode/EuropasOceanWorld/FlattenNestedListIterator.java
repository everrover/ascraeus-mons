package dsa.leetcode.EuropasOceanWorld;

import java.util.*;

public class NestedIterator implements Iterator<Integer> {
  private Stack<Iterator<NestedInteger>> st;
  private NestedInteger curr;

  // Constructor
  public NestedIterator(List<NestedInteger> nestedList) {
    this.st = new Stack<>();
    this.curr = null;
    st.push(nestedList.iterator());
  }

  @Override
  public Integer next() {
    if (!peeking()) throw new NoSuchElementException();
    Integer result = curr.getInteger();
    curr = null;
    return result;
  }

  @Override
  public boolean hasNext() {
    return peeking();
  }

  private boolean peeking() {
    if (curr != null) return true;
    while (!st.isEmpty()) {
      if (!st.peek().hasNext()) {
        st.pop();
        continue;
      }
      NestedInteger next = st.peek().next();
      if (next.isInteger()) {
        curr = next;
        return true;
      }
      st.push(next.getList().iterator());
    }
    return false;
  }

  /**
   * Stack is used to simulate the traversal of the nested list
   * Peeking function checks if there is a next integer available in the structure
   * 
   * TC: O(n) SC: O(d)
   * #stack #iterator #dfs #design #medium
   * https://leetcode.com/problems/flatten-nested-list-iterator/?envType=problem-list-v2&envId=design
   */
}