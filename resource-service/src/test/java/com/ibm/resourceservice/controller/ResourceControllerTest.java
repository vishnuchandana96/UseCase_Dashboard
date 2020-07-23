package com.ibm.resourceservice.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ibm.resourceservice.domain.Resource;
import com.ibm.resourceservice.service.ResourceServiceImpl;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import java.util.ArrayList;
import java.util.List;
import static org.hamcrest.Matchers.hasSize;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)   // the test class should initialize the annotation
@AutoConfigureMockMvc
@SpringBootTest
public class ResourceControllerTest
{

        //Creating the object for the mockmvc to mock the service
        @Autowired
        MockMvc mockMvc;
        //Mocking the resource service
        @Mock
        ResourceServiceImpl resourceService;

        @Mock
        private KafkaTemplate<String,Resource> kafkaTemplate;   //@InjectMock creates an instance of the class and
        //injects the mocks that are marked with the annotations @Mock into it.
        @InjectMocks
        ResourceController resourceController;

        private Resource resource;

        private List<Resource> list = null;

        @Before
        public void setup() {
            // To initialise the mocked objects we need to call this otherwise NullPointer EXception will araise
            MockitoAnnotations.initMocks(this);
            mockMvc = MockMvcBuilders.standaloneSetup(resourceController).build();
            resource = new Resource("1","Back Office","Back Office Services","000ER1","Vishnu Chandana","IBM Employee",false);
        }

        @After
        public void tearDown() {
            this.resource = null;
            this.list = null;
        }

        //Creating the testcase for the saving the resource in the database
        @Test
        public void saveResourceTest() throws  Exception {
            //Sample resource details
            //When the saveResource is called it has to return the saved resource
            when(resourceService.saveResource(any())).thenReturn(resource);
            mockMvc.perform(post("/api/v1/resource")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(asJsonString(resource)))
                    .andExpect(status().isCreated());
            //It has to call the resource only once
            verify(resourceService, times(1)).saveResource(resource);
        }


        @Test
        public void getAllResourcesTest() throws Exception
        {
            Resource resource1 = new Resource("2","Front Office","Front Office Services","000ER2","Bhavya","IBM Employee",false);
            Resource resource2 = new Resource("3","Front Office1","Front Office Services1","000ER3","Bhavya Sri","IBM Employee",false);
            //Sample resource details
            List<Resource> resources = new ArrayList<>();
            resources.add(resource1);
            resources.add(resource2);
            //Call the getResource method
            when(resourceService.getResources()).thenReturn(resources);
            mockMvc.perform(get("/api/v1/resource")
                    .contentType(MediaType.APPLICATION_JSON))
                    .andExpect(status().isOk())
                    .andExpect(jsonPath("$", hasSize(2)));
            //It has to call the getResources only once
            verify(resourceService, times(1)).getResources();
            verifyNoMoreInteractions(resourceService);
        }

        @Test
        public void updateResourceTest() throws Exception
        {
            Resource resource1 = new Resource("2","Front Office","Front Office Services","000ER2","Bhavya","IBM Employee",false);
            // when the updateResource is called it has to return the updated resource
            when(resourceService.updateResource(resource1,"2")).thenReturn(resource1);
            mockMvc.perform(put("/api/v1/resource/{emp_sno}",2)
                    .contentType(MediaType.APPLICATION_JSON)
                    .accept(MediaType.APPLICATION_JSON)
                    .content(asJsonString(resource1)))
                    .andExpect(status().isOk())
                    .andDo(print());
            verify(resourceService,times(1)).updateResource(resource1,"2");
            verifyNoMoreInteractions(resourceService);

        }

        @Test
        public void deleteResourceTest() throws Exception
        {
            Resource resource1 = new Resource("2","Front Office","Front Office Services","000ER2","Bhavya","IBM Employee",false);
            //When the deleteResource is called it has to return the deleted resource
            when(resourceService.deleteResource("2")).thenReturn(resource1);
            mockMvc.perform(delete("/api/v1/resource/{emp_sno}","2")
                    .contentType(MediaType.APPLICATION_JSON))
                    .andExpect(status().isOk());
            verify(resourceService, times(1)).deleteResource("2");
            verifyNoMoreInteractions(resourceService);
        }

        //This method is used to change the object to the string
        public static String asJsonString(final Object obj) {
            try {
                return new ObjectMapper().writeValueAsString(obj);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }

    }


