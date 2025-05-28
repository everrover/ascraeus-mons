package dsa.leetcode.EuropasOceanWorld;

import java.util.*;

public class DesignInMemoryFileSystem {
  /**
   * https://leetcode.com/problems/design-in-memory-file-system/?envType=problem-list-v2&envId=design
   *
   * Because of simplified constraints, simply didn't handle the erronous cases
   * and assumed that the paths and files are valid. Otherwise, it's simple brute-force
   * based off the trie structure / Tree structure.
   *
   * TC: O(d + k + n log n), SC: O(n)
   *
   * #hash-table #string #design #trie #sorting #hard
   */
  private static class N {
    public String dir;
    public Map<String, N> to = new HashMap<>();
    public Map<String, StringBuilder> files = new HashMap<>();
    public N(String dir){
      this.dir = dir;
    }
  }

  N root = new N("/");

  public DesignInMemoryFileSystem() {
    // Initialize the root directory
  }

  public List<String> ls(String path) {
    String []route = path.split("/");
    N curr = root;
    for(int i=1; i<route.length; i++){
      String dir = route[i];
      if(curr.to.containsKey(dir))
        curr = curr.to.get(dir);
      else if(curr.files.containsKey(dir)){
        return List.of(dir);
        // }else{
        //   // throw new Exception("Req path doesn't exist");
        //   return List.of("path", "doesn't exist");
      }
    }
    List<String> res = new ArrayList<>(curr.files.keySet());
    res.addAll(curr.to.keySet());
    Collections.sort(res);
    return res;
  }

  public void mkdir(String path) {
    String []route = path.split("/");
    N curr = root;
    for(int i=1; i<route.length; i++){
      String dir = route[i];
      curr.to.putIfAbsent(dir, new N(dir));
      curr = curr.to.get(dir);
    }
  }

  public void addContentToFile(String filePath, String content) {
    String []route = filePath.split("/");
    N curr = root;
    for(int i=1; i<route.length-1; i++){
      String dir = route[i];
      curr = curr.to.get(dir);
    }
    String fname = route[route.length-1];
    curr.files.putIfAbsent(fname, new StringBuilder());
    curr.files.put(fname, curr.files.get(fname).append(content));
  }

  public String readContentFromFile(String filePath) {
    String []route = filePath.split("/");
    N curr = root;
    for(int i=1; i<route.length-1; i++){
      curr = curr.to.get(route[i]);
    }
    String fname = route[route.length-1];
    return curr.files.get(fname).toString();
  }
}

/**
 * Your FileSystem object will be instantiated and called as such:
 * FileSystem obj = new FileSystem();
 * List<String> param_1 = obj.ls(path);
 * obj.mkdir(path);
 * obj.addContentToFile(filePath,content);
 * String param_4 = obj.readContentFromFile(filePath);
 */