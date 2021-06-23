package ibm.github.amandaoliveira.clients.controllers;

import ibm.github.amandaoliveira.clients.models.ClientInput;
import ibm.github.amandaoliveira.clients.models.ClientOutput;
import ibm.github.amandaoliveira.clients.services.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api/v1/clients")
public class ClientController {

    @Autowired
    private ClientService service;

    @GetMapping
    public ResponseEntity<List<ClientOutput>> findAll(){
        return ResponseEntity.ok(service.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity get(@PathVariable("id") Long id){
        return ResponseEntity.ok(service.findById(id));
    }

    @PostMapping
    public ResponseEntity post(@RequestBody ClientInput inputDTO){
        return ResponseEntity.ok(service.saveDTO(inputDTO));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity delete(@PathVariable ("id") Long id){
        service.delete(id);
        return ResponseEntity.ok().build();

    }

 }
