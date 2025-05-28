package dsa.leetcode.EuropasOceanWorld;

import java.util.*;

public class DesignInMemoryFileSystem {
  private class N {
    Map<String, N> to = new TreeMap<>();
    Map<String, StringBuilder> files = new TreeMap<>();
  }

  private N root;

  public DesignInMemoryFileSystem() {
    root = new N();
  }

  public List<String> ls(String path) {
    N curr = root;
    List<String> res = new ArrayList<>();
    if (!path.equals("/")) {
      String[] route = path.split("/");
      for (int i = 1; i < route.length; i++) {
        curr = curr.to.get(route[i]);
      }
      String last = route[route.length - 1];
      if (curr.files.containsKey(last)) {
        res.add(last);
        return res;
      }
    }
    res.addAll(curr.to.keySet());
    res.addAll(curr.files.keySet());
    return res;
  }

  public void mkdir(String path) {
    N curr = root;
    String[] route = path.split("/");
    for (int i = 1; i < route.length; i++) {
      curr = curr.to.computeIfAbsent(route[i], k -> new N());
    }
  }

  public void addContentToFile(String filePath, String content) {
    String[] route = filePath.split("/");
    N curr = root;
    for (int i = 1; i < route.length - 1; i++) {
      curr = curr.to.get(route[i]);
    }
    String fname = route[route.length - 1];
    curr.files.putIfAbsent(fname, new StringBuilder());
    curr.files.put(fname, curr.files.get(fname).append(content));
  }

  public String readContentFromFile(String filePath) {
    String[] route = filePath.split("/");
    N curr = root;
    for (int i = 1; i < route.length - 1; i++) {
      curr = curr.to.get(route[i]);
    }
    String fname = route[route.length - 1];
    return curr.files.get(fname).toString();
  }
}

/**
 * https://leetcode.com/problems/design-in-memory-file-system/?envType=problem-list-v2&envId=design
 *
 * Implement an in-memory file system with capabilities to list files/directories, create directories,
 * add content to files, and read file contents. The system stores directories and files as nodes in a tree structure.
 * Users can simulate directory creation and file manipulation.
 *
 * TC: O(d + k + n log n), SC: O(n)
 *
 * #hash-table #string #design #trie #sorting #hard
 */