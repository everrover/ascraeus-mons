package dsa.leetcode.EuropasOceanWorld;

import java.util.*;

public class MaxStack {

  /**
   * https://leetcode.com/problems/max-stack/description/
   *
   * Implement a max stack with operations to add to, remove from, and get max element efficiently.
   * Utilizes a doubly linked list and a TreeMap to maintain quick access to the max.
   *
   * TC: O(1) for top, O(logn) for push, pop, peekMax, and popMax 
   * SC: O(n)
   * #stack #design #doubly-linked-list #ordered-set #hard
   */

  private class Node {
    int value;
    Node prev, next;
    
    Node(int value) {
      this.value = value;
    }
  }

  private Node head, tail;
  private TreeMap<Integer, Stack<Node>> map;

  public MaxStack() {
    head = new Node(0);
    tail = new Node(0);
    head.next = tail;
    tail.prev = head;
    map = new TreeMap<>();
  }

  private void addNode(Node node) {
    head.next.prev = node;
    node.next = head.next;
    head.next = node;
    node.prev = head;
  }

  // Rest of the methods will follow the operations as described
}