import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

class Course {
    private String courseCode;
    private String title;
    private String description;
    private int capacity;
    private List<String> registeredStudents;

    public Course(String courseCode, String title, String description, int capacity) {
        this.courseCode = courseCode;
        this.title = title;
        this.description = description;
        this.capacity = capacity;
        this.registeredStudents = new ArrayList<>();
    }

    public String getCourseCode() {
        return courseCode;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public int getCapacity() {
        return capacity;
    }

    public int getAvailableSlots() {
        return capacity - registeredStudents.size();
    }

    public boolean registerStudent(String studentId) {
        if (getAvailableSlots() > 0 && !registeredStudents.contains(studentId)) {
            registeredStudents.add(studentId);
            return true;
        }
        return false;
    }

    public boolean dropStudent(String studentId) {
        return registeredStudents.remove(studentId);
    }

    @Override
    public String toString() {
        return "Course Code: " + courseCode + ", Title: " + title + ", Description: " + description +
               ", Capacity: " + capacity + ", Available Slots: " + getAvailableSlots();
    }
}

class Student {
    private String studentId;
    private String name;
    private List<String> registeredCourses;

    public Student(String studentId, String name) {
        this.studentId = studentId;
        this.name = name;
        this.registeredCourses = new ArrayList<>();
    }

    public String getStudentId() {
        return studentId;
    }

    public String getName() {
        return name;
    }

    public List<String> getRegisteredCourses() {
        return registeredCourses;
    }

    public boolean registerCourse(String courseCode) {
        if (!registeredCourses.contains(courseCode)) {
            registeredCourses.add(courseCode);
            return true;
        }
        return false;
    }

    public boolean dropCourse(String courseCode) {
        return registeredCourses.remove(courseCode);
    }
}

public class StudentCourseRegistrationSystem {
    private Map<String, Course> courses; // Store courses by course code
    private Map<String, Student> students; // Store students by student ID

    public StudentCourseRegistrationSystem() {
        courses = new HashMap<>();
        students = new HashMap<>();

        // Sample data
        courses.put("CS101", new Course("CS101", "Intro to Computer Science", "Learn the basics of computer science.", 30));
        courses.put("MATH101", new Course("MATH101", "Calculus I", "Introduction to calculus.", 25));

        students.put("S001", new Student("S001", "Alice"));
        students.put("S002", new Student("S002", "Bob"));
    }

    public void displayCourses() {
        System.out.println("\nAvailable Courses:");
        for (Course course : courses.values()) {
            System.out.println(course);
        }
    }

    public void registerStudentForCourse(String studentId, String courseCode) {
        Student student = students.get(studentId);
        Course course = courses.get(courseCode);

        if (student != null && course != null) {
            if (course.registerStudent(studentId) && student.registerCourse(courseCode)) {
                System.out.println(student.getName() + " successfully registered for " + course.getTitle());
            } else {
                System.out.println("Registration failed. Course may be full or already registered.");
            }
        } else {
            System.out.println("Invalid student ID or course code.");
        }
    }

    public void dropStudentFromCourse(String studentId, String courseCode) {
        Student student = students.get(studentId);
        Course course = courses.get(courseCode);

        if (student != null && course != null) {
            if (course.dropStudent(studentId) && student.dropCourse(courseCode)) {
                System.out.println(student.getName() + " successfully dropped from " + course.getTitle());
            } else {
                System.out.println("Drop failed. Student may not be registered for this course.");
            }
        } else {
            System.out.println("Invalid student ID or course code.");
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        StudentCourseRegistrationSystem system = new StudentCourseRegistrationSystem();

        while (true) {
            System.out.println("\n1. Display Courses");
            System.out.println("2. Register for a Course");
            System.out.println("3. Drop a Course");
            System.out.println("4. Exit");
            System.out.print("Choose an option: ");

            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    system.displayCourses();
                    break;
                case 2:
                    System.out.print("Enter Student ID: ");
                    String regStudentId = scanner.nextLine();
                    System.out.print("Enter Course Code: ");
                    String regCourseCode = scanner.nextLine();
                    system.registerStudentForCourse(regStudentId, regCourseCode);
                    break;
                case 3:
                    System.out.print("Enter Student ID: ");
                    String dropStudentId = scanner.nextLine();
                    System.out.print("Enter Course Code: ");
                    String dropCourseCode = scanner.nextLine();
                    system.dropStudentFromCourse(dropStudentId, dropCourseCode);
                    break;
                case 4:
                    System.out.println("Exiting...");
                    scanner.close();
                    return;
                default:
                    System.out.println("Invalid option. Please try again.");
            }
        }
    }
}
