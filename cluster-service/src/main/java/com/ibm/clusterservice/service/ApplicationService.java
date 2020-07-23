package com.ibm.clusterservice.service;

import com.ibm.clusterservice.domain.Application;
import com.ibm.clusterservice.exception.ApplicationAlreadyExistsException;

import java.util.List;

public interface ApplicationService
{
    // Once the user creates a application those details have to be saved to database
    public Application saveApplication(Application application) throws ApplicationAlreadyExistsException;
    // Getting all applications
    public List<Application> getApplications();
    // Updating Application based on application_id
    public Application updateApplication(Application application,String application_id);
    // Deleting the Application
    public Application deleteApplication(String application_id);
}


