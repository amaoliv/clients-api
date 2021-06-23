package ibm.github.amandaoliveira.clients.services;

import ibm.github.amandaoliveira.clients.mapper.ClientMapper;
import ibm.github.amandaoliveira.clients.models.ClientEntity;
import ibm.github.amandaoliveira.clients.models.ClientInput;
import ibm.github.amandaoliveira.clients.models.ClientOutput;
import ibm.github.amandaoliveira.clients.repositories.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ClientService {

    @Autowired
    private ClientRepository rep;

    public List<ClientOutput> findAll() {

        List<ClientEntity> list = rep.findAll();

        List<ClientOutput> listDTO = new ArrayList<>();

        ClientOutput dtos;

        for (ClientEntity clientEntity: list){
            dtos = ClientMapper.INSTANCE.toClientOutput(clientEntity);
            listDTO.add(dtos);
        }

        return listDTO;
    }

    public Optional<ClientOutput> findById(Long id) {
        Optional<ClientEntity> opt = rep.findById(id);
        return opt.map(this::toClientOutput);

    }

    private ClientOutput toClientOutput(ClientEntity clientEntity){
        return ClientMapper.INSTANCE.toClientOutput(clientEntity);
    }

    public ClientOutput saveDTO(ClientInput inputDTO) throws RuntimeException{
        try {
            ClientEntity client = ClientMapper.INSTANCE.toClientEntity(inputDTO);
            rep.save(client);
            ClientOutput output = ClientMapper.INSTANCE.toClientOutput(client);
            return output;
        } catch (RuntimeException e){
            throw new ResponseStatusException(HttpStatus.CONFLICT);
        }
    }

    public void delete(Long id) {
        rep.deleteById(id);
    }

}
