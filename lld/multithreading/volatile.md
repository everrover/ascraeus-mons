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