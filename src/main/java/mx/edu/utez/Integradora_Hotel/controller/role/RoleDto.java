package mx.edu.utez.Integradora_Hotel.controller.role;

import jakarta.persistence.Column;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter

public class RoleDto {
    private Long id_role;
    private String nombre;
}
