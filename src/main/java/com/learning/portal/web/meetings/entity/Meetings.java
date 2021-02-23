package com.learning.portal.web.meetings.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.learning.portal.core.audit.CreatedByAndUpdatedBy;
import com.learning.portal.web.classes.entity.Grade;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.Date;

@Entity
@Table(name = "meetings")
public class Meetings extends CreatedByAndUpdatedBy {


    @Column(name = "name")
    @NotBlank(message = "Name is required")
    private String name;

    @JsonFormat(
            shape = JsonFormat.Shape.STRING,
            pattern = "dd-MM-yyyy hh:mm:ss"
    )
    @Column(name = "start_time")
    @NotBlank(message = "Start time required")
    private Date startTime;

    @Column(name = "grade_id")
    private Long gradeId;

    @JoinColumn(name = "grade_id",updatable = false,insertable = false,nullable = false)
    @ManyToOne(fetch = FetchType.LAZY)
    private Grade gradeLink;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getStartTime() {
        return startTime;
    }

    @JsonFormat(
            shape = JsonFormat.Shape.STRING,
            pattern = "dd-MM-yyyy hh:mm:ss"
    )
    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Long getGradeId() {
        return gradeId;
    }

    public void setGradeId(Long gradeId) {
        this.gradeId = gradeId;
    }

    @JsonIgnore
    public Grade getGradeLink() {
        return gradeLink;
    }



}
