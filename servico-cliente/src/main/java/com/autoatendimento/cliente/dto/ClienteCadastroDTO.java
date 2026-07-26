package com.autoatendimento.cliente.dto;

import com.autoatendimento.cliente.entity.Cliente;
import com.autoatendimento.cliente.entity.Endereco;
import com.autoatendimento.cliente.entity.EnderecoEntrega;

import jakarta.validation.constraints.NotBlank;

public class ClienteCadastroDTO {

    @NotBlank private String nome;
    @NotBlank private String cpfCnpj;
    @NotBlank private String telefone;
    @NotBlank private String email;
    @NotBlank private String login;
    @NotBlank private String senha;
    
    // Campos do endereço que vêm desmembrados do frontend Angular
    @NotBlank private String cep;
    @NotBlank private String logradouro;
    @NotBlank private String numero;
    private String complemento;
    @NotBlank private String bairro;
    @NotBlank private String cidade;
    @NotBlank private String uf;

    // Método que o seu controller precisa para converter o DTO em entidade válida
    public Cliente paraEntidade() {
        Cliente c = new Cliente();
        c.setNome(this.nome);
        c.setCpfCnpj(this.cpfCnpj.replaceAll("\\D", ""));
        c.setTelefone(this.telefone.replaceAll("\\D", ""));
        c.setEmail(this.email);

        EnderecoEntrega e = new EnderecoEntrega();
        e.setCep(this.cep.replaceAll("\\D", ""));
        e.setLogradouro(this.logradouro);
        e.setNumero(this.numero);
        e.setComplemento(this.complemento);
        e.setBairro(this.bairro);
        e.setCidade(this.cidade);
        e.setUf(this.uf);
        e.setApelidoEndereco("Principal"); // Primeiro endereço vira o principal
        e.setCliente(c);
        
        c.getEnderecos().add(e);
        return c;
    }

    // --- Mantenha todos os seus métodos Getters e Setters gerados abaixo ---
    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }
    public String getCpfCnpj() { return cpfCnpj; }
    public void setCpfCnpj(String cpfCnpj) { this.cpfCnpj = cpfCnpj; }
    public String getTelefone() { return telefone; }
    public void setTelefone(String telefone) { this.telefone = telefone; }
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
    public String getLogin() { return login; }
    public void setLogin(String login) { this.login = login; }
    public String getSenha() { return senha; }
    public void setSenha(String senha) { this.senha = senha; }
    public String getCep() { return cep; }
    public void setCep(String cep) { this.cep = cep; }
    public String getLogradouro() { return logradouro; }
    public void setLogradouro(String logradouro) { this.logradouro = logradouro; }
    public String getNumero() { return numero; }
    public void setNumero(String numero) { this.numero = numero; }
    public String getComplemento() { return complemento; }
    public void setComplemento(String complemento) { this.complemento = complemento; }
    public String getBairro() { return bairro; }
    public void setBairro(String bairro) { this.bairro = bairro; }
    public String getCidade() { return cidade; }
    public void setCidade(String cidade) { this.cidade = cidade; }
    public String getUf() { return uf; }
    public void setUf(String uf) { this.uf = uf; }
}
