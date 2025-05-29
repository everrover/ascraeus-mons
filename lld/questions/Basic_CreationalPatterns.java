package lld.questions;

public class Basic_CreationalPatterns {

  /**
   * Factory Method is meant for creating X classes of families of similar objects i.e. with same parent class
   *
   * ![Factory Method Pattern](factory-method-pattern.png)
   *
   * For eg: Transportation with three mediums - Land, Water and Air.
   *
   * - Use abstract class / interface for the parent class (Transport is a root and is always interface)
   * - Use public abstract / concrete class for the concrete classes (LandTransport, WaterTransport, AirTransport, can
   * extend further to three level hierarchy[Transport -> AirTransport -> HelicopterTransport+PlaneTransport] or more)
   * Uses:
   * - When
   *  - a client cannot anticipate the class of objects it must create
   *  - implementation/number of a classes is unknown before
   *  - to create an object of a class in a specific way
   *  - to provide a common interface for creating objects, i.e. we want to create an object of a class
   *    - incl methods and types
   * - To control number of instances of a class or it's subclasses eg: Database connections, etc
   *  - We control the number of instances
   *    - by making the constructor private
   *    - providing a static method for creating instances
   *    - creating a static number - instance count
   *    - synchronized block to ensure thread-safety
   *
   *
   * Pros & Cons:
   * - Pros:
   *  1. Open/Closed Principle: New classes can be added without changing existing code.
   *  2. SRP: Client code depends on abstractions, not concrete implementations, building logic and underlying
   *  details/dependencies
   *  3. Flexibility: Easy compatibility just using a common interface, allowing easy swap of concrete objects
   *  4. Reusability: Common interface can be reused across different parts of the codebase
   *  - Cons:
   *  1. Bloated code complexity! More classes, more interfaces, more boilerplate code.
   *
   *
   * Notes:
   * - Generally FactoryMethod/AbstractFactory morphs-into-something-else like AbstractFactory, Builder, Prototype, etc.
   * or a mixture of several patterns.
   * -
   */

  private static interface Transport {
    public void travel();

    public Transport createTransport();
  }

  private static class LandTransport implements Transport {

    private LandTransport() {
      System.out.println("Creating land transport");
    }

    @Override
    public void travel() {
      System.out.println("Traveling by land");
    }

    @Override
    public Transport createTransport() {
      return new LandTransport();
    }
  }

  private static class WaterTransport implements Transport {

    private WaterTransport() {
      System.out.println("Creating water transport");
    }

    @Override
    public void travel() {
      System.out.println("Traveling by water");
    }

    @Override
    public Transport createTransport() {
      return new WaterTransport();
    }
  }

  private static class AirTransport implements Transport {

    private AirTransport() {
      System.out.println("Creating air transport");
    }

    @Override
    public void travel() {
      System.out.println("Traveling by air");
    }

    @Override
    public Transport createTransport() {
      return new AirTransport();
    }
  }

  /**
   * Abstract Factory is meant for creating X x Y classes of families of similar objects i.e. with same parent class
   *
   * ![Abstract Factory Pattern](abstract-factory-pattern.png)
   *
   * For eg: Gothic, Modern and Classic furniture with two types of furniture - Chair and Table.
   * |         | Chair       | Table |
   * | Gothic  | GothicChair | GothicTable |
   * | Modern  | ModernChair | ModernTable |
   * | Classic | ClassicChair | ClassicTable |
   *
   * An ensemble of
   * - public interface(Furniture is a root and is always interface)
   * - public abstract classes(Either of X or Y, here the Furniture items i.e. Chair and Table)
   * - public concrete factory classes(Either of Y or X, here the FurnitureFactory of Gothic, Modern and Classic)
   * - private concrete classes (Concrete implementations of the abstract classes, here the GothicChair, ModernChair, etc.)
   *  - Why private? Because we don't want to expose the concrete classes to the client code, only the factory classes
   *    and the abstract classes/interfaces should be exposed for use by the client code
   *
   * Application:
   * - Defining and using a family of related or dependent objects without specifying their concrete classes.
   * i.e. what's told above
   * - When the system needs to be independent of how its objects are created, composed, and represented. Same as with
   * Builder pattern. `interface`s provide this superpower... ❗️
   * - Ex: UI toolkit, HTML elements, [products]Fish and their families, Furniture and their families, etc.
   *
   * Pros & Cons:
   * - Pros:
   *  1. Same as Factory Method, but with more flexibility and extensibility
   *  4. For a given family of objects, on both X or Y, the code is de-coupled
   * - Cons:
   *  1. Bloated code complexity!
   *
   */
  public static interface FurnitureFactory {
    Chair createChair();
    Table createTable();
  }

  public static class ModernFurnitureFactory implements FurnitureFactory {
    public Chair createChair(){
      System.out.println("Creating modern chair");
      return new ModernChair();
    }
    public Table createTable(){
      System.out.println("Creating modern table");
      return new ModernTable();
    }
  }

