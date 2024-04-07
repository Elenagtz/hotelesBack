package mx.edu.utez.Integradora_Hotel.controller.usuario;

import jakarta.persistence.Column;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import mx.edu.utez.Integradora_Hotel.model.role.Role;
import mx.edu.utez.Integradora_Hotel.model.usuario.Usuario;

@NoArgsConstructor
@Getter
@Setter

public class UsuarioDto {
    private Long id_usuario;
    private String nombre;
    private String apellidoP;
    private String apellidoM;
    private String correo;
    private String contrasena;
    private Boolean status; //no
    private Role role;

    public Usuario toEntity(){
        if (role == null)
            return new Usuario(id_usuario, nombre, apellidoP, apellidoM, correo, contrasena, status);
        return new Usuario(id_usuario, nombre, apellidoP, apellidoM, correo, contrasena, status, role);
    }
}
