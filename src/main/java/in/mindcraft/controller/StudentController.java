package in.mindcraft.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import in.mindcraft.dto.StudentProcedureResponse;
import in.mindcraft.service.StudentProcedureService;
import in.mindcraft.entity.Student;
import in.mindcraft.service.StudentService;

import jakarta.validation.Valid;


@RestController
@RequestMapping("/students")
public class StudentController {


	private final StudentService studentService;

	private final StudentProcedureService studentProcedureService;

	public StudentController(
	        StudentService studentService,
	        StudentProcedureService studentProcedureService
	) {
	    this.studentService = studentService;
	    this.studentProcedureService = studentProcedureService;
	}


    // =========================================
    // CREATE
    // =========================================

    @PostMapping
    public ResponseEntity<Student>
    createStudent(

            @Valid
            @RequestBody
            Student student
    ) {

        Student savedStudent =
                studentService
                .createStudent(student);


        return new ResponseEntity<>(
                savedStudent,
                HttpStatus.CREATED
        );

    }


    // =========================================
    // BULK CREATE
    // =========================================

    @PostMapping("/bulk")
    public ResponseEntity<List<Student>>
    createBulkStudents(

            @Valid
            @RequestBody
            List<Student> students
    ) {

        List<Student> savedStudents =
                studentService
                .createBulkStudents(students);


        return new ResponseEntity<>(
                savedStudents,
                HttpStatus.CREATED
        );

    }


    // =========================================
    // GET ALL
    // IMPORTANT: Keep before /{id}
    // =========================================

    @GetMapping
    public ResponseEntity<List<Student>>
    getAllStudents() {

        return ResponseEntity.ok(
                studentService.getAllStudents()
        );

    }


    // =========================================
    // GET BY ID
    // =========================================

    @GetMapping("/{id}")
    public ResponseEntity<Student>
    getStudentById(

            @PathVariable Integer id
    ) {

        return ResponseEntity.ok(
                studentService
                .getStudentById(id)
        );

    }


    // =========================================
    // UPDATE
    // =========================================

    @PutMapping("/{id}")
    public ResponseEntity<Student>
    updateStudent(

            @PathVariable Integer id,

            @Valid
            @RequestBody
            Student student
    ) {

        return ResponseEntity.ok(
                studentService
                .updateStudent(id, student)
        );

    }


    // =========================================
    // SOFT DELETE
    // =========================================

    @PatchMapping("/{id}/deactivate")
    public ResponseEntity<Student>
    softDeleteStudent(

            @PathVariable Integer id
    ) {

        return ResponseEntity.ok(
                studentService
                .softDeleteStudent(id)
        );

    }


    // =========================================
    // HARD DELETE
    // =========================================

    @DeleteMapping("/{id}")
    public ResponseEntity<String>
    deleteStudent(

            @PathVariable Integer id
    ) {

        studentService.deleteStudent(id);


        return ResponseEntity.ok(
                "Student deleted successfully"
        );

    }


    // =========================================
    // ACTIVITY
    // =========================================

    @GetMapping("/activity")
    public ResponseEntity<List<Student>>
    getStudentsByActivity(

            @RequestParam String status
    ) {

        return ResponseEntity.ok(
                studentService
                .getStudentsByActivity(status)
        );

    }


    // =========================================
    // DERIVED QUERY - GENDER
    // =========================================

    @GetMapping("/gender")
    public ResponseEntity<List<Student>>
    getStudentsByGender(

            @RequestParam String gender
    ) {

        return ResponseEntity.ok(
                studentService
                .getStudentsByGender(gender)
        );

    }


    // =========================================
    // DERIVED QUERY - RANK >=
    // =========================================

    @GetMapping("/rank/greater")
    public ResponseEntity<List<Student>>
    getStudentsByRankGreaterThanEqual(

            @RequestParam String rank
    ) {

        return ResponseEntity.ok(
                studentService
                .getStudentsByRankGreaterThanEqual(rank)
        );

    }


