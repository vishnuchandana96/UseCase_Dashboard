package com.ibm.clusterservice.domain;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;

@Document(collection="application")
public class Application
{
    //It is the id of the application
    @Id
    //It generates the id automatically
    @GeneratedValue(strategy = GenerationType.AUTO)
    private String application_id;
    private String cluster_name;
    private String application_name;
    private String application_description;
    private boolean isdeleted;

    public Application(String application_id, String cluster_name, String application_name, String application_description, boolean isdeleted) {
        this.application_id = application_id;
        this.cluster_name = cluster_name;
        this.application_name = application_name;
        this.application_description = application_description;
        this.isdeleted = isdeleted;
    }

    public Application()
    {

    }

    public Application(String application_id, String cluster_name, String application_name, String application_description) {
        this.application_id = application_id;
        this.cluster_name = cluster_name;
        this.application_name = application_name;
        this.application_description = application_description;
    }


    public String getApplication_id() {
        return application_id;
    }

    public void setApplication_id(String application_id) {
        this.application_id = application_id;
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

    public String getApplication_description() {
        return application_description;
    }

    public void setApplication_description(String application_description) {
        this.application_description = application_description;
    }

    public boolean isIsdeleted() {
        return isdeleted;
    }

    public void setIsdeleted(boolean isdeleted) {
        this.isdeleted = isdeleted;
    }

}
