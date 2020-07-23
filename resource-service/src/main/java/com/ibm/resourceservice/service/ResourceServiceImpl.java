package com.ibm.resourceservice.service;

import com.ibm.resourceservice.domain.Resource;
import com.ibm.resourceservice.exception.ResourceAlreadyExistsException;
import com.ibm.resourceservice.repository.ResourceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@Service
public class ResourceServiceImpl implements ResourceService
{
    @Autowired
    private ResourceRepository resourceRepository;

    public ResourceServiceImpl(ResourceRepository resourceRepository)
    {
        this.resourceRepository=resourceRepository;
    }

    public Resource saveResource(Resource resource) throws ResourceAlreadyExistsException
    {
        Resource savedResource=null;
        if(resourceRepository.findResourceByEmp_sno(resource.getEmp_sno()).size()>0)
        {
            throw new ResourceAlreadyExistsException("Resource Already Exist");
        }

        else {
            savedResource= resourceRepository.save(resource);
        }
        return savedResource;
    }

    public List<Resource> getResources()
    {
      List<Resource> resourceList = resourceRepository.findAll();
      resourceList = resourceList.stream().filter(x -> !x.isIsdeleted()).collect(Collectors.toList());
      return resourceList;
    }

    public Resource updateResource(Resource resource, String emp_sno)
    {
        Optional<Resource> resource1=resourceRepository.findById(emp_sno);
        resource.setEmp_id(resource.getEmp_id());
        resource.setEmp_name(resource.getEmp_name());
        resource.setEmp_sno(emp_sno);
        Resource modifiedResource=resourceRepository.save(resource);
        return modifiedResource ;
    }

    public Resource deleteResource(String emp_sno)
    {
        Optional<Resource> resource1=resourceRepository.findById(emp_sno);
        resourceRepository.delete(resource1.get());
        return resource1.get();
    }
}
