package ibm.github.amandaoliveira.clients.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import ibm.github.amandaoliveira.clients.models.ClientOutput;
import ibm.github.amandaoliveira.clients.services.ClientService;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.hamcrest.CoreMatchers.is;


@WebMvcTest(controllers = ClientController.class)
@ActiveProfiles("test")
public class ClientControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ClientService service;

    @Autowired
    private ObjectMapper objectMapper;

    private List<ClientOutput> list;

    @BeforeEach
    void setUp() {
        this.list = new ArrayList<>();
        this.list.add(new ClientOutput(1L, "Client1", "Address1", "CPF1", "PhoneNumber1"));
        this.list.add(new ClientOutput(2L, "Client2", "Address2", "CPF2", "PhoneNumber2"));
        this.list.add(new ClientOutput(3L, "Client3", "Address3", "CPF3", "PhoneNumber3"));
    }

    @Test
    public void shouldFetchAllClients() throws Exception{
        given(service.findAll()).willReturn(list);

        this.mockMvc.perform(get("/api/v1/clients"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.size()", is(list.size())));
    }

    @Test
    public void shouldFetchOnlyOneClientById() throws Exception{
        ClientOutput client = list.get(0);
        given(service.findById(client.getId())).willReturn(Optional.of(client));

        this.mockMvc.perform(get("/api/v1/clients/{id}", client.getId()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name", is(client.getName())))
                .andExpect(jsonPath("$.cpf", is(client.getCpf())))
                .andExpect(jsonPath("$.address", is(client.getAddress())))
                .andExpect(jsonPath("$.phoneNumber", is(client.getPhoneNumber())));
    }

    @Test
    public void shouldDeleteClient() throws Exception{
        ClientOutput client = list.get(0);
        this.mockMvc.perform(MockMvcRequestBuilders
                .delete("/api/v1/clients/{id}", client.getId())
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }


    @Test
    public void shouldSaveNewClient() throws Exception{

        ClientOutput newClient = new ClientOutput(null, "NewClient", "NewAddress", "NewCPF","NewPhoneNumber");
        given(service.saveDTO(any())).willReturn(newClient);

        this.mockMvc.perform(post("/api/v1/clients")
                .content(objectMapper.writeValueAsString(newClient))
                .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name", is(newClient.getName())))
                .andExpect(jsonPath("$.cpf", is(newClient.getCpf())))
                .andExpect(jsonPath("$.address", is(newClient.getAddress())))
                .andExpect(jsonPath("$.phoneNumber", is(newClient.getPhoneNumber())));

    }
}
