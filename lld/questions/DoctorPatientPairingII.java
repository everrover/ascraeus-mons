package lld.questions;

import java.util.*;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;

public class DoctorPatientPairingII {
  /**
   * Patient ------> Doctor
   *
   * System ------[CRUD]-----> [Doctor] List - LinkedHashMap - O(1) - K: doctorId, V: Doctor
   *
   *        ------[CRUD]-----> [Patient] Queue FIFO - Poll - O(1)
   *                                                - Offer - O(1)
   *                                                - Seeks, Update, Delete - O(n)
   *                                     LinkedHashMap - All ops O(1)
   *
   * Match{
   *   doctor[Doctor]
   *   patient[Patient]
   * }
   *
   * 5 subscribers - `n` subscribers each for a given doctor
   * Atomic operations: CAS
   *
   * async process: treatAndRevert(doctor, patient, subscnt){
   *    // check if doctor is available
   *    if(!doctor.isAvailable && !doctor.naAfterTreatment) { return }
   *
   *    doctor.isAvailable = false;
   *    doctor.patientInRoom = patient;
   *    doctor.treatPatient();
   *    patient.patientBeingTreated = true; // patient is being treated
   *
   *    // history of doctor-patient pairing
   *    Match match = new Match(doctor, patient, ...);
   *    history.addMomento(match);
   *
   *    // if doctor can't be available after this
   *    if(!doctor.naAfterTreatment) {
   *        doctorPublisher.isAvailable = true;
   *    }
   *
   *    // make doctor and subscriber available again
   *    doctor.patientInRoom = null;
   *    atomicdecrement(subscnt);
   * }
   *
   *
   * final int maxsubs = 5;
   * atomicint subscnt = 0;
   * while(true) {
   *  sleep(1);
 *    if(patientQueue.isEmpty()) continue;
   *  while(!doctorQueue.isEmpty() && subscnt < maxsubs) {
   *    atomicincrement(subscnt);
   *    Doctor doctor = doctorQueue.poll();
   *    Patient patient = null;
   *    while(patientQueue.isEmpty()) {
   *      patient = patientQueue.poll();
   *      if(patient == null) {
   *
   *
   *    if(patient == null && patientQueue.isEmpty()) atomicdecrement(subscnt); break;
   *    treatAndRevert(doctor, patient, subscnt);
   *  }
 *  }
   *
   *
   * [Doctor] List - Set
   *
   * Queue - DoctorSubs - When a given doctor is available - Push based
   * |
   * [Doctor - doctorId]
   * |
   * v
   * Subscriber - for loop : For each doctor in the queue till `n` doctors are running
   *              - Check if patient Q is not empty
   *                - If T: sleep(5); continue;
   *              - Synchronously fetch a patient from Q - POLL
   *              - patientInRoom = patientId;
   *              - doctor.isAvailable = false;
   *              - doctor.treatPatient();
   *
   * [Patient] Queue FIFO
   *

   *
   * Doctor
   * - name, doctorId, specialization, isAvailable, patientInRoom=patientId, naAfterTreatment=false, ...
   * + Doctor(name, specialization) - CREATE
   *    {
   *      this.doctorId = UUID.randomUUID().toString(); // Unique ID for each doctor
   *      this.name = name;
   *      this.specialization = specialization;
   *      this.isAvailable = true; // Doctor is available by default
   *      this.patientInRoom = null; // No patient in room by default
   *      this.naAfterTreatment = false; // Doctor can be available after treatment
   *    }
   * + getDetails() - READ
   *   {
   *   return "Doctor Name: " + this.name + ", Specialization: " + this.specialization + ...
   *   }
   * + updateDetails(name, specialization, ...) - UPDATE
   * + findById(doctorId) - READ
   * + findDoctors(page=1, size=10) - READ - need to keep track of doctors using an ArrayList for paging
   * + delete(doctorId) - DELETE
   * {
   *   if (doctorId == null || doctorId.isEmpty()) return;
   *   // logic
   *   synchronized {
     *   doctor.naAfterTreatment = true; // Doctor cannot be available after treatment
     *   while(!doctor.isAvailable) sleep(1000); // Wait for doctor to finish treatment
     *   doctor.isAvailable = false; // Doctor is not available anymore
     *   // Remove doctor from the list
   *     doctorlinkedhashmap.remove(doctorId);
     *   doctors.removeIf(d -> d.getDoctorId().equals(doctorId));
   *   }
   * }
   * +
   * Patient
   * - name, patientId, patientBeingTreated=false, ...
   * - Same as above for doctor !
   * - Additionally need a queue for maintaining patients in FIFO order
   */

