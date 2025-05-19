package dsa.leetcode.EuropasOceanWorld;

import java.util.*;


interface NI {

  // @return true if this NestedInteger holds a single integer, rather than a nested list.
  public boolean isInteger();

  // @return the single integer that this NestedInteger holds, if it holds a single integer
  // Return null if this NestedInteger holds a nested list
  public Integer getInteger();

  // @return the nested list that this NestedInteger holds, if it holds a nested list
  // Return empty list if this NestedInteger holds a single integer
  public List<NestedInteger> getList();
}

class NestedInteger implements NI {

  public Integer value;
  public List<NestedInteger> list;

  @Override
  public boolean isInteger() {
    return value != null;
  }

  @Override
  public Integer getInteger() {
    return value;
  }

  @Override
  public List<NestedInteger> getList() {
    return list;
  }
}

class NestedIterator implements Iterator<Integer> {

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
   * ~ to [Peeking Iteraor](../fermi_s_paradox/PeekingIterator.java) but used alternate approach
   * 
   * TC: O(n) SC: O(d)
   * #stack #iterator #dfs #design #medium
   * https://leetcode.com/problems/flatten-nested-list-iterator/?envType=problem-list-v2&envId=design
   */
}

class FlattenNestedListIterator {
}