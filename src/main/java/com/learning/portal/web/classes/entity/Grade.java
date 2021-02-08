package com.learning.portal.web.classes.entity;

import com.learning.portal.core.audit.CreatedByAndUpdatedBy;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

@Entity
@Table(name = "class")
public class Grade extends CreatedByAndUpdatedBy {


    @NotBlank(message = "Name is required")
    @Column(name = "name")
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
