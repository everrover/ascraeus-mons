package dsa.leetcode.jovianMoonOrbit;

import java.util.*;

public class AccountsMerge {
  /**
   * https://leetcode.com/problems/accounts-merge/submissions/
   *
   * We iterate through each account merging accounts with common emails. The strategy is to use a 
   * disjoint set (also known as union-find) to group emails encountering a shared email, indicating 
   * that the emails should belong to the same cluster. Finally, sort and compile the emails under their 
   * respective names.
   * 
   * Could've used DFS/BFS and binary search to improve the performance, but the current solution is sufficient.
   *
   * TC: O(A \log A), where A is the total number of accounts. SC: O(A)
   * #array #hash-table #string #union-find #medium #bfs #dfs #binary-search
   */

  public static N find(N root) {
    var a = root;
    while (a.p != a) a = a.p;
    root.p = a;
    return a;
  }

  public List<List<String>> accountsMerge(List<List<String>> accounts) {
    Map<Integer, Set<String>> rmap = new HashMap<>();
    Map<String, Integer> map = new HashMap<>();
    Map<Integer, N> nmap = new HashMap<>();
    for (int j = 0; j < accounts.size(); j++) {
      List<String> lst = accounts.get(j);
      nmap.put(j, new N(j));
      for (int i = 1; i < lst.size(); i++) {
        String em = lst.get(i);
        if (map.containsKey(em)) {
          union(nmap.get(j), nmap.get(map.get(em)));
        } else {
          map.put(em, j);
        }
      }
    }
    for (int j = 0; j < accounts.size(); j++) {
      List<String> lst = accounts.get(j);
      int found = find(nmap.get(j)).index;
      rmap.putIfAbsent(found, new HashSet<>());
      Set<String> resset = rmap.get(found);
      for (int i = 1; i < lst.size(); i++) resset.add(lst.get(i));
    }
    List<List<String>> res = new LinkedList<>();
    for (Map.Entry<Integer, Set<String>> entry : rmap.entrySet()) {
      Integer key = entry.getKey();
      Set<String> lst = entry.getValue();
      List<String> reslist = new ArrayList<>(lst);
      Collections.sort(reslist);
      reslist.add(0, accounts.get(key).get(0));
      res.add(reslist);
    }
    return res;
  }
}

// Needed to include a class N to manage disjoint set elements
class N {
  int index;
  N p;
  N(int i) {index = i; p = this;}
  static void union(N a, N b) {
    a = find(a);
    b = find(b);
    if (a != b) a.p = b;
  }
}