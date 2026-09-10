package in.mindcraft.scheduler;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import in.mindcraft.entity.Student;
import in.mindcraft.service.StudentService;

@Component
public class StudentScheduler {

    private final StudentService studentService;

    public StudentScheduler(
            StudentService studentService
    ) {
        this.studentService = studentService;
    }


    @Scheduled(fixedRate = 30000)
    public void generateStudentSummary() {

        List<Student> students =
                studentService.getAllStudents();

        List<Student> activeStudents =
                studentService.getStudentsByActivity("Y");

        int totalStudents =
                students.size();

        int activeStudentCount =
                activeStudents.size();

        int inactiveStudentCount =
                totalStudents - activeStudentCount;


        System.out.println(
                "\n=============================="
        );

        System.out.println(
                "STUDENT SUMMARY"
        );

        System.out.println(
                "Time: " + LocalDateTime.now()
        );

        System.out.println(
                "Total Students: "
                + totalStudents
        );

        System.out.println(
                "Active Students: "
                + activeStudentCount
        );

        System.out.println(
                "Inactive Students: "
                + inactiveStudentCount
        );

        System.out.println(
                "==============================\n"
        );

    }

}