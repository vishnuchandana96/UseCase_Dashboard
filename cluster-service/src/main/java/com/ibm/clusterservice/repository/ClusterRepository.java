package com.ibm.clusterservice.repository;

import com.ibm.clusterservice.domain.Cluster;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ClusterRepository extends MongoRepository<Cluster,String>
{

    @Query(" { 'cluster_name' :  ?0 , 'isdeleted' : false  } ")
    public List<Cluster> findByCluster_name(String cluster_name);

}
