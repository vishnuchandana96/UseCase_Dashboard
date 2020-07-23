//package com.ibm.clusterservice.service;
//
//import com.ibm.clusterservice.domain.Application;
//import com.ibm.clusterservice.repository.ApplicationRepository;
//import org.junit.After;
//import org.junit.Assert;
//import org.junit.Before;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.Mockito;
//import org.mockito.MockitoAnnotations;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.test.context.junit4.SpringRunner;
//import org.springframework.test.web.servlet.MockMvc;
//import org.springframework.test.web.servlet.setup.MockMvcBuilders;
//import java.util.ArrayList;
//import java.util.List;
//import java.util.Optional;
//
//import static org.junit.Assert.assertEquals;
//import static org.mockito.Mockito.*;
//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
//
//
//@RunWith(SpringRunner.class)   // the test class should initialize the annotation
//@AutoConfigureMockMvc
//@SpringBootTest
//public class ApplicationServiceTest
//{
//    //Creating the object for the mockmvc to mock the service
//    @Autowired
//    MockMvc mockMvc;
//    //Mocking the application repository
//    @Mock
//    ApplicationRepository applicationRepository;
//    //@InjectMock creates an instance of the class and
//    //injects the mocks that are marked with the annotations @Mock into it.
//    @InjectMocks
//    ApplicationServiceImpl applicationService;
//
//    private Application application;
//
//    private List<Application> list = null;
//
//    @Before
//    public void setup() {
//        // To initialise the mocked objects we need to call this otherwise NullPointer Exception will araise
//        MockitoAnnotations.initMocks(this);
//        mockMvc = MockMvcBuilders.standaloneSetup(applicationService).build();
//        application = new Application("1","D&B","Fortpolio","D&B Services");
//    }
//
//    @After
//    public void tearDown() {
//        this.application = null;
//        this.list = null;
//    }
//
//    @Test
//    public void saveApplicationTest() throws Exception
//    {
//        when(applicationRepository.save(application)).thenReturn(application);
//        Application savedapplication=applicationService.saveApplication(application);
//        Assert.assertEquals(application,savedapplication);
//        //verify here verifies that applicationRepository save method is only called once
//        verify(applicationRepository, times(1)).save(Mockito.any(Application.class));
//    }
//
//    @Test
//    public void updateApplicationTest() throws Exception {
//        when(applicationRepository.save(any())).thenReturn(application);
//        Application savedApplication=applicationService.updateApplication(application,"1");
//        assertEquals(application,savedApplication);
//        verify(applicationRepository,times(1)).save(application);
//    }
//
//    @Test
//    public void getAllApplicationsTest() throws Exception
//    {
//        //Sample application details
//        List<Application> applicationList = new ArrayList<>();
//        applicationList.add(application);
//        //It checks when the save application is called it has to return the save application object
//        when(applicationRepository.findAll()).thenReturn(applicationList);
//        List<Application> retrievedApplications = applicationService.getApplications();
//        Assert.assertEquals(applicationList,retrievedApplications);
//        //verify here verifies that applicationRepository findALL method is only called once
//        verify(applicationRepository,times(1)).findAll();
//        verifyNoMoreInteractions(applicationRepository);
//    }
//
//    @Test
//    public void deleteApplicationTest() throws Exception
//    {
//        //sample application details for the testcase
//        Optional<Application> optionalApplication = Optional.of(application);
//        when(applicationRepository.findById("1")).thenReturn(optionalApplication);
//        Application result = applicationService.deleteApplication("1");
//        Assert.assertNotNull(result);
//        //verify here verifies that applicationRepository delete method is only called once
//        verify(applicationRepository,times(1)).delete(Mockito.any(Application.class));
//        verify(applicationRepository,times(1)).findById("1");
//        verifyNoMoreInteractions(applicationRepository);
//    }
//
//}
//
