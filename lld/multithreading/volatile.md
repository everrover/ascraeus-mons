### `volatile` variables

Forces reads and writes of variables from only within memory... [supported in C, C++(some versions), Java as per my knowledge, not in Python, JS, Go]

> 'Volatile Guarantee - Any update to a volatile field updates the shared value of the field immediately. In other words, a different thread cannot get an inconsistent value of the shared variable after its value is updated.'
> 'Happens-Before Ordering - Any W to a volatile field happends before every subsequent read of the same field.'
> -Baeldung

WHY???

- Shared multi-processor arch can cause **cache coherance issues**
  - RAM data is loaded into CPU cache and is then operated on
  - Same data if is loaded for a multi-threaded op across two or more CPUs can also cause race-conditions
  - Time between loading and it being pushed to memory again can become a reason for race-conditions
  - Problems aggragavated especially with 
    - Out-of-order exn
    - Branch prediction
    - Speculative exn

Since we operate on in-memory values only, *to an extent* race-conditions and cache-coherance problems can be avoided...
without using Mutex or sync blocks.

Piggybacking - In practical terms, it can be used for inter-thread communication, ~ to channels within Go.

Props
- **Visibility guarantee** : Non-V vars, when they'll be written to mem from CPU? we don't know. i.e. there's a delay between cache and memory r/w's
  - Consider two +5 and +10 inc threads loaded into two CPUs
  w/o volatile cnt :::>>> T1 : cnt = 0+5 | T2 : cnt = 0+10 || M/M : cnt = 0 => 5[if T1 comes later and both read 0]/10[if T2 comes later and both read 0]/15[correct, is possible]
  w/ volatile cnt :::>>> T1 : cnt = &cnt | T2 : cnt = &cnt || M/M : cnt = 15[both read from within memory and inc]
- **Happens before** : Consider *re-ordered exn* and *parallel exn*
  - `a=b+c; d++;` No dependency -> so can happen in parallel
  - T1: `a=b+c; d=a+e;` Dependency -> so can't happen in parallel
  - T1: `a=b+c; flag = true;` T2: `if(flag) d=a+e` -> ❌:parallel
  - If `volatile` is applied on second `d`, `d=a+e` is executed only after, first. It's enforced...
  - `volatile` -> `flag`, `d=a+e` is executed only after first finishes

When is `volatile` practical?
- When one W and multiple R's are present
- When new val written is independent of prev value or is associative. i.e. on Atomic operations on written shared value.
  - Counters, Comparisons, etc.

The short time-gap between R & W from memory is still a possible conflicting point.
```java
// try it - src:baeldung
// inc
static volatile int count = 0;

void increment() {
    count++;
}
// 
Thread t1 = new Thread(new Runnable() {
    @Override
    public void run() {
        for(int index=0; index<1000; index++) {
            increment();
        }
    }
});

Thread t2 = new Thread(new Runnable() {
    @Override
    public void run() {
        for(int index=0; index<1000; index++) {
            increment();
        }
    }
});

t1.start();
t2.start();

t1.join();
t2.join();
```