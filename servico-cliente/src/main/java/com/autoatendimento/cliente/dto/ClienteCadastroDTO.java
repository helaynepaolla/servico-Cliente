package com.autoatendimento.cliente.dto;

import org.springframework.beans.BeanUtils;
import com.autoatendimento.cliente.entity.Cliente;
import com.autoatendimento.cliente.entity.Endereco;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;

public class ClienteCadastroDTO {
    
    @NotBlank private String nome;
    @NotBlank private String cpfCnpj;
    private String telefone;
    private String email;
    @Valid private Endereco endereco;
    
    // Dados de acesso
    @NotBlank private String login;
    @NotBlank private String senha;

    
    // Getters e Setters
    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }
    public String getCpfCnpj() { return cpfCnpj; }
    public void setCpfCnpj(String cpfCnpj) { this.cpfCnpj = cpfCnpj; }
    public String getTelefone() { return telefone; }
    public void setTelefone(String telefone) { this.telefone = telefone; }
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
    public Endereco getEndereco() { return endereco; }
    public void setEndereco(Endereco endereco) { this.endereco = endereco; }
    public String getLogin() { return login; }
    public void setLogin(String login) { this.login = login; }
    public String getSenha() { return senha; }
    public void setSenha(String senha) { this.senha = senha; }
}