# Snapshot Isolation

**Problem** : Stale reads with writes! Most prevalent in OLTP systems. Example: Banking systems, stock trading systems, etc.

Possible solutions:

- Use lock-based isolation to prevent dirty reads.
  - Basically, execute the transaction in a serializable manner. All transaction rows are locked until the transaction is complete. Even for read operations.
  - Reduces concurrency
  - Increased contention against multiple threads
- Allow dirty reads but use a mechanism to detect and resolve conflicts.
  - This allows for higher concurrency but can lead to inconsistent data being read.
  - Basically, the transaction reads the data and then checks if any other transactions have modified it before committing. If so, it rolls back and retries.
  - This can lead to increased contention and reduced performance.
- Use row-level versioning to allow concurrent reads and writes.
  - Each transaction gets a snapshot of the database at the start of the transaction *against the reqd rows* or maybe a full snapshot till a given seq-number/timestamp.
  - The transaction can read the snapshot without being blocked by other transactions.
  - When the transaction is ready to commit, it checks if any other transactions have modified the data it read. If so, it aborts and retries.
  - This allows for higher concurrency and reduces contention. Increases retries.

OK! Basically row-versioning is a WAL (Write-ahead log) per row for as long as possible in temp-db tables OR as is reqd by transactions.

| TS | 1 | 2 | 3 | 4 | 5 | 6 | 7 | 8 | 9 | 10 | 11 | 12 | 13 | 14 | 15 | 16 | 17 | 18 | 19 | 20 |
| :---: | :---: | :---: | :---: | :---: | :---: | :---: | :---: | :---: | :---: | :---: | :---: | :---: | :---: | :---: | :---: | :---: | :---: | :---: | :---: | :---: |
| R2D2 8000C | +1000C #1 | | | | -800C #3 | | | | +2000C | | | | -1000C #7 | | | | | | | |
| C3P0 2000C | | | | |  | | | | | | | | | | | | | | | |
| Han 3000C | -500C #1 | | | | +800C #3 + (+400C❗️) #4 + [+100C] #5 | | | | -1000C #7 | | | | +500C #7 | | | | | | | |
| Chewie 1000C | -500C #1 | | | | | | | | -1000C | | | | +500C #7 | | | | | | | |
| Luke 1000C | | | -800C #2 | | (-800C❗️) #4 [-200C] #5 | | | | | | | | +10000C #6 | | | | | | | |
| Leia 80000C | | | +800C #2 | | (+400C❗️) #4 + [+100C] #5 | | | | | | | | -10000C #6 | | | | | | | |

#5 can be resolved by the system but we'll still rollback and retry, since conflicting #4 is present, even if invalid. 

If stale reads are present due to #2[Write #2 not commited before #4 reads], for #4 or #5, then we rollback and retry since a write observed before `5` against these two transactions...

#7 & #6 can be resolved simultaneously by system since completely independent entities are involved

A given transaction needs the DB row operations till a certain point only, hence seq-numbers are reqd to filter recent entries. Same goes for write-conflict resolution.

### Two types:

- **Read Committed** Snapshot Isolation: The transaction reads only committed data and  does not see uncommitted changes made by other transactions. [default isolation level]
  - Snapshots are short-lived
  - 
- **Snapshot Isolation**: The snapshots are maintained on transactional level. Other transactions read the state of the database transactions happened before the transaction started.

### Limitations:

- Snapshots are to be maintained for a given time period. This can be done using a timestamp or a sequence number. 
  - Generating the seq number can become a bottleneck.
- Storing the snapshots consumes memory and disk space. 
- In case of Write-conflicts, the transaction needs to be rolled back and retried. Instead of waiting, we are retrying...