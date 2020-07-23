package com.ibm.clusterservice.controller;

import com.ibm.clusterservice.domain.Application;
import com.ibm.clusterservice.exception.ApplicationAlreadyExistsException;
import com.ibm.clusterservice.service.ApplicationService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "api/v1")
@CrossOrigin(origins = "*")
public class ApplicationController
{
    //creating the object of the application service to invoke all the methods in the service
    @Autowired
    private ApplicationService applicationService;

    @Autowired
   // private KafkaTemplate<String, Application> kafkaTemplate;

    // Declaration and Intialization of topic name
    private static final String TOPIC = "Application";

    Logger logger= LoggerFactory.getLogger(this.getClass());

    public ApplicationController(ApplicationService applicationService) {
        this.applicationService=applicationService;
    }

    //This method is used to save the application to the database by the url i.e., application
    @PostMapping("application")
    public ResponseEntity<?> saveApplication(@RequestBody Application application)
    {
        logger.info("Entered application endpoint to save application");
        Application savedApplication=null;
        try {
            savedApplication = applicationService.saveApplication(application);
        } catch (ApplicationAlreadyExistsException e) {
            logger.error(e.getMessage());
           return new ResponseEntity<String>(e.getMessage(),HttpStatus.CONFLICT);
        }
       // this.kafkaTemplate.send(TOPIC,savedApplication);
        System.out.println("-------Application details in kafka -------" +savedApplication);
        return new ResponseEntity<Application>(savedApplication, HttpStatus.CREATED);
    }

    @GetMapping("application")
    public ResponseEntity<?> getAllApplications()
    {
        logger.info("Entered application endpoint to get applications");
        //Getting all the applications as a list
        return new ResponseEntity<List<Application>>(applicationService.getApplications(), HttpStatus.OK);
    }

    //Deleting the application according to the application_id
    @DeleteMapping("application/{application_id}")
    public ResponseEntity<?> deleteApplication(@PathVariable String application_id) {
        logger.info("Entered application/id endpoint to delete specific application");
        //deleting the application using the id
        return new ResponseEntity<Application>(applicationService.deleteApplication(application_id), HttpStatus.OK);
    }

    // Updating the application details based on application_id
    @PutMapping("application/{application_id}")
    public ResponseEntity<?> updateApplication(@RequestBody Application application, @PathVariable String application_id) {
        logger.info("Entered application/id endpoint to update application");
        Application updateapplication = applicationService.updateApplication(application,application_id);
        return new ResponseEntity<Application>(updateapplication, HttpStatus.OK);
    }

}

