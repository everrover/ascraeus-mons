### Chapter 1: Intro to Design Patterns = *Strategy Pattern*

**The Strategy Pattern** defines a family of algorithms, encapsulates each one, and makes them interchangeable. Strategy lets the algorithm vary independently from clients that use it.

> Design pattern
> ==============
> Identify the aspects of your application that vary and separate them from what stays the same.
> ==============
> Essentially, encapsulate what varies. DRY principle

> Design principle
> ================
> Program to an interface[any super-type which have to be implemented concretely], not an implementation
> ================
> Allow behavior to be extended without modifying the code that uses it. i.e. polymorphism
> In case of Java, it is achieved by using interfaces, abstract classes and super-classes

> Design principle
> ================
> Favor composition over inheritance
> ================
> Inheritance = "is-a" relationship, Composition = "has-a" relationship. `composition > inheritance`.
> Composition allows runtime behavior change, inheritance is static

---

```java
public class Duck {
  public void quack() {
    System.out.println("Quack");
  }

  public void swim() {
    System.out.println("Swim");
  }

  public void display() {
    System.out.println("Display");
  }

  public void fly() { // will cause non-flying ducks to fly
    // 
    System.out.println("Fly");
  }
}

public class MallardDuck extends Duck {
  public void display() {
    System.out.println("MallardDuck display");
  }
}

public class RedheadDuck extends Duck {
  public void display() {
    System.out.println("RedheadDuck display");
  }
}

// ... other duck classes

public class RubberDuck extends Duck {
  public void display() {
    System.out.println("RubberDuck display");
  }

  // public void fly() { 
  //   // code duplication is an unintended side-effect, other non-flying ducks will have to override this method
  //   System.out.println("RubberDuck is sitting on the ground");
  // }
}
```

```java
public interface Quackable {
  public void quack();
}

public interface Flyable {
  public void fly();
}

public interface Duck {
  public void swim();
  public void display();
}

public class MallardDuck implements Duck, Quackable, Flyable {
  public void swim() {
    System.out.println("Swim");
  }

  public void display() {
    System.out.println("MallardDuck display");
  }

  public void quack() {
    System.out.println("Quack");
  }

  public void fly() {
    System.out.println("Fly");
  }
}

public class RubberDuck implements Duck, Quackable {
  public void swim() {
    System.out.println("Swim");
  }

  public void display() {
    System.out.println("RubberDuck display");
  }

  public void quack() {
    System.out.println("Quack");
  }
}

// - enforces rules, but classes have to implement all needed methods
// - if any change in req arise then all classes have to be updated
```

> Design pattern
> ==============
> Identify the aspects of your application that vary and separate them from what stays the same.

```java

public class FlyWithWings implements Flyable {
  public void fly() {
    System.out.println("Fly with wings");
  }
}

public class FlyNoWay implements Flyable {
  public void fly() {
    System.out.println("Can't fly");
  }
}

public class QuackHard implements Quackable {
  public void quack() {
    System.out.println("Quack hard");
  }
}

public class Squeak implements Quackable {
  public void quack() {
    System.out.println("Squeak");
  }
}

public class Duck {
  Flyable flyBehavior;
  Quackable quackBehavior;

  public Duck() {
  }

  public Duck(Flyable fb, Quackable qb) {
    flyBehavior = fb;
    quackBehavior = qb;
  }

  public void swim() {
    System.out.println("Swim");
  }

  public void display() {
    System.out.println("Display");
  }

  public void performFly() {
    flyBehavior.fly();
  }

  public void performQuack() {
    quackBehavior.quack();
  }

  public void setFlyBehavior(Flyable fb) {
    flyBehavior = fb;
  }

  public void setQuackBehavior(Quackable qb) {
    quackBehavior = qb;
  }
}

// Interfaces allowed to morph the behavior of the duck at runtime according to the type of duck
// By overriding the behavior of the parent class. Parnent could be an abstract class here.
// Otherwise it was default to the behavior of the parent class

public class MallardDuck extends Duck {
  public MallardDuck() {
    flyBehavior = new FlyWithWings();
    quackBehavior = new QuackHard();
  }

  public void display() {
    System.out.println("MallardDuck display");
  }
}

public class RubberDuck extends Duck {
  public RubberDuck() {
    flyBehavior = new FlyNoWay();
    quackBehavior = new Squeak();
  }

  public void display() {
    System.out.println("RubberDuck display");
  }
}
```

> Design principle
> ================
> Program to an interface[any super-type which have to be implemented concretely], not an implementation
> ================
> Allow behavior to be extended without modifying the code that uses it. i.e. polymorphism
> In case of Java, it is achieved by using interfaces, abstract classes and super-classes

