package ibm.github.amandaoliveira.clients.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ClientInput implements Serializable {

    private static final long serialVersionUID = 1L;

    private String name;

    private String address;

    private String cpf;

    private String phoneNumber;
}