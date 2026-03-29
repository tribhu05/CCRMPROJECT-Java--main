package edu.ccrm.io;

import edu.ccrm.config.AppConfig;
import edu.ccrm.domain.Course;
import edu.ccrm.domain.Semester;
import edu.ccrm.domain.Student;
import edu.ccrm.service.CourseService;
import edu.ccrm.service.StudentService;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.stream.Stream;

public class CSVImportExportService {

    private final Path dataDir = AppConfig.getInstance().getDataDir();

    public void importStudents(Path csv, StudentService studentService) throws IOException {
        try (Stream<String> lines = Files.lines(csv)) {
            lines.skip(1).map(line -> line.split(","))
                    .forEach(tokens -> {
                        // CSV: regNo,fullName,email
                        if (tokens.length >= 3) {
                            Student s = new Student(tokens[0].trim(), tokens[1].trim(), tokens[2].trim());
                            studentService.addStudent(s);
                        }
                    });
        }
    }

    public void importCourses(Path csv, CourseService courseService) throws IOException {
        try (Stream<String> lines = Files.lines(csv)) {
            lines.skip(1).map(line -> line.split(","))
                    .forEach(tokens -> {
                        // CSV: code,title,credits,instructor,semester,department
                        if (tokens.length >= 6) {
                            Course c = new Course.Builder(tokens[0].trim())
                                    .title(tokens[1].trim())
                                    .credits(Integer.parseInt(tokens[2].trim()))
                                    .instructor(tokens[3].trim())
                                    .semester(Semester.valueOf(tokens[4].trim().toUpperCase()))
                                    .department(tokens[5].trim())
                                    .build();
                            courseService.addCourse(c);
                        }
                    });
        }
    }

    public Path exportStudents(StudentService studentService, String fileName) throws IOException {
        Path out = dataDir.resolve(fileName);
        StringBuilder sb = new StringBuilder();
        sb.append("regNo,fullName,email\n");
        for (Student s : studentService.listStudents()) {
            sb.append(String.format("%s,%s,%s\n", s.getRegNo(), s.getFullName(), s.getEmail()));
        }
        Files.write(out, sb.toString().getBytes());
        return out;
    }

    public Path exportCourses(CourseService courseService, String fileName) throws IOException {
        Path out = dataDir.resolve(fileName);
        StringBuilder sb = new StringBuilder();
        sb.append("code,title,credits,instructor,semester,department\n");
        for (Course c : courseService.listCourses()) {
            sb.append(String.format("%s,%s,%d,%s,%s,%s\n",
                    c.getCode().getCode(), c.getTitle(), c.getCredits(),
                    c.getInstructorName(), c.getSemester(), c.getDepartment()));
        }
        Files.write(out, sb.toString().getBytes());
        return out;
    }
}