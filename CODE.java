import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

// Patient Class
class Patient {
    private String name;
    private int age;
    private String ailment;

    public Patient(String name, int age, String ailment) {
        this.name = name;
        this.age = age;
        this.ailment = ailment;
    }

    @Override
    public String toString() {
        return "Patient Name: " + name + ", Age: " + age + ", Ailment: " + ailment;
    }
}

// Doctor Class
class Doctor {
    private String name;
    private String specialty;

    public Doctor(String name, String specialty) {
        this.name = name;
        this.specialty = specialty;
    }

    @Override
    public String toString() {
        return "Doctor Name: " + name + ", Specialty: " + specialty;
    }
}

// Appointment Class
class Appointment {
    private Patient patient;
    private Doctor doctor;
    private LocalDateTime appointmentTime;

    public Appointment(Patient patient, Doctor doctor, LocalDateTime appointmentTime) {
        this.patient = patient;
        this.doctor = doctor;
        this.appointmentTime = appointmentTime;
    }

    @Override
    public String toString() {
        return "Appointment: " + appointmentTime +
                "\n" + patient.toString() +
                "\n" + doctor.toString();
    }
}

// Hospital Management System Class
public class HospitalManagementSystem {
    private List<Patient> patients = new ArrayList<>();
    private List<Doctor> doctors = new ArrayList<>();
    private List<Appointment> appointments = new ArrayList<>();

    public void addPatient(Patient patient) {
        patients.add(patient);
        System.out.println("Patient added successfully.");
    }

    public void addDoctor(Doctor doctor) {
        doctors.add(doctor);
        System.out.println("Doctor added successfully.");
    }

    public void scheduleAppointment(Patient patient, Doctor doctor, LocalDateTime appointmentTime) {
        Appointment appointment = new Appointment(patient, doctor, appointmentTime);
        appointments.add(appointment);
        System.out.println("Appointment scheduled successfully.");
    }

    public void viewAppointments() {
        if (appointments.isEmpty()) {
            System.out.println("No appointments scheduled.");
            return;
        }
        for (Appointment appointment : appointments) {
            System.out.println(appointment);
            System.out.println("------------------------------");
        }
    }

    public static void main(String[] args) {
        HospitalManagementSystem hms = new HospitalManagementSystem();
        Scanner scanner = new Scanner(System.in);
        
        // Input number of doctors
        System.out.print("Enter number of doctors: ");
        int numDoctors = scanner.nextInt();
        scanner.nextLine(); // Consume newline

        // Adding doctors
        for (int i = 0; i < numDoctors; i++) {
            System.out.print("Enter doctor's name: ");
            String doctorName = scanner.nextLine();
            System.out.print("Enter doctor's specialty: ");
            String doctorSpecialty = scanner.nextLine();
            hms.addDoctor(new Doctor(doctorName, doctorSpecialty));
        }

        // Input number of patients
        System.out.print("Enter number of patients: ");
        int numPatients = scanner.nextInt();
        scanner.nextLine(); // Consume newline

        // Adding patients
        for (int i = 0; i < numPatients; i++) {
            System.out.print("Enter patient's name: ");
            String patientName = scanner.nextLine();
            System.out.print("Enter patient's age: ");
            int patientAge = scanner.nextInt();
            scanner.nextLine(); // Consume newline
            System.out.print("Enter patient's ailment: ");
            String patientAilment = scanner.nextLine();
            hms.addPatient(new Patient(patientName, patientAge, patientAilment));
        }

        // Scheduling appointments
        for (int i = 0; i < numPatients; i++) {
            System.out.println("Scheduling appointment for Patient " + (i + 1) + ":");
            System.out.print("Enter appointment date and time (YYYY-MM-DDTHH:MM): ");
            String appointmentInput = scanner.nextLine();
            LocalDateTime appointmentTime = LocalDateTime.parse(appointmentInput);

            System.out.print("Select doctor (1-" + numDoctors + "): ");
            int doctorIndex = scanner.nextInt() - 1;
            scanner.nextLine(); // Consume newline

            hms.scheduleAppointment(hms.patients.get(i), hms.doctors.get(doctorIndex), appointmentTime);
        }

        // Viewing appointments
        System.out.println("\nViewing Appointments:");
        hms.viewAppointments();

        scanner.close();
    }
}
