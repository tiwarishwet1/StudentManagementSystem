package in.mindcraft.service;

import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;
import org.springframework.stereotype.Service;

import in.mindcraft.dto.StudentProcedureResponse;

@Service
public class StudentProcedureService {

    private static final Logger logger =
            LoggerFactory.getLogger(
                    StudentProcedureService.class
            );

    private final JdbcTemplate jdbcTemplate;

    // Constructor injection
    public StudentProcedureService(
            JdbcTemplate jdbcTemplate
    ) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public StudentProcedureResponse getStudentDetails(
            Integer studentId
    ) {

        logger.info(
                "Calling stored procedure for student ID: {}",
                studentId
        );

        SimpleJdbcCall jdbcCall =
                new SimpleJdbcCall(jdbcTemplate)
                        .withProcedureName(
                                "get_student_details"
                        );

        Map<String, Object> result =
                jdbcCall.execute(
                        Map.of(
                                "p_student_id",
                                studentId
                        )
                );

        logger.info(
                "Stored procedure executed successfully for ID: {}",
                studentId
        );

        logger.info(
                "Procedure result: {}",
                result
        );

        String studentName =
                (String) result.get(
                        "p_student_name"
                );

        String studentGender =
                (String) result.get(
                        "p_student_gender"
                );

        if (studentName == null) {

            logger.warn(
                    "No student found from procedure for ID: {}",
                    studentId
            );

            return null;
        }

        logger.info(
                "Student found from procedure - Name: {}, Gender: {}",
                studentName,
                studentGender
        );

        return new StudentProcedureResponse(
                studentName,
                studentGender
        );
    }
}