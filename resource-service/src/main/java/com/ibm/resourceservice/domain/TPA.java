package com.ibm.resourceservice.domain;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;

@Document(collection = "tpa")
@Data
public class TPA
{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private String tpa_id;
    private String cluster_name;
    private String application_name;
    private String tpa;
    private String tpa_status;
    private String tpa_pd;
    private String delivery_date;
    private boolean isdeleted;

    public TPA(String tpa_id, String cluster_name, String application_name, String tpa, String tpa_status, String tpa_pd, String delivery_date, boolean isdeleted) {
        this.tpa_id = tpa_id;
        this.cluster_name = cluster_name;
        this.application_name = application_name;
        this.tpa = tpa;
        this.tpa_status = tpa_status;
        this.tpa_pd = tpa_pd;
        this.delivery_date = delivery_date;
        this.isdeleted = isdeleted;
    }

    public TPA() {
    }

    public String getTpa_id() {
        return tpa_id;
    }

    public void setTpa_id(String tpa_id) {
        this.tpa_id = tpa_id;
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

    public String getTpa() {
        return tpa;
    }

    public void setTpa(String tpa) {
        this.tpa = tpa;
    }

    public String getTpa_status() {
        return tpa_status;
    }

    public void setTpa_status(String tpa_status) {
        this.tpa_status = tpa_status;
    }

    public String getTpa_pd() {
        return tpa_pd;
    }

    public void setTpa_pd(String tpa_pd) {
        this.tpa_pd = tpa_pd;
    }

    public String getDelivery_date() {
        return delivery_date;
    }

    public void setDelivery_date(String delivery_date) {
        this.delivery_date = delivery_date;
    }

    public boolean isIsdeleted() {
        return isdeleted;
    }

    public void setIsdeleted(boolean isdeleted) {
        this.isdeleted = isdeleted;
    }

}
