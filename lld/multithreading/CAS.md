### Compare and swap

In a way it's a mutex on hardware, in my opinion.

It's an OS-level or hardware level instruction meant to update the contents of a register or memory location if and only if it's equal to a certain value. Else the update fails...

Java -> `java.util.concurrent.atomic`

```java
// high level working
public final int getAndIncrement() {
    for (;;) {
        int current = get();
        int next = current + 1;
        if (compareAndSet(current, next))
            return current;
    }
}
```

**Pros**
- For generic workloads(less to mid contention) it works
- Superb for primitive types

**Cons**
- For high workloads(>10^6 ops per key per machine as 👍🏻) it works poorly due to infinite loop
- For object level abstractions it's not possible to perform comparison on hardware level
  - Practically DB level queries mitigate it using sth like 2PC, `UPDATE WHERE` or seq numbers for write-conflict resolution

Applications
- Wait-free RB trees - BST
- Primitives in concurrant scenarios
- Java
```
AtomicBoolean
AtomicInteger
AtomicIntegerArray
AtomicLong
AtomicReference - An object reference that may be updated atomically - How effective/practical it is, don't know
DoubleAccumulator
DoubleAdder
LongAdder
LongAccumulator
```