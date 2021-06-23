package ibm.github.amandaoliveira.clients.service;

import ibm.github.amandaoliveira.clients.mapper.ClientMapper;
import ibm.github.amandaoliveira.clients.models.ClientEntity;
import ibm.github.amandaoliveira.clients.models.ClientInput;
import ibm.github.amandaoliveira.clients.models.ClientOutput;
import ibm.github.amandaoliveira.clients.repositories.ClientRepository;
import ibm.github.amandaoliveira.clients.services.ClientService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;

@RunWith(MockitoJUnitRunner.class)
public class ClientServiceTest {

    @Mock
    private ClientRepository rep;

    @Mock
    private ClientMapper map;

    @InjectMocks
    private ClientService service;

    @InjectMocks
    private ClientEntity entity;

    @InjectMocks
    private ClientOutput output;

    @Mock
    List<ClientOutput> outputList;

    @Before
    public void setUp(){
        this.entity.setId(1L);
        this.entity.setName("NewClient");
        this.entity.setAddress("NewAddress");
        this.entity.setCpf("NewCPF");
        this.entity.setPhoneNumber("NewPhoneNumber");

        this.output.setId(1L);
        this.output.setName("NewClient");
        this.output.setAddress("NewAddress");
        this.output.setCpf("NewCPF");
        this.output.setPhoneNumber("NewPhoneNumber");
    }

    @Test
    public void shouldSaveNewClient(){

        ClientInput input = new ClientInput();

        Mockito.when(map.toClientEntity(any(ClientInput.class))).thenReturn(entity);
        Mockito.when(rep.save(any(ClientEntity.class))).thenReturn(entity);

        output = service.saveDTO(input);

        //verification
        Assert.assertEquals(input.getName(), output.getName());

    }

    @Test
    public void shouldFetchById() {

        Mockito.when(rep.findById(output.getId())).thenReturn(Optional.of(entity));

        Optional<ClientOutput> finalClient = service.findById(output.getId());

        //verification
        Assert.assertEquals(output.getId(),finalClient.get().getId());

    }

    @Test
    public void shouldFetchAll(){

        List<ClientOutput> compareList = new ArrayList<>();

        Mockito.when(outputList.size()).thenReturn(20);
        Mockito.when(service.findAll()).thenReturn(outputList);

        List<ClientOutput> finalList = service.findAll();

        //verification
        Assert.assertEquals(finalList.getClass(), compareList.getClass());
        Assert.assertEquals(20, finalList);
    }

}
