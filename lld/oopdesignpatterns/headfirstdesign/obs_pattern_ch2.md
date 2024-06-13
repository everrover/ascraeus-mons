### Chapter 2: Intro to Design Patterns = *Observer Pattern*



---

```java
public class WeatherData {
  private float temperature;
  private float humidity;
  private float pressure;

  public float getTemperature() {
    return temperature;
  }

  public float getHumidity() {
    return humidity;
  }

  public float getPressure() {
    return pressure;
  }

  public void setMeasurements(float temperature, float humidity, float pressure) {
    this.temperature = temperature;
    this.humidity = humidity;
    this.pressure = pressure;
    measurementsChanged(); // on update, it's called
  }

  public void measurementsChanged() {
    // ... we work here
  }
}
```

---

```java
public class WeatherData {
  // ... same as above

  public void measurementsChanged() {
    float temp = getTemperature();
    float humidity = getHumidity();
    float pressure = getPressure();

    currentConditionsDisplay.update(temp, humidity, pressure);
    statisticsDisplay.update(temp, humidity, pressure);
    forecastDisplay.update(temp, humidity, pressure);

  }
}

/*
 * No way to add or remove display elements
 * Display elements are tightly coupled to WeatherData, and vice versa i.e. if we add a new display, we have to modify WeatherData
 * Displays don't implement a common interface(encapsulation is broken)
 */

```

> **Publishers+Subscribers = Observer Pattern**
> ============================================
> **Observer Pattern** defines a one-to-many dependency between set of objects so that when one object changes state, all its dependents are notified and updated automatically
> ============================================
> One-to-many dependency bw. subject and observers

```java
public interface Subject { // One
  public void registerObserver(Observer o);
  public void removeObserver(Observer o);
  public void notifyObservers();
}

public interface Observer { // Many
  public void update(float temp, float humidity, float pressure);
}

public class ConcreteSubject implements Subject { // One
  private List<Observer> observers;
  private float temperature;
  private float humidity;
  private float pressure;

  public ConcreteSubject() {
    observers = new ArrayList<>();
  }

  public void registerObserver(Observer o) {
    observers.add(o);
  }

  public void removeObserver(Observer o) {
    observers.remove(o);
  }

  public void notifyObservers() {
    for (Observer o : observers) {
      o.update(temperature, humidity, pressure);
    }
  }

  public void measurementsChanged() {
    notifyObservers();
  }

  public void setMeasurements(float temperature, float humidity, float pressure) {
    this.temperature = temperature;
    this.humidity = humidity;
    this.pressure = pressure;
    measurementsChanged();
  }
}

public class ConcreteObserver implements Observer { // Many
  private float temperature;
  private float humidity;
  private float pressure;

  public void update(float temperature, float humidity, float pressure) {
    this.temperature = temperature;
    this.humidity = humidity;
    this.pressure = pressure;
    display();
  }

  public void display() {
    System.out.println("Temperature: " + temperature + " Humidity: " + humidity + " Pressure: " + pressure);
  }
}
```

1. Observers are dependent on the subject, but the subject is independent of the observers. Hence, the **loose coupling**. Thus inreractions bw. subject and observers need less awareness of each other.

> Design Principle: **Strive for loosely coupled designs between objects that interact**
> ========================================================================================
> **Loose coupling** = When two objects are loosely coupled, they can interact, but have very little knowledge of each other. A favorable design(even in high-level architecture) is to have many small, simple, independent objects interacting with each other, rather than a few large, complex ones interacting with each other.