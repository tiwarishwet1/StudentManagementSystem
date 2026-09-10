package in.mindcraft.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import in.mindcraft.entity.Student;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

public interface StudentRepository
        extends JpaRepository<Student, Integer> {

    // =========================================
    // NORMAL CUSTOM METHODS
    // =========================================

    // FIND BY ID
    Optional<Student> findStudentByStudentId(
            Integer studentId
    );

    // FIND BY ACTIVITY
    List<Student> findStudentsByActiveSw(
            String activeSw
    );


    // =========================================
    // DERIVED QUERIES
    // =========================================


    // a) FIND BY GENDER
    List<Student> findStudentsByStudentGender(
            String studentGender
    );


    // b) RANK GREATER THAN OR EQUAL
    List<Student> findStudentsByStudentRankGreaterThanEqual(
            String studentRank
    );


    // c) RANK LESS THAN OR EQUAL
    List<Student> findStudentsByStudentRankLessThanEqual(
            String studentRank
    );


    // d) GENDER AND RANK GREATER THAN OR EQUAL
    List<Student>
    findStudentsByStudentGenderAndStudentRankGreaterThanEqual(
            String studentGender,
            String studentRank
    );


    // e) NAME STARTS WITH
    List<Student> findStudentsByStudentNameStartingWith(
            String studentName
    );


    // f) NAME CONTAINS
    List<Student> findStudentsByStudentNameContaining(
            String studentName
    );


    // g) FIND BY EMAIL
    Optional<Student> findStudentByStudentEmail(
            String studentEmail
    );


    // h) GENDER IS NULL
    List<Student> findStudentsByStudentGenderIsNull();

 // =========================================
 // NATIVE QUERY - GET ACTIVE STUDENTS
 // =========================================

 @Query(
     value = "SELECT * FROM student_dtls WHERE active_sw = 'Y'",
     nativeQuery = true
 )
 List<Student> getActiveStudentsNative();


 // =========================================
 // NATIVE QUERY - UPDATE GENDER
 // =========================================

 @Modifying
 @Transactional
 @Query(
     value = "UPDATE student_dtls " +
             "SET student_gender = :gender " +
             "WHERE student_id = :studentId",
     nativeQuery = true
 )
 int updateStudentGenderNative(
         Integer studentId,
         String gender
 );


 // =========================================
 // NATIVE QUERY - HARD DELETE
 // =========================================

 @Modifying
 @Transactional
 @Query(
     value = "DELETE FROM student_dtls " +
             "WHERE student_id = :studentId",
     nativeQuery = true
 )
 int deleteStudentNative(
         Integer studentId
 );
 
 //Special Test
// List<Student> findByStudentName(String studentName);
 
//=========================================
//DUPLICATE TEST - DERIVED QUERY
//=========================================

Student findStudentByStudentName(
      String studentName
);

//=========================================
//DUPLICATE TEST - NATIVE QUERY
//=========================================

@Query(
 value = """
         SELECT *
         FROM student_dtls
         WHERE student_name = :studentName
         """,
 nativeQuery = true
)
Student findStudentByNameNative(
     String studentName
);
}