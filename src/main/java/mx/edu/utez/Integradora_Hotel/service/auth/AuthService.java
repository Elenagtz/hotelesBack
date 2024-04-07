package mx.edu.utez.Integradora_Hotel.service.auth;
import org.springframework.beans.factory.annotation.Autowired;
import mx.edu.utez.Integradora_Hotel.config.ApiResponse;
import mx.edu.utez.Integradora_Hotel.controller.auth.SignedDto;
import mx.edu.utez.Integradora_Hotel.model.usuario.Usuario;
import mx.edu.utez.Integradora_Hotel.security.jwt.JwtProvider;
import mx.edu.utez.Integradora_Hotel.service.usuario.UsuarioService;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional
public class AuthService {

    private final UsuarioService usuarioService;

    private final AuthenticationManager manager;
    private final JwtProvider provider;

    public AuthService(UsuarioService usuarioService, AuthenticationManager manager, JwtProvider provider) {
        this.usuarioService = usuarioService;
        this.manager = manager;
        this.provider = provider;
    }

    @Transactional(readOnly = true)
    public ResponseEntity<ApiResponse> signIn(String correo, String contrasena){
        try {
            Optional<Usuario> foundUser = usuarioService.findByEmail(correo);
            if (foundUser.isEmpty())
                return new ResponseEntity<>(new ApiResponse(
                        HttpStatus.NOT_FOUND, true, "No se encontró el correo"
                ), HttpStatus.NOT_FOUND);

            Usuario usuario = foundUser.get();
            System.out.println(usuario);
            Authentication auth = manager.authenticate(
                    new UsernamePasswordAuthenticationToken(correo, contrasena)
            );

            SecurityContextHolder.getContext().setAuthentication(auth);
            String token = provider.generateToken(auth);

            SignedDto signedDto = new SignedDto(token, "Bearer", usuario, usuario.getRole());
            return new ResponseEntity<>(new ApiResponse(signedDto, HttpStatus.OK), HttpStatus.OK);
        }catch (Exception e){
            e.printStackTrace();
            String message = "CredentialMismatch";
            if (e instanceof DisabledException)
                message = "UserDisabled";
            return new ResponseEntity<>(
                    new ApiResponse(HttpStatus.BAD_REQUEST, true, message),
                    HttpStatus.BAD_REQUEST
            );
        }
    }
}
