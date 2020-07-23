package com.ibm.clusterservice.service;

import com.ibm.clusterservice.domain.Cluster;
import com.ibm.clusterservice.exception.ClusterAlreadyExistsException;

import java.util.List;

public interface ClusterService
{
    // Once the user creates a cluster those details have to be saved to database
    public Cluster saveCluster(Cluster cluster) throws ClusterAlreadyExistsException;
    // Getting all clusters
    public List<Cluster> getClusters();
    // Updating Cluster based on cluster_id
    public Cluster updateCluster(Cluster cluster,String cluster_id);
    // Deleting the Cluster
    public Cluster deleteCluster(String cluster_id);
}
