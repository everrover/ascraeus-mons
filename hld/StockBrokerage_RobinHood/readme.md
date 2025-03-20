
### Some other references

- https://www.youtube.com/watch?v=SAa6xFyATcw&t=745s&ab_channel=Jordanhasnolife
- 

### Requirements:
- User should be able to ASK or BID for a stock[or any financial instruments] on stock exchange
- Users should be able to see realtime value of their portfolio and each component of it
- The calls to the stock exchange should be minimal[$$$]

### Optional requirements:
- Optimal usage of network resources
  - Using a single socket per user during live hours
- Reconciliation at the end of the day - need all events executed at end-of-the-day

### Non-functional requirements

- Consistency

### Estimations

- 100M users(DAU), 500M total
- DB calc??? Cache calc???
- 
- Network calc???

### Stock pricing overview

- STK price = max(BID1, BID2, ...) for each stock - easy for me to visualize and ponder
  - Other ways: Last trade price, avg(highest BID, lowest ASk) 
- Trades happen when BID >= ASK
- You'll have a list of ASKs and a list of BIDs
  - How to decide?? - Two pointer from high to low on either end to generate notifications
- Many exchanges might have same stocks and aggregating the data is our systems workload

### Consistent hashing

- Hashing the servers to direct the call to one server only per user N:1(mutually exclusive)
- Sharding DB#1 and cache on basis of user's hash

### Components - LB, User Servers, StockCaller[gateway for multiple SEs], SE[external dependency]

### Optional: Notification system, Auth system, InHouse Trade resolver, ReconciliationService

Reducing number of calls to the exchage(s)
- Needed in-house stock exchange (if legalities allow)
  - to maintain a cache of STK prices within the system
  - ##1
- [StockCaller]
  - Calling the exchange to fetch `n` number of BIDS and ASKS simultaneously
  - [StockCaller]Sending batched calls to the exchange for BIDS and ASKS
  - [StockCaller]If notification systems exist within the exchange try using them for updates
  - [StockCaller]If request is sent or rcv from StockExchange we can ask for other multiple things in parallel
  - ##1 [StockCaller]Try resolving two trades for ASKer and BIDder within the system and notify the SE
    - Ex - A BID req goes, and a relevant ASK exists within the system. Use it well.
  - Basically exploit SE to the fullest
- To have real high frequency algorithmic trades a separate tier can be created where calls would be batched and sent together to the SE without relying on StockCaller - easier to impl - definitely more costly

### User-facing servers - RDBMS#1

- RDBMS : Maintains write consistency. Especially w.r.t. cancellations of orders.
  - Active-Passive config based DB can be used to accomodate write-consistency and ASK/BID call workloads via SE notifications per order
- Sharding :: w.r.t users is possible[users A isn't related to user B, separate relational joins aren't needed in that case] with consistent hashing via `userId`
- Communication:
  - Real-time updates : Server -> User
    - Socket based communication for real-time updates to the user
    - One socket connection per user
    - Hardware and OS/Containers can be configured for high number of socket connections per node
  - User -> Server
    - HTTPS REST calls
    - Will contain crucial data points

**DB tables:** 

- `[userid, totalPortfolioVal, (... configs), (... profile)]` - Users
- `[holdingId, stockId, inUse, userID, confirmationNeededOrNo, holdingBoughtAt, ]` - HOLDINGs
  - inUse allows to execute soft-deletes in case of cancellations or sale of it
- `[askId, holdingId, userId, confirmed, (inUse, reason), (others...)]` - ASKs
  - `confirmed` flag reserves the ASK - exec at #5
- `[bidId, stockId, userId, (inUse, reason), (others...)]` - BIDs

### StockCaller | DB - RDBMS#2

- Communication
  - SC -> SE(s)
    - UDP multicast twice/thrice / whatever's needed [diff SEs might hv different requirements]
    - Deduplication(just thinking what they might do) : Using seq number
- StockCaller is acting as our gateway and information-server
  - Consistent hashing needed to distribute workload
- RDBMS#2
  - Sharded on `stockId`
  - Active-active or Active-Passive config based instances can be used per shard to accomodate high read workloads

- `[stockId, exchangeId, stockName, stockPrice, (others ..., SEBIDs, SEASKs, LastTrades), updatedAt]` - STOCKs
  - For reducing WRITEs[stock-prices] on RDBMS
- `[seOrderId, askId/bidId, type, stockId, userId, (others...)]` - mappings

### StockExchange Server : M - RDBMS#2
- Responsible for rcv updates[notifications from a single exchange]
- SE(s) -> SEC
  - UDP multicast rcv
  - Deduplication : Using seq number or update-ts with hashing- Active-active or Active-Passive config per node [a node is a single server single/multiple SE]
  - Hardware and OS/Containers can be configured for high number of socket connections per node
- Tracking live nodes? `zookeeper` would be needed[for heatbeats, network co-ordination and consensus checks for service] to track
- AA will need seq numbers to accomodate dedup

### Pricing Server : N - RDBMS#2
- #SES -> #PS
  - Web sockets based communication
  - [M #SES servers -> N #PS servers :: communication]
    - #PS would be excessively loaded 
- Serves info from exchange to user-servers
  - Stock price and other info aggregated across multiple SEs
  - A set of aggregated ASKs and BIDs from out of our system to provision a view for users to gauge the market😉
  - Sth else... if needed
- Use from RDBMS - Violates SRP a bit, but prevents overwhelming the SE
  - Queries for ASKs - {[askId: price, ...], ...}
  - Queries for BIDs - {[bidId: price, ...], ...}

### Event streaming - listened via `zookeeper`

- Streaming all the events within the system
  - Kafka + CassandraDB 
  - stream[eventId, eventPayload, (others ...)]

### SE

- They can publish on multiple streams.(most common is **UDP multicast** as is used by MQTT for exchange info and **REST calls for trade info**) Hence central notifications handler is essential.
- They send not

### Questions??

- Why not keep Stock Price within the HOLDINGs and BIDs table? 
  - For each stock update we need to update all HOLDINGs and BIDs.
- Since we're sharding `BIDs` and `ASKs` by `userId`'s to avoid race-conditions(due to cancellations) we'll need to call all shards for fetching `BIDs` and `ASKs` for accomodating calls/notifications from SE. 
  - To accomodate that in `mappings` table `userId` is added. So whenever we get a notification w.r.t. an `orderId` we'll route it to an appropriate shard. 
  - This helps in managing internal trades as well(if legalities allow)
- In practice web-sockets can cause an additional overhead in application mainetence and upgrades. How do we tackle it?
  - Using UDP multi-cast/uni-cast or native TCP based REST calls
  - Stateless all the way
- Why can't we have a central notification service?
  - Multi read-write overhead to different services. Complicates maintenence and design.

p.s. Notifications can be called events, request, logs, whatever we'd like to say.