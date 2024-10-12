package net.coursemanagement.course_app.dto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;


public class EnrollmentDTO {
    private Long cId;
    private Long sId;

    public Long getcId() {
        return cId;
    }

    public void setcId(Long cId) {
        this.cId = cId;
    }

    public Long getsId() {
        return sId;
    }

    public void setsId(Long sId) {
        this.sId = sId;
    }
}
