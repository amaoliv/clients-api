package ibm.github.amandaoliveira.clients.repositories;

import ibm.github.amandaoliveira.clients.models.ClientEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClientRepository extends JpaRepository<ClientEntity, Long> {

}
