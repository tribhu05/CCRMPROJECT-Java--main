package edu.ccrm.cli;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Scanner;

import edu.ccrm.config.AppConfig;
import edu.ccrm.domain.Course;
import edu.ccrm.domain.Semester;
import edu.ccrm.domain.Student;
import edu.ccrm.exceptions.DuplicateEnrollmentException;
import edu.ccrm.exceptions.MaxCreditLimitExceededException;
import edu.ccrm.io.BackupService;
import edu.ccrm.io.CSVImportExportService;
import edu.ccrm.service.CourseServiceImpl;
import edu.ccrm.service.EnrollmentServiceImpl;
import edu.ccrm.service.StudentServiceImpl;

public class Main {
    public static void main(String[] args) throws Exception {
        var studentService = new StudentServiceImpl();
        var courseService = new CourseServiceImpl();
        var enrollmentService = new EnrollmentServiceImpl(studentService, courseService);
        var io = new CSVImportExportService();
        var backup = new BackupService();
        var dataDir = AppConfig.getInstance().getDataDir();
        
        // Auto Import CSV data if files exist .....
     
        Path studentsCsv = dataDir.resolve("students.csv");
        Path coursesCsv  = dataDir.resolve("courses.csv");

        try {
            if (Files.exists(studentsCsv)) {
                io.importStudents(studentsCsv, studentService);
                System.out.println("Imported students.csv");
            }
            if (Files.exists(coursesCsv)) {
                io.importCourses(coursesCsv, courseService);
                System.out.println("Imported courses.csv");
            }
        } catch (IOException ex) {
            System.err.println("CSV import failed: " + ex.getMessage());
        }
        // ==========================================

        Scanner sc = new Scanner(System.in);
        boolean running = true;
        while (running) {
            System.out.println("\nCCRM Menu:");
            System.out.println("1) Add student  2) Add course  3) Enroll student");
            System.out.println("4) Record marks 5) List students 6) List courses");
            System.out.println("7) Export data  8) Backup exports 9) Exit");
            System.out.print("Choice> ");
            String choice = sc.nextLine().trim();
            switch (choice) {
                case "1": {
                    System.out.print("regNo: "); String reg = sc.nextLine().trim();
                    System.out.print("fullName: "); String name = sc.nextLine().trim();
                    System.out.print("email: "); String email = sc.nextLine().trim();
                    studentService.addStudent(new Student(reg, name, email));
                    System.out.println("Added.");
                    break;
                }
                case "2": {
                    System.out.print("code: "); String code = sc.nextLine().trim();
                    System.out.print("title: "); String title = sc.nextLine().trim();
                    System.out.print("credits: "); int cr = Integer.parseInt(sc.nextLine().trim());
                    System.out.print("instructor: "); String ins = sc.nextLine().trim();
                    System.out.print("semester (SPRING/SUMMER/FALL): "); Semester sem = Semester.valueOf(sc.nextLine().trim().toUpperCase());
                    System.out.print("dept: "); String dept = sc.nextLine().trim();
                    Course c = new Course.Builder(code).title(title).credits(cr).instructor(ins).semester(sem).department(dept).build();
                    courseService.addCourse(c);
                    System.out.println("Course added.");
                    break;
                }
                case "3": {
                    System.out.print("student regNo: "); String regNo = sc.nextLine().trim();
                    System.out.print("course code: "); String courseCode = sc.nextLine().trim();
                    try {
                        enrollmentService.enroll(regNo, courseCode);
                        System.out.println("Enrolled.");
                    } catch (DuplicateEnrollmentException | MaxCreditLimitExceededException ex) {
                        System.err.println("Cannot enroll: " + ex.getMessage());
                    }
                    break;
                }
                case "4": {
                    System.out.print("student regNo: "); String regNo = sc.nextLine().trim();
                    System.out.print("course code: "); String courseCode = sc.nextLine().trim();
                    System.out.print("marks (0-100): "); int marks = Integer.parseInt(sc.nextLine().trim());
                    try { enrollmentService.recordMarks(regNo, courseCode, marks); System.out.println("Recorded."); }
                    catch (Exception ex) { System.err.println("Error: " + ex.getMessage()); }
                    break;
                }
                case "5":
                    studentService.listStudents().forEach(System.out::println);
                    break;
                case "6":
                    courseService.listCourses().forEach(System.out::println);
                    break;
                case "7": {
                    Path s = io.exportStudents(studentService, "students_export.csv");
                    Path c = io.exportCourses(courseService, "courses_export.csv");
                    System.out.println("Exports written to: " + s + ", " + c);
                    break;
                }
                case "8": {
                    Path exports = dataDir;
                    Path backupsBase = dataDir.resolve("backups");
                    Path snapshot = backup.backupDir(exports, backupsBase);
                    long size = edu.ccrm.util.RecursiveUtil.totalSize(snapshot);
                    System.out.println("Backup created at: " + snapshot + " (size bytes=" + size + ")");
                    break;
                }
                case "9":
                    running = false; break;
                default:
                    System.out.println("Unknown choice.");
            }
        }
        sc.close();
        System.out.println("Goodbye!");
        
    }
}