  public static class ClassicFurnitureFactory implements FurnitureFactory {
    public Chair createChair(){
      System.out.println("Creating classic chair");
      return new ClassicChair();
    }
    public Table createTable(){
      System.out.println("Creating classic table");
      return new ClassicTable();
    }
  }

  public static class GothicFurnitureFactory implements FurnitureFactory {
    public Chair createChair() {
      System.out.println("Creating gothic chair");
      return new GothicChair();
    }

    public Table createTable() {
      System.out.println("Creating gothic table");
      return new GothicTable();
    }
  }

  public abstract static class Chair {
    abstract void sit();
  }

  public abstract static class Table {
    abstract void placeItems();
  }

  private static class ModernChair extends Chair {
    @Override
    void sit() {
      System.out.println("Sitting on a modern chair");
    }
  }

  private static class ModernTable extends Table {
    @Override
    void placeItems() {
      System.out.println("Placing items on a modern table");
    }
  }

  private static class ClassicChair extends Chair {
    @Override
    void sit() {
      System.out.println("Sitting on a classic chair");
    }
  }

  private static class ClassicTable extends Table {
    @Override
    void placeItems() {
      System.out.println("Placing items on a classic table");
    }
  }

  private static class GothicChair extends Chair {
    @Override
    void sit() {
      System.out.println("Sitting on a gothic chair");
    }
  }

  private static class GothicTable extends Table {
    @Override
    void placeItems() {
      System.out.println("Placing items on a gothic table");
    }
  }

  /**
   * Builder pattern - Allows building objects step-by-step
   *
   * Applications:
   * - Telescoping constructor: Very large constructor, and a large number of different constructors
   * -
   */

  public static class Director {
    public Director(){

    }
//    public Person buildDin() {
//      Person person = Person.
//          .setName("Din")
//          .setAge(30)
//          .setOccupation("Nincompoop")
//          .setAddress("123 Din Street");
//      return person;
//    }
//    public Builder buildDan() {
//
//    }
//    public Builder buildDun() {
//
//    }
  }

  public static interface Builder {
    public Builder setName(String name);
    public Builder setAge(int age);
    public Builder setAddress(String address);
    public Builder setOccupation(String occupation);

    public Builder build();

    public Builder getResult();

  }

  public static class Person implements Builder {
    private String name;
    private int age;
    private String address;

    private String occupation;

    private Person() {
      // private constructor to prevent direct instantiation
    }

    @Override
    public Builder setName(String name) {
      this.name = name;
      return this;
    }

    @Override
    public Builder setAge(int age) {
      this.age = age;
      return this;
    }

    @Override
    public Builder setAddress(String address) {
      this.address = address;
      return this;
    }

    @Override
    public Builder setOccupation(String occupation) {
      this.occupation = occupation;
      return this;
    }

    @Override
    public Builder build() {
      return new Person();
    }

    public Person getResult() {
      return this;
    }
  }

  public static class Engineer implements Builder {
    private String name;
    private int age;
    private String address;

    private String occupation;

    private Engineer() {
      // private constructor to prevent direct instantiation
      this.occupation = "Engineer";
    }

    @Override
    public Builder setName(String name) {
      this.name = name;
      return this;
    }

    @Override
    public Builder setAge(int age) {
      this.age = age;
      return this;
    }

    @Override
    public Builder setAddress(String address) {
      this.address = address;
      return this;
    }

    @Override
    public Builder setOccupation(String occupation) {
      return this;
    }

    @Override
    public Builder build() {
      return new Engineer();
    }

    public Engineer getResult() {
      return this;
    }
  }

  /**
   *
   * Singleton pattern - Ensures a class has only one instance and provides a global point of access to it.
   *
   * Applications:
   * - When exactly one object is needed to coordinate actions across the system.
   * * - When a class should have only one instance and it must be accessible to clients from a well-known access point.
   * * - When an instance of a class is needed to control access to a shared resource, such as a database or a file.
   *
   * Pros & Cons:
   * - Pros:
   *  1. Controlled access to the sole instance.
   *  2. Reduced namespace pollution, as the singleton class is not instantiated multiple times.
   *  3. Lazy initialization, if implemented, can save resources by delaying the creation of the instance until it is needed.
   *
   * - Cons:
   *  1. Can introduce global state into an application, making it harder to test and debug.
   */

  public static class Singleton {
    private static Singleton instance;

    private Singleton() {
      // private constructor to prevent instantiation
    }

    public static synchronized Singleton getInstance() {
      if (instance == null) {
        instance = new Singleton();
      }
      return instance;
    }

    public void doSomething() {
      System.out.println("Doing something in the singleton instance");
    }
  }

  private static class LazyMultithreadedSingleton {
    private static LazyMultithreadedSingleton instance;

    private LazyMultithreadedSingleton() {
      // private constructor to prevent instantiation
    }

    public static LazyMultithreadedSingleton getInstance() {
      if (instance == null) {
        synchronized (LazyMultithreadedSingleton.class) {
          if (instance == null) {
            instance = new LazyMultithreadedSingleton();
          }
        }
      }
      return instance;
    }

    public void doSomething() {
      System.out.println("Doing something in the lazy multithreaded singleton instance");
    }
  }

}