  // -----------------------------------------------------------------------

    private static class Match {

        private static Queue<Match> q = new LinkedList<>();
        public Doctor doctor;
        public Patient patient;
        public Date date;

        private Match(Doctor doctor, Patient patient) {
            this.doctor = doctor;
            this.patient = patient;
            this.date = new Date(); // Current date and time
        }

        @Override
        public String toString() {
            return "Match{" +
                    "doctor=" + doctor.getDoctorDetails() +
                    ", patient=" + patient.getPatientDetails() +
                    ", date=" + date +
                    '}';
        }

        public static void createMatchAndStore(Doctor doctor, Patient patient) {
            if (doctor == null || patient == null) {
                throw new IllegalArgumentException("Doctor and Patient cannot be null");
            }
            Match m = new Match(doctor, patient);
            q.offer(m); // Add to the queue
            return;
        }
    }

  private static class Patient {
    private static LinkedHashMap<String, Patient> patientLinkedHashMap = new LinkedHashMap<>();
    public static Queue<Patient> patientQueue = new LinkedList<>(); // FIFO queue for patients

    public String patientId;
    public String name;
    public boolean patientBeingTreated;
    public boolean patientDeleted;

    private Patient(String name) {
      this.patientId = java.util.UUID.randomUUID().toString(); // Unique ID for each patient
      this.name = name;
      this.patientBeingTreated = false; // Patient is not being treated by default
      this.patientDeleted = false; // Patient is not deleted by default
    }

    public static Patient createPatient(String name) {
      Patient patient = new Patient(name);
      synchronized (patientLinkedHashMap) {
        patientLinkedHashMap.put(patient.patientId, patient);
        patientQueue.offer(patient); // Add to the queue
      }
      return patient;
    }

    // getters and setters ...
    public String getPatientDetails() {
      return "Patient Name: " + this.name + ", Patient ID: " + this.patientId +
             ", Is Being Treated: " + this.patientBeingTreated +
              ", Is Deleted: " + this.patientDeleted;
    }

    public void updateDetails(String name) {
      synchronized (this) {
        this.name = name;
      }
    }

    public static Patient findById(String patientId) {
      if (patientId == null || patientId.isEmpty() || !patientLinkedHashMap.containsKey(patientId)) {
        return null; // Not found
      }
      return patientLinkedHashMap.get(patientId);
    }

    public static List<Patient> findPatients(int page, int size) {
      if (page < 1 || size < 1) return Collections.emptyList();
      int start = (page - 1) * size;
      int end = Math.min(start + size, patientLinkedHashMap.size());
      List<Patient> patients = new ArrayList<>(patientLinkedHashMap.values());
      if (start >= patients.size()) return Collections.emptyList(); // No patients in this page
      return patients.subList(start, end);
    }

    public static void delete(String patientId) {
      if (patientId == null || patientId.isEmpty() || !patientLinkedHashMap.containsKey(patientId)) return;
      Patient patient = patientLinkedHashMap.get(patientId);
      synchronized (patient) {
        if(patient.patientBeingTreated || patient.patientDeleted) {
          return; // Cannot delete if patient is being treated or already deleted
        }
        // Mark patient as deleted
        patient.patientDeleted = true; // Mark patient as deleted
        patient.patientBeingTreated = false; // Ensure patient is not being treated anymore
      }
    }

    public synchronized void deletePatientPermanently() {
      patientLinkedHashMap.remove(this.patientId); // Remove from the map
    }

