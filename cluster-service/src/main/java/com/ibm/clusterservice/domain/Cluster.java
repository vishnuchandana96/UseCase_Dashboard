package com.ibm.clusterservice.domain;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;

//creates the user document in the mongo database
@Document(collection="cluster")
public class Cluster
{
    //It is the id of the cluster
    @Id
    //It generates the id automatically
    @GeneratedValue(strategy = GenerationType.AUTO)
    private String cluster_id;
    private String cluster_name;
    private String cluster_description;
    private String cluster_date;
    private boolean isdeleted;

    public Cluster(String cluster_id, String cluster_name, String cluster_description, String cluster_date, boolean isdeleted) {
        this.cluster_id = cluster_id;
        this.cluster_name = cluster_name;
        this.cluster_description = cluster_description;
        this.cluster_date = cluster_date;
        this.isdeleted = isdeleted;
    }

    public Cluster() {
    }


    public String getCluster_id() {
        return cluster_id;
    }

    public void setCluster_id(String cluster_id) {
        this.cluster_id = cluster_id;
    }

    public String getCluster_name() {
        return cluster_name;
    }

    public void setCluster_name(String cluster_name) {
        this.cluster_name = cluster_name;
    }

    public String getCluster_description() {
        return cluster_description;
    }

    public void setCluster_description(String cluster_description) {
        this.cluster_description = cluster_description;
    }

    public String getCluster_date() {
        return cluster_date;
    }

    public void setCluster_date(String cluster_date) {
        this.cluster_date = cluster_date;
    }

    public boolean isIsdeleted() {
        return isdeleted;
    }

    public void setIsdeleted(boolean isdeleted) {
        this.isdeleted = isdeleted;
    }


}
