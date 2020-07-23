package com.ibm.clusterservice.service;

import com.ibm.clusterservice.domain.Cluster;
import com.ibm.clusterservice.exception.ClusterAlreadyExistsException;
import com.ibm.clusterservice.repository.ClusterRepository;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.util.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletResponse;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ClusterServiceImpl implements ClusterService
{
    // Creating object for cluster repository in order to connect with the database
    @Autowired
    private ClusterRepository clusterRepository;

    public ClusterServiceImpl(ClusterRepository clusterRepository)
    {
        this.clusterRepository=clusterRepository;
    }

    // This method is used to save the details of cluster to the database
    @Override
    public Cluster saveCluster(Cluster cluster) throws ClusterAlreadyExistsException
    {
        Cluster savedCluster=null;
        // To save the cluster to the mongodatabase
        if(clusterRepository.findByCluster_name(cluster.getCluster_name()).size()>0)
        {
            throw new ClusterAlreadyExistsException("Cluster Already Exist");
        }
        else {
             savedCluster = clusterRepository.save(cluster);
        }
        // returning the saved cluster
        return savedCluster;
    }

    // To get all the details of the cluster
    @Override
    public List<Cluster> getClusters() {
       // Getting all the cluster details
        List<Cluster> clusterList =  clusterRepository.findAll();
        clusterList = clusterList.stream().filter(x -> !x.isIsdeleted()).collect(Collectors.toList());
        return clusterList;
    }

    // This method is used to update cluster name
    @Override
    public Cluster updateCluster(Cluster cluster, String cluster_id)
    {
      Optional<Cluster> cluster1=clusterRepository.findById(cluster_id);
        cluster.setCluster_name(cluster.getCluster_name());
        //cluster.setCluster_deleted_status(true);
        cluster.setCluster_id(cluster_id);
        Cluster modifiedCluster=clusterRepository.save(cluster);
        return modifiedCluster ;
    }

    @Override
    public Cluster deleteCluster(String cluster_id)
    {
        Optional<Cluster> cluster1=clusterRepository.findById(cluster_id);
        //delete the cluster
        clusterRepository.delete(cluster1.get());
        //Return the deleted cluster
        return cluster1.get();

    }


}
