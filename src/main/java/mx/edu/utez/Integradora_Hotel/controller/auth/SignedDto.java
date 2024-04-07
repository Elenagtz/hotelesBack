package mx.edu.utez.Integradora_Hotel.controller.auth;

import lombok.Value;
import mx.edu.utez.Integradora_Hotel.model.role.Role;
import mx.edu.utez.Integradora_Hotel.model.usuario.Usuario;

@Value
public class SignedDto {
    String token;
    String tokenType;
    Usuario usuario;
    Role role;
}
