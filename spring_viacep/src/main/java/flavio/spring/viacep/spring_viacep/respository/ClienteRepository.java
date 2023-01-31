package flavio.spring.viacep.spring_viacep.respository;

import flavio.spring.viacep.spring_viacep.model.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClienteRepository extends JpaRepository<Cliente, Long> {

}
