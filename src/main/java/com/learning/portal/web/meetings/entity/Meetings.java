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
    private String startTime;

    @JsonFormat(
            shape = JsonFormat.Shape.STRING,
            pattern = "dd-MM-yyyy hh:mm:ss"
    )
    @Column(name = "end_time")
    @NotBlank(message = "End time required")
    private String endTime;

    @Column(name = "grade_id")
    private Long gradeId;

    @Column(name = "link")
    private String link;

    @Column(name = "password")
    private String password;

    @Transient
    private String gradeName;

    @JoinColumn(name = "grade_id",updatable = false,insertable = false,nullable = false)
    @ManyToOne(fetch = FetchType.LAZY)
    private Grade gradeLink;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }


    public Long getGradeId() {
        return gradeId;
    }

    public void setGradeId(Long gradeId) {
        this.gradeId = gradeId;
    }

    public String getGradeName() {
        return gradeName;
    }

    public void setGradeName(String gradeName) {
        this.gradeName = gradeName;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }


    public Grade getGradeLink() {
        return gradeLink;
    }

}
