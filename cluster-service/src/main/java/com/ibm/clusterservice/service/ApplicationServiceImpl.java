package com.ibm.clusterservice.service;

import com.ibm.clusterservice.domain.Application;
import com.ibm.clusterservice.exception.ApplicationAlreadyExistsException;
import com.ibm.clusterservice.repository.ApplicationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ApplicationServiceImpl implements ApplicationService {

    // Creating object for application repository in order to connect with the database
    @Autowired
    private ApplicationRepository applicationRepository;

    public ApplicationServiceImpl(ApplicationRepository applicationRepository) {
    this.applicationRepository=applicationRepository;
    }

    // This method is to save the application into the database
    @Override
    public Application saveApplication(Application application) throws ApplicationAlreadyExistsException
    {
        Application savedApplication=null;
        // To save the application to the mongodatabase
        if(applicationRepository.findByApplication_name(application.getApplication_name()).size()>0)
        {
            throw new ApplicationAlreadyExistsException("Application already exist");
        }
        else
        {
        savedApplication=applicationRepository.save(application);
        }
       // returning the saved application
       return savedApplication;
    }

    // Getting all the applications
    @Override
    public List<Application> getApplications()
    {
        List<Application> applicationList =  applicationRepository.findAll();
        applicationList = applicationList.stream().filter(x -> !x.isIsdeleted()).collect(Collectors.toList());
        return applicationList;
    }

    @Override
    public Application updateApplication(Application application, String application_id) {
       Optional<Application> application1=applicationRepository.findById(application_id);
       application.setApplication_name(application.getApplication_name());
       Application modifiedApplication=applicationRepository.save(application);
        return modifiedApplication;
    }

    @Override
    public Application deleteApplication(String application_id) {
        Optional<Application> application1=applicationRepository.findById(application_id);
        //delete the application
        applicationRepository.delete(application1.get());
        //Return the deleted application
        return application1.get();
    }
}