    public static Patient getNextPatient() {
      synchronized (patientQueue) {
        if (patientQueue.isEmpty()) return null; // No patients in the queue
        while (patientQueue.peek() != null && patientQueue.peek().patientDeleted) continue; // No patient available or patient is deleted
        return patientQueue.poll(); // Get the next patient in FIFO order
      }
    }
  }
  private static class Doctor {

    private static LinkedHashMap<String, Doctor> doctorLinkedHashMap = new LinkedHashMap<>();

    public String doctorId;
    public String name;
    public String specialization;
    public boolean isAvailable;
    public String patientInRoom; // patientId
    public boolean naAfterTreatment; // Doctor cannot be available after treatment

    private Doctor(String name, String specialization) {
      this.doctorId = java.util.UUID.randomUUID().toString(); // Unique ID for each doctor
      this.name = name;
      this.specialization = specialization;
      this.isAvailable = true; // Doctor is available by default
      this.patientInRoom = null; // No patient in room by default
      this.naAfterTreatment = false; // Doctor can be available after treatment
    }

    public static Doctor createDoctor(String name, String specialization) {
      Doctor doctor = new Doctor(name, specialization);
      synchronized (doctorLinkedHashMap) {
        doctorLinkedHashMap.put(doctor.doctorId, doctor);
      }
      return doctor;
    }

    // getters and setters ...
    public String getDoctorDetails() {
      return "Doctor Name: " + this.name + ", Specialization: " + this.specialization +
             ", Doctor ID: " + this.doctorId + ", Is Available: " + this.isAvailable +
             ", Patient in Room: " + this.patientInRoom + ", NA After Treatment: " + this.naAfterTreatment;
    }

    public void updateDetails(String name, String specialization) {
      synchronized (this) {
        this.name = name;
        this.specialization = specialization;
      }
    }

    public static Doctor findById(String doctorId) {
      if (doctorId == null || doctorId.isEmpty() || !doctorLinkedHashMap.containsKey(doctorId)) {
        return null; // Not found
      }
      return doctorLinkedHashMap.get(doctorId);
    }

    public static List<Doctor> findDoctors(int page, int size) {
      if (page < 1 || size < 1) return Collections.emptyList();
      int start = (page - 1) * size;
      int end = Math.min(start + size, doctorLinkedHashMap.size());
      List<Doctor> doctors = new ArrayList<>(doctorLinkedHashMap.values());
      if (start >= doctors.size()) return Collections.emptyList(); // No doctors in this page
      return doctors.subList(start, end);
    }

    public static void delete(String doctorId) {
      if (doctorId == null || doctorId.isEmpty() || !doctorLinkedHashMap.containsKey(doctorId)) return;
      Doctor doctor = doctorLinkedHashMap.get(doctorId);
      synchronized (doctor) {
        doctor.naAfterTreatment = true; // Doctor cannot be available after treatment
        // Can cause deadlocks
//        while (!doctor.isAvailable) {
//          try {
//            Thread.sleep(1000); // Wait for doctor to finish treatment
//          } catch (InterruptedException e) {
//            Thread.currentThread().interrupt(); // Restore interrupted status
//          }
//        }
//        doctor.isAvailable = false; // Doctor is not available anymore
//        doctorLinkedHashMap.remove(doctorId);
      }
    }

    public synchronized void treatPatient() {
      if (isAvailable && patientInRoom != null) {
        Patient patient = Patient.findById(patientInRoom);
        if (patient == null || patient.patientDeleted) {
          System.out.println("Patient is deleted.");
          this.patientInRoom = null; // No patient in room
          Observer.notifyDoctorAvailable(this); // Notify observers that doctor is available
        }else {
          // Simulate treating patient
          System.out.println("Treating patient: " + (patientInRoom != null ? patientInRoom : "No patient in room"));
          try {
            Thread.sleep(2000); // Simulate time taken to treat patient
          } catch (InterruptedException e) {
            Thread.currentThread().interrupt(); // Restore interrupted status
          }
          patient.patientDeleted = true; // Mark patient as deleted after treatment
          Match.createMatchAndStore(this, patient); // Create match and store it
          this.patientInRoom = null; // Clear patient in room after treatment
          this.isAvailable = naAfterTreatment; // Set availability based on naAfterTreatment
        }
        Observer.notifyDoctorAvailable(this); // Notify observers that doctor is available
      }else{
        System.out.println("Doctor is not available or no patient in room.");
        this.patientInRoom = Patient.findById(patientInRoom).getPatientDetails(); // Get patient details
      }
    }
  }

