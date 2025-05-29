package lld.questions;

public class Basic_StructuralPattern {
  /**
   * Adapter Pattern
   */

  public interface Client { // Process JSON - existing logic
    void request(String data); // method which processes JSON
  }

  private static class ExistingClient implements Client {
    @Override
    public void request(String data) { // existing method to process JSON
      System.out.println("Processing JSON request");
    }
  }

  private static class NewClient {
    public void specificRequest(String data) { // new method to process specific request
      System.out.println("Processing specific request");
    }
  }

  public static class Adapter extends NewClient implements Client {
    @Override
    public void request(String data) {
      String processedData = "Processed: " + data; // Adapt the data if needed
      specificRequest(processedData); // Adapting the new client's method to the existing client's interface
    }
  }
}
