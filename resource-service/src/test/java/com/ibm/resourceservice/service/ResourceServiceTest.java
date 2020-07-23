package com.ibm.resourceservice.service;

import com.ibm.resourceservice.domain.Resource;
import com.ibm.resourceservice.repository.ResourceRepository;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.verifyNoMoreInteractions;

@RunWith(SpringRunner.class)   // the test class should initialize the annotation
@AutoConfigureMockMvc
@SpringBootTest
public class ResourceServiceTest
{
    //Creating the object for the mockmvc to mock the service
    @Autowired
    MockMvc mockMvc;
    //Mocking the resource repository
    @Mock
    ResourceRepository resourceRepository;

    @Mock
    private KafkaTemplate<String,Resource> kafkaTemplate;
    //@InjectMock creates an instance of the class and
    //injects the mocks that are marked with the annotations @Mock into it.
    @InjectMocks
    ResourceServiceImpl resourceService;

    private Resource resource;

    private List<Resource> list = null;

    @Before
    public void setup() {
        // To initialise the mocked objects we need to call this otherwise NullPointer Exception will araise
        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(resourceService).build();
        resource = new Resource("1","Back Office","Back Office Services","000ER1","Vishnu Chandana","IBM Employee",false);
    }

    @After
    public void tearDown() {
        this.resource = null;
        this.list = null;
    }

    @Test
    public void saveResourceTest() throws Exception
    {
        when(resourceRepository.save(resource)).thenReturn(resource);
        Resource savedResource = resourceService.saveResource(resource);
        assertEquals(resource, savedResource);
        //verify here verifies that resourceRepository save method is only called once
        verify(resourceRepository, times(1)).save(Mockito.any(Resource.class));
    }

    @Test
    public void updateResourceTest() throws Exception {
        when(resourceRepository.save(any())).thenReturn(resource);
        Resource savedResource=resourceService.updateResource(resource,"1");
        assertEquals(resource,savedResource);
        verify(resourceRepository,times(1)).save(resource);
    }

    @Test
    public void getAllResourcesTest() throws Exception
    {
        //Sample resource details
        List<Resource> resourceList = new ArrayList<>();
        resourceList.add(resource);
        //It checks when the save resource is called it has to return the save resource object
        when(resourceRepository.findAll()).thenReturn(resourceList);
        List<Resource> retrievedResources = resourceService.getResources();
        assertEquals(resourceList,retrievedResources);
        //verify here verifies that resourceRepository findALL method is only called once
        verify(resourceRepository,times(1)).findAll();
        verifyNoMoreInteractions(resourceRepository);
    }

    @Test
    public void deleteResourceTest() throws Exception
    {
        //sample resource details for the testcase
        Optional<Resource> optionalResource = Optional.of(resource);
        when(resourceRepository.findById("1")).thenReturn(optionalResource);
        Resource result = resourceService.deleteResource("1");
        Assert.assertNotNull(result);
        //verify here verifies that rsourceRepository delete method is only called once
        verify(resourceRepository,times(1)).delete(Mockito.any(Resource.class));
        verify(resourceRepository,times(1)).findById("1");
        verifyNoMoreInteractions(resourceRepository);
    }

}