  private static void sleep(long millis) {
      try {
          Thread.sleep(millis);
      } catch (InterruptedException e) {
          Thread.currentThread().interrupt(); // Restore interrupted status
      } catch (Exception e) {
          System.out.println("An error occurred while sleeping: " + e.getMessage());
      }
  }

  private static class Observer {

      private static AtomicInteger subscnt = new AtomicInteger(0); // Atomic counter for subscribers
      private static Queue<Doctor> availableDoctors = new LinkedList<>();
      public static void notifyDoctorAvailable(Doctor doctor) {
          if (doctor != null && doctor.isAvailable) {
              availableDoctors.offer(doctor);
              System.out.println("Doctor is now available: " + doctor.getDoctorDetails());
          } else {
              System.out.println("Doctor is not available or null.");
          }
      }

      public static void runner() {
        Executor pool = Executors.newFixedThreadPool(5); // Thread pool with 5 threads
        while (true) {
            if (subscnt.get() >= 5) { // If max subscribers reached, wait
                sleep(5000); // Sleep for a while before checking again
                continue; // Skip to next iteration
            }
            if (!availableDoctors.isEmpty()) {
              synchronized (availableDoctors) {
                subscnt.incrementAndGet(); // Increment subscriber count

                Doctor doctor = availableDoctors.peek();
                if (doctor != null) System.out.println("Processing available doctor: " + doctor.getDoctorDetails());
                Patient patient = Patient.getNextPatient(); // Get next patient from the queue
                if (patient != null) {
                  CompletableFuture<Void> cf = CompletableFuture.runAsync(() -> {
                    synchronized (patient) {
                      doctor.patientInRoom = patient.patientId; // Set patient in room
                      doctor.treatPatient(); // Treat the patient
                    }
                    Observer.subscnt.decrementAndGet(); // Decrement subscriber count after treatment
                  }, pool);
                } else {
                    System.out.println("No patients available for treatment.");
                }
              }
            }
            sleep(5000); // Sleep for a while before checking again
        }
      }
      // init runner
      public static void init() {
          Thread observerThread = new Thread(Observer::runner);
          observerThread.setDaemon(true); // Set as daemon thread
          observerThread.start(); // Start the observer thread
      }
  }

  // -------------------------------------------------------

  private static void createDoctor() {
    Scanner sc = new Scanner(System.in);
    System.out.print("Enter Doctor Name: ");
    String name = sc.nextLine();
    System.out.print("Enter Specialization: ");
    String specialization = sc.nextLine();
    Doctor doctor = Doctor.createDoctor(name, specialization);
    System.out.println("Doctor created: " + doctor.getDoctorDetails());
  }

  private static void getDoctor() {
    System.out.print("Enter Doctor ID: ");
    String doctorId = new Scanner(System.in).nextLine();
    Doctor foundDoctor = Doctor.findById(doctorId);
    if (foundDoctor != null) {
      System.out.println("Doctor found: " + foundDoctor.getDoctorDetails());
    } else {
      System.out.println("Doctor not found with ID: " + doctorId);
    }
  }

  private static void updateDoctor() {
    System.out.print("Enter Doctor ID to update: ");
    String updateDoctorId = new Scanner(System.in).nextLine();
    Doctor updateDoctor = Doctor.findById(updateDoctorId);
    if (updateDoctor != null) {
      System.out.print("Enter new Name: ");
      String newName = new Scanner(System.in).nextLine();
      System.out.print("Enter new Specialization: ");
      String newSpecialization = new Scanner(System.in).nextLine();
      updateDoctor.updateDetails(newName, newSpecialization);
      System.out.println("Doctor updated: " + updateDoctor.getDoctorDetails());
    } else {
      System.out.println("Doctor not found with ID: " + updateDoctorId);
    }
  }

