### Solid principles

**S**ingle Responsibility Principle (SRP): _A class should have one and only one reason to change_, 
meaning that a class should have only one job or responsibility.
**O**pen/Closed Principle (OCP): _Software entities (classes, modules, functions, etc.) should be open for extension but closed for modification_,
meaning that the behavior of a module can be extended without modifying its source code.
```java
// payments processor

public interface PaymentProcessor {
    void processPayment(Payment payment);
}

public class PaypalPaymentProcessor implements PaymentProcessor {
    public void processPayment(Payment payment) {
        // process payment using PayPal API
    }
}

public class StripePaymentProcessor implements PaymentProcessor {
    public void processPayment(Payment payment) {
        // process payment using Stripe API
    }
}

// add a new payment processor
public class SquarePaymentProcessor implements PaymentProcessor {
    public void processPayment(Payment payment) {
        // process payment using Square API
    }
}

```
**L**iskov Substitution Principle (LSP): _Objects of a superclass should be replaceable with objects of a subclass without 
affecting the correctness of the program_, meaning that a subclass should be substitutable for its superclass.
```java
class Rect {
    protected int width;
    protected int height;
    public Rect(int width, int height) {
        this.width = width;
        this.height = height;
    }
    public int getArea() {
        return width * height;
    }
}

class Square extends Rect {
    public Square(int side) {
        super(side, side);
    }
    
    public int getArea() {
        return this.width * this.width;
    }
}
```

**I**nterface Segregation Principle (ISP): _Clients should not be forced to depend on interfaces they do not use_,
meaning that a class should not be forced to implement interfaces it does not use.

```java
public interface Vertebrae {
    void eat();
    void fly();
}
public class Bird implements Vertebrae {
    public void eat() {
        // eat
    }
    
    public void fly() {
        // fly
    }
}
public class Fish implements Vertebrae {
    public void eat() {
        // eat
    }
    
    public void fly() {
        // not applicable - should'nt be forced to implement
        throw new UnsupportedOperationException();
    }
}
```

**D**ependency Inversion Principle (DIP): _High-level modules should not depend on low-level modules. Both should depend on abstractions_,
Additionally, abstractions should not depend on details. Details should depend on abstractions.

```java
// ~ examples : List, Map, Set don't need to know about the implementation details of the data structure  
// List.add -> LinkedList.add, ArrayList.add, etc. but the client or high level user doesn't need to know about the implementation details
// STORE
public class Store {
    private PaymentProcessor paymentProcessor;

    public Store(PaymentProcessor paymentProcessor) {
        this.paymentProcessor = paymentProcessor;
    }

    public void checkout(Payment payment) {
        paymentProcessor.processPayment(payment); // STORE doesn't need to know about the payment processor details, just 
      // the interface details of how to process the payment, and what to expect
    }
}

public interface PaymentProcessor {
    void processPayment(Payment payment);
}

// PAYPAL
public class PaypalPaymentProcessor implements PaymentProcessor {
    public void processPayment(Payment payment) {
        // process payment using PayPal API
    }
}

// STRIPE
public class StripePaymentProcessor implements PaymentProcessor {
    public void processPayment(Payment payment) {
        // process payment using Stripe API
    }
}


```