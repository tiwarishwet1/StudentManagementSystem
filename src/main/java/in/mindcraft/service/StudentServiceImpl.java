package in.mindcraft.service;

import in.mindcraft.entity.Student;
import org.springframework.stereotype.Service;
import in.mindcraft.repository.*;
import java.util.NoSuchElementException;
import java.util.List;

@Service
public class StudentServiceImpl implements StudentService {

    private final StudentRepository studentRepository;

    public StudentServiceImpl(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }
    
    
    // 1. POSTMAPPING API FOR adding students into the DB 

    @Override
    public Student createStudent(Student student) {

        student.setActiveSw("Y");

        return studentRepository.save(student);
    }
    
     // 1. POSTMAPPING API FOR adding students into the DB 
    
    //--------------------------------------------------------------//

	// 2. POSTMAPPING API FOR adding BULK students into DB

    @Override
    public List<Student> createBulkStudents(List<Student> students){
    	for (Student student: students) {
    		student.setActiveSw("Y");
    	}
    	
    	return studentRepository.saveAll(students);
    }
	// 2. POSTMAPPING API FOR adding BULK students into DB
    
    // ------------------------------------------------------------//
    
	// 3. GETMAPPING API FOR Fetching StudentById

    @Override
    public Student getStudentById(Integer studentId) {

        return studentRepository.findById(studentId)
                .orElseThrow(() -> new NoSuchElementException(
                        "Student not found with ID: " + studentId
                ));
    }
	// 3. GETMAPPING API FOR Fetching StudentById
    
    // ------------------------------------------------------------//

	// 4. GETMAPPING API FOR FETCHING ALL STUDENTS
 
    @Override
    public List<Student> getAllStudents() {
    	
    	return studentRepository.findAll();
    }
	// 4. GETMAPPING API FOR FETCHING ALL STUDENTS

    // ------------------------------------------------------------//

	// 5. GETMAPPING API FOR FETCHING ALL ACTIVE STUDENTS
    
    @Override
    public List<Student> getActiveStudents() {

        return studentRepository.findByActiveSw("Y");
    }
	// 5. GETMAPPING API FOR FETCHING ALL ACTIVE STUDENTS
    
    // ------------------------------------------------------------//


	// 6. PUT MAPPING API FOR UPDATING STUDENT BY STUDENTID

    @Override
    public Student updateStudent(Integer studentId, Student student ) {
    	Student existingStudent = studentRepository.findById(studentId)
                        .orElseThrow(() -> new NoSuchElementException("No such Student found for this ID:" + studentId));
    	existingStudent.setStudentName(student.getStudentName());
    	existingStudent.setStudentRank(student.getStudentRank());
    	existingStudent.setStudentEmail(student.getStudentEmail());
    	existingStudent.setStudentGender(student.getStudentGender());
    	
    	return studentRepository.save(existingStudent);
    
    }
	// 6. PUT MAPPING API FOR UPDATING STUDENT BY STUDENTID
    
    // ------------------------------------------------------------//

	// 7. DELETE MAPPING API FOR DELETING STUDENT BY STUDENTID

    
    @Override
    public void hardDeleteStudent(Integer id) {
    		Student student = studentRepository.findById(id)
    				.orElseThrow(() -> new NoSuchElementException(
                           "No such Student found for this ID: " + id
    						));
    		 studentRepository.delete(student);
  }
   
	// 7. DELETE MAPPING API FOR DELETING STUDENT BY STUDENTID
    
    // ------------------------------------------------------------//

    // 8. SOFTDELETE MAPPING API FOR DEACTIVTING STUDENT
    
    @Override
    public Student softDeleteStudent(Integer studentId) {

        Student existingStudent = studentRepository.findById(studentId)
                .orElseThrow(() -> new NoSuchElementException(
                        "No such Student found for this ID: " + studentId
                ));

        existingStudent.setActiveSw("N");

        return studentRepository.save(existingStudent);
    }

    // 8. SOFTDELETE MAPPING API FOR DEACTIVTING STUDENT
    
    // ------------------------------------------------------------//

    // 9. GETMAPPING API FOR FETCHING STUDENTS BY ACTIVITY(Y/N)

    @Override
    public List<Student> getStudentsByActivity(String activeSw) {
    	
    	return studentRepository.findByActiveSw(activeSw);
    }
    
    // 9. GETMAPPING API FOR FETCHING STUDENTS BY ACTIVITY(Y/N)

}

	
