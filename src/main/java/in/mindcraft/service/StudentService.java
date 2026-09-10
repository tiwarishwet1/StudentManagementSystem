package in.mindcraft.service;

import java.util.List;

import in.mindcraft.entity.Student;

public interface StudentService {

    // =========================================
    // CRUD OPERATIONS
    // =========================================

    Student createStudent(Student student);

    List<Student> createBulkStudents(
            List<Student> students
    );

    Student getStudentById(
            Integer studentId
    );

    List<Student> getAllStudents();

    Student updateStudent(
            Integer studentId,
            Student student
    );

    Student softDeleteStudent(
            Integer studentId
    );

    void deleteStudent(
            Integer studentId
    );


    // =========================================
    // ACTIVITY
    // =========================================

    List<Student> getStudentsByActivity(
            String status
    );


    // =========================================
    // DERIVED QUERIES
    // =========================================

    // a) Gender
    List<Student> getStudentsByGender(
            String gender
    );


    // b) Rank >=
    List<Student> getStudentsByRankGreaterThanEqual(
            String rank
    );


    // c) Rank <=
    List<Student> getStudentsByRankLessThanEqual(
            String rank
    );


    // d) Gender AND Rank >=
    List<Student> getStudentsByGenderAndRank(
            String gender,
            String rank
    );


    // e) Name starts with
    List<Student> getStudentsByNameStartingWith(
            String name
    );


    // f) Name contains
    List<Student> getStudentsByNameContaining(
            String name
    );


    // g) Email
    Student getStudentByEmail(
            String email
    );


    // h) Gender NULL
    List<Student> getStudentsWithNullGender();

    
 // =========================================
 // NATIVE QUERIES
 // =========================================

 // GET ACTIVE STUDENTS
 List<Student> getActiveStudentsNative();


 // UPDATE STUDENT GENDER
 void updateStudentGenderNative(
         Integer studentId,
         String gender
 );


 // HARD DELETE STUDENT
 void deleteStudentNative(
         Integer studentId
 );
 
 
 // Special

 
 Student testDuplicateDerivedQuery(
	        String studentName
	);

	Student testDuplicateNativeQuery(
	        String studentName
	);
}