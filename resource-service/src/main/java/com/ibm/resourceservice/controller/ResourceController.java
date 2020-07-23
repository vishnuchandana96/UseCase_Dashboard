package com.ibm.resourceservice.controller;

import com.ibm.resourceservice.domain.Resource;
import com.ibm.resourceservice.exception.ResourceAlreadyExistsException;
import com.ibm.resourceservice.service.ResourceService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@RestController
@RequestMapping(value = "api/v1")
@CrossOrigin(origins = "*")
public class ResourceController
{
        @Autowired
        private ResourceService resourceService;

        Logger logger= LoggerFactory.getLogger(this.getClass());

        public ResourceController(ResourceService resourceService)
        {
            this.resourceService=resourceService;
        }

        //This method is used to save the resource to the database by the url i.e., resource
        @PostMapping("resource")
        public ResponseEntity<?> saveResource(@RequestBody Resource resource)
        {
            logger.info("Entered resource endpoint to save resource");
            Resource savedResource=null;
            try {
                savedResource = resourceService.saveResource(resource);
            } catch (ResourceAlreadyExistsException e) {
                logger.error(e.getMessage());
                return new ResponseEntity<String>(e.getMessage(), HttpStatus.CONFLICT);
            }

            return new ResponseEntity<Resource>(savedResource, HttpStatus.CREATED);
        }



        @GetMapping("resource")
        public ResponseEntity<?> getAllResources()
        {
            logger.info("Entered resource end point to get resources");
            //Getting all the resources as a list
            return new ResponseEntity<List<Resource>>(resourceService.getResources(), HttpStatus.OK);
        }

        //Deleting the resource according to the emp_sno
        @DeleteMapping("resource/{emp_sno}")
        public ResponseEntity<?> deleteResource(@PathVariable String emp_sno) {
            logger.info("Entered resource/emp_sno endpoint to delete resource");
            return new ResponseEntity<Resource>(resourceService.deleteResource(emp_sno), HttpStatus.OK);
        }

        // Updating the resource details based on emp_sno
        @PutMapping("resource/{emp_sno}")
        public ResponseEntity<?> updateResource(@RequestBody Resource resource, @PathVariable String emp_sno) {
            logger.info("Entered resource/emp_sno end point to update resource");
            Resource updatedresource = resourceService.updateResource(resource,emp_sno);
            return new ResponseEntity<Resource>(updatedresource, HttpStatus.OK);
        }

    }
