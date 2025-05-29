package lld.questions;

import java.util.ArrayList;
import java.util.List;

public class Basic_BehavioralPattern {
  /**
   * Observer Pattern
   */

  public interface Publisher { // Process JSON - existing logic
    void subscribe(Subscriber subscriber); // method to subscribe to updates
    void unsubscribe(Subscriber subscriber); // method to unsubscribe from updates
    void notifySubscribers(String data); // method to notify all subscribers
  }

  public interface Subscriber { // Interface for subscribers
    void update(String data); // method to receive updates
  }

  public class PublisherI implements Publisher {
    private final List<Subscriber> subscribers = new ArrayList<>();

    @Override
    public void subscribe(Subscriber subscriber) {
      subscribers.add(subscriber);
    }

    @Override
    public void unsubscribe(Subscriber subscriber) {
      subscribers.remove(subscriber);
    }

    @Override
    public void notifySubscribers(String data) {
      for (Subscriber subscriber : subscribers) {
        subscriber.update(data);
      }
    }
  }
}
