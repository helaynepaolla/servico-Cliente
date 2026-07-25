// Novo arquivo: AuthFeignClient.java
package com.autoatendimento.cliente.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "servico-autenticacao", url = "${auth.service.url:http://localhost:8081/api/auth}")
public interface AuthFeignClient {
	
	 // Inserimos o caminho completo correto que o AuthController espera
    @PostMapping("/api/auth/criar-credencial")  // Endpoint que vamos adicionar no auth
    void criarCredencial(@RequestBody CriarCredencialRequest request);
    
    record CriarCredencialRequest(String login, String senha, String perfil, String nomeCompleto) {}
    record UsuarioAuthResponse(Long id, String login, String perfil) {}
}