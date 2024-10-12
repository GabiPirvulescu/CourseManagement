package net.coursemanagement.course_app.dto;

public class DepartmentTeacherDTO {
    private Long tId;
    private Long dId;

    public Long gettId() {
        return tId;
    }

    public void settId(Long tId) {
        this.tId = tId;
    }

    public Long getdId() {
        return dId;
    }

    public void setdId(Long dId) {
        this.dId = dId;
    }

    public DepartmentTeacherDTO() {
    }
}
