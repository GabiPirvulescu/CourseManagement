package net.coursemanagement.course_app.dto;

public class DepartmentCourseDTO {
    private Long cId;
    private Long dId;

    public Long getcId() {
        return cId;
    }

    public void setcId(Long cId) {
        this.cId = cId;
    }

    public Long getdId() {
        return dId;
    }

    public void setdId(Long dId) {
        this.dId = dId;
    }

    public DepartmentCourseDTO() {
    }
}
