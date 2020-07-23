package com.ibm.clusterservice.repository;

import com.ibm.clusterservice.domain.Application;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ApplicationRepository extends MongoRepository<Application,String>
{
    @Query(" { 'application_name' :  ?0 , 'isdeleted' : false } ")
    public List<Application> findByApplication_name(String application_name);

}
