package in.mindcraft.service;

import java.util.List;

import org.springframework.stereotype.Service;

import in.mindcraft.entity.Student;
import in.mindcraft.exception.StudentNotFoundException;
import in.mindcraft.repository.StudentRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Service
public class StudentServiceImpl
        implements StudentService {
	private static final Logger logger =
            LoggerFactory.getLogger(
                    StudentServiceImpl.class
            );
	
    private final StudentRepository studentRepository;

    public StudentServiceImpl(
            StudentRepository studentRepository
    ) {
        this.studentRepository = studentRepository;
    }

    // =========================================
    // CREATE
    // =========================================

    @Override
    public Student createStudent(
            Student student
    ) {

        logger.info(
                "Creating student with email: {}",
                student.getStudentEmail()
        );

        Student savedStudent =
                studentRepository.save(student);

        logger.info(
                "Student created successfully with ID: {}",
                savedStudent.getStudentId()
        );

        return savedStudent;
    }

    // =========================================
    // BULK CREATE
    // =========================================

    @Override
    public List<Student> createBulkStudents(
            List<Student> students
    ) {

        return studentRepository.saveAll(students);

    }

    // =========================================
    // GET BY ID
    // =========================================

    @Override
    public Student getStudentById(
            Integer studentId
    ) {

        logger.info(
                "Fetching student with ID: {}",
                studentId
        );

        Student student = studentRepository
                .findStudentByStudentId(studentId)
                .orElseThrow(() -> {

                    logger.warn(
                            "Student not found with ID: {}",
                            studentId
                    );

                    return new StudentNotFoundException(
                            "Student not found with ID: "
                            + studentId
                    );
                });

        logger.info(
                "Student found with ID: {}",
                studentId
        );

        return student;
    }

    // =========================================
    // GET ALL
    // =========================================

    @Override
    public List<Student> getAllStudents() {

        return studentRepository.findAll();

    }

    // =========================================
    // UPDATE
    // =========================================

    @Override
    public Student updateStudent(
            Integer studentId,
            Student student
    ) {

        logger.info(
                "Updating student with ID: {}",
                studentId
        );

        Student existingStudent =
                studentRepository
                        .findStudentByStudentId(studentId)
                        .orElseThrow(() -> {

                            logger.warn(
                                    "Student not found for update. ID: {}",
                                    studentId
                            );

                            return new StudentNotFoundException(
                                    "Student not found with ID: "
                                    + studentId
                            );
                        });

        existingStudent.setStudentName(
                student.getStudentName()
        );

        existingStudent.setStudentRank(
                student.getStudentRank()
        );

        existingStudent.setStudentGender(
                student.getStudentGender()
        );

        existingStudent.setStudentEmail(
                student.getStudentEmail()
        );

        existingStudent.setActiveSw(
                student.getActiveSw()
        );

        Student updatedStudent =
                studentRepository.save(existingStudent);

        logger.info(
                "Student updated successfully. ID: {}",
                studentId
        );

        return updatedStudent;
    }

    // =========================================
    // GET BY ACTIVITY
    // =========================================

    @Override
    public List<Student> getStudentsByActivity(
            String status
    ) {

        return studentRepository
                .findStudentsByActiveSw(status);

    }

    // =========================================
    // SOFT DELETE
    // =========================================

    @Override
    public Student softDeleteStudent(
            Integer studentId
    ) {

        logger.info(
                "Soft deleting student with ID: {}",
                studentId
        );

        Student student =
                studentRepository
                        .findStudentByStudentId(studentId)
                        .orElseThrow(() -> {

                            logger.warn(
                                    "Student not found for soft delete. ID: {}",
                                    studentId
                            );

                            return new StudentNotFoundException(
                                    "Student not found with ID: "
                                    + studentId
                            );
                        });

        student.setActiveSw("N");

        Student updatedStudent =
                studentRepository.save(student);

        logger.info(
                "Student soft deleted successfully. ID: {}",
                studentId
        );

        return updatedStudent;
    }

    // =========================================
    // HARD DELETE
    // =========================================

    @Override
    public void deleteStudent(
            Integer studentId
    ) {

        logger.info(
                "Hard deleting student with ID: {}",
                studentId
        );

        Student student =
                studentRepository
                        .findStudentByStudentId(studentId)
                        .orElseThrow(() -> {

                            logger.warn(
                                    "Student not found for deletion. ID: {}",
                                    studentId
                            );

                            return new StudentNotFoundException(
                                    "Student not found with ID: "
                                    + studentId
                            );
                        });

        studentRepository.delete(student);

        logger.info(
                "Student hard deleted successfully. ID: {}",
                studentId
        );
    }
    // =========================================
    // DERIVED QUERY - GENDER
    // =========================================

    @Override
    public List<Student> getStudentsByGender(
            String gender
    ) {

        return studentRepository
                .findStudentsByStudentGender(gender);

    }

    // =========================================
    // DERIVED QUERY - RANK >=
    // =========================================

    @Override
    public List<Student>
    getStudentsByRankGreaterThanEqual(
            String rank
    ) {

        return studentRepository
                .findStudentsByStudentRankGreaterThanEqual(
                        rank
                );

    }

    // =========================================
    // DERIVED QUERY - RANK <=
    // =========================================

    @Override
    public List<Student>
    getStudentsByRankLessThanEqual(
            String rank
    ) {

        return studentRepository
                .findStudentsByStudentRankLessThanEqual(
                        rank
                );

    }

    // =========================================
    // DERIVED QUERY - GENDER AND RANK
    // =========================================

    @Override
    public List<Student>
    getStudentsByGenderAndRank(
            String gender,
            String rank
    ) {

        return studentRepository
                .findStudentsByStudentGenderAndStudentRankGreaterThanEqual(
                        gender,
                        rank
                );

    }

    // =========================================
    // DERIVED QUERY - NAME STARTS WITH
    // =========================================

    @Override
    public List<Student>
    getStudentsByNameStartingWith(
            String name
    ) {

        return studentRepository
                .findStudentsByStudentNameStartingWith(
                        name
                );

    }

    // =========================================
    // DERIVED QUERY - NAME CONTAINS
    // =========================================

    @Override
    public List<Student>
    getStudentsByNameContaining(
            String name
    ) {

        return studentRepository
                .findStudentsByStudentNameContaining(
                        name
                );

    }

    // =========================================
    // DERIVED QUERY - EMAIL
    // =========================================

    @Override
    public Student getStudentByEmail(
            String email
    ) {

        return studentRepository
                .findStudentByStudentEmail(email)
                .orElseThrow(
                        () -> new StudentNotFoundException(
                                "Student not found with email: "
                                + email
                        )
                );

    }

    // =========================================
    // DERIVED QUERY - GENDER NULL
    // =========================================

    @Override
    public List<Student>
    getStudentsWithNullGender() {

        return studentRepository
                .findStudentsByStudentGenderIsNull();

    }

    // =========================================
    // NATIVE QUERY - GET ACTIVE STUDENTS
    // =========================================

    @Override
    public List<Student> getActiveStudentsNative() {

        return studentRepository
                .getActiveStudentsNative();

    }

    // =========================================
    // NATIVE QUERY - UPDATE GENDER
    // =========================================

    @Override
    public void updateStudentGenderNative(
            Integer studentId,
            String gender
    ) {

        logger.info(
                "Native query: Updating gender for student ID: {}",
                studentId
        );

        int updatedRows =
                studentRepository
                        .updateStudentGenderNative(
                                studentId,
                                gender
                        );

        if (updatedRows == 0) {

            logger.warn(
                    "Native update failed. Student not found: {}",
                    studentId
            );

            throw new StudentNotFoundException(
                    "Student not found with ID: "
                    + studentId
            );
        }

        logger.info(
                "Native query: Gender updated successfully for ID: {}",
                studentId
        );
    }
    // =========================================
    // NATIVE QUERY - HARD DELETE
    // =========================================

    @Override
    public void deleteStudentNative(
            Integer studentId
    ) {

        int deletedRows =
                studentRepository
                .deleteStudentNative(studentId);

        if (deletedRows == 0) {

            throw new StudentNotFoundException(
                    "Student not found with ID: "
                    + studentId
            );

        }

    }

    // =========================================
    // DUPLICATE TEST - DERIVED QUERY
    // =========================================

    @Override
    public Student testDuplicateDerivedQuery(
            String studentName
    ) {

        return studentRepository
                .findStudentByStudentName(studentName);

    }

    // =========================================
    // DUPLICATE TEST - NATIVE QUERY
    // =========================================

    @Override
    public Student testDuplicateNativeQuery(
            String studentName
    ) {

        return studentRepository
                .findStudentByNameNative(studentName);

    }

}