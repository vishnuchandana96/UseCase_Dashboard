package com.ibm.clusterservice.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ibm.clusterservice.domain.Application;
import com.ibm.clusterservice.service.ApplicationServiceImpl;
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
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import java.util.ArrayList;
import java.util.List;
import static org.hamcrest.Matchers.hasSize;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)   // the test class should initialize the annotation
@AutoConfigureMockMvc
@SpringBootTest
public class ApplicationControllerTest
{
    //Creating the object for the mockmvc to mock the service
    @Autowired
    MockMvc mockMvc;
    //Mocking the application service
    @Mock
    ApplicationServiceImpl applicationService;
    @Mock
    private KafkaTemplate<String, Application> kafkaTemplate;
    //@InjectMock creates an instance of the class and
    //injects the mocks that are marked with the annotations @Mock into it.
    @InjectMocks
    ApplicationController applicationController;

    private Application application;

    private List<Application> list = null;

    @Before
    public void setup() {
        // To initialise the mocked objects we need to call this otherwise NullPointer EXception will araise
        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(applicationController).build();
        application = new Application("1","D&B","Fortpolio","D&B Services",false);
    }

    @After
    public void tearDown() {
        this.application = null;
        this.list = null;
    }

    //Creating the testcase for the saving the application in the database
    @Test
    public void saveApplicationTest() throws  Exception {
        //Sample application details
        //When the saveApplication is called it has to return the saved application
        when(applicationService.saveApplication(any())).thenReturn(application);
        //It has to perform the action whenever the application url template is called using the post method
        mockMvc.perform(post("/api/v1/application")
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(application)))
                .andExpect(status().isCreated());
        //It has to call the application only once
        verify(applicationService, times(1)).saveApplication(application);
    }


    @Test
    public void getAllApplicationsTest() throws Exception
    {
        Application application1 = new Application("2,","Front Office","Fortpolio","28/10/2019",false);
        Application application2 = new Application("3,","D&B","Fortpolio","26/10/2019",false);
        //Sample application details
        List<Application> applications = new ArrayList<>();
        applications.add(application1);
        applications.add(application2);
        //Call the getApplication method
        when(applicationService.getApplications()).thenReturn(applications);
        mockMvc.perform(get("/api/v1/application")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)));
        //It has to call the getApplications only once
        verify(applicationService, times(1)).getApplications();
        verifyNoMoreInteractions(applicationService);
    }

    @Test
    public void updateApplicationTest() throws Exception
    {
        Application application1 = new Application("2,","Front Office","Fortpolio","28/10/2019",false);
        // when the updateApplication is called it has to return the updated application
        when(applicationService.updateApplication(application1,"2")).thenReturn(application1);
        mockMvc.perform(put("/api/v1/application/{id}","2")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(asJsonString(application1)))
                .andExpect(status().isOk())
                .andDo(print());
        verify(applicationService,times(1)).updateApplication(application1,"2");
        verifyNoMoreInteractions(applicationService);
    }

    @Test
    public void deleteApplicationTest() throws Exception
    {
        //Sample application details
        Application application1 = new Application("2,","Front Office","Fortpolio","28/10/2019",false);
        //When the deleteApplication is called it has to return the deleted application
        when(applicationService.deleteApplication("2")).thenReturn(application1);
        mockMvc.perform(delete("/api/v1/application/{id}","2")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
        verify(applicationService, times(1)).deleteApplication("2");
        verifyNoMoreInteractions(applicationService);
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
