package mx.edu.utez.Integradora_Hotel.model.role;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import mx.edu.utez.Integradora_Hotel.model.usuario.Usuario;

import java.util.List;
import java.util.Set;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "role")
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_role;
    @Column(length = 25, nullable = false, unique = true)
    private String nombre;

    //RELACIONES
    @JsonIgnoreProperties(value = {"usuario"})
    @OneToMany(cascade = CascadeType.PERSIST, mappedBy = "role")
    private List<Usuario> usuario;
    public Role(String nombre) {
        this.nombre = nombre;
    }
}
