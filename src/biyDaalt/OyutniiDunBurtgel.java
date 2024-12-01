package biyDaalt;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

// Subject class - Stores course information
class Subject {
    private String subjectCode;
    private String subjectName;
    private float credit;

    public Subject(String subjectCode, String subjectName, float credit) {
        this.subjectCode = subjectCode;
        this.subjectName = subjectName;
        this.credit = credit;
    }

    @Override
    public String toString() {
        return subjectCode + " - " + subjectName + " (" + credit + " credits)";
    }

    public String getSubjectCode() {
        return subjectCode;
    }
}

// Major class - Stores major information
class Major {
    private String majorCode;
    private String majorName;

    public Major(String majorCode, String majorName) {
        this.majorCode = majorCode;
        this.majorName = majorName;
    }

    @Override
    public String toString() {
        return majorCode + " - " + majorName;
    }
}

// Lessons class - Stores information about the lessons each student took
class Lessons {
    private Subject learned;
    private int score;

    public Lessons(Subject learned, int score) {
        this.learned = learned;
        this.score = score;
    }

    public String getGrade() {
        if (score >= 90) return "A";
        else if (score >= 80) return "B";
        else if (score >= 70) return "C";
        else if (score >= 60) return "D";
        else return "F";
    }

    public int getScore() {
        return score;
    }

    @Override
    public String toString() {
        return learned + " - Score: " + score + " (" + getGrade() + ")";
    }
}

// Student class - Stores student information and lessons taken
class Student {
    private String studentCode;
    private ArrayList<Lessons> lessons = new ArrayList<>();

    public Student(String studentCode) {
        this.studentCode = studentCode;
    }

    public void addLesson(Lessons lesson) {
        lessons.add(lesson);
    }

    private float convertScoreToGPA(int score) {
        if (score >= 90) return 4.0f;
        else if (score >= 80) return 3.0f;
        else if (score >= 70) return 2.0f;
        else if (score >= 60) return 1.0f;
        else return 0.0f; // Scores below 60 are failing
    }

    public float calculateGPA() {
        if (lessons.size() == 0) return 0.0f;
        float totalPoints = 0;
        for (Lessons lesson : lessons) {
            float gpaPoints = convertScoreToGPA(lesson.getScore());
            System.out.println("Exam: " + lesson + ", GPA: " + gpaPoints);
            totalPoints += gpaPoints;
        }
        float gpa = totalPoints / lessons.size();
        System.out.println("Student Code: " + studentCode + ", GPA: " + gpa);
        return gpa;
    }


    public boolean hasMoreThanThreeFails() {
        int failCount = 0;
        for (Lessons lesson : lessons) {
            if (lesson.getGrade().equals("F")) {
                failCount++;
            }
        }
        return failCount >= 3;
    }

    public String getStudentCode() {
        return studentCode;
    }

    @Override
    public String toString() {
        return "Student Code: " + studentCode + ", GPA: " + calculateGPA();
    }
}

// Registration class - Handles loading and displaying data
class Registration {
    private ArrayList<Student> studentList = new ArrayList<>();
    private ArrayList<Subject> subjectList = new ArrayList<>();
    private ArrayList<Major> majorList = new ArrayList<>();

    public void loadSubjects(String fileName) throws IOException {
        try (BufferedReader input = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = input.readLine()) != null) {
                String[] values = line.split("/");
                Subject subject = new Subject(values[0], values[1], Float.parseFloat(values[2]));
                subjectList.add(subject);
            }
        }
    }

    public void loadMajors(String fileName) throws IOException {
        try (BufferedReader input = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = input.readLine()) != null) {
                String[] values = line.split("/");
                Major major = new Major(values[0], values[1]);
                majorList.add(major);
            }
        }
    }

    public void loadExams(String fileName) throws IOException {
        try (BufferedReader input = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = input.readLine()) != null) {
                System.out.println("Reading line: " + line);
                String[] values = line.split("/");
                String studentCode = values[0];
                String subjectCode = values[1];
                int score = Integer.parseInt(values[2]);

                Student student = findOrCreateStudent(studentCode);
                Subject subject = findSubjectByCode(subjectCode);
                if (subject != null) {
                    Lessons lesson = new Lessons(subject, score);
                    student.addLesson(lesson);
                } else {
                    System.out.println("Subject not found for code: " + subjectCode);
                }
            }
        }
    }

    private Student findOrCreateStudent(String studentCode) {
        for (Student student : studentList) {
            if (student.getStudentCode().equals(studentCode)) {
                return student;
            }
        }
        Student newStudent = new Student(studentCode);
        studentList.add(newStudent);
        return newStudent;
    }

    private Subject findSubjectByCode(String subjectCode) {
        for (Subject subject : subjectList) {
            if (subject.getSubjectCode().equals(subjectCode)) {
                return subject;
            }
        }
        return null;
    }

    public void displaySubjects() {
        for (Subject subject : subjectList) {
            System.out.println(subject);
        }
    }

    public void displayMajors() {
        for (Major major : majorList) {
            System.out.println(major);
        }
    }

    public void displayAverageGPA() {
        if (studentList.size() == 0) {
            System.out.println("Average GPA: 0.0");
            return;
        }
        float totalGPA = 0;
        for (Student student : studentList) {
            totalGPA += student.calculateGPA();
        }
        System.out.println("Average GPA: " + (totalGPA / studentList.size()));
    }

    public void displayFailingStudents() {
        for (Student student : studentList) {
            if (student.hasMoreThanThreeFails()) {
                System.out.println(student);
            }
        }
    }
}

// Main class to run the program
public class OyutniiDunBurtgel {
    public static void main(String[] args) {
        Registration reg = new Registration();
        try {
            reg.loadSubjects("C:\\Users\\Dell\\IdeaProjects\\untitled\\src\\biyDaalt\\Subjects.txt");
            reg.loadMajors("C:\\Users\\Dell\\IdeaProjects\\untitled\\src\\biyDaalt\\Professions.txt");
            reg.loadExams("C:\\Users\\Dell\\IdeaProjects\\untitled\\src\\biyDaalt\\Exams.txt");

            System.out.println("Нийт хичээлүүд:");
            reg.displaySubjects();

            System.out.println("Нийт мэргэжлүүд:");
            reg.displayMajors();

            System.out.println("Нийт оюутнуудын дундаж голч дүн:");
            reg.displayAverageGPA();

            System.out.println("Хасагдах оюутнууд:");
            reg.displayFailingStudents();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
