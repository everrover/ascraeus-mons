package lld.questions;

public class Basic_FurnitureFactory {

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








}
