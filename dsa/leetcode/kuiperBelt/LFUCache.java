package dsa.leetcode.KuiperBelt;

import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.Map;

/**
 * https://leetcode.com/problems/lfu-cache/
 * Design and implementation for a Least Frequently Used (LFU) Cache.
 * Incorporates efficient get and put operations adhering to LFU caching rules.
 * 
 * Linked key -> pair(value, freq)
 * Linked freq -> keys(HashSet with DoublyLinkedList for LRU)
 * 
 * On get operation, increment the frequency of the key and update the freqHash element(move the key from cnt->cnt+1)
 * On put operation, if the cache is full, remove the least frequently used element from the freqHash(or LRU element)
 *  and nodeHash and insert the new key
 *  If the key is already present, update the value and increment the frequency of the key and update the freqHash element(move the key from cnt->cnt+1, simply called get(key))
 *
 * TC: O(1) for both get and put operations
 * SC: O(n) where n is the total number of keys that can be inserted in the cache
 * #hash-table #linked-list #design #doubly-linked-list #hard
 */
class LFUCache {

  private static class Pair{
    public int a,b;
    public Pair(int a, int b){
      this.a = a;
      this.b = b;
    }
    public String toString(){
      return "{"+a+"-"+b+"}";
    }
  }

  private Map<Integer, Pair> nodeHash;
  private Map<Integer, LinkedHashSet<Integer>> freqHash;
  int currCount, capacity, leastFreq;

  public LFUCache(int capacity) {
    nodeHash = new HashMap<>();
    freqHash = new HashMap<>();
    this.capacity = capacity;
    leastFreq = Integer.MAX_VALUE;
  }

  public int get(int key) {
    if(nodeHash.containsKey(key)){
      Pair pair = nodeHash.get(key);
      incCount(key, pair.b);
      return pair.a;
    }else{
      return -1;
    }
  }

  private void incCount(int key, int cnt){
    freqHash.get(cnt).remove(key);
    if(freqHash.get(cnt).isEmpty()) {
      if(leastFreq == cnt) leastFreq++;
      freqHash.remove(cnt);
    }
    cnt++;
    freqHash.putIfAbsent(cnt, new LinkedHashSet<>());
    freqHash.get(cnt).add(key);
    nodeHash.get(key).b = cnt;
  }

  private void insertFreq(int key, int cnt){
    if(freqHash.containsKey(cnt)){
      freqHash.get(cnt).add(key);
    }else{
      LinkedHashSet lhs = new LinkedHashSet<>();
      lhs.add(key);
      freqHash.put(cnt, lhs);
    }
  }

  public void put(int key, int value) {
    if(capacity == 0){
      return;
    }else if(nodeHash.containsKey(key)){
      nodeHash.get(key).a = value;
      get(key); // just to update
    }else {
      while (nodeHash.size() >= capacity) {
        LinkedHashSet<Integer> freqNodeHashed = freqHash.get(leastFreq);
        // code truncated for brevity
      }
    }
  }
}
// my own implementation of LFU Cache elements
// class LFUCache {
//    private class Node {
//        public int val, key, count;
//        public Node next, prev;
//        public Node(int key, int val){
//            this.key = key;
//            this.val = val;
//            this.count = 1;
//            this.next = this.prev = null;
//        }
//    }

//    private class NodeQ{
//        private Node start, end;
//        public int count;
//        public NodeQ(){
//            start=new Node(-1, -1);
//            end=new Node(-2, -2);
//            start.next = end;
//            end.prev = start;
//            count = 0;
//        }
//        public void addNode(Node node){
//            if(count == 0) {
//                start.next = node;
//                end.prev = node;
//                node.prev = start;
//                node.next = end;
//            }else{
//                start.next.prev = node;
//                node.prev = start;
//                node.next = start.next;
//                start.next = node;
//            }
//            count++;
//        }
//        public void deleteNode(Node node){
//            node.next.prev = node.prev;
//            node.prev.next = node.next;
//            node.next = node.prev = null;
//            count--;
//        }
//        public Node deque(){ Node ret = end.prev; deleteNode(ret); return ret; }
//    }

//    private HashMap<Integer, Node> nodeHash;
//    private HashMap<Integer, NodeQ> nodeHashQ;
//    int currCount, capacity, leastFreq;

//    public LFUCache(int capacity) {
//        nodeHash = new HashMap<>();
//        nodeHashQ = new HashMap<>();
//        this.capacity = capacity;
//        currCount = 0;
//        leastFreq = Integer.MAX_VALUE;
//    }

//    public int get(int key) {
//        if(nodeHash.containsKey(key)){
//            Node node = nodeHash.get(key);
//            incCount(node, false);
//            return node.val;
//        } else return -1;
//        // Method bodies omitted for brevity
//    }
//    // Additional methods to implement LFU Cache logic
// }