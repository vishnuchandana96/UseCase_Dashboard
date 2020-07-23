package com.ibm.resourceservice.repository;

import com.ibm.resourceservice.domain.Resource;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

@Repository
public interface ResourceRepository extends MongoRepository<Resource,String>
{
    @Query(" { 'emp_sno' :  ?0 , 'isdeleted' : false } ")
    public List<Resource> findResourceByEmp_sno(String emp_sno);

}
