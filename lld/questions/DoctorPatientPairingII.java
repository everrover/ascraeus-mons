package lld.questions;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class DoctorPatientPairing {

  // -----------------------------------------------------------------------
  // Momento object for saving and viewing the state of the previous matches
  public class Originator {
    private Momento state;
    public Momento createMomento(Doctor doctor, Patient patient) {

    }
    // restore not needed here
  }

  public interface Momento {
    public void saveState(Publisher publisher);
//    public void restoreState(Publisher publisher);
  }

  public class DoctorPatientPairedState implements Momento {
    private Doctor doctor;
    private Patient patient;

    public DoctorPatientPairedState(Doctor doctor, Patient patient) {
      this.doctor = doctor;
      this.patient = patient;
    }

    @Override
    public void saveState(Publisher publisher) {
      // Logic to save the state of the pairing
      System.out.println("Saving state for Doctor: " + doctor.getName() + " and Patient: " + patient);
    }

//    @Override
//    public void restoreState(Publisher publisher) {
//      // Logic to restore the state of the pairing
//      System.out.println("Restoring state for Doctor: " + doctor.getName() + " and Patient: " + patient);
//    }
  }

  public interface History {
    public void addMomento(Momento momento);

    public int getSize();
    public Momento getLastMomento();
    public List<Momento> getAllMomento();
    public Momento getMomentoByIndex(int index);
  }

  public class HistoryCareTaker implements History {
    private List<Momento> momentos = new ArrayList<>();

    @Override
    public void addMomento(Momento momento) {
      momentos.add(momento);
    }

    @Override
    public int getSize() {
      return momentos.size();
    }

    @Override
    public Momento getLastMomento() {
      if (momentos.isEmpty()) return null;
      return momentos.get(momentos.size() - 1);
    }

    @Override
    public List<Momento> getAllMomento() {
      return new ArrayList<>(momentos);
    }

    @Override
    public Momento getMomentoByIndex(int index) {
      if (index < 0 || index >= momentos.size()) return null;
      return momentos.get(index);
    }
  }
  // ------------------------------------------------------------

  // ------------------------------------------------------------
  // Observer pattern :
  // Pub-sub for automated workflows via simple notification pushes
  public interface SubscriberI<T> {
    boolean notify(T message);
  }

  public interface PublisherI<T> {
    public void subscribe(SubscriberI subscriber);
    public void unsubscribe(SubscriberI subscriber);
    public void notifySubscribers(T message);

    public void doSomething(T message);
  }

  public static abstract class Publisher<T> implements PublisherI<T> {
    protected List<SubscriberI<T>> subscribers = new ArrayList<>();

    @Override
    public void subscribe(SubscriberI subscriber){
      if (!subscribers.contains(subscriber)) {
        subscribers.add(subscriber);
      }
    }
    @Override
    public void unsubscribe(SubscriberI subscriber){
      subscribers.remove(subscriber);
    }
    @Override
    public void notifySubscribers(T message){
      for (SubscriberI subscriber : subscribers) {
        subscriber.notify(message);
      }
    }
  }

  public static class DoctorAvailablePublisher extends Publisher<Doctor> {

    private DoctorAvailablePublisher() {
      // Constructor can be used to initialize any specific state if needed
    }

    private static volatile DoctorAvailablePublisher instance;

    public static DoctorAvailablePublisher getInstance() {
      DoctorAvailablePublisher localInstance = instance;
      if (localInstance != null) return localInstance;
      synchronized (DoctorAvailablePublisher.class) {
        if (instance == null) {
          instance = new DoctorAvailablePublisher();
        }
        return instance;
      }
    }

    @Override
    public void doSomething(Doctor message) {
      // Implementation for Doctor specific logic
      System.out.println("Doctor is shutting down.");
      notifySubscribers(message);
    }

    @Override
    public void notifySubscribers(Doctor message) {
      // Notify all subscribers about the Doctor
      for (SubscriberI subscriber: subscribers) {
        if(subscriber.notify(message)) return;
      }
    }
  }

  public static class DoctorPatientMatchingPublisher extends Publisher<DoctorPatientPairedState> {

    private DoctorPatientMatchingPublisher() {
      // Constructor can be used to initialize any specific state if needed
    }

    private static volatile DoctorPatientMatchingPublisher instance;

    public static DoctorPatientMatchingPublisher getInstance() {
      DoctorPatientMatchingPublisher localInstance = instance;
      if (localInstance != null) return localInstance;
      synchronized (DoctorPatientMatchingPublisher.class) {
        if (instance == null) {
          instance = new DoctorPatientMatchingPublisher();
        }
        return instance;
      }
    }
    @Override
    public void doSomething(DoctorPatientPairedState message) {
      // Implementation for doctor specific logic
      System.out.println("Doctor is shutting down.");
      notifySubscribers(message);
    }

    @Override
    public void notifySubscribers(DoctorPatientPairedState message) {
      // Notify all subscribers about the doctor shutdown
      for (SubscriberI subscriber: subscribers) {
        if(subscriber.notify(message)) return;
      }
    }
  }

  // -------------------------------------------------------
  public interface PollQueue<T> {
    public void add(T patient);
    public T getNext();
    public boolean isEmpty();
    public int size();
  }

  public class DoctorAvailableNotificationQueue implements PollQueue<Doctor> {
    private Queue<Doctor> queue = new LinkedList<>();

    @Override
    public synchronized void add(Doctor patient) {
      queue.add(patient);
    }

    @Override
    public synchronized Doctor getNext() {
      return queue.poll();
    }

    @Override
    public boolean isEmpty() {
      return queue.isEmpty();
    }

    @Override
    public int size() {
      return queue.size();
    }
  }

  public class PatientDoctorMatchQueue implements PollQueue<DoctorPatientPairedState> {
    private Queue<DoctorPatientPairedState> queue = new LinkedList<>();
    @Override
    public synchronized void add(DoctorPatientPairedState state) {
      queue.add(state);
    }

    @Override
    public synchronized DoctorPatientPairedState getNext() {
      return queue.poll();
    }

    @Override
    public boolean isEmpty() {
      return queue.isEmpty();
    }

    @Override
    public int size() {
      return queue.size();
    }

  }

  // -------------------------------------------------------
  // Classes for Doctor and Patient
  public class Patient{
    private String name;

    public Patient(String name) {
      this.name = name;
    }

    public String getName() {
      return name;
    }

    public void setName(String name) {
      this.name = name;
    }
  }

  public class Doctor implements SubscriberI<DoctorPatientPairedState> {
    private String name;
    private String specialization;

    volatile boolean isAvailable;
    volatile Patient patientInRoom;
    DoctorAvailablePublisher doctorPublisher;

    public Doctor(String name, String specialization) {
      this.name = name;
      this.specialization = specialization;
      this.isAvailable = true; // Doctor is available by default
      this.patientInRoom = null; // No patient in room by default
      this.doctorPublisher = DoctorAvailablePublisher.getInstance();
    }

    public String getName() {
      return name;
    }

    public String getSpecialization() {
      return specialization;
    }

    @Override
    public boolean notify(DoctorPatientPairedState message) {
      if (isAvailable) {
        patientInRoom = message.patient;
        isAvailable = false; // Doctor is now busy with a patient
        treatPatient();
      }
      return false;
    }

    private void treatPatient() {
      // Logic to treat the patient
      System.out.println("Doctor " + name + " is treating patient: " + patientInRoom.getName());
      // After treatment, doctor becomes available again
      isAvailable = true;
      patientInRoom = null; // No patient in room after treatment
      doctorPublisher.notifySubscribers(this); // Notify that the doctor is available again
    }
  }

  public class PatientNotificationSubscriber implements SubscriberI<Doctor> {

    private Queue<Patient> patients = new LinkedList<>();
    DoctorPatientMatchingPublisher patientPublisher = DoctorPatientMatchingPublisher.getInstance();

    public void addPatient(Patient patient) {
      patients.add(patient);
    }

    public synchronized Patient getNextPatient() {
      return patients.poll();
    }
    @Override
    public boolean notify(Doctor message) {
      // Logic to handle patient notification
      Patient patient = getNextPatient();
      patientPublisher.notifySubscribers(new DoctorPatientPairedState(message, patient));
      return true; // Return true if the notification was handled successfully
    }
  }
  // -------------------------------------------------------

  private List<Doctor> doctors = new ArrayList<>();
  private DoctorAvailablePublisher doctorPublisher = new DoctorAvailablePublisher();
  private DoctorPatientMatchingPublisher patientInRoomPublisher = new DoctorPatientMatchingPublisher();

  public static void main(String[] args) {
    DoctorPatientPairing dpp = new DoctorPatientPairing();
    // Scanner for inputs
    System.out.println("Welcome to the Doctor-Patient Pairing System!");
    while(true){
      System.out.println("Please enter a command: doctor-ip, patient-ip, patient-drop, doctor-drop, exit");
      String ip = "sth";
      switch (ip) {
        case "get-doctors": // get all doctors
          System.out.println("Fetching all doctors...");
          // Logic to fetch and display all doctors
          break;
        case "get-patients": // get all patients
          System.out.println("Fetching all patients...");
          // Logic to fetch and display all patients
          break;
        case "get-doctor": // get a specific doctor
          System.out.println("Fetching a specific doctor...");
          // Logic to fetch and display a specific doctor
          break;
        case "get-patient": // get a specific patient
          System.out.println("Fetching a specific patient...");
          // Logic to fetch and display a specific patient
          break;
        case "doctor-ip": // input a new doctor
          break;
        case "patient-ip": // input a new patient
          break;
        case "patient-drop": // drop a patient
          break;
        case "doctor-drop": // drop a doctor
          break;
        case "exit": // exit the program
          System.out.println("Exiting...");
          return;
      }
    }

  }

}
