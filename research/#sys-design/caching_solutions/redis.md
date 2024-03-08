# Redis - in-memory data structure store

- Supports several data structures
  - strings, hashes, lists, sets, sorted sets with range queries, bitmaps, hyperloglogs, geospatial indexes with radius queries, and streams
- Lua scripting allowed on server
- Pub/Sub provision?
- Single-threaded model used
  - No context switching overhead
  - No locks/deadlocks/thread-safety issues
  - Event-driven model for I/O mgmt
  - Multiplexing(in case of linux `epoll`, BSD->`kqueue`, `select` system calls)
- Heavy use of memory and optimizations centered around it
- Persistence support present
  - RDB snapshots
  - AOF logs
  - Hybrid(using both)
- Eviction policies
  - LRU
  - LFU
  - Random
  - TTL(can be used with any of the above)

**Horizontal scaling**

- Master slave replication for clusters
- Hash slotting(16834 slots) for sharding???
- Regional clusters will be restricted to network latency

- Network:: uses RESP(Redis Serialization Protocol), simple text-based protocol, meant for Redis specific serialization and deserialization:: multiplexing connections on single event loop run
- Sentinel monitors the cluster and check for failures and does **automatic failover**
  - Notifies admin if failover is not possible or for triggering alerts
  - Acts as a source-of-truth for the cluster(configuration-provider)
  - Concensus and quorum based decision making for more accurate failover decisions
  - Multi-sentinel setup can be used for high-availability

**Use Cases(GPT):**

- Caching: One of the most common uses of Redis is to cache frequently accessed data to reduce the load on databases and improve application response times.
- Session Storage: Redis is often used for session management in web applications due to its speed and ability to expire keys automatically.
- Real-Time Analytics: The fast read and write capabilities make Redis ideal for real-time analytics applications.
- Messaging Queues: Redis's Pub/Sub and list data structures are useful for implementing message queues.
- Leaderboards/Counting: Sorted sets make it easy to implement leaderboards for games and applications, as well as for counting and ranking items.

*Supported data structures:*

- Strings
- Lists
- Sets
- Sorted sets
- Hashes
- Bit arrays
- HyperLogLogs???
- Geospatial indexes???
- Streams???
- Bitmaps???

Core of this is using config dependent, zip-lists, intsets and other data-structures internally for memory and speed optimization.

**LB how??**

Server-end
- Partitioning across hash slots
- Each node gets subset of these hash-slots
- Rebalancing is done by moving slots from one node to another

Client-end
- Client library either maintains a mapping of which node holds which hash slots(cluster-aware client), or it doesn't
  - Used by Lettuce
- In first, client can directly connect to the node holding the hash slot, otherwise it connects to a random node and then gets redirected to the correct node
  - Used by Jedis
- LB for hot-spots
  - use key-prefixes to distribute hot-spots across nodes

**Jedis vs. Lettuce**

- Jedis
  - Blocking I/O
  - Synchronous
  - Not thread-safe
  - Connection pooling needed for multi-threaded apps
  - No automatic failover
  - Reactive-programming support absent
  - Use for simple, lightly multi-threaded applications
- Lettuce
  - Non-blocking I/O
  - Asynchronous/synchronous
  - Thread safe
  - Allows using advanced Redis utilization features
    - Cluster support
    - Pub/Sub
    - Sentinel support
    - etc
  - Automatic failover
  - Reactive-programming support present
  - Use for high-performance, multi-threaded, non-blocking I/O and advanced Redis utilization

**Other libraries**

- Reactive Redis
- Redisson

**Data str optimizations, how?**

- [Ziplists](https://redis.com/glossary/redis-ziplist/#:~:text=A%20ziplist%20is%20a%20compressed,allocations%20and%20reduces%20memory%20overhead.)
- [List](https://www.tecmint.com/open-source-caching-tools-for-linux/#1_Redis)