package in.mindcraft.dto;

public class StudentProcedureResponse {

    private String studentName;

    private String studentGender;

    public StudentProcedureResponse() {
    }

    public StudentProcedureResponse(
            String studentName,
            String studentGender
    ) {
        this.studentName = studentName;
        this.studentGender = studentGender;
    }

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(
            String studentName
    ) {
        this.studentName = studentName;
    }

    public String getStudentGender() {
        return studentGender;
    }

    public void setStudentGender(
            String studentGender
    ) {
        this.studentGender = studentGender;
    }
}