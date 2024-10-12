package net.coursemanagement.course_app.dto;

public class DepartmentStudentDTO {
    private Long sId;
    private Long dId;

    public Long getsId() {
        return sId;
    }

    public void setsId(Long sId) {
        this.sId = sId;
    }

    public Long getdId() {
        return dId;
    }

    public void setdId(Long dId) {
        this.dId = dId;
    }

    public DepartmentStudentDTO() {
    }
}
