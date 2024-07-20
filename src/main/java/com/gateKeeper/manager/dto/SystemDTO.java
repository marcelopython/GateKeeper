package com.gateKeeper.manager.dto;

import com.gateKeeper.manager.model.Company;
import com.gateKeeper.manager.model.System;

import javax.validation.constraints.NotBlank;


public class SystemDTO {

    private Long id;

    @NotBlank
    private String name;

    private String description;

    private Company companyId;

    public SystemDTO() {
    }

    public SystemDTO(Long id , String name, String description, Company companyId) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.companyId = companyId;
    }

    public System toEntity() {
        return new System(this.name, this.description, this.companyId);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Company getCompanyId() {
        return companyId;
    }

    public void setCompanyId(Company companyId) {
        this.companyId = companyId;
    }
}
