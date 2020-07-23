package com.ibm.resourceservice.repository;

import com.ibm.resourceservice.domain.TPA;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TPARepository extends MongoRepository<TPA,String>
{
}
