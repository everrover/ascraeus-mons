### Singleton pattern

```java

public class Singleton {
  // ... other variables

  private static Singleton uniqueInstance;

  private Singleton(/* other variables */) { // private constructor - can't be instantiated with `new`
    // set variables
  }

  public static Singleton getInstance() {
    synchronized (Singleton.class) { // thread-safe access to the instance
      if (uniqueInstance == null) {
        uniqueInstance = new Singleton();
      }
    }
    return uniqueInstance;
  }

  // ... other methods
}

```

- Singleton pattern **ensures that a class has only one instance** and **provides a global point of access to it.**
- Use when you want to **control the number of instances** that can be created, or when you want to **restrict access to a single, global instance.** 
- Examples: 
  - **Configuration settings** for an application
  - **Logging** service
  - **Device manager** for a hardware device
  - **Network/DB connection pool** or interfaces
