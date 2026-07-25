package com.autoatendimento.cliente.controller;

import com.autoatendimento.cliente.entity.Cliente;
import com.autoatendimento.cliente.repository.ClienteRepository;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/clientes")
public class ClienteController {

    private final ClienteRepository repository;
    public ClienteController(ClienteRepository repository) { this.repository = repository; }

    @PostMapping
    public ResponseEntity<Cliente> cadastrar(@Valid @RequestBody Cliente cliente) {
        return new ResponseEntity<>(repository.save(cliente), HttpStatus.CREATED);
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
}