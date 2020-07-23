package com.ibm.resourceservice.service;

import com.ibm.resourceservice.domain.Resource;
import com.ibm.resourceservice.exception.ResourceAlreadyExistsException;

import java.util.List;

public interface ResourceService
{

    public Resource saveResource(Resource resource) throws ResourceAlreadyExistsException;
    public List<Resource> getResources();
    public Resource updateResource(Resource resource,String emp_sno);
    public Resource deleteResource(String emp_sno);

}

