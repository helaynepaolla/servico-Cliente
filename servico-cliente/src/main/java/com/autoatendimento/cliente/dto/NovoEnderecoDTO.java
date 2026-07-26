package com.autoatendimento.cliente.dto;

import jakarta.validation.constraints.NotBlank;

public class NovoEnderecoDTO {
    @NotBlank private String cep;
    @NotBlank private String logradouro;
    @NotBlank private String numero;
    private String complemento;
    @NotBlank private String bairro;
    @NotBlank private String cidade;
    @NotBlank private String uf;
    @NotBlank private String apelidoEndereco; // Ex: "Trabalho", "Casa 2"

    // Getters e Setters
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
    public String getApelidoEndereco() { return apelidoEndereco; }
    public void setApelidoEndereco(String apelidoEndereco) { this.apelidoEndereco = apelidoEndereco; }
}
