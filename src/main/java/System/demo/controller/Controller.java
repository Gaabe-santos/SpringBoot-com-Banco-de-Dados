package System.demo.controller;

import System.demo.repository.Repository;
import System.demo.dto.ClienteDTO;
import System.demo.entity.Cliente;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "/cliente/v1")

public class Controller {

    @Autowired
    Repository repository;

    @PostMapping
    public Cliente create(@RequestBody Cliente cliente){
        Cliente clienteSaved = repository.save(cliente);
        return clienteSaved;
    }

    @GetMapping("/{id}")
    @ResponseBody
    public Optional<Cliente> getClienteById(@PathVariable Long id){
        Optional<Cliente> clienteReturned = repository.findById(id);
        return clienteReturned;
    }

    @DeleteMapping("/{id}")
    public String deleteCLienteById(@PathVariable Long id){
        try{
            Optional<Cliente> cliente = Optional.of(repository.getById(id));
            if(cliente != null){
                repository.deleteById(id);
                return "Cliente de " + id + " deletado com sucesso!";
            }else{

                throw new Exception("Cliente inexistente!");

            }
        }catch(Exception e){
            e.printStackTrace();
            return "O cliente de " + id + " não existe para ser deletado!" +
                    " Por favor, entre em contato com o atendimento 222 222 222";
        }
    }

    @GetMapping
    public List<Cliente> listClientes(){
        return repository.findAll();
    }

    @PutMapping("/atualize/{id}")
    public String updateClienteById(@RequestBody ClienteDTO clienteDTO, @PathVariable Long id){
        Optional<Cliente> velhoCLiente = repository.findById(id);
        if(velhoCLiente.isPresent()){
            Cliente cliente = velhoCLiente.get();
            cliente.setEndereco(clienteDTO.getEndereco());
            repository.save(cliente);
            return "Cliente de ID " + cliente.getId() + " atualizado com sucesso!!";

        }else{
           return "CLiente de ID " + id + " não existe";
        }

    }

}
