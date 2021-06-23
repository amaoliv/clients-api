package ibm.github.amandaoliveira.clients.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

@Data
@AllArgsConstructor
@Entity
@NoArgsConstructor
@Table(name = "tb_clients")
public class ClientEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    private Long id;

    @Column (name = "name")
    private String name;
    @Column (name = "address")
    private String address;
    @Column (name = "cpf", unique = true)
    private String cpf;
    @Column (name = "phone_number")
    private String phoneNumber;
}
