- https://www.designgurus.io/answers/detail/what-is-bloom-filter

HashSet and RedisSets used to check membership for a small dataset. But fails for appropriately timed large datasets.

Probabilistic data structures offer constant time complexity and constant space complexity at the expense of providing an answer that is non-deterministic.

Key ideas::
- Probabilistic
- Space efficient
- Checks if an element is a member of a set
- NO is definitive, YES is probabilistic

When to use::

- Constant time and space complexity to test membership
- Small memory footprint
- Insert and query operations are parallelizable
- A few false positives are acceptable
- No false negatives
- Allows privacy since only hashed values are used

When not to use::

- When you need a deterministic answer
- When you need to store the actual data
- When you need to delete data(exception: Counting Bloom Filter)

How does it work::

It uses buckets. 

BF discards the actual data and stores the hash of the data. The hash is used to set the bits in the buckets.

Hash functions: Item is passed through `k` hash functions to get `k` hash values. Each hash value is used to set/check a bit in the bucket.

Adding an item: Hashed values are used to set the bits in the buckets. Hash collisions are possible.

Check membership: Hashed values are checked against the bit set in the buckets. All bits should be set to confirm membership.

False positives: If all bits are set, it's a member. But, if all bits are not set, it's not a member. But, if all bits are set, it's not guaranteed that it's a member. It could be a false positive.

TC:: O(k) [for both check and add], k is the number of hash functions
SC:: O(n), n is the number of buckets

```
Buckets:

| 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 

Let's take item `BLUE` & `RED` and pass them through 3 hash functions to get 3 hash values.

Hash1(BLUE) = 3
Hash2(BLUE) = 7
Hash3(BLUE) = 11

Hash1(RED) = 4
Hash2(RED) = 7
Hash3(RED) = 9

Set the bits in the buckets(b just used to denote, it's set by BLUE):

| 0 | 0 | 0 | 1b | 1r | 0 | 0 | 1b1r | 0 | 1r | 0 | 1b | 0 | 0 | 0 | 

Check membership for `BLUE`:

Hash1(BLUE) = 3
Hash2(BLUE) = 7
Hash3(BLUE) = 11

On checking the bits in the buckets, all bits are set. So, `BLUE` is a member of the set.

Check membership for `GREEN`:

Hash1(GREEN) = 3
Hash2(GREEN) = 8
Hash3(GREEN) = 11

On checking the bits in the buckets, all bits are not set. So, `GREEN` is not a member of the set.

Check membership for `BLACK`:

Hash1(BLACK) = 3
Hash2(BLACK) = 7
Hash3(BLACK) = 9

On checking the bits in the buckets, all bits are set. So, `BLACK` is a member of the set. But, it's a false positive. Not because it's due to the hash collision, but because the bits are set by other items.

```

Real world solutions::

- Google BigTable
- Redis

Use cases:

- Prevent expecnsive disk seeks and writes.
```
        server
          |
(check membership of key)
          |
          v
      Bloom Filter
          ^
          |
(if present, might be false positive)
          |
          v
         Disk
```
- Check for ID being unique in a distributed system by checking if it's already being used
- Filter already shown posts in a feed for a user
- Check for mis-spelled words or strong words in a text
- Filter blocked IP addresses, URLs, or email addresses
- MapReduce uses it
- **Log structured merge trees** use it to reduce disk seeks and writes in *Cassandra* to check if key exists in SSTable.

### Extensions

- Counting Bloom Filter: 
  Instead of setting bits, it increments[ADD-ITEM]/decrements[DELETE-ITEM] the counter. It can be used to count the number of times an item is added. **Allows tracking of the number of times an item is added and deletes as well.**

  SC increases but complexity=O(k).
  TC increases =O(2k)=O(k) for ADD and DELETE operations. But, it's still constant.
- Scalable Bloom Filter:
  When the number of items exceeds the capacity of the Bloom Filter, it's replaced with a new one. The old one is merged with the new one. **Allows scaling of the Bloom Filter.**
- Striped Bloom Filter:
  Multiple Bloom Filters are used to reduce the false positive rate. Probably(**hashing, across concurrently running**) across shards. Each shard can manage a section of bits or 1 bit if needed. **Reduces the false positive rate.**

[Using in Redis:](https://redis.io/docs/latest/develop/data-types/probabilistic/bloom-filter/)

```bash
# Create a Bloom Filter, error_rate(float) is the probability of false positives, capacity(INT) is the number of items
BF.RESERVE key error_rate capacity

# Add an item
BF.ADD key item

# Check membership
BF.EXISTS key item

```

[Using in Hadoop:](https://hadoop.apache.org/docs/r3.3.1/hadoop-mapreduce-client/hadoop-mapreduce-client-core/MapReduceTutorial.html)
- http://infotech-bg.com/sites/default/files/presentations/2021/A02.pdf
  - Hot cache training
  - And using the trained BF on running data stream
- https://redis.io/blog/bloom-filter/
- https://gsd.di.uminho.pt/members/cbm/ps/dbloom.pdf
- hthttps://theory.stanford.edu/~rinap/papers/esa2006b.pdf
- https://www.eecs.harvard.edu/~michaelm/postscripts/im2005b.pdf
- https://ieeexplore.ieee.org/document/5751342
- [Go code](https://github.com/iamthebot/hyperbloom/blob/master/naivestriped.go)
- https://redis.io/docs/latest/develop/data-types/probabilistic/bloom-filter/
- https://redis.io/docs/latest/develop/data-types/probabilistic/cuckoo-filter/