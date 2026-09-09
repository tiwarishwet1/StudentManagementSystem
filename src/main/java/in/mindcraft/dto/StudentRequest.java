//package in.mindcraft.dto;
//
//import jakarta.validation.constraints.NotBlank;
//import jakarta.validation.constraints.NotNull;
//import jakarta.validation.constraints.Pattern;
//import jakarta.validation.constraints.Email;
//
//public class StudentRequest {
//
//    @NotBlank(message = "Student name is required")
//    @Pattern(
//        regexp = "^[A-Za-z ]+$",
//        message = "Student name must contain only letters and spaces"
//    )
//    private String studentName;
//
//    @NotBlank(message = "Student rank is required")
//    @Pattern(
//    		regexp = "^[1-9][0-9]*$",
//    		message = "Student Rank can only be positive"
//    		)
//    private String studentRank;
//    
//    @NotBlank(message = "Student gender is required")
//    @Pattern(
//    		regexp = "[MF]",
//    		message = "Student Gender must be M|F"
//    		)
//    private String studentGender;
//    
//    @NotBlank(message = "Student email is required")
//    @Email
//    private String studentEmail;
//    
//    public StudentRequest() {
//    }
//
//	public String getStudentName() {
//		return studentName;
//	}
//
//	public void setStudentName(String studentName) {
//		this.studentName = studentName;
//	}
//
//	public String getStudentRank() {
//		return studentRank;
//	}
//
//	public void setStudentRank(String studentRank) {
//		this.studentRank = studentRank;
//	}
//
//	public String getStudentGender() {
//		return studentGender;
//	}
//
//	public void setStudentGender(String studentGender) {
//		this.studentGender = studentGender;
//	}
//
//	public String getStudentEmail() {
//		return studentEmail;
//	}
//
//	public void setStudentEmail(String studentEmail) {
//		this.studentEmail = studentEmail;
//	}
//
//
//    
//    
//    }
