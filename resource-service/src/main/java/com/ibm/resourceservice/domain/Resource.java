package com.ibm.resourceservice.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;

@Document(collection="resource")
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class Resource
{
    @Id
    //It generates the id automatically
    @GeneratedValue(strategy = GenerationType.AUTO)
    private String emp_sno;
    private String cluster_name;
    private String application_name;
    private String emp_id;
    private String emp_name;
    private String emp_description;
    private boolean isdeleted;

    public Resource(String emp_sno, String cluster_name, String application_name, String emp_id, String emp_name, String emp_description, boolean isdeleted) {
        this.emp_sno = emp_sno;
        this.cluster_name = cluster_name;
        this.application_name = application_name;
        this.emp_id = emp_id;
        this.emp_name = emp_name;
        this.emp_description = emp_description;
        this.isdeleted = isdeleted;
    }

    public Resource() {
    }

    public String getEmp_sno() {
        return emp_sno;
    }

    public void setEmp_sno(String emp_sno) {
        this.emp_sno = emp_sno;
    }

    public String getCluster_name() {
        return cluster_name;
    }

    public void setCluster_name(String cluster_name) {
        this.cluster_name = cluster_name;
    }

    public String getApplication_name() {
        return application_name;
    }

    public void setApplication_name(String application_name) {
        this.application_name = application_name;
    }

    public String getEmp_id() {
        return emp_id;
    }

    public void setEmp_id(String emp_id) {
        this.emp_id = emp_id;
    }

    public String getEmp_name() {
        return emp_name;
    }

    public void setEmp_name(String emp_name) {
        this.emp_name = emp_name;
    }

    public String getEmp_description() {
        return emp_description;
    }

    public void setEmp_description(String emp_description) {
        this.emp_description = emp_description;
    }

    public boolean isIsdeleted() {
        return isdeleted;
    }

    public void setIsdeleted(boolean isdeleted) {
        this.isdeleted = isdeleted;
    }

}
