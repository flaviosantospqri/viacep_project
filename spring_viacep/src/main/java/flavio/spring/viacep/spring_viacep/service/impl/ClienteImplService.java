package flavio.spring.viacep.spring_viacep.service.impl;

import flavio.spring.viacep.spring_viacep.model.Cliente;
import flavio.spring.viacep.spring_viacep.model.Endereco;
import flavio.spring.viacep.spring_viacep.respository.ClienteRepository;
import flavio.spring.viacep.spring_viacep.respository.EnderecoRepository;
import flavio.spring.viacep.spring_viacep.service.ClienteService;
import flavio.spring.viacep.spring_viacep.service.EnderecoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ClienteImplService implements ClienteService {

    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private EnderecoRepository enderecoRepository;

    @Autowired
    private EnderecoService enderecoService;

    @Override
    public Iterable<Cliente> buscarTodos() {
        return clienteRepository.findAll();
    }

    @Override
    public Cliente buscarPorId(Long id) {
        Optional<Cliente> cliente = clienteRepository.findById(id);
        if(cliente.isEmpty()) System.out.println("Não encontrado");
        return cliente.get();
    }

    @Override
    public void inserir(Cliente cliente) {
        salvarClienteComCep(cliente);

    }


    @Override
    public void atualizar(Long id, Cliente cliente) {
        Optional<Cliente> clienteBd = clienteRepository.findById(id);
        if(clienteBd.isPresent()){
            salvarClienteComCep(cliente);
        }

    }

    @Override
    public void deletar(Long id) {
        clienteRepository.deleteById(id);

    }
    private void salvarClienteComCep(Cliente cliente) {
        String cep = cliente.getEndereco().getCep();
        Endereco endereco = enderecoRepository.findById(cep).orElseGet(()->{
            Endereco novoEndereco = enderecoService.consultaCep(cep);
            enderecoRepository.save(novoEndereco);
            return novoEndereco;
        });
        cliente.setEndereco(endereco);
        clienteRepository.save(cliente);
    }
}