  private static void deleteDoctor() {
    System.out.print("Enter Doctor ID to delete: ");
    String deleteDoctorId = new Scanner(System.in).nextLine();
    Doctor.delete(deleteDoctorId);
    System.out.println("Doctor deleted with ID: " + deleteDoctorId);
  }

  private static void getDoctorPage(){
    System.out.print("Enter page number: ");
    int page = new Scanner(System.in).nextInt();
    System.out.print("Enter page size: ");
    int size = new Scanner(System.in).nextInt();
    List<Doctor> doctors = Doctor.findDoctors(page, size);
    if (doctors.isEmpty()) {
      System.out.println("No doctors found for the given page and size.");
    } else {
      System.out.println("Doctors on page " + page + ":");
      for (Doctor doc : doctors) {
        System.out.println(doc.getDoctorDetails());
      }
    }
  }

  private static void createPatient() {
    Scanner sc = new Scanner(System.in);
    System.out.print("Enter Patient Name: ");
    String name = sc.nextLine();
    Patient patient = Patient.createPatient(name);
    System.out.println("Patient created: " + patient.getPatientDetails());
  }

  private static void getPatient() {
    System.out.print("Enter Patient ID: ");
    String patientId = new Scanner(System.in).nextLine();
    Patient foundPatient = Patient.findById(patientId);
    if (foundPatient != null) {
      System.out.println("Patient found: " + foundPatient.getPatientDetails());
    } else {
      System.out.println("Patient not found with ID: " + patientId);
    }
  }

  private static void updatePatient() {
    System.out.print("Enter Patient ID to update: ");
    String updatePatientId = new Scanner(System.in).nextLine();
    Patient updatePatient = Patient.findById(updatePatientId);
    if (updatePatient != null) {
      System.out.print("Enter new Name: ");
      String newName = new Scanner(System.in).nextLine();
      updatePatient.updateDetails(newName);
      System.out.println("Patient updated: " + updatePatient.getPatientDetails());
    } else {
      System.out.println("Patient not found with ID: " + updatePatientId);
    }
  }

  private static void deletePatient() {
    System.out.print("Enter Patient ID to delete: ");
    String deletePatientId = new Scanner(System.in).nextLine();
    Patient.delete(deletePatientId);
    System.out.println("Patient deleted with ID: " + deletePatientId);
  }

  private static void getPatientPage() {
    System.out.print("Enter page number: ");
    int page = new Scanner(System.in).nextInt();
    System.out.print("Enter page size: ");
    int size = new Scanner(System.in).nextInt();
    List<Patient> patients = Patient.findPatients(page, size);
    if (patients.isEmpty()) {
      System.out.println("No patients found for the given page and size.");
    } else {
      System.out.println("Patients on page " + page + ":");
      for (Patient pat : patients) {
        System.out.println(pat.getPatientDetails());
      }
    }
  }


  public static void main(String[] args) {
      Observer.init();
    DoctorPatientPairingII dpp = new DoctorPatientPairingII();
    // Scanner for inputs
    System.out.println("Welcome to the Doctor-Patient Pairing System!");
    while(true){
      System.out.println("Please enter a command: doctor-ip, doctor-get, doctor-update, doctor-delete, doctor-page, patient-ip, patient-get, patient-update, patient-delete, patient-page, exit");
      String ip = "sth";
      switch (ip) {
        case "doctor-ip": // create a new doctor
          createDoctor();
          break;
        case "doctor-get": // get doctor details by ID
          getDoctor();
          break;
        case "doctor-update": // update doctor details
          updateDoctor();
          break;
        case "doctor-delete": // delete a doctor
          deleteDoctor();
          break;
        case "doctor-page": // get paginated list of doctors
          getDoctorPage();
          break;
        case "patient-ip": // create a new patient
          createPatient();
          break;
        case "patient-get": // get patient details by ID
          getPatient();
          break;
        case "patient-update": // update patient details
          updatePatient();
          break;
        case "patient-delete": // delete a patient
          deletePatient();
          break;
        case "patient-page": // get paginated list of patients
          getPatientPage();
          break;
        case "exit": // exit the program
          System.out.println("Exiting...");
          return;
      }
    }

  }

}
