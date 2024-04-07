package mx.edu.utez.Integradora_Hotel.controller.auth;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

@Data
public class SignDto {
    @NotBlank
    @NotEmpty
    private String correo;
    @NotBlank
    @NotEmpty
    private String contrasena;
}
