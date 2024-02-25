# Open-source caching solutions

❗Note: All examples i present will be based off either Java or in shell.

- [Redis](./redis.md)
- Memcache
- Couchbase
- Apache Ignite
- Hazelcast IMDG
- Mcrouter
- Varnish Cache
- Squid Caching Proxy
- NGINX
- Apache Traffic Server

**Niche cases**
- CDNs for static content storage(helps in load on source server as well)
- Static assets and config storage on client machines
- 

Mostly memory-based caching solutions are prevalent. Since DBs are already there... so disk-based caching is used only for very specific use-cases(queries, warmer backups, etc.)

**Cache warming is a common practice**. It's a process of pre-loading the cache with data that is likely to be needed in the near future.

Thoughts::

- In a cache cluster(master-slave) can we configure a cache to be only one which should store snapshots of data in SSDs for backups and cache warming?
  - Allocating more resources to this back-up node, can it help?

## Basic caching strategies

![Caching strategies](./caching_strategies.jpeg)

- Cache miss costly if compute is costly(prevalent in all scenarios)

**Major ones::**
- Cache-aside caching [Read]
  - Practical for read-heavy workloads(most scenarios)
  - Inconsistent data needs TTL which needs to be fine-tuned
- Read-through caching [Read]
  - Practical for read-heavy workloads
  - Reduced network cost(if separate cache server used)
  - When the system starts:: Cache warming at times needed since all data is not cached 
  - TTL needs to be fine-tuned, although better to use if 
- Write-through caching [Write]
  - Limited use case - for very infrequent writes or **for very high consistency req**
- Write-back caching [Write]
  - Practical for de-duplication of writes for write-heavy workloads
  - Can lose data if cache server goes down
  - Otherwise similar to write-through caching
- Write-around caching [Write]
  - Reads are mostly cache hits
  - Reader-writer must separately exist for it to be effective[not shown in diagram]
  - I've personally/professionally used it with hybrid strategy( - cache-aside strategy on reader-end and channelling writes to cache and database separately on writer-end)

❗Hybrid/augmented strategies can and must be used as per the system's requirements.

**Monitored aspects of cache-effectiveness**

- Cache hit ratio
- Cache entry inconsistency(basically sampling some data points periodically allows to monitor this)
- Cache server read/write latency
- Cache read/write count
- Cache eviction rate
- Monitoring writes on DB for entries that are cached(used to accurately gauge the TTLs)
... many others can be monitored
