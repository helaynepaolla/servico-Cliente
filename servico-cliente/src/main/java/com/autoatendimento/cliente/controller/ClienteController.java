package com.autoatendimento.cliente.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.autoatendimento.cliente.client.AuthFeignClient;
import com.autoatendimento.cliente.dto.ClienteCadastroDTO;
import com.autoatendimento.cliente.dto.NovoEnderecoDTO;
import com.autoatendimento.cliente.entity.Cliente;
import com.autoatendimento.cliente.repository.ClienteRepository;
import com.autoatendimento.cliente.service.ClienteService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/clientes")
public class ClienteController { 
	public ClienteService clienteService;
	private final ClienteRepository repository;
    private final AuthFeignClient authClient;
    public ClienteController(ClienteRepository repository, AuthFeignClient authClient) { 
    	this.repository = repository; 
    	this.authClient = authClient;
    }
    
    @PostMapping
    public ResponseEntity<Cliente> cadastrar(@Valid @RequestBody ClienteCadastroDTO dto) {
        // Converte DTO para entidade Cliente
        Cliente cliente = new Cliente();
        //BeanUtils.copyProperties(dto, cliente);
        cliente = dto.paraEntidade();
        Cliente salvo = repository.save(cliente);
        
        try {
        // Chama serviço de autenticação para criar credencial
        authClient.criarCredencial(
            new AuthFeignClient.CriarCredencialRequest(
                dto.getLogin(), 
                dto.getSenha(), 
                "CLIENTE", 
                dto.getNome()
            )
        );
        } catch (Exception e) {
            System.out.println("⚠️ Falha ao gerar a credencial no microsserviço de autenticação: " + e.getMessage());
        }
        return new ResponseEntity<>(salvo, HttpStatus.CREATED);
    }    

    @GetMapping("/{id}")
    public ResponseEntity<Cliente> buscarPorId(@PathVariable Long id) {
        return repository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/cpf-cnpj/{cpf}")
    public ResponseEntity<Cliente> buscarPorCpf(@PathVariable String cpf) {
        Optional<Cliente> cliente = repository.findByCpfCnpj(cpf);
        return cliente.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }    
    
    
 // Listar todos os clientes
    @GetMapping
    public ResponseEntity<List<Cliente>> listarTodos() {
        return ResponseEntity.ok(repository.findAll());
    }

    // Editar cliente
    @PutMapping("/{id}")
    public ResponseEntity<Cliente> editar(@PathVariable Long id, @Valid @RequestBody Cliente dadosAtualizados) {
        return repository.findById(id)
                .map(cliente -> {
                    BeanUtils.copyProperties(dadosAtualizados, cliente, "id", "dataCadastro");
                    return ResponseEntity.ok(repository.save(cliente));
                })
                .orElse(ResponseEntity.notFound().build());
    }

    // Ativar/Inativar cliente
    @PatchMapping("/{id}/status")
    public ResponseEntity<Cliente> alternarStatus(@PathVariable Long id) {
        return repository.findById(id)
                .map(cliente -> {
                    cliente.setAtivo(!cliente.getAtivo());
                    return ResponseEntity.ok(repository.save(cliente));
                })
                .orElse(ResponseEntity.notFound().build());
    }

    // Excluir cliente (exclusão lógica via status)
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluir(@PathVariable Long id) {
        return repository.findById(id)
                .map(cliente -> {
                    cliente.setAtivo(false);
                    repository.save(cliente);
                    return ResponseEntity.noContent().<Void>build();
                })
                .orElse(ResponseEntity.notFound().build());
    }
    
    @PostMapping("/{id}/enderecos")
    public ResponseEntity<Cliente> adicionarEndereco(@PathVariable Long id, @Valid @RequestBody NovoEnderecoDTO dto) {
        Cliente clienteAtualizado = clienteService.adicionarEndereco(id, dto);
        return ResponseEntity.ok(clienteAtualizado);
    }

}