package in.mindcraft.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import in.mindcraft.entity.Student;

//import org.springframework.data.jpa.repository.Query;
public interface StudentRepository extends JpaRepository <Student, Integer>{

	List<Student> findBystudentId(Integer studentId);
	List<Student> findByActiveSw(String activeSw);

}
