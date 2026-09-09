package in.mindcraft.service;

import java.util.List;
import in.mindcraft.entity.*;

public interface StudentService {	
    // 1. POSTMAPPING API FOR adding students into the DB 
	Student createStudent(Student student);	 
    // 1. POSTMAPPING API FOR adding students into the DB 
	
	// 2. POSTMAPPING API FOR adding BULK students into DB
	List<Student> createBulkStudents(List<Student> students);
	// 2. POSTMAPPING API FOR adding BULK students into DB

	// 3. GETMAPPING API FOR Fetching StudentById
	Student getStudentById(Integer studentId );
	// 3. GETMAPPING API FOR Fetching StudentById
	
	// 4. GETMAPPING API FOR FETCHING ALL STUDENTS
	List<Student> getAllStudents();
	// 4. GETMAPPING API FOR FETCHING ALL STUDENTS
	
	// 5. GETMAPPING API FOR FETCHING ALL ACTIVE STUDENTS
	List<Student> getActiveStudents();
	// 5. GETMAPPING API FOR FETCHING ALL ACTIVE STUDENTS

	// 6. PUT MAPPING API FOR UPDATING STUDENT BY STUDENTID
	Student updateStudent(Integer studentId, Student student);
	// 6. PUT MAPPING API FOR UPDATING STUDENT BY STUDENTID
    
	// 7. DELETE MAPPING API FOR DELETING STUDENT BY STUDENTID
	 void hardDeleteStudent(Integer id);
    // 7. DELETE MAPPING API FOR DELETING STUDENT BY STUDENTID
	 
    // 8. PATCH MAPPING API FOR DEACTIVTING STUDENT
	  Student softDeleteStudent(Integer studentId);
	// 8. PATCH MAPPING API FOR DEACTIVTING STUDENT

	// 9. GETMAPPING API FOR FETCHING STUDENTS BY ACTIVITY(Y/N)
	  List<Student> getStudentsByActivity(String activeWs);
   // 9. GETMAPPING API FOR FETCHING STUDENTS BY ACTIVITY(Y/N)

}
	
	
