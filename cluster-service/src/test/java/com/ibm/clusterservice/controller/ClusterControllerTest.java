package com.ibm.clusterservice.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ibm.clusterservice.domain.Cluster;
import com.ibm.clusterservice.service.ClusterServiceImpl;
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
public class ClusterControllerTest {

    //Creating the object for the mockmvc to mock the service
    @Autowired
    MockMvc mockMvc;
    //Mocking the cluster service
    @Mock
    ClusterServiceImpl clusterService;
    @Mock
    private KafkaTemplate<String, Cluster> kafkaTemplate;
    //@InjectMock creates an instance of the class and
    //injects the mocks that are marked with the annotations @Mock into it.
    @InjectMocks
    ClusterController clusterController;

    private Cluster cluster;

    private List<Cluster> list = null;

    @Before
    public void setup() {
        // To initialise the mocked objects we need to call this otherwise NullPointer EXception will araise
        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(clusterController).build();
        cluster = new Cluster("1,","Back Office","Back Office Services","24/10/2019",false);
    }

    @After
    public void tearDown() {
        this.cluster = null;
        this.list = null;
    }

    //Creating the testcase for the saving the cluster in the database
    @Test
    public void saveClusterTest() throws  Exception {
        //Sample cluster details
        //When the saveCLuster is called it has to return the saved cluster
        when(clusterService.saveCluster(any())).thenReturn(cluster);
        //It has to perform the action whenever the cluster url template is called using the post method
        mockMvc.perform(post("/api/v1/cluster")
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(cluster)))
                .andExpect(status().isCreated());
        //It has to call the cluster only once
        verify(clusterService, times(1)).saveCluster(cluster);
    }


    @Test
    public void getAllClustersTest() throws Exception
    {
        Cluster cluster1 = new Cluster("2,","Front Office","Front Office Services","28/10/2019",false);
        Cluster cluster2 = new Cluster("3,","D&B","Database Services","26/10/2019",false);
        //Sample cluster details
        List<Cluster> clusters = new ArrayList<>();
        clusters.add(cluster1);
        clusters.add(cluster2);
        //Call the getCluster method
        when(clusterService.getClusters()).thenReturn(clusters);
        mockMvc.perform(get("/api/v1/cluster")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)));
        //It has to call the getClusters only once
        verify(clusterService, times(1)).getClusters();
        verifyNoMoreInteractions(clusterService);
    }

    @Test
    public void updateClusterTest() throws Exception
    {
        Cluster cluster1 = new Cluster("2,","Front Office","Front Office Services","28/10/2019",false);
        // when the updateCluster is called it has to return the updated cluster
        when(clusterService.updateCluster(cluster1,"2")).thenReturn(cluster1);
        mockMvc.perform(put("/api/v1/cluster/{id}",2)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(asJsonString(cluster1)))
                .andExpect(status().isOk())
                .andDo(print());
        verify(clusterService,times(1)).updateCluster(cluster1,"2");
        verifyNoMoreInteractions(clusterService);

    }

    @Test
    public void deleteClusterTest() throws Exception
    {
        //Sample cluster details
        Cluster cluster1 = new Cluster("2,","Front Office","Front Office Services","28/10/2019",false);
        //When the deleteCluster is called it has to return the deleted cluster
        when(clusterService.deleteCluster("2")).thenReturn(cluster1);
        mockMvc.perform(delete("/api/v1/cluster/{id}","2")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
        verify(clusterService, times(1)).deleteCluster("2");
        verifyNoMoreInteractions(clusterService);
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
