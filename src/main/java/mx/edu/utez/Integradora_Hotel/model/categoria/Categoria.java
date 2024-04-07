package mx.edu.utez.Integradora_Hotel.model.categoria;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import mx.edu.utez.Integradora_Hotel.model.elemento.Elemento;

import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "categoria")
public class Categoria {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_categoria;
    @Column(length = 50, nullable = false)
    private String nombrecategoria;

        @JsonIgnore
        @OneToMany( cascade = CascadeType.ALL, fetch = FetchType.EAGER)
        private List<Elemento> elemento;

    public Categoria(Long id_categoria, String nombrecategoria) {
        this.id_categoria = id_categoria;
        this.nombrecategoria = nombrecategoria;
    }

    public Categoria(Long id_categoria, String nombrecategoria, List<Elemento> elemento) {
        this.id_categoria = id_categoria;
        this.nombrecategoria = nombrecategoria;
        this.elemento = elemento;
    }
}