    // =========================================
    // DERIVED QUERY - RANK <=
    // =========================================

    @GetMapping("/rank/less")
    public ResponseEntity<List<Student>>
    getStudentsByRankLessThanEqual(

            @RequestParam String rank
    ) {

        return ResponseEntity.ok(
                studentService
                .getStudentsByRankLessThanEqual(rank)
        );

    }


    // =========================================
    // DERIVED QUERY - GENDER AND RANK
    // =========================================

    @GetMapping("/gender-rank")
    public ResponseEntity<List<Student>>
    getStudentsByGenderAndRank(

            @RequestParam String gender,

            @RequestParam String rank
    ) {

        return ResponseEntity.ok(
                studentService
                .getStudentsByGenderAndRank(
                        gender,
                        rank
                )
        );

    }


    // =========================================
    // DERIVED QUERY - NAME STARTS WITH
    // =========================================

    @GetMapping("/name/starts")
    public ResponseEntity<List<Student>>
    getStudentsByNameStartingWith(

            @RequestParam String name
    ) {

        return ResponseEntity.ok(
                studentService
                .getStudentsByNameStartingWith(name)
        );

    }


    // =========================================
    // DERIVED QUERY - NAME CONTAINS
    // =========================================

    @GetMapping("/name/contains")
    public ResponseEntity<List<Student>>
    getStudentsByNameContaining(

            @RequestParam String name
    ) {

        return ResponseEntity.ok(
                studentService
                .getStudentsByNameContaining(name)
        );

    }


    // =========================================
    // DERIVED QUERY - EMAIL
    // =========================================

    @GetMapping("/email")
    public ResponseEntity<Student>
    getStudentByEmail(

            @RequestParam String email
    ) {

        return ResponseEntity.ok(
                studentService
                .getStudentByEmail(email)
        );

    }


    // =========================================
    // DERIVED QUERY - GENDER NULL
    // =========================================

    @GetMapping("/gender/null")
    public ResponseEntity<List<Student>>
    getStudentsWithNullGender() {

        return ResponseEntity.ok(
                studentService
                .getStudentsWithNullGender()
        );

    }
    
    @GetMapping("/native/active")
    public ResponseEntity<List<Student>>
    getActiveStudentsNative() {

        List<Student> students =
                studentService
                .getActiveStudentsNative();

        return ResponseEntity.ok(students);

    }
    
    @GetMapping("/procedure/{id}")
    public ResponseEntity<StudentProcedureResponse>
    getStudentProcedure(
            @PathVariable Integer id
    ) {

        return ResponseEntity.ok(
                studentProcedureService
                        .getStudentDetails(id)
        );
    }
    
    @PatchMapping("/native/{id}/gender")
    public ResponseEntity<String>
    updateStudentGenderNative(

            @PathVariable Integer id,

            @RequestParam String gender
    ) {

        studentService
                .updateStudentGenderNative(
                        id,
                        gender
                );

        return ResponseEntity.ok(
                "Student gender updated successfully"
        );

    }
    
    
    @DeleteMapping("/native/{id}")
    public ResponseEntity<String>
    deleteStudentNative(

            @PathVariable Integer id
    ) {

        studentService
                .deleteStudentNative(id);

        return ResponseEntity.ok(
                "Student deleted successfully using native query"
        );

    }
      
 // =========================================
 // DUPLICATE TEST - DERIVED QUERY
 // =========================================

 @GetMapping("/test/duplicate/derived")
 public ResponseEntity<Student>
 testDuplicateDerivedQuery(

         @RequestParam String name

 ) {

     return ResponseEntity.ok(

             studentService
             .testDuplicateDerivedQuery(name)

     );

 }


 // =========================================
 // DUPLICATE TEST - NATIVE QUERY
 // =========================================

 @GetMapping("/test/duplicate/native")
 public ResponseEntity<Student>
 testDuplicateNativeQuery(

         @RequestParam String name

 ) {

     return ResponseEntity.ok(

             studentService
             .testDuplicateNativeQuery(name)

     );

 }

}