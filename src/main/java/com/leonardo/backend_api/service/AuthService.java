package com.leonardo.backend_api.service;

import java.time.LocalDateTime;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.leonardo.backend_api.dto.AcessDTO;
import com.leonardo.backend_api.dto.AuthenticationDTO;
import com.leonardo.backend_api.dto.UsuarioDTO;
import com.leonardo.backend_api.entity.UsuarioEntity;
import com.leonardo.backend_api.entity.enums.TipoSituacaoUsuario;
import com.leonardo.backend_api.repository.UsuarioRepository;
import com.leonardo.backend_api.security.jwt.JwtUtils;

@Service
public class AuthService {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtUtils jwtUtils;

    @Autowired
    private EmailService emailService;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public AcessDTO login(AuthenticationDTO authDto) {
        try {
            UsernamePasswordAuthenticationToken userAuth =
                    new UsernamePasswordAuthenticationToken(authDto.getlogin(), authDto.getsenha());

            Authentication authentication = authenticationManager.authenticate(userAuth);

            UserDetailsImpl userAuthenticated = (UserDetailsImpl) authentication.getPrincipal();

            String token = jwtUtils.generateJwtToken(userAuthenticated);

            UsuarioEntity usuario = usuarioRepository.findByLogin(authDto.getlogin())
                    .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));

            String mensagem = "Olá, " + usuario.getNome() + "!\n\n"
                    + "Seu login foi realizado com sucesso.\n\n"
                    + "Seu token de acesso:\n"
                    + token + "\n\n"
                    + "Utilize este token para acessar a API.";

            emailService.enviarEmailTexto(usuario.getEmail(), "Token de acesso", mensagem);

            return new AcessDTO(token);

        } catch (BadCredentialsException e) {
            return new AcessDTO("Acesso negado");
        }
    }

    public UsuarioDTO registrar(UsuarioDTO usuario) {
        UsuarioEntity usuarioEntity = new UsuarioEntity(usuario);
        usuarioEntity.setId(null);
        usuarioEntity.setSituacao(TipoSituacaoUsuario.PENDENTE);
        usuarioEntity.setSenha(passwordEncoder.encode(usuario.getSenha()));

        String token = UUID.randomUUID().toString();
        usuarioEntity.setTokenConfirmacao(token);
        usuarioEntity.setTokenExpiracao(LocalDateTime.now().plusHours(24));

        UsuarioDTO usuarioSalvo = new UsuarioDTO(usuarioRepository.save(usuarioEntity));

        String link = "http://localhost:8080/auth/confirmar?token=" + token;
        String mensagem = "Olá, " + usuario.getNome() + "!\n\n"
                + "Clique no link abaixo para confirmar seu cadastro:\n"
                + link + "\n\n"
                + "O link expira em 24 horas.";

        emailService.enviarEmailTexto(usuario.getEmail(), "Confirme seu cadastro", mensagem);

        return usuarioSalvo;
    }

    public String confirmarCadastro(String token) {
        UsuarioEntity usuario = usuarioRepository.findByTokenConfirmacao(token)
                .orElseThrow(() -> new RuntimeException("Token inválido"));

        if (usuario.getTokenExpiracao().isBefore(LocalDateTime.now())) {
            return "Token expirado. Solicite um novo cadastro.";
        }

        usuario.setSituacao(TipoSituacaoUsuario.ATIVO);
        usuario.setTokenConfirmacao(null);
        usuario.setTokenExpiracao(null);
        usuarioRepository.save(usuario);

        return "Cadastro confirmado com sucesso!";
    }
}