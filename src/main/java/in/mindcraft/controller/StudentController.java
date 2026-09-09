package in.mindcraft.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.DeleteMapping;


import java.util.List;

import in.mindcraft.entity.Student;
import in.mindcraft.service.StudentService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/students")
public class StudentController {

    private final StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    // 1. POSTMAPPING API FOR adding students into the DB 
    
    @PostMapping
    public ResponseEntity<Student> createStudent(
            @Valid @RequestBody Student student) {

        Student savedStudent = studentService.createStudent(student);

        return new ResponseEntity<>(
                savedStudent,
                HttpStatus.CREATED
        );
    }
    
    // 1. POSTMAPPING API FOR adding students into the DB 
    
    //---------------------------------------------------------
    
    
	// 2. POSTMAPPING API FOR adding BULK students into DB

    @PostMapping("/bulk")
    public ResponseEntity<List<Student>> createBulkStudents(@Valid @RequestBody List<Student> student){
    	List<Student> savedstudents = studentService.createBulkStudents(student);
    
    	return new ResponseEntity<>(
    			savedstudents, HttpStatus.CREATED);
    }
	// 2. POSTMAPPING API FOR adding BULK students into DB
    
    //---------------------------------------------------------

    // 3. GETMAPPING API FOR Fetching StudentById

        @GetMapping("/{id}")
        public ResponseEntity<Student> getStudentById(
                @PathVariable Integer id) {

            Student student = studentService.getStudentById(id);

            return ResponseEntity.ok(student);
        }
        
     // 3. GETMAPPING API FOR Fetching StudentById

    //---------------------------------------------------------
  
    // 4. GETMAPPING API FOR FETCHING ALL STUDENTS

        @GetMapping
        public ResponseEntity<List<Student>> getAllStudents(){
        	List<Student> students = studentService.getAllStudents();
        	
        	return ResponseEntity.ok(students);
        }
        
    // 4. GETMAPPING API FOR FETCHING ALL STUDENTS

    //---------------------------------------------------------

	// 5. GETMAPPING API FOR FETCHING ALL ACTIVE STUDENTS

    @GetMapping("/active")
    public ResponseEntity<List<Student>> getActiveStudents() {

        List<Student> student = studentService.getActiveStudents();

        return ResponseEntity.ok(student);    
        }
    
	// 5. GETMAPPING API FOR FETCHING ALL ACTIVE STUDENTS
    
    //---------------------------------------------------------

	// 6. PUT MAPPING API FOR UPDATING STUDENT BY STUDENTID

        @PutMapping("/{id}")
        public ResponseEntity<Student>updateStudent(
        		@PathVariable("id") Integer id,
        		@Valid @RequestBody Student student
        		){
        	Student updateStudent = studentService.updateStudent(id, student);
        	return ResponseEntity.ok(updateStudent);
        	 }
	// 6. PUT MAPPING API FOR UPDATING STUDENT BY STUDENTID
        
    //---------------------------------------------------------

	// 7. DELETE MAPPING API FOR DELETING STUDENT BY STUDENTID
    
    @DeleteMapping("/{id}")
    public ResponseEntity<String> hardDeleteStudent(
    		@PathVariable("id") Integer id)
    		{
    			
    		studentService.hardDeleteStudent(id);
    	    return ResponseEntity.ok("Student Deleted Successfully");

    		}
	// 7. DELETE MAPPING API FOR DELETING STUDENT BY STUDENTID
    
    //---------------------------------------------------------
    
     // 8. SOFTDELETE MAPPING API FOR DEACTIVTING STUDENT

        @PatchMapping("/{id}/deactivate")
        public ResponseEntity<Student> softDeleteStudent(
                @PathVariable("id") Integer StudentId) {

            Student student = studentService.softDeleteStudent(StudentId);

            return ResponseEntity.ok(student);
        }
       

        
      // 8. SOFTDELETE MAPPING API FOR DEACTIVTING STUDENT
        
     //---------------------------------------------------------

    // 9. GETMAPPING API FOR FETCHING STUDENTS BY ACTIVITY(Y/N)

        @GetMapping("/activity")
        public ResponseEntity<List<Student>> getStudentByActivity(@RequestParam String status){
        	
        	List<Student> student = studentService.getStudentsByActivity(status);
        	
        	return ResponseEntity.ok(student);
        }
        
     // 9. GETMAPPING API FOR FETCHING STUDENTS BY ACTIVITY(Y/N)
        
     //---------------------------------------------------------      
}
