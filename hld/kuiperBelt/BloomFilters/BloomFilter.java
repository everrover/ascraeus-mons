package hld.kuiperBelt.BloomFilters;

public class BloomFilter {
  /**
   * ./notes.md
   * 
   * this is just a naive implementation of bloom filter
   */

  private int size;
  private int hashCount;
  private int[] hashSeeds;
  private int[] bitArray;

  public BloomFilter() {
    this.size = 1000;
    this.hashCount = 3;
    this.hashSeeds = new int[]{3, 5, 7};
    this.bitArray = new int[size];
  }
  public BloomFilter(int size, int hashCount, int[] hashSeeds) {
    this.size = size;
    this.hashCount = hashCount;
    this.hashSeeds = hashSeeds;
    this.bitArray = new int[size];
  }

  public void add(String key) {
    for (int i = 0; i < hashCount; i++) {
      int hash = hash(key, hashSeeds[i]);
      bitArray[hash] = 1;
    }
  }

  public boolean contains(String key) {
    for (int i = 0; i < hashCount; i++) {
      int hash = hash(key, hashSeeds[i]);
      if (bitArray[hash] == 0) {
        return false;
      }
    }
    return true;
  }

  private int hash(String key, int seed) {
    int hash = 0;
    for (int i = 0; i < key.length(); i++) {
      hash = (hash * seed + key.charAt(i)) % size;
    }
    return hash;
  }
   
}
