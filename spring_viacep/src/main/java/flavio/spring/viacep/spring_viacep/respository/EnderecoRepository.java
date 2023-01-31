package flavio.spring.viacep.spring_viacep.respository;

import flavio.spring.viacep.spring_viacep.model.Endereco;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EnderecoRepository extends JpaRepository<Endereco, String> {
}