```java

public interface Animal {
  public void makeSound();
}

// public abstract class Animal {
//   public abstract void makeSound();
// }

public class Dog implements Animal {
    private String bark() {
      return "Bark";
    }
    public void makeSound() {
      bark();
    }
}

public class Cat implements Animal {
  private String meow() {
    return "Meow";
  }
  public void makeSound() {
    meow();
  }
}
Dog d = new Dog();
d.makeSound(); // Bark

Cat c = new Cat();
c.makeSound(); // Meow

Animal a = new Dog();
a.makeSound(); // Bark

a = new Cat();
a.makeSound(); // Meow

// both ultimately call the same method, but behavior is determined at runtime
```

- Favor identifying the design before impl
- Classes can represent a state as well, a functional behaviour or an entity. Each with it's own data points and methods. Essentially, everything can be represented as an hierarchy of classes, each with it's own behavior and data points. Like a tree structure.
- Inheritance = "is-a" relationship, Composition = "has-a" relationship. `composition > inheritance`.
  - "Dog is an animal, Dog has a tail, Dog has a bark; Cat is an animal, Cat has a meow, Cat has a tail;"

```java
// if we impl `getters` and `setters` in the duck class, then we can change the behavior of the duck at runtime

public class Duck {
  Flyable flyBehavior;
  Quackable quackBehavior;

  public Duck() {
  }

  public Duck(Flyable fb, Quackable qb) {
    flyBehavior = fb;
    quackBehavior = qb;
  }

  public void swim() {
    System.out.println("Swim");
  }

  public void display() {
    System.out.println("Display");
  }

  public void performFly() {
    flyBehavior.fly();
  }

  public void performQuack() {
    quackBehavior.quack();
  }

  public void setFlyBehavior(Flyable fb) {
    flyBehavior = fb;
  }

  public void setQuackBehavior(Quackable qb) {
    quackBehavior = qb;
  }
}

public class MallardDuck extends Duck {
  public MallardDuck() {
    flyBehavior = new FlyWithWings();
    quackBehavior = new QuackHard();
  }

  public void display() {
    System.out.println("MallardDuck display");
  }
}

public class RubberDuck extends Duck {
  public RubberDuck() {
    flyBehavior = new FlyNoWay();
    quackBehavior = new Squeak();
  }

  public void display() {
    System.out.println("RubberDuck display");
  }
}

public class MiniDuckSimulator {
  public static void main(String[] args) {
    Duck mallard = new MallardDuck();
    mallard.performFly();
    mallard.performQuack();

    Duck rubber = new RubberDuck();
    rubber.performFly();
    rubber.performQuack();

    rubber.setFlyBehavior(new FlyWithWings()); // change the behavior at runtime
    rubber.performFly();
  }
}
```

```java
// Duck call
public class DuckCall {
  Quackable quackBehavior;

  public DuckCall() {
    quackBehavior = new QuackHard();
  }

  public void performQuack() {
    quackBehavior.quack();
  }

  public void setQuackBehavior(Quackable qb) {
    quackBehavior = qb;
  }
}
```

**The Strategy Pattern** defines a family of algorithms, encapsulates each one, and makes them interchangeable. Strategy lets the algorithm vary independently from clients that use it.

```java
public interface WeaponBehavior {
  public void useWeapon();
}

public class KnifeBehavior implements WeaponBehavior {
  public void useWeapon() {
    System.out.println("Knife");
  }
}

public class SwordBehavior implements WeaponBehavior {
  public void useWeapon() {
    System.out.println("Sword");
  }
}

public class BowAndArrowBehavior implements WeaponBehavior {
  public void useWeapon() {
    System.out.println("Bow and arrow");
  }
}

public class AxeBehavior implements WeaponBehavior {
  public void useWeapon() {
    System.out.println("Axe");
  }
}

public class Character {
  WeaponBehavior weapon;

  public Character() {
  }

  public void fight() {
    weapon.useWeapon();
  }

  public void setWeapon(WeaponBehavior wb) {
    weapon = wb;
  }
}

public class King extends Character {
  public King() {
    weapon = new SwordBehavior();
  }
}

public class Queen extends Character {
  public Queen() {
    weapon = new BowAndArrowBehavior();
  }
}

public class Troll extends Character {
  public Troll() {
    weapon = new AxeBehavior();
  }
}

public class Knight extends Character {
  public Knight() {
    weapon = new KnifeBehavior();
  }
}

public class CharacterSimulator {
  public static void main(String[] args) {
    Character king = new King();
    king.fight();

    Character queen = new Queen();
    queen.fight();

    Character troll = new Troll();
    troll.fight();

    Character knight = new Knight();
    knight.fight();

    knight.setWeapon(new BowAndArrowBehavior());
    knight.fight();
  }
}
```
