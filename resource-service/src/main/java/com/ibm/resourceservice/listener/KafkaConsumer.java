/*
package com.ibm.resourceservice.listener;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ibm.resourceservice.domain.Resource;
import com.ibm.resourceservice.exception.ResourceAlreadyExistsException;
import com.ibm.resourceservice.service.ResourceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class KafkaConsumer
{

    private Resource resource;
    @Autowired
    private ResourceService resourceService;

    @KafkaListener(topics = "Application", groupId = "group_id")
    public void consume(String resource) throws IOException , ResourceAlreadyExistsException
    {
        Resource obj = new ObjectMapper().readValue(resource, Resource.class);
        System.out.printf("-----Consumed details------"+resource);
        resourceService.saveResource(obj);

    }
}
*/
