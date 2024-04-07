package mx.edu.utez.Integradora_Hotel.controller.categoria;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import mx.edu.utez.Integradora_Hotel.model.categoria.Categoria;
import mx.edu.utez.Integradora_Hotel.model.elemento.Elemento;

import java.util.List;

@NoArgsConstructor
@Getter
@Setter

public class CategoriaDto {
    private Long id_categoria;
    private String nombrecategoria;
    private List<Elemento> elemento;

   public Categoria toEntity(){
       return new Categoria(id_categoria, nombrecategoria, elemento);
   }
}
