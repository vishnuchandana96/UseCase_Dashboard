package com.ibm.clusterservice.controller;

import com.ibm.clusterservice.domain.Cluster;
import com.ibm.clusterservice.exception.ClusterAlreadyExistsException;
import com.ibm.clusterservice.service.ClusterService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

@RestController
@RequestMapping(value = "api/v1")
@CrossOrigin(origins = "*")
public class ClusterController
{
    //creating the object of the cluster service to invoke all the methods in the service
    @Autowired
    private ClusterService clusterService;

    public String url ="http://localhost:8097/api/v1/resource";
    

    Logger logger= LoggerFactory.getLogger(this.getClass());

    public ClusterController(ClusterService clusterService) {
        this.clusterService=clusterService;
    }

    //This method is used to save the cluster to the database by the url i.e., cluster
    @PostMapping("cluster")
    public ResponseEntity<?> saveCluster(@RequestBody Cluster cluster)
    {
        logger.info("Entered cluster endpoint to save cluster");
        Cluster savedCluster=null;
        try {
            savedCluster = clusterService.saveCluster(cluster);
        } catch (ClusterAlreadyExistsException e) {
            logger.error(e.getMessage());
           return new ResponseEntity<String>(e.getMessage(),HttpStatus.CONFLICT);
        }
      //  ResponseEntity<String> result= new RestTemplate().postForEntity(url,savedCluster,String.class);
       // System.out.println("-------Posting cluster data-----" +result);
        return new ResponseEntity<Cluster>(savedCluster, HttpStatus.CREATED);
    }

    @GetMapping("cluster")
    public ResponseEntity<?> getAllClusters()
    {
        logger.info("Entered cluster end point to get clusters");
        //Getting all the clusters as a list
        return new ResponseEntity<List<Cluster>>(clusterService.getClusters(), HttpStatus.OK);
    }

    //Deleting the cluster according to the cluster_id
    @DeleteMapping("cluster/{cluster_id}")
    public ResponseEntity<?> deleteCluster(@PathVariable String cluster_id) {
        logger.info("Entered cluster/id endpoint to delete cluster");
        //deleting the cluster using the id
        return new ResponseEntity<Cluster>(clusterService.deleteCluster(cluster_id), HttpStatus.OK);
    }

    // Updating the cluster details based on cluster_id
    @PutMapping("cluster/{cluster_id}")
    public ResponseEntity<?> updateCluster(@RequestBody Cluster cluster, @PathVariable String cluster_id) {
        logger.info("Entered cluster/id end point to update cluster");
        Cluster updatecluster = clusterService.updateCluster(cluster,cluster_id);
        return new ResponseEntity<Cluster>(updatecluster, HttpStatus.OK);
    }


}
