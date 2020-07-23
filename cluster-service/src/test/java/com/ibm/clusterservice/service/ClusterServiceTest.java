//package com.ibm.clusterservice.service;
//
//import com.ibm.clusterservice.domain.Cluster;
//import com.ibm.clusterservice.repository.ClusterRepository;
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
//
//@RunWith(SpringRunner.class)   // the test class should initialize the annotation
//@AutoConfigureMockMvc
//@SpringBootTest
//public class ClusterServiceTest {
//    //Creating the object for the mockmvc to mock the service
//    @Autowired
//    MockMvc mockMvc;
//    //Mocking the cluster repository
//    @Mock
//    ClusterRepository clusterRepository;
//    //@InjectMock creates an instance of the class and
//    //injects the mocks that are marked with the annotations @Mock into it.
//    @InjectMocks
//    ClusterServiceImpl clusterService;
//
//    private Cluster cluster;
//
//    private List<Cluster> list = null;
//
//    @Before
//    public void setup() {
//        // To initialise the mocked objects we need to call this otherwise NullPointer Exception will araise
//        MockitoAnnotations.initMocks(this);
//        mockMvc = MockMvcBuilders.standaloneSetup(clusterService).build();
//        cluster = new Cluster("1,", "Back Office", "Back Office Services", "24/10/2019");
//    }
//
//    @After
//    public void tearDown() {
//        this.cluster = null;
//        this.list = null;
//    }
//
//    @Test
//    public void saveClusterTest() throws Exception
//    {
//        when(clusterRepository.save(cluster)).thenReturn(cluster);
//        Cluster savedCluster = clusterService.saveCluster(cluster);
//        assertEquals(cluster, savedCluster);
//        //verify here verifies that clusterRepository save method is only called once
//        verify(clusterRepository, times(1)).save(Mockito.any(Cluster.class));
//    }
//
//    @Test
//    public void updateClusterTest() throws Exception {
//        when(clusterRepository.save(any())).thenReturn(cluster);
//        Cluster savedCluster=clusterService.updateCluster(cluster,"1");
//        assertEquals(cluster,savedCluster);
//        verify(clusterRepository,times(1)).save(cluster);
//    }
//
//    @Test
//    public void getAllClustersTest() throws Exception
//    {
//        //Sample cluster details
//        List<Cluster> clusterList = new ArrayList<>();
//        clusterList.add(cluster);
//        //It checks when the save cluster is called it has to return the save cluster object
//        when(clusterRepository.findAll()).thenReturn(clusterList);
//        List<Cluster> retrievedClusters = clusterService.getClusters();
//        assertEquals(clusterList,retrievedClusters);
//        //verify here verifies that clusterRepository findALL method is only called once
//        verify(clusterRepository,times(1)).findAll();
//        verifyNoMoreInteractions(clusterRepository);
//    }
//
//    @Test
//    public void deleteClusterTest() throws Exception
//    {
//        //sample cluster details for the testcase
//        Optional<Cluster> optionalCluster = Optional.of(cluster);
//        when(clusterRepository.findById("1")).thenReturn(optionalCluster);
//        Cluster result = clusterService.deleteCluster("1");
//        Assert.assertNotNull(result);
//        //verify here verifies that clusterRepository delete method is only called once
//        verify(clusterRepository,times(1)).delete(Mockito.any(Cluster.class));
//        verify(clusterRepository,times(1)).findById("1");
//        verifyNoMoreInteractions(clusterRepository);
//    }
//
//}
//
