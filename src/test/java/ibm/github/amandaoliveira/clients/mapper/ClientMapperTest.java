package ibm.github.amandaoliveira.clients.mapper;

import ibm.github.amandaoliveira.clients.models.ClientEntity;
import ibm.github.amandaoliveira.clients.models.ClientInput;
import ibm.github.amandaoliveira.clients.models.ClientOutput;
import org.junit.Assert;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.runner.RunWith;
import org.mapstruct.factory.Mappers;
import org.mockito.InjectMocks;
import org.mockito.Spy;
import org.mockito.junit.MockitoJUnitRunner;


@RunWith(MockitoJUnitRunner.class)
public class ClientMapperTest {

    @Spy
    private ClientMapper map = Mappers.getMapper(ClientMapper.class);

    @InjectMocks
    private ClientInput input;

    @InjectMocks
    private ClientEntity entity;

    @BeforeEach
    void setUp() {

    }

    @Test
    public void shouldConvertClientInputToClientEntity() {

        //scenario
        this.input.setName("Name1");
        this.input.setCpf("CPF1");
        this.input.setAddress("Address1");
        this.input.setPhoneNumber("PhoneNumber1");

        //execution
        ClientEntity toEntity = map.toClientEntity(input);

        //verification
        Assert.assertEquals(toEntity.getClass(), ClientEntity.class);
        Assert.assertEquals(toEntity.getName(), input.getName());
        Assert.assertEquals(toEntity.getCpf(), input.getCpf());
        Assert.assertEquals(toEntity.getAddress(), input.getAddress());
        Assert.assertEquals(toEntity.getPhoneNumber(), input.getPhoneNumber());

    }

    @Test
    public void shouldConvertClientEntityToClientOutput() {

        //scenario
        this.entity.setId(1L);
        this.entity.setName("Name1");
        this.entity.setCpf("CPF1");
        this.entity.setAddress("Address1");
        this.entity.setPhoneNumber("PhoneNumber1");

        //execution
        ClientOutput toOutput = map.toClientOutput(entity);

        //verification
        Assert.assertEquals(toOutput.getClass(), ClientOutput.class);
        Assert.assertEquals(toOutput.getId(), entity.getId());
        Assert.assertEquals(toOutput.getName(), entity.getName());
        Assert.assertEquals(toOutput.getCpf(), entity.getCpf());
        Assert.assertEquals(toOutput.getAddress(), entity.getAddress());
        Assert.assertEquals(toOutput.getPhoneNumber(), entity.getPhoneNumber());
    }

    @Test
    public void shouldConvertClientInputToClientOutput(){

        //scenario
        this.input.setName("Name1");
        this.input.setCpf("CPF1");
        this.input.setAddress("Address1");
        this.input.setPhoneNumber("PhoneNumber1");

        //execution
        ClientEntity toEntity = map.toClientEntity(input);
        ClientOutput toOutput = map.toClientOutput(toEntity);

        //verification
        Assert.assertEquals(toOutput.getClass(), ClientOutput.class);
        Assert.assertEquals(toOutput.getId(), toEntity.getId());
        Assert.assertEquals(toOutput.getName(), input.getName());
        Assert.assertEquals(toOutput.getName(), toEntity.getName());
        Assert.assertEquals(toOutput.getCpf(), input.getCpf());
        Assert.assertEquals(toOutput.getCpf(), toEntity.getCpf());
        Assert.assertEquals(toOutput.getAddress(), input.getAddress());
        Assert.assertEquals(toOutput.getAddress(), toEntity.getAddress());
        Assert.assertEquals(toOutput.getPhoneNumber(), input.getPhoneNumber());
        Assert.assertEquals(toOutput.getPhoneNumber(), toEntity.getPhoneNumber());
    }
}
