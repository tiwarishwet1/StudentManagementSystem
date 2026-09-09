package in.mindcraft.entity;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Email;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

@Entity
@Table(name = "student_dtls")
public class Student {

    @Id 
	@GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name = "student_id")
    private Integer studentId;

    @NotBlank(message = "student name is required")
    @Pattern(
        regexp = "^[A-Za-z ]+$",
        message = "student name must contain only letters and spaces"
    )
    @Column(name = "student_name")
    private String studentName;

    @NotBlank(message = "student rank is required")
    @Pattern(
    		regexp = "^[1-9][0-9]*$",
    		message = "Rank can only be a positive number"
    		)
    @Column(name = "student_rank")
    private String studentRank;
    
    
    @NotBlank(message = "Gender is required")
    @Pattern(
        regexp = "^[MF]$",
        message = "Gender must be M or F"
    )
    @Column(name = "student_gender")
    private String studentGender;
    
    @Pattern(
        regexp = "^[YN]$",
        message = "Active flag must be Y or N"
    )
    @Column(name = "active_sw")
    private String activeSw;

    @CreationTimestamp
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    @Column(name = "created_date", updatable = false)
    private LocalDateTime createdDate;


    @UpdateTimestamp
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    @Column(name = "updated_date")
    private LocalDateTime updatedDate;


    @NotBlank(message = "Student email is required")
    @Email(message = "Please provide a valid email address")
    @Column(name = "student_email", unique=true)
    private String studentEmail;
    
    public Student() {
    }


	public Integer getStudentId() {
		return studentId;
	}


	public void setStudentId(Integer studentId) {
		this.studentId = studentId;
	}


	public String getStudentName() {
		return studentName;
	}


	public void setStudentName(String studentName) {
		this.studentName = studentName;
	}


	public String getStudentRank() {
		return studentRank;
	}


	public void setStudentRank(String studentRank) {
		this.studentRank = studentRank;
	}


	public String getStudentGender() {
		return studentGender;
	}


	public void setStudentGender(String studentGender) {
		this.studentGender = studentGender;
	}


	public String getActiveSw() {
		return activeSw;
	}


	public void setActiveSw(String activeSw) {
		this.activeSw = activeSw;
	}


	public LocalDateTime getCreatedDate() {
		return createdDate;
	}


	public LocalDateTime getUpdatedDate() {
		return updatedDate;
	}



	public String getStudentEmail() {
		return studentEmail;
	}


	public void setStudentEmail(String studentEmail) {
		this.studentEmail = studentEmail;
	}

	
}
