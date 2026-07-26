package com.autoatendimento.cliente.service;

import com.autoatendimento.cliente.dto.ClienteCadastroDTO;
import com.autoatendimento.cliente.dto.NovoEnderecoDTO;
import com.autoatendimento.cliente.entity.Cliente;
import com.autoatendimento.cliente.repository.ClienteRepository;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.server.ResponseStatusException;

import java.util.HashMap;
import java.util.Map;

@Service
public class ClienteService {
	private final ClienteRepository repository;
    private final RestTemplate restTemplate;
    
 // URL do microsserviço de autenticação através do API Gateway ou direto
    private final String AUTH_SERVICE_URL = "http://localhost:8081/auth/cadastro";

    public ClienteService(ClienteRepository repository, RestTemplate restTemplate) {
        this.repository = repository;
        this.restTemplate = restTemplate;
    }

    @Transactional
    public Cliente adicionarEndereco(Long clienteId, NovoEnderecoDTO dto) {
        Cliente cliente = repository.findById(clienteId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Cliente não encontrado"));

        com.autoatendimento.cliente.entity.EnderecoEntrega novoEnd = new com.autoatendimento.cliente.entity.EnderecoEntrega();
        novoEnd.setCep(dto.getCep().replaceAll("\\D", ""));
        novoEnd.setLogradouro(dto.getLogradouro());
        novoEnd.setNumero(dto.getNumero());
        novoEnd.setComplemento(dto.getComplemento());
        novoEnd.setBairro(dto.getBairro());
        novoEnd.setCidade(dto.getCidade());
        novoEnd.setUf(dto.getUf());
        novoEnd.setApelidoEndereco(dto.getApelidoEndereco());
        novoEnd.setCliente(cliente);

        cliente.getEnderecos().add(novoEnd);
        return repository.save(cliente);
    }

